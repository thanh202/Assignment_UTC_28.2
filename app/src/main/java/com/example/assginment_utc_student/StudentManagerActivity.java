package com.example.assginment_utc_student;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assginment_utc_student.adapter.ClassesAdapter;
import com.example.assginment_utc_student.adapter.StudentAdapter;
import com.example.assginment_utc_student.dao.ClassesDAO;
import com.example.assginment_utc_student.dao.StudentDAO;
import com.example.assginment_utc_student.model.Classes;
import com.example.assginment_utc_student.model.Student;

import java.util.ArrayList;
import java.util.HashMap;

public class StudentManagerActivity extends AppCompatActivity {
    private Spinner spnClasses;
    private Button btnAddStudent;
    private ListView lvStudents;
    private ClassesDAO classesDAO;
    private StudentDAO studentDAO;
    private String selectedClassId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_manager);

        // Khởi tạo DAO
        classesDAO = new ClassesDAO(StudentManagerActivity.this);
        studentDAO = new StudentDAO(StudentManagerActivity.this);

        // Ánh xạ view
        spnClasses = findViewById(R.id.spnClasses);
        btnAddStudent = findViewById(R.id.btnAddStudent);
        lvStudents = findViewById(R.id.lvStudents); // Giả sử bạn có ListView để hiển thị sinh viên

        // Lấy dữ liệu lớp học
        ArrayList<Classes> list = classesDAO.getAll();

        // Thiết lập adapter cho spinner
        ArrayList<HashMap<String, String>> listSPN = new ArrayList<>();
        for (Classes classes : list) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("id", classes.getId());
            hashMap.put("name", classes.getName());
            listSPN.add(hashMap);
        }
        SimpleAdapter adapter = new SimpleAdapter(StudentManagerActivity.this,
                listSPN, R.layout.item_spinner,
                new String[]{"id", "name"},
                new int[]{R.id.txtID, R.id.txtName});

        spnClasses.setAdapter(adapter);

        // Lấy lớp được chọn
        spnClasses.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String, String> selected = (HashMap<String, String>) spnClasses.getItemAtPosition(position);
                selectedClassId = selected.get("id");
                // Cập nhật danh sách sinh viên theo lớp được chọn
                updateStudentList();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // Thiết lập sự kiện click cho nút thêm sinh viên
        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddStudentDialog();
            }
        });
    }

    private void updateStudentList() {
        if (selectedClassId != null) {
            try {
                ArrayList<Student> students = studentDAO.getStudentsByClassId(selectedClassId);
                StudentAdapter adapter = new StudentAdapter(students, StudentManagerActivity.this);
                lvStudents.setAdapter(adapter);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Lỗi: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void showAddStudentDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_student, null);
        builder.setView(dialogView);

        // Ánh xạ các view trong dialog
        EditText edtStudentId = dialogView.findViewById(R.id.edtStudentId);
        EditText edtStudentName = dialogView.findViewById(R.id.edtStudentName);
        Button btnCancel = dialogView.findViewById(R.id.btnCancel);
        Button btnSave = dialogView.findViewById(R.id.btnSave);

        AlertDialog dialog = builder.create();
        dialog.show();

        // Xử lý sự kiện nút "Hủy"
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        // Xử lý sự kiện nút "Lưu"
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String studentId = edtStudentId.getText().toString().trim();
                String studentName = edtStudentName.getText().toString().trim();

                // Kiểm tra dữ liệu nhập vào
                if (studentId.isEmpty() || studentName.isEmpty()) {
                    Toast.makeText(StudentManagerActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Tạo đối tượng sinh viên mới
                Student student = new Student(studentId, studentName, selectedClassId);

                // Thêm sinh viên vào cơ sở dữ liệu
                boolean result = studentDAO.insertStudent(student);
                if (result) {
                    Toast.makeText(StudentManagerActivity.this, "Thêm sinh viên thành công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    // Cập nhật lại danh sách sinh viên
                    updateStudentList();
                } else {
                    Toast.makeText(StudentManagerActivity.this, "Thêm sinh viên thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}