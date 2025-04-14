package com.example.assginment_utc_student.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StudentDatabase extends SQLiteOpenHelper {
    public StudentDatabase(Context context){
        super(context, "STUDENT", null , 1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE CLASSES (id text primary key, name text)" ;
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS CLASSES";
        sqLiteDatabase.execSQL(sql);
    }
}
