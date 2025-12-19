package com.example.healthsheet;

import static com.example.healthsheet.DataBaseHelper.LOG_TABLE;
import static com.example.healthsheet.DataBaseHelper.MEASURE_TABLE;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.healthsheet.entities.CalorieLog;
import com.example.healthsheet.entities.Measurements;

import java.sql.SQLDataException;

public class MeasurementManager {
    private DataBaseHelper dbHelper;
    private SQLiteDatabase database;
    private Context context;
    public MeasurementManager(Context context){
        //dbHelper=new DataBaseHelper(context);
        this.context=context;
    }
    public MeasurementManager open() throws SQLDataException {
        dbHelper=new DataBaseHelper(context,null);
        database=dbHelper.getWritableDatabase();
        return this;
    }
    public void close(){
        dbHelper.close();
    }
    public long insertMeasurement(Measurements measurement){
        ContentValues content=new ContentValues();
        content.put(DataBaseHelper.MEASURE_USER, measurement.getUser().getId());
        content.put(DataBaseHelper.MEASURE_DATE,measurement.getDate());
        content.put(DataBaseHelper.MEASURE_HEIGHT,measurement.getHeight());
        content.put(DataBaseHelper.MEASURE_AGE,measurement.getAge());
        content.put(DataBaseHelper.MEASURE_WEIGHT,measurement.getWeight());
        content.put(DataBaseHelper.MEASURE_HIP,measurement.getHip());
        content.put(DataBaseHelper.MEASURE_WAIST,measurement.getWaist());
        return database.insert(MEASURE_TABLE,null,content);
    }


}
