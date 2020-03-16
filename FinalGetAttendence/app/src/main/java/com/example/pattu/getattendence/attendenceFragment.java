package com.example.pattu.getattendence;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.Calendar;

import java.text.SimpleDateFormat;
import java.util.Locale;


@RequiresApi(api = Build.VERSION_CODES.N)
public class attendenceFragment extends Fragment {

    View rootView;
    Calendar myCalendar = java.util.Calendar.getInstance();
    ImageView date;
    TextView tv;
    Spinner branch,sem;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView= inflater.inflate(R.layout.attendence,container,false);

        Button take=(Button)rootView.findViewById(R.id.button);
        branch=(Spinner)rootView.findViewById(R.id.spinner);
        sem=(Spinner)rootView.findViewById(R.id.spinner2);
        take.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), CustomAttendence.class);
                i.putExtra("branch",branch.getSelectedItem().toString());
                i.putExtra("sem",sem.getSelectedItem().toString());
                String myFormat = "dd/MM/yyyy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                i.putExtra("date",sdf.format(myCalendar.getTime()));

                startActivity(i);
            }
        });

        date = (ImageView)rootView.findViewById(R.id.imageView5);


        final DatePickerDialog.OnDateSetListener setDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
                String day = String.valueOf(dayOfMonth);
            }
        };



        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getContext(), setDate, myCalendar.get(java.util.Calendar.YEAR), myCalendar.get(java.util.Calendar.MONTH), myCalendar.get(java.util.Calendar.DAY_OF_MONTH)).show();
            }
        });
        return rootView;


    }
    private void updateLabel() {
        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        tv=(TextView)rootView.findViewById(R.id.getDate);
        tv.setText(sdf.format(myCalendar.getTime()));
    }



   /* public void add_student(View view) {
        startActivity(new Intent(getContext(), AddStudent.class));
    }*/

}







