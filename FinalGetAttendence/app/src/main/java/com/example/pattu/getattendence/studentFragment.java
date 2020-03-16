package com.example.pattu.getattendence;

import android.content.Intent;
import android.provider.DocumentsContract;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class studentFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.activity_student_fragment,container,false);
        FloatingActionButton fab=(FloatingActionButton)rootView.findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              startActivity(new Intent(getContext(),AddStudent.class));

            }
        });
        DBStudent db = new DBStudent(getContext());
        List<Student> students = db.allStudent();

        ArrayList<String> nameArray = new ArrayList<>();
        ArrayList<Integer> idArray = new ArrayList<>();

        Boolean t = false;
        for (Student student:students){
            t = true;
            idArray.add(student.get_id());
            nameArray.add(student.get_name());
        }

        String[] name = new String[nameArray.size()];
        final Integer[] id1 = new Integer[idArray.size()];
        if (t) {
            for (int i = 0; i < nameArray.size(); i++) {
                id1[i] = idArray.get(i);
                name[i] = nameArray.get(i);
            }
        }
        ListView lv = (ListView)rootView.findViewById(R.id.listView2);

        lv.setAdapter(new CustomArrayAdapter(getContext(), name, id1));

        return rootView;
    }
}
