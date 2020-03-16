package com.example.pattu.getattendence;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class RadioActivity extends AppCompatActivity {

    int pos;
    int pos1;

    String name;
    String branch;
    String sem;
    String roll;
    String date;
    DBStudent db;
    EditText ed;
    int p;
    int a;
    int l;
    int c;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio);

        name = getIntent().getStringExtra("name");
        branch = getIntent().getStringExtra("branch");
        sem = getIntent().getStringExtra("sem");
        roll = getIntent().getStringExtra("roll");
        date = getIntent().getStringExtra("date");
        db = new DBStudent(RadioActivity.this);
        ed = (EditText) findViewById(R.id.textView4);
        final RadioGroup rg=(RadioGroup)findViewById(R.id.radioGroup);
        final RadioButton rb1=(RadioButton)findViewById(R.id.radio1);
        RadioButton rb2=(RadioButton)findViewById(R.id.radio2);
        RadioButton rb3=(RadioButton)findViewById(R.id.radio3);
        RadioButton rb4=(RadioButton)findViewById(R.id.radio4);

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        }

        Student student = db.studentAttend(date, branch, sem, name, roll);

        if (student.get_present() == 1)
            rb1.setChecked(true);
        else if (student.get_absent() == 1)
            rb2.setChecked(true);
        else if (student.get_leave() == 1)
            rb3.setChecked(true);
        else if (student.get_note() == 1) {
            rb4.setChecked(true);
            ed.setVisibility(View.VISIBLE);
            ed.setText(student.get_note_text());
        }


        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override

            public void onCheckedChanged(RadioGroup group, int checkedId) {

                pos=rg.indexOfChild(findViewById(checkedId));

                pos1=rg.indexOfChild(findViewById(rg.getCheckedRadioButtonId()));



                switch (pos)

                {

                    case 0 :
                        ed.setVisibility(View.GONE);
                        p = 1;
                        a = 0;
                        l = 0;
                        c = 0;
                        Toast.makeText(RadioActivity.this, "Student Is Present", Toast.LENGTH_SHORT).show();

                        break;

                    case 1 :
                        p = 0;
                        a = 1;
                        l = 0;
                        c = 0;
                        ed.setVisibility(View.GONE);
                        Toast.makeText(getBaseContext(), "Student Is Absent",

                                Toast.LENGTH_SHORT).show();
                        break;

                    case 2 :
                        p = 0;
                        a = 0;
                        l = 1;
                        c = 0;
                        ed.setVisibility(View.GONE);
                        Toast.makeText(getBaseContext(), "Student Is On Leave",

                                Toast.LENGTH_SHORT).show();
                        break;

                    case 3 :

                        p = 0;
                        a = 0;
                        l = 0;
                        c = 1;
                        ed.setVisibility(View.VISIBLE);
                        Toast.makeText(getBaseContext(), "Student Is",

                                Toast.LENGTH_SHORT).show();
                        break;


                    default :

                        Toast.makeText(getBaseContext(),"You Have Clicked" ,

                                Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        });
    }

    @Override

    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);

        return true;

    }

    public void submit(View view) {
        if (p == 1) {
            db.takeAttendence(date, name, branch, roll, sem, 1, 0, 0, 0, "none");
        } else if (a == 1) {
            db.takeAttendence(date, name, branch, roll, sem, 0, 1, 0, 0, "none");
        } else if (l == 1) {
            db.takeAttendence(date, name, branch, roll, sem, 0, 0, 1, 0, "none");
        } else if (c == 1) {
            db.takeAttendence(date, name, branch, roll, sem, 0, 0, 0, 1, ed.getText().toString());
        }

        Intent i = new Intent(RadioActivity.this, CustomAttendence.class);
        i.putExtra("branch", branch);
        i.putExtra("sem", sem);
        i.putExtra("date", date);
        startActivity(i);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}