package com.example.ionutcristian.seriesadicted;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

/**
 * Created by IonutCristian on 6/1/2015.
 */
public class SeriesProvider extends ContentProvider{

    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private SeriesDB mOpenHelper;

    private static final int POPULARS = 100;
    private static final int DETAILS = 200;
   // private static final int DETAILS_ID = 201;

    private static final SQLiteQueryBuilder sDetailsByPopularBuilder;

    static{
        sDetailsByPopularBuilder = new SQLiteQueryBuilder();
        sDetailsByPopularBuilder.setTables(
                SeriesContract.PopularsEntry.TABLE_NAME + " INNER JOIN " +
                        SeriesContract.DetailEntry.TABLE_NAME +
                        " ON " + SeriesContract.PopularsEntry.TABLE_NAME +
                        "." + SeriesContract.PopularsEntry.COLUMN_ID_DETAIL +
                        " = " + SeriesContract.DetailEntry.TABLE_NAME +
                        "." + SeriesContract.DetailEntry._ID);
    }

    private static UriMatcher buildUriMatcher() {
        // I know what you're thinking.  Why create a UriMatcher when you can use regular
        // expressions instead?  Because you're not crazy, that's why.

        // All paths added to the UriMatcher have a corresponding code to return when a match is
        // found.  The code passed into the constructor represents the code to return for the root
        // URI.  It's common to use NO_MATCH as the code for this case.
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = SeriesContract.CONTENT_AUTORITY;

        // For each type of URI you want to add, create a corresponding code.
        matcher.addURI(authority, SeriesContract.PATH_POPULARS, POPULARS);

        matcher.addURI(authority, SeriesContract.PATH_DETAIL, DETAILS);
        //matcher.addURI(authority, SeriesContract.PATH_DETAIL + "/#", DETAILS_ID);

        return matcher;
    }

    @Override
    public boolean onCreate() {
        mOpenHelper = new SeriesDB(getContext());
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        // Here's the switch statement that, given a URI, will determine what kind of request it is,
        // and query the database accordingly.
        Cursor retCursor;
        switch (sUriMatcher.match(uri)) {

            case POPULARS: {
                retCursor = mOpenHelper.getReadableDatabase().query(
                        SeriesContract.PopularsEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            }

            case DETAILS: {
                retCursor = mOpenHelper.getReadableDatabase().query(
                        SeriesContract.DetailEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            }

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;
    }

    @Override
    public String getType(Uri uri) {

        // Use the Uri Matcher to determine what kind of URI this is.
        final int match = sUriMatcher.match(uri);

        switch (match) {
            case DETAILS:
                return SeriesContract.DetailEntry.CONTENT_TYPE;
            case POPULARS:
                return SeriesContract.PopularsEntry.CONTENT_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        Uri returnUri;

        switch (match) {
            case POPULARS: {
                long _id = db.insert(SeriesContract.PopularsEntry.TABLE_NAME, null, values);
                if ( _id > 0 )
                    returnUri = SeriesContract.PopularsEntry.buildPopularUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }
            case DETAILS: {
                long _id = db.insert(SeriesContract.DetailEntry.TABLE_NAME, null, values);
                if ( _id > 0 )
                    returnUri = SeriesContract.DetailEntry.buildDetailUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
