package com.example.pattu.getattendence;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class ForgotPassword extends AppCompatActivity {


    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("Login", 0);
        final EditText name = (EditText) findViewById(R.id.forgot_name);
        final EditText email = (EditText) findViewById(R.id.forgot_email);
        final EditText phone = (EditText) findViewById(R.id.forgot_phone);
        final EditText userName = (EditText) findViewById(R.id.forgot_username);
        Button login = (Button) findViewById(R.id.forgot_password);

        if(pref.getBoolean("loggedIn", false)) {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ForgotPasswordTask(name.getText().toString(), email.getText().toString(), phone.getText().toString(), userName.getText().toString()).execute("https://a1h1.000webhostapp.com/forgot_password.php");
            }
        });
    }

    private class ForgotPasswordTask extends AsyncTask<String, String, String> {
        String name;
        String email;
        String phone;
        String userName;

        ForgotPasswordTask( String name, String email, String phone, String userName) {
            this.name = name;
            this.email = email;
            this.phone = phone;
            this.userName = userName;
        }

        final ProgressDialog progress = new ProgressDialog(ForgotPassword.this);

        @Override
        protected void onPreExecute() {
            progress.setTitle("Loading");
            progress.setMessage("Wait while loading...");
            progress.setCancelable(false);
            progress.show();
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();

                urlConnection.setRequestMethod("POST");
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);

                HashMap<String, String> hm = new HashMap<>();
                hm.put("name", name);
                hm.put("email", email);
                hm.put("phone", phone);
                hm.put("uname", userName);

                Extra text = new Extra();

                OutputStream os = urlConnection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(text.getPostDataString(hm));

                writer.flush();
                writer.close();
                os.close();
                urlConnection.connect();

                InputStream stream = urlConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                StringBuilder buffer = new StringBuilder();

                String line;
                while((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                return buffer.toString();
            }
            catch (Exception e) {
                Log.e("Url", e.toString());
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                JSONObject finalResult = new JSONObject(s);

                int error           = finalResult.getInt("error");
                String password      = finalResult.getString("pwd");

                progress.dismiss();

                if(error == 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ForgotPassword.this);

                    builder.setTitle("Error");
                    builder.setMessage("Account not Found!!!");

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    AlertDialog alert = builder.create();
                    alert.show();
                }

                else if(error == 1) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ForgotPassword.this);

                    builder.setTitle("Your Password");
                    builder.setMessage(password);

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            Intent i = new Intent(ForgotPassword.this, LogIn.class);
                            startActivity(i);
                        }
                    });

                    AlertDialog alert = builder.create();
                    alert.show();
                }
            }
            catch (Exception e) {
                Log.e("Url", e.toString());
            }
        }
    }
}
