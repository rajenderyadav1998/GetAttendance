package com.example.pattu.getattendence;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AddStudent extends AppCompatActivity {
    Button Submit;
    EditText name;
    Spinner class1;
    EditText roll;
    Spinner subject;
    EditText phone;
    EditText email;

    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        Submit = (Button) findViewById(R.id.button);

        name = (EditText) findViewById(R.id.editText);

        class1 = (Spinner) findViewById(R.id.spinner);
        roll = (EditText) findViewById(R.id.editText7);
        subject = (Spinner) findViewById(R.id.spinner2);
        phone = (EditText) findViewById(R.id.editText9);
        email = (EditText) findViewById(R.id.editText10);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBStudent db = new DBStudent(AddStudent.this);
                db.addStudent(name.getText().toString() , class1.getSelectedItem().toString(), roll.getText().toString(), subject.getSelectedItem().toString(), phone.getText().toString(), email.getText().toString());

                startActivity(new Intent(AddStudent.this, MainActivity.class));
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.raj1, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(AddStudent.this, MainActivity.class);
        startActivity(i);

    }
}
