package com.example.ionutcristian.seriesadicted;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
public class SeriesDataBase extends SQLiteOpenHelper {

    Context c;
    public static final String DATABASE_NAME = "srdb.db";
    public static final String CONTACTS_TABLE_NAME = "Series";
    public static final String COLUMN_ID = "_ID";
    public static final String COLUMN_TITLE = "TITLE";
    public static final String COLUMN_SHORT_DESC = "SH_DESC";
    public static final String COLUMN_LONG_DESC = "LG_DESC";
    public static final String COLUMN_GENRE = "GENRE";
    public static final String COLUMN_COMPANY = "COMPANY";
    public static final String COLUMN_LAST_EPISODE = "LAST_EP";
    public static final String COLUMN_NEXT_EPISODE = "NEXT_EP";
    public static final String COLUMN_IMDB = "IMDB";
    public static final String COLUMN_GRADE = "GRADE";

    public SeriesDataBase(Context context)
    {
        super(context, DATABASE_NAME , null, 1);
        c = context;
       // Toast.makeText(c, "Constructor", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_SERIES_TABLE = "CREATE TABLE Series (_ID INTEGER PRIMARY KEY, TITLE TEXT NOT NULL, SH_DESC TEXT NOT NULL, " +
                "LG_DESC TEXT NOT NULL, GENRE TEXT NOT NULL, COMPANY TEXT NON NULL, STATUS TEXT NOT NULL, CHECKED BOOLEAN, " +
                "LAST_EP TEXT NOT NULL, NEXT_EP TEXT NOT NULL, IMDB TEXT NOT NULL, GRADE DOUBLE);";

        db.execSQL(SQL_CREATE_SERIES_TABLE);
        //Toast.makeText(c, "Creare", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Series");
        onCreate(db);
    }

    public boolean insertSeries (int id, String title, String sht_desc, String lng_desc, String genre,  String company,
                                 String stat, boolean chk, String last_ep, String next_ep, String imdb, double grade)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("_ID", id);
        contentValues.put("TITLE", title);
        contentValues.put("SH_DESC", sht_desc);
        contentValues.put("LG_DESC", lng_desc);
        contentValues.put("GENRE", genre);
        contentValues.put("COMPANY", company);
        contentValues.put("STATUS", stat);
        contentValues.put("CHECKED", chk);
        contentValues.put("LAST_EP", last_ep);
        contentValues.put("NEXT_EP", next_ep);
        contentValues.put("IMDB", imdb);
        contentValues.put("GRADE", grade);
        db.insert("Series", null, contentValues);
        //Toast.makeText(c, "Inserare", Toast.LENGTH_SHORT).show();
        return true;
    }

    public Cursor getData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from Series where _ID="+id+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, CONTACTS_TABLE_NAME);
        return numRows;
    }

    public boolean updateSeries (int id, String title, String sht_desc, String lng_desc, String genre,  String company,
                                 String stat, boolean chk, String last_ep, String next_ep, String imdb, double grade)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("_ID", id);
        contentValues.put("TITLE", title);
        contentValues.put("SH_DESC", sht_desc);
        contentValues.put("LG_DESC", lng_desc);
        contentValues.put("GENRE", genre);
        contentValues.put("COMPANY", company);
        contentValues.put("STATUS", stat);
        contentValues.put("CHECKED", chk);
        contentValues.put("LAST_EP", last_ep);
        contentValues.put("NEXT_EP", next_ep);
        contentValues.put("IMDB", imdb);
        contentValues.put("GRADE", grade);
        db.update("Series", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteSeries (Integer id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("Series",
                "_ID = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<String> getAllTitles()
    {
        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from Series", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(COLUMN_TITLE)));
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<String> getNextEpisodes()
    {
        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from Series", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(COLUMN_NEXT_EPISODE)));
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<String> getShDesc()
    {
        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from Series", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(COLUMN_SHORT_DESC)));
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<String> getLgDesc()
    {
        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from Series", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(COLUMN_LONG_DESC)));
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<String> getGenre()
    {
        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from Series", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(COLUMN_GENRE)));
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<String> getLastEp()
    {
        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from Series", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(COLUMN_LAST_EPISODE)));
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<String> getNextEp()
    {
        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from Series", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(COLUMN_NEXT_EPISODE)));
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<String> getCompany()
    {
        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from Series", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(COLUMN_COMPANY)));
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<String> getImdb()
    {
        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from Series", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(COLUMN_IMDB)));
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<String> getGrade()
    {
        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from Series", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(COLUMN_GRADE)));
            res.moveToNext();
        }
        return array_list;
    }
}