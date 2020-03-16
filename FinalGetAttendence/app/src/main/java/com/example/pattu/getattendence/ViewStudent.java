package com.example.pattu.getattendence;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class ViewStudent extends AppCompatActivity {

    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    Spinner branch,sem;
    Calendar myCalendar = Calendar.getInstance();
    ImageView date;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        branch=(Spinner)findViewById(R.id.spinner3);
        sem=(Spinner)findViewById(R.id.spinner4);
        date= (ImageView) findViewById(R.id.imageView6);
        lv = (ListView) findViewById(R.id.listView3);

        final DatePickerDialog.OnDateSetListener setDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();

            }
        };

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(ViewStudent.this, setDate, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        ImageButton b1 = (ImageButton) findViewById(R.id.take123);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String myFormat = "dd/MM/yyyy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                DBStudent db = new DBStudent(ViewStudent.this);
                List<Student> students = db.allStudentAttendance(sdf.format(myCalendar.getTime()), branch.getSelectedItem().toString(), sem.getSelectedItem().toString());

                ArrayList<String> nameArray = new ArrayList<>();
                ArrayList<String> rollArray = new ArrayList<>();
                ArrayList<String> noteTextArray = new ArrayList<>();
                ArrayList<Integer> presentArray = new ArrayList<>();
                ArrayList<Integer> absentArray = new ArrayList<>();
                ArrayList<Integer> leaveArray = new ArrayList<>();
                ArrayList<Integer> noteArray = new ArrayList<>();

                Boolean t = false;
                for (Student student:students){
                    t = true;
                    nameArray.add(student.get_name());
                    rollArray.add(student.get_roll());
                    presentArray.add(student.get_present());
                    absentArray.add(student.get_absent());
                    leaveArray.add(student.get_leave());
                    noteArray.add(student.get_note());
                    noteTextArray.add(student.get_note_text());
                }

                String[] name = new String[nameArray.size()];
                String[] roll = new String[rollArray.size()];
                String[] noteText = new String[noteTextArray.size()];
                Integer[] present = new Integer[presentArray.size()];
                Integer[] absent = new Integer[absentArray.size()];
                Integer[] leave = new Integer[leaveArray.size()];
                Integer[] note = new Integer[noteArray.size()];

                if (t) {
                    for (int i = 0; i < nameArray.size(); i++) {
                        name[i] = nameArray.get(i);
                        roll[i] = rollArray.get(i);
                        noteText[i] = noteTextArray.get(i);
                        present[i] = presentArray.get(i);
                        absent[i] = absentArray.get(i);
                        leave[i] = leaveArray.get(i);
                        note[i] = noteArray.get(i);
                    }
                }

                lv.setAdapter(new CustomViewStudent(ViewStudent.this, name, roll, noteText, present, absent, leave, note));
            }
        });
    }

    private void updateLabel() {
        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        // TextView tv = (TextView) findViewById(R.id.getText);
        //tv.setText(sdf.format(myCalendar.getTime()));
    }

   /* final String branch = getIntent().getStringExtra("branch");
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
*/
}
