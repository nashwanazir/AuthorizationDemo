package com.example.asus.authorizationdemo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.authorizationdemo.helper.DatabaseHelper;

public class LoginActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper=new DatabaseHelper(LoginActivity.this);
    EditText email,password;
    Button login;
    TextView link_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email= (EditText) findViewById(R.id.email);
        password= (EditText) findViewById(R.id.password);
        login= (Button) findViewById(R.id.btnLogin);
        link_signup= (TextView) findViewById(R.id.link_signup);


        // **************************************************
        // OnClickListener methods
        // **************************************************

        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String eml = email.getText().toString();
                String pwd = password.getText().toString();
                if (databaseHelper.check(eml, pwd)) {


                    Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);
                    startActivity(intent);
                    clear();
                    Toast.makeText(LoginActivity.this, "Student Logged In Successfully!", Toast.LENGTH_SHORT).show();


                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();

                }

            }


        });

        link_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Start the SignUp Activity
                Intent i = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(i);
            }


        });

    }

    private void clear() {
        email.setText(null);
        password.setText(null);
    }
}
