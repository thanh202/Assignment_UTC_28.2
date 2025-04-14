package com.example.assginment_utc_student;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.assginment_utc_student.dao.ClassesDAO;
import com.example.assginment_utc_student.model.Classes;


public class MainActivity extends AppCompatActivity {
    //call DAO
    private ClassesDAO classesDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ánh xạ từ id layout main
        Button btnAddClass = findViewById(R.id.btnAddClass);
        Button btnClassManager = findViewById(R.id.btnClassManager);
        Button btnStudent = findViewById(R.id.btnStudentManager);

        //call DAO
        classesDAO = new ClassesDAO(MainActivity.this);
        //1
        btnAddClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //cutom dialog
                showDiaLogAddclass();
            }
        });
        //2
        btnClassManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ClassesManagerActivity.class));
            }
        });
    }

    private void showDiaLogAddclass() {
        AlertDialog.Builder builder =new AlertDialog.Builder(MainActivity.this);
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_add_class,null);
        builder.setView(view);

        AlertDialog dialog = builder.create();
        dialog.show();

        // anh xa vao layout dialog
        EditText edtClassID = view.findViewById(R.id.edtClassId);
        EditText edtClassName = view.findViewById(R.id.edtClassName);
        Button btnClear = view.findViewById(R.id.btnclear);
        Button btnAddClass = view.findViewById(R.id.btnAdd);
        // xoa trắng
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtClassID.setText("");
                edtClassName.setText("");
            }
        });
        //add DAO
        btnAddClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //... DAO.inserLop();
                String idClass = edtClassID.getText().toString();
                String idName = edtClassName.getText().toString();
                Classes classes = new Classes(idClass,idName);
                if (classesDAO.insertClass(classes)){
                    Toast.makeText(MainActivity.this, "thêm thành công", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });

    }
}