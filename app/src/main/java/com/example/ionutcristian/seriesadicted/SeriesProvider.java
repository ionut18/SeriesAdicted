package com.example.ionutcristian.seriesadicted;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

/**
 * Created by IonutCristian on 6/1/2015.
 */
public class SeriesProvider extends ContentProvider{

    private static final UriMatcher sUriMatcher = buildUriMatcher();


    private static final int POPULARS = 100;
    private static final int DETAILS = 200;
   // private static final int DETAILS_ID = 201;

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
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
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
        return null;
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
