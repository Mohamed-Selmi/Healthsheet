package com.example.healthsheet;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String DB_NAME="tracking.sqlite";
    public static final int DB_VERSION=2;
    public static final String USER_TABLE= "user";
    public static final String USER_ID= "id";
    public static final String USER_NAME="username";
    public static final String USER_EMAIL= "email";
    public static final String USER_PASSWORD= "password";
    public static final String USER_PICTURE= "picture";
    public static final String LOG_TABLE="calorielog";
    public static final String LOG_ID= "id";
    public static final String LOG_DATE="date";
    public static final String LOG_USER= "user";
    public static final String LOG_AMOUNT= "calories";

    public static final String CREATE_USER_TABLE="CREATE TABLE "+USER_TABLE+" ("+
            USER_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+
            USER_EMAIL+ " TEXT NOT NULL UNIQUE, "+
            USER_NAME+ " TEXT NOT NULL, "+
            USER_PASSWORD+ " TEXT NOT NULL, "+
            USER_PICTURE+ " BLOB);";
    public static final String CREATE_LOG_TABLE="CREATE TABLE "+LOG_TABLE+" ("+
            LOG_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+
            LOG_USER+ " INTEGER NOT NULL, "+
            LOG_DATE+ " TEXT NOT NULL, "+
            LOG_AMOUNT+ " INTEGER, "+
            "FOREIGN KEY (user) REFERENCES user (id) );"
            ;
    public DataBaseHelper(@Nullable Context context, @Nullable SQLiteDatabase.CursorFactory factory){

        super(context,DB_NAME,factory,DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_LOG_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ USER_TABLE);
        onCreate(db);
    }
}
