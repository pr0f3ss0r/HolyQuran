package com.tosindroid.pr0f.holyquran;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.util.Log;

import java.sql.SQLData;

import androidx.annotation.Nullable;

public class FavDB extends SQLiteOpenHelper {

    private static int DB_VERSION = 1;
    private static String DATABASE_NAME = "favoritesDB";
    private static String TABLE_NAME =  "fav_table";
    public static String KEY_ID =  "id";
    public static String SURAH_NAME = "surah_name";
    public static String PAGE_NUMBER = "page_number";
    public static String JUZ_NUMBER = "juz_number";
    private static  String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + KEY_ID + " TEXT," + SURAH_NAME + " TEXT," + PAGE_NUMBER + " TEXT,"
            + JUZ_NUMBER + " TEXT)";

    public FavDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //create empty table
    public void insertEmpty(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        // enter values

    }

    public void insertIntoTheDatabase(String surah_name, String page_number, String juz_number, String id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
         cv.put(SURAH_NAME, surah_name);
         cv.put(PAGE_NUMBER, page_number);
         cv.put(JUZ_NUMBER, juz_number);
         cv.put(KEY_ID, id);
         db.insert(TABLE_NAME, null, cv);
        Log.d("FAv db status", surah_name + "status -" + cv);
    }

    public Cursor readAllData(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_ID + "=" + id+ "";
        return db.rawQuery(sql, null, null);
    }

    //remove line from database
    public void remove_fav(String id){
        SQLiteDatabase db = this.getWritableDatabase();
       //String sql = "UPDATE " + TABLE_NAME + " SET " +
    }

    //select all favorite list
    public Cursor selectAllFavoriteList(){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_NAME + "WHERE ";
        return db.rawQuery(sql, null, null);
    }
}
