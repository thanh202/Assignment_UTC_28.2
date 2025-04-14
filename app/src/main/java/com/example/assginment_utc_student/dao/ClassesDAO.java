package com.example.assginment_utc_student.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.assginment_utc_student.database.StudentDatabase;
import com.example.assginment_utc_student.model.Classes;

import java.util.ArrayList;

public class ClassesDAO {
    StudentDatabase studentDatabase;
    public ClassesDAO (Context context){
        studentDatabase = new StudentDatabase(context);
    }
    //call database
    public ArrayList<Classes> getAll(){
        SQLiteDatabase database = studentDatabase.getReadableDatabase();
        ArrayList<Classes> list = new ArrayList<>();
        //get toan bo thong tin cua lop
        Cursor cursor = database.rawQuery("SELECT * FROM CLASSES", null);
        if (cursor.getCount() != 0){
                cursor.moveToFirst();
                do {
                    list.add(new Classes(cursor.getString(0), cursor.getString(1)));

                }while (cursor.moveToNext());
        }
        return list;
    }
    //insert

    public boolean insertClass(Classes classes){
        try {
            SQLiteDatabase database = studentDatabase.getWritableDatabase();
            ContentValues contentValues = new ContentValues();

            contentValues.put("id", classes.getId());
            contentValues.put("name",classes.getName());
            database.insert("CLASSES", null, contentValues);
            return true;
        }catch (Exception ex){
            return false;
        }
    }
}
