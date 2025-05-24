package com.example.assginment_utc_student.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StudentDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "STUDENT";
    private static final int DATABASE_VERSION = 2; // Tăng version lên 2

    public StudentDatabase(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlClasses = "CREATE TABLE CLASSES (id text primary key, name text)";
        sqLiteDatabase.execSQL(sqlClasses);

        String sqlStudent = "CREATE TABLE STUDENT (id text primary key, name text, class_id text, " +
                "FOREIGN KEY (class_id) REFERENCES CLASSES(id))";
        sqLiteDatabase.execSQL(sqlStudent);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // Chỉ tạo bảng STUDENT nếu nâng cấp từ version 1 lên 2
        if (oldVersion == 1 && newVersion == 2) {
            String sqlStudent = "CREATE TABLE STUDENT (id text primary key, name text, class_id text, " +
                    "FOREIGN KEY (class_id) REFERENCES CLASSES(id))";
            sqLiteDatabase.execSQL(sqlStudent);
        } else {
            // Xóa tất cả các bảng nếu là trường hợp khác
            String sqlStudent = "DROP TABLE IF EXISTS STUDENT";
            sqLiteDatabase.execSQL(sqlStudent);

            String sqlClasses = "DROP TABLE IF EXISTS CLASSES";
            sqLiteDatabase.execSQL(sqlClasses);

            // Tạo lại các bảng
            onCreate(sqLiteDatabase);
        }
    }
}