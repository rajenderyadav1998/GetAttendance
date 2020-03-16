package com.example.pattu.getattendence;

import android.os.Bundle;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class CustomAttendence extends AppCompatActivity {

    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_attendence_student);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab1=(FloatingActionButton)findViewById(R.id.fab12);
        Log.d("test", "test");
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("test", "test1");
                Intent i=new Intent(CustomAttendence.this,ViewStudent.class);
                startActivity(i);
            }
        });

        final String branch = getIntent().getStringExtra("branch");
        final String sem = getIntent().getStringExtra("sem");
        final String date = getIntent().getStringExtra("date");

        DBStudent db = new DBStudent(this);

        List<Student> students = db.sudentCat(branch, sem);

        final ArrayList<String> nameArray = new ArrayList<>();
        ArrayList<Integer> idArray = new ArrayList<>();
        final ArrayList<String> rollArray = new ArrayList<>();

        Boolean t = false;
        for (Student student:students){
            t = true;
            idArray.add(student.get_id());
            nameArray.add(student.get_name());
            rollArray.add(student.get_roll());
        }


        final String[] name = new String[nameArray.size()];
        final Integer[] id1 = new Integer[idArray.size()];
        String[] roll = new String[rollArray.size()];

        if (t) {
            for (int i = 0; i < nameArray.size(); i++) {
                id1[i] = idArray.get(i);
                name[i] = nameArray.get(i);
                roll[i] = rollArray.get(i);

            }
        }

        ListView lv = (ListView) findViewById(R.id.listattendence);

        lv.setAdapter(new CustomArrayAdapterAttendence(CustomAttendence.this, name, roll));

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(CustomAttendence.this, RadioActivity.class);
                i.putExtra("date", date);
                i.putExtra("name", name[position]);
                i.putExtra("branch", branch);
                i.putExtra("roll", rollArray.get(position));
                i.putExtra("sem", sem);

                startActivity(i);

            }
        });
    }
}
