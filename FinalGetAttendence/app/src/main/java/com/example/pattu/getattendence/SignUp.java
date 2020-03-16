package com.example.pattu.getattendence;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

public class SignUp extends AppCompatActivity {

    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final EditText name = (EditText) findViewById(R.id.name);
        final EditText email = (EditText) findViewById(R.id.email);
        final EditText phone = (EditText) findViewById(R.id.phone);
        final EditText userName = (EditText) findViewById(R.id.sign_up_username);
        final EditText password = (EditText) findViewById(R.id.sign_up_password);

        Button submit = (Button) findViewById(R.id.sign_up);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SignUpTask(name.getText().toString(), email.getText().toString(), phone.getText().toString(), userName.getText().toString(), password.getText().toString()).execute("https://a1h1.000webhostapp.com/sign_up.php");
            }
        });
    }

    public void login(View view) {
        Intent i = new Intent(this, LogIn.class);
        startActivity(i);
    }

    private class SignUpTask extends AsyncTask<String, String, String> {

        String name;
        String email;
        String phone;
        String userName;
        String password;

        SignUpTask (String name, String email, String phone, String userName, String password) {
            this.name = name;
            this.email = email;
            this.phone = phone;
            this.userName = userName;
            this.password = password;
        }

        final ProgressDialog progress = new ProgressDialog(SignUp.this);

        @Override
        protected void onPreExecute() {
            progress.setTitle("Loading");
            progress.setMessage("Loading...plss wait!!!");
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
                hm.put("phone", phone);
                hm.put("email", email);
                hm.put("uname", userName);
                hm.put("pwd", password);

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

                int emailError = finalResult.getInt("email_error");

                int userNameError = finalResult.getInt("uname_error");
                int signUpConfirm = finalResult.getInt("signup_confirm");

                progress.dismiss();

                if (emailError == 1) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(SignUp.this);

                    builder.setTitle("Error");
                    builder.setMessage("Email Already Exist!!!");

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    AlertDialog alert = builder.create();
                    alert.show();
                }
                else if (userNameError == 1) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(SignUp.this);

                    builder.setTitle("Error");
                    builder.setMessage("Username Already Exist!!!");

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    AlertDialog alert = builder.create();
                    alert.show();
                }
                else if (signUpConfirm == 1) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(SignUp.this);

                    builder.setTitle("Success");
                    builder.setMessage("Registration Completed!!!");

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent i = new Intent(SignUp.this, LogIn.class);
                            startActivity(i);
                            dialog.dismiss();
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
