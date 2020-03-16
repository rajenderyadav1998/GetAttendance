package com.example.pattu.getattendence;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class ListAttendenceStudent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("test", "test");
        Toast.makeText(this, "Hello", Toast.LENGTH_LONG).show();
        setContentView(R.layout.activity_list_attendence_student);
        Log.d("test", "test");
        FloatingActionButton fab1=(FloatingActionButton)findViewById(R.id.fab12);
        Log.d("test", "test");
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("test", "test1");
                Intent i=new Intent(ListAttendenceStudent.this,ViewStudent.class);
                startActivity(i);
            }
        });
        Log.d("test", "test");
    }

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.view,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.view) {
            Intent intent = new Intent(this, Setting.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }*/
}
