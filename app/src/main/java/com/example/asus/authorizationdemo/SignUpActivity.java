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

    DatabaseHelper databaseHelper = new DatabaseHelper(SignUpActivity.this);
    EditText name, email, password, con_password;
    Button signup;
    TextView link_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Initialize views
        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.reg_email);
        password = (EditText) findViewById(R.id.reg_password);
        signup = (Button) findViewById(R.id.btnSignup);
        link_login = (TextView) findViewById(R.id.link_login);
        con_password = (EditText) findViewById(R.id.con_password);


        // OnClickListener for signup button
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Check if email and password is valid
                if (!validate()) {
                    return;
                }

                // Check if email exist in student table
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

                } else {
                    Toast.makeText(SignUpActivity.this, "Email already exists! Try again", Toast.LENGTH_SHORT).show();

                }
            }
        });

        // Link to Login Activity
        link_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Start Login Activity
                Intent i = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

    }

    /**
     * Clear all input edit text
     */
    private void clear() {
        name.setText(null);
        email.setText(null);
        password.setText(null);

    }

    /**
     * Validate email and password
     */
    public boolean validate() {
        boolean valid = true;

        String nme = name.getText().toString();
        String eml = email.getText().toString();
        String pwd = password.getText().toString();
        String confirm = con_password.getText().toString();

        if (nme.isEmpty()) {
            name.setError("enter a valid name");
            valid = false;
        } else {
            name.setError(null);
        }

        if (eml.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(eml).matches()) {
            email.setError("enter a valid email address");
            valid = false;
        } else {
            email.setError(null);
        }

        if (pwd.isEmpty() || pwd.length() <= 4) {
            password.setError("too short");
            valid = false;
        } else if (!pwd.equals(confirm)) {
            con_password.setError("password doesnt match");
            valid = false;
        } else {
            password.setError(null);
        }

        return valid;
    }
}

