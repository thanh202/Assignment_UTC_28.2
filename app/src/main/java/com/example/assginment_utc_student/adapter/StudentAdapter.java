package com.example.assginment_utc_student.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.assginment_utc_student.R;
import com.example.assginment_utc_student.model.Student;

import java.util.ArrayList;

public class StudentAdapter extends BaseAdapter {
    private ArrayList<Student> list;
    private Context context;

    public StudentAdapter(ArrayList<Student> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = ((Activity) context).getLayoutInflater();
            convertView = layoutInflater.inflate(R.layout.item_student, parent, false);
        }

        // Ánh xạ view
        TextView txtStudentNumber = convertView.findViewById(R.id.txtStudentNumber);
        TextView txtStudentId = convertView.findViewById(R.id.txtStudentId);
        TextView txtStudentName = convertView.findViewById(R.id.txtStudentName);

        // Gán dữ liệu
        txtStudentNumber.setText(String.valueOf(position + 1));
        txtStudentId.setText("Mã SV: " + list.get(position).getId());
        txtStudentName.setText("Tên SV: " + list.get(position).getName());

        return convertView;
    }
}