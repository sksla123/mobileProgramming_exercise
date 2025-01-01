package com.example.j202012195_1210;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class StudentAdapter extends BaseAdapter {
    Context context;
    ArrayList<Student> students = new ArrayList<Student>();

    public StudentAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem(int i) {

        return students.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        context = viewGroup.getContext();
        Student student = students.get(i);

        if (view == null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.student_view, viewGroup, false);
        }

        TextView textView1 = (TextView) view.findViewById(R.id.textView);
        TextView textView2 = (TextView) view.findViewById(R.id.textView2);
        TextView textView3 = (TextView) view.findViewById(R.id.textView3);

        textView1.setText(student.getHakgwa());
        textView2.setText(student.getHakbun());
        textView3.setText(student.getName());

        return view;
    }

    public void addItem(Student student) {
        students.add(student);
    }

    public void removeItem(int pos) {
        students.remove(pos);
    }
}
