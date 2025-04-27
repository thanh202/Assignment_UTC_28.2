package com.example.assginment_utc_student;

import android.os.Bundle;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.assginment_utc_student.dao.ClassesDAO;
import com.example.assginment_utc_student.model.Classes;

import java.util.ArrayList;
import java.util.HashMap;

public class StudentManagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_manager);

        // anh sa
        Spinner spnClasses = findViewById(R.id.spnClasses);
        Button btnAddStudent = findViewById(R.id.btnAddStudent);

        //data
        ClassesDAO classesDAO = new ClassesDAO(StudentManagerActivity.this);
        ArrayList<Classes> list = classesDAO.getAll();

        //adapter
        ArrayList<HashMap<String, String>> listSPN = new ArrayList<>();
        for (Classes classes : list){
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("id", classes.getId());
            hashMap.put("name", classes.getName());
            listSPN.add(hashMap);
        }
        SimpleAdapter adapter = new SimpleAdapter(StudentManagerActivity.this,
                listSPN, R.layout.item_spinner,
                new String[]{"id","name"},
                new int[]{R.id.txtID, R.id.txtName});
        //add adpater spinner

        spnClasses.setAdapter(adapter);
    }
}