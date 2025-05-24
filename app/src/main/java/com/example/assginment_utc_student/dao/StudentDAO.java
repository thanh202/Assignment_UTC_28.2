package com.example.assginment_utc_student.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.assginment_utc_student.database.StudentDatabase;
import com.example.assginment_utc_student.model.Student;

import java.util.ArrayList;

public class StudentDAO {
    StudentDatabase studentDatabase;

    public StudentDAO(Context context) {
        studentDatabase = new StudentDatabase(context);
    }

    public ArrayList<Student> getAll() {
        SQLiteDatabase database = studentDatabase.getReadableDatabase();
        ArrayList<Student> list = new ArrayList<>();

        Cursor cursor = database.rawQuery("SELECT * FROM STUDENT", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new Student(cursor.getString(0), cursor.getString(1), cursor.getString(2)));
            } while (cursor.moveToNext());
        }
        return list;
    }

    public ArrayList<Student> getStudentsByClassId(String classId) {
        SQLiteDatabase database = studentDatabase.getReadableDatabase();
        ArrayList<Student> list = new ArrayList<>();

        Cursor cursor = database.rawQuery("SELECT * FROM STUDENT WHERE class_id = ?", new String[]{classId});
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new Student(cursor.getString(0), cursor.getString(1), cursor.getString(2)));
            } while (cursor.moveToNext());
        }
        return list;
    }

    public boolean insertStudent(Student student) {
        try {
            SQLiteDatabase database = studentDatabase.getWritableDatabase();
            ContentValues contentValues = new ContentValues();

            contentValues.put("id", student.getId());
            contentValues.put("name", student.getName());
            contentValues.put("class_id", student.getClassId());

            long value = database.insert("STUDENT", null, contentValues);
            return value != -1;
        } catch (Exception ex) {
            return false;
        }
    }
}