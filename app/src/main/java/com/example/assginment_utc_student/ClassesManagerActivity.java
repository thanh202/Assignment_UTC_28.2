package com.example.assginment_utc_student;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.assginment_utc_student.adapter.ClassesAdapter;
import com.example.assginment_utc_student.dao.ClassesDAO;
import com.example.assginment_utc_student.model.Classes;

import java.util.ArrayList;

public class ClassesManagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_classes_manager);
        // anh xa
        ListView listClasses = findViewById(R.id.listClasses);

        //data
        ClassesDAO classesDAO = new ClassesDAO(ClassesManagerActivity.this);
        ArrayList<Classes> list = classesDAO.getAll();

        //adapter
        ClassesAdapter adapter = new ClassesAdapter(list, ClassesManagerActivity.this);

        //set adapter
        listClasses.setAdapter(adapter);
    }

}