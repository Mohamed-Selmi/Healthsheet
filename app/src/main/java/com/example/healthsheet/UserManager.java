package com.example.healthsheet;

import static com.example.healthsheet.DataBaseHelper.USER_EMAIL;
import static com.example.healthsheet.DataBaseHelper.USER_ID;
import static com.example.healthsheet.DataBaseHelper.USER_PASSWORD;
import static com.example.healthsheet.DataBaseHelper.USER_TABLE;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.healthsheet.entities.User;

import java.sql.PreparedStatement;
import java.sql.SQLDataException;
import java.util.Arrays;

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
        //content.put(DataBaseHelper.USER_GENDER,"male");//user.getGender());
        return database.insert(USER_TABLE,null,content);
    }
    //I need to change this to OOP later, gonna be a long night!
//    public boolean loginUser2(User user){
//        ContentValues content=new ContentValues();
//        content.put(DataBaseHelper.USER_EMAIL,user.getEmail());
//        content.put(DataBaseHelper.USER_PASSWORD,user.getPassword());
//    String[] columns=new String[]{
//               "cast(count(1) AS BIT)"
//        };
//        String whereClause="email =? AND password =?";
//        String[] whereArgs= new String[]{
//                user.getEmail(),
//                user.getPassword()
//        };
//
//       // String query="SELECT * from user"+USER_TABLE+" where "+USER_EMAIL+"="+user.getEmail()+" AND "+USER_PASSWORD="="+user.getPassword()+";";
//        Cursor c=database.query(USER_TABLE,null,whereClause,whereArgs,null,null,null);
//        c.close();
//        return c.moveToFirst();
//    }
    public boolean loginUser(User user) throws SQLException{
        String Query = "Select * from " + USER_TABLE + " where "+USER_EMAIL + " = '" + user.getEmail()+"' AND "+ USER_PASSWORD+" = '"+ user.getPassword()+"';";
        Cursor cursor = database.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;

    }
    public User getUser(String email) throws SQLException {
        User user;
        String Query = "Select * from " + USER_TABLE + " where "+USER_EMAIL + " = '" + email+"';";
        Log.d("myTag","test:"+email);

        //String Query="SELECT * from user where email="+email+";";
        //Log.d("myTag","query:"+email);

        Cursor cursor = database.rawQuery(Query, null);

        if(cursor.getCount() <= 0){
            cursor.close();
            Log.d("myTag","no user found");
            return null;
        }
        cursor.moveToFirst();
        user=new User(cursor.getString(1),cursor.getString(2),cursor.getString(3));
        Log.d("myTag",user.toString());
        cursor.close();
        return user;
    };
    public int update(User user){
        ContentValues content=new ContentValues();
        content.put(DataBaseHelper.USER_EMAIL,user.getEmail());
        content.put(DataBaseHelper.USER_NAME,user.getUsername());
        content.put(DataBaseHelper.USER_PASSWORD,user.getPassword());
        return database.update(USER_TABLE,content,DataBaseHelper.USER_ID+"=?",new String[] {String.valueOf(user.getId())});
    }
    public void delete(User user){
        database.delete(USER_TABLE,DataBaseHelper.USER_ID+"="+user.getId(),null);
    }

    //public int update(String id,String)

}
