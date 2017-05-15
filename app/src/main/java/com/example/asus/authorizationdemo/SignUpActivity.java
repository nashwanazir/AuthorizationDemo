package com.example.asus.authorizationdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.authorizationdemo.helper.DatabaseHelper;
import com.example.asus.authorizationdemo.model.Student;

public class SignUpActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper=new DatabaseHelper(SignUpActivity.this);
    EditText name,email,password;
    Button signup;
    TextView link_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name= (EditText) findViewById(R.id.name);
        email= (EditText) findViewById(R.id.reg_email);
        password= (EditText) findViewById(R.id.reg_password);
        signup= (Button) findViewById(R.id.btnSignup);
        link_login= (TextView) findViewById(R.id.link_login);




        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // check if email exist in student table
                if (!databaseHelper.check(email.getText().toString())) {

                    String nme = name.getText().toString();
                    String eml = email.getText().toString();
                    String pwd = password.getText().toString();

                    Student s = new Student();
                    s.setName(nme);
                    s.setEmail(eml);
                    s.setPassword(pwd);
                    databaseHelper.addStudent(s);

                    Toast.makeText(SignUpActivity.this, "Student Registered Successfully!", Toast.LENGTH_SHORT).show();
                    clear();
                    finish();
                }

                else
                {
                    Toast.makeText(SignUpActivity.this, "Email already exists! Try again", Toast.LENGTH_SHORT).show();

                }
            }
        });

        link_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Start Login Activity
                Intent i=new Intent(SignUpActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });

    }

    /**
     * This method is to empty all input edit text
     */
    private void clear() {
        name.setText(null);
        email.setText(null);
        password.setText(null);

    }
}

