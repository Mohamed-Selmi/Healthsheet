package com.example.healthsheet;

import static com.example.healthsheet.DataBaseHelper.USER_TABLE;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.healthsheet.entities.User;

import java.sql.SQLDataException;

public class UserManager {
    private DataBaseHelper dbHelper;
    private SQLiteDatabase database;
    private Context context;
    public UserManager(Context context){
        //dbHelper=new DataBaseHelper(context);
        this.context=context;
    }
    public UserManager open() throws SQLDataException {
    dbHelper=new DataBaseHelper(context,null);
    database=dbHelper.getWritableDatabase();
    return this;
    }
    public void close(){
        dbHelper.close();
    }
    public long insertUser(User user){
        ContentValues content=new ContentValues();
        content.put(DataBaseHelper.USER_EMAIL,user.getEmail());
        content.put(DataBaseHelper.USER_NAME,user.getUsername());
        content.put(DataBaseHelper.USER_PASSWORD,user.getPassword());
        return database.insert(USER_TABLE,null,content);
    }
    //I need to change this to OOP later, gonna be a long night!
    public int update(User user){
        return 1;
    }
    //public int update(String id,String)
}
