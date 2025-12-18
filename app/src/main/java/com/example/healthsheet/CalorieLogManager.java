package com.example.healthsheet;

import static com.example.healthsheet.DataBaseHelper.LOG_TABLE;
import static com.example.healthsheet.DataBaseHelper.USER_TABLE;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.healthsheet.entities.CalorieLog;
import com.example.healthsheet.entities.User;

import java.sql.SQLDataException;

public class CalorieLogManager {
    private DataBaseHelper dbHelper;
    private SQLiteDatabase database;
    private Context context;

    public CalorieLogManager(Context context){
        //dbHelper=new DataBaseHelper(context);
        this.context=context;
    }
    public CalorieLogManager open() throws SQLDataException {
        dbHelper=new DataBaseHelper(context,null);
        database=dbHelper.getWritableDatabase();
        return this;
    }
    public void close(){
        dbHelper.close();
    }
    public long insertLog(CalorieLog calorieLog){
        ContentValues content=new ContentValues();
        content.put(DataBaseHelper.LOG_USER, calorieLog.getUser().getId());
        content.put(DataBaseHelper.LOG_DATE,calorieLog.getDate());
        content.put(DataBaseHelper.LOG_AMOUNT,calorieLog.getAmount());
        return database.insert(LOG_TABLE,null,content);
    }

}
