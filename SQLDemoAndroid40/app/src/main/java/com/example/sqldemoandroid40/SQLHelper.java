package com.example.sqldemoandroid40;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SQLHelper extends SQLiteOpenHelper {
    private static final String TAG = "SQLHelper";
    static final String DB_NAME = "OrderFoods.db";
    static final String DB_TABLE = "Foods";
    static final int DB_VERSION = 1;
    SQLiteDatabase sqLiteDatabase;
    ContentValues contentValues;

    public SQLHelper(@Nullable Context context) {
        super( context, DB_NAME, null, DB_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCreateTable = "CREATE TABLE Foods (" +
                "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "name Text," +
                "price INTEGER," +
                "number INTEGER," +
                "description Text)";
        db.execSQL( queryCreateTable );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL( "DROP TABLE IF EXISTS " + DB_TABLE );
            onCreate( db );
        }
    }

    public void insertFoods(Foods foods) {
        sqLiteDatabase = getWritableDatabase();
        contentValues = new ContentValues();
        contentValues.put( "name", foods.getName() );
        contentValues.put( "price", foods.getPrice() );
        contentValues.put( "number", foods.getNumber() );
        contentValues.put( "description", foods.getDes() );
        sqLiteDatabase.insert( DB_TABLE, null, contentValues );
    }

    public boolean deleteFoods(String id) {
        sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete( DB_TABLE, "id =? ",
                new String[]{String.valueOf( id )} ) > 0;
    }

    public boolean deleteAllFoods() {
        sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete( DB_TABLE, null, null ) > 0;
    }

    public void updateFoods(String id, Foods foods) {
        sqLiteDatabase = getWritableDatabase();
        contentValues = new ContentValues();
        contentValues.put( "name", foods.getName() );
        contentValues.put( "price", foods.getPrice() );
        contentValues.put( "number", foods.getNumber() );
        contentValues.put( "description", foods.getDes() );
        sqLiteDatabase.update( DB_TABLE, contentValues, "id =? ",
                new String[]{String.valueOf( id )} );
    }

    public List<Foods> getAllFoods() {
        List<Foods> foodsList = new ArrayList<>();
        Foods foods;
        sqLiteDatabase = getWritableDatabase();
        Cursor cursor = sqLiteDatabase.query( false,DB_TABLE, null,
                null, null, null
                , null, null,null );
        while(cursor.moveToNext()){
            String name = cursor.getString( cursor.getColumnIndex( "name" ) );
            float price = cursor.getFloat( cursor.getColumnIndex( "price" ) );
            int number = cursor.getInt( cursor.getColumnIndex( "number" ) );
            String description = cursor.getString( cursor.getColumnIndex( "description" ) );
            foods = new Foods( name,price,number,description );
            foodsList.add( foods );
        }
        return foodsList;
    }
}
