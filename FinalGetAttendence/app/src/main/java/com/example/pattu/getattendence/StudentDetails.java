package com.example.pattu.getattendence;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class StudentDetails extends AppCompatActivity {

    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        TextView ed1=(TextView) findViewById(R.id.textView9);
        TextView ed3=(TextView) findViewById(R.id.textView11);
        TextView ed4=(TextView) findViewById(R.id.textView15);
        TextView ed5=(TextView) findViewById(R.id.textView13);
        TextView ed6=(TextView) findViewById(R.id.textView14);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Toast.makeText(this, getIntent().getStringExtra("id"), Toast.LENGTH_LONG).show();

        DBStudent db=new DBStudent(StudentDetails.this);
        Student student = db.student(getIntent().getStringExtra("id"));
        String name="";


        student.get_id();
        ed1.setText(student.get_name());
        ed6.setText(student.get_phone());
        ed4.setText(student.get_email());
        ed5.setText(student.get_subject());
        ed3.setText(student.get_class1());
    }


}


