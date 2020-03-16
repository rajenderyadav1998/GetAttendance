package com.example.pattu.getattendence;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
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

public class LogIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("Login", 0);
        final EditText userName = (EditText) findViewById(R.id.username);
        final EditText password = (EditText) findViewById(R.id.password);
        Button login = (Button) findViewById(R.id.login);

        if(pref.getBoolean("loggedIn", false)) {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CheckNetwork.isInternetAvailable(getApplicationContext())) {
                    new LogInTask(userName.getText().toString(), password.getText().toString()).execute("https://a1h1.000webhostapp.com/login.php");
                }
                    else{
                        Snackbar.make(v, "Oops! No Internet Connection", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    }
                }

        });
    }

    public void signUp(View view) {
        Intent i = new Intent(this, SignUp.class);
        startActivity(i);
    }

    public void forgotPassword(View view) {
        Intent i = new Intent(this, ForgotPassword.class);
        startActivity(i);
    }

    private class LogInTask extends AsyncTask<String, String, String> {
        String userName;
        String password;

        LogInTask(String userName, String password) {
            this.userName = userName;
            this.password = password;
        }

        final ProgressDialog progress = new ProgressDialog(LogIn.this);

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

            SharedPreferences sharedpreferences;

            try {
                String MyPREFERENCES = "Login" ;
                String id       = "id";
                String name     = "name";
                String phone    = "phone";
                String email    = "email";
                String userName = "userName";

                sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                JSONObject finalResult = new JSONObject(s);

                int error           = finalResult.getInt("error");
                String idValue      = finalResult.getString("id");
                String nameValue    = finalResult.getString("name");
                String emailValue   = finalResult.getString("email");
                String phoneValue   = finalResult.getString("phone");
                String userNameValue= finalResult.getString("uname");

                progress.dismiss();

                if(error == 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(LogIn.this);

                    builder.setTitle("Error");
                    builder.setMessage("Either User Name or Password is Wrong");

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
                    SharedPreferences.Editor editor = sharedpreferences.edit();

                    editor.putBoolean("loggedIn", true);
                    editor.putString(id, idValue);
                    editor.putString(name, nameValue);
                    editor.putString(phone, phoneValue);
                    editor.putString(email, emailValue);
                    editor.putString(userName, userNameValue);
                    editor.apply();

                    Intent i = new Intent(LogIn.this, MainActivity.class);
                    startActivity(i);
                }
            }
            catch (Exception e) {
                Log.e("Url", e.toString());
            }
        }
    }
}
