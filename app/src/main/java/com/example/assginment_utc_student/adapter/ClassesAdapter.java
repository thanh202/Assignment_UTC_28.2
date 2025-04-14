package com.example.assginment_utc_student.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.assginment_utc_student.R;
import com.example.assginment_utc_student.model.Classes;

import java.util.ArrayList;

public class ClassesAdapter extends BaseAdapter {
    //khai bao
    private ArrayList<Classes> list;
    private Context context;

    public ClassesAdapter(ArrayList<Classes> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null){
            LayoutInflater layoutInflater = ((Activity) context).getLayoutInflater();
            view = layoutInflater.inflate(R.layout.item_list_classes, viewGroup , false);
        }
        //anh xa
        TextView txtSTT = view.findViewById(R.id.txtSTT);
        TextView txtTenLop = view.findViewById(R.id.txtTenLop);
        TextView txtMaLop = view.findViewById(R.id.txtMaLop);


        txtSTT.setText(String.valueOf((i+1)));
        txtMaLop.setText(list.get(i).getId());
        txtTenLop.setText(list.get(i).getName());

        return view;
    }
}
