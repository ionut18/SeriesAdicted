package com.example.ionutcristian.seriesadicted;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.ionutcristian.seriesadicted.SeriesContract.DetailEntry;
import com.example.ionutcristian.seriesadicted.SeriesContract.PopularsEntry;


/**
 * Created by IonutCristian on 6/1/2015.
 */
public class SeriesDB extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "series.db";

    public SeriesDB (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //SeriesDB series = new SeriesDB(getContext());

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_POPULAR_TABLE = "CREATE TABLE" + PopularsEntry.TABLE_NAME + " (" +
                PopularsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                PopularsEntry.COLUMN_ID_DETAIL +  " INTEGER NOT NULL," +
                PopularsEntry.COLUMN_SERIES_TITLE + " TEXT NOT NULL," +
                PopularsEntry.COLUMN_SHORT_DESC + " TEXT NOT NULL," +
                PopularsEntry.COLUMN_STATUS + " TEXT NOT NULL," +
                PopularsEntry.COLUMN_CKECK + " BOOLEAN," +
                "FOREIGN KEY (" + PopularsEntry.COLUMN_ID_DETAIL + ") REFERENCES " +
                DetailEntry.TABLE_NAME + " (" + DetailEntry._ID + ");";

        sqLiteDatabase.execSQL(SQL_CREATE_POPULAR_TABLE);

        Log.v(SeriesDB.class.getSimpleName(), "a intrat in creare tabels");

        final String SQL_CREATE_DETAIL_TABLE = "CREATE TABLE" + DetailEntry.TABLE_NAME + " (" +
                DetailEntry._ID + " INTEGER PIMARY KEY AUTOINCREMENT," +
                DetailEntry.COLUMN_TITLE + " TEXT NOT NULL," +
                DetailEntry.COLUMN_LONG_DESC + " TEXT NOT NULL," +
                DetailEntry.COLUMN_GENRE + " TEXT NOT NULL," +
                DetailEntry.COLUMN_COMPANY + " TEXT NOT NULL," +
                DetailEntry.COLUMN_LAST_EPISODE + " TEXT NOT NULL," +
                DetailEntry.COLUMN_NEXT_EPISODE + " TEXT NOT NULL," +
                DetailEntry.COLUMN_IMDB + " TEXT NOT NULL);";

        sqLiteDatabase.execSQL(SQL_CREATE_DETAIL_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + PopularsEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DetailEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    public void addPopularSeries(String title, int id_detail, String sh_desc, String stat, boolean chk) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PopularsEntry.COLUMN_SERIES_TITLE, title);
        values.put(PopularsEntry.COLUMN_ID_DETAIL, id_detail);
        values.put(PopularsEntry.COLUMN_SHORT_DESC, sh_desc);
        values.put(PopularsEntry.COLUMN_STATUS, stat);
        values.put(PopularsEntry.COLUMN_CKECK, chk);

        long newRowId;
        newRowId = sqLiteDatabase.insert(
                PopularsEntry.TABLE_NAME,
                PopularsEntry._ID,
                values);
    }

    public Cursor getPopularSeriers() {

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(PopularsEntry.TABLE_NAME, new String[] { PopularsEntry.COLUMN_SERIES_TITLE,
                PopularsEntry.COLUMN_ID_DETAIL, PopularsEntry.COLUMN_SHORT_DESC}, null, null, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        return cursor;
    }

}
