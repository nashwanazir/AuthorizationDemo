package com.example.asus.authorizationdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.authorizationdemo.helper.DatabaseHelper;

public class LoginActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper = new DatabaseHelper(LoginActivity.this);
    EditText email, password;
    Button login;
    TextView link_signup,forgotpass;
    CheckBox rem;
    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize views
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.btnLogin);
        link_signup = (TextView) findViewById(R.id.link_signup);
        forgotpass= (TextView) findViewById(R.id.forgotpass);
        rem= (CheckBox) findViewById(R.id.remcheck);

        session = new Session(LoginActivity.this);

        // if student already logged in, redirect to Welcome Activity
        if (session.loggedin()) {

            String n = session.getName();
            Intent in = new Intent(LoginActivity.this, WelcomeActivity.class);
            in.putExtra("name", n);
            startActivity(in);
            finish();
        }


        // OnClickListener for login button
        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String eml = email.getText().toString();
                String pwd = password.getText().toString();

                // Check if email and password is valid
                if (!validate()) {

                    Toast.makeText(LoginActivity.this, "Login failed! Please try again", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Check if email and password exist in student table
                if (databaseHelper.check(eml, pwd)) {

                    String n = databaseHelper.getName(eml);
                    if (rem.isChecked()) {
                        session.setLoggedin(true);
                        session.setName(n);
                    }

                    Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);
                    intent.putExtra("name",n);
                    startActivity(intent);
                    clear();
                    Toast.makeText(LoginActivity.this, "Student Logged In Successfully!", Toast.LENGTH_SHORT).show();


                } else {
                    Toast.makeText(LoginActivity.this, "No such Student! Please create an account", Toast.LENGTH_SHORT).show();

                }

            }


        });

        // Clicking forgot password will show a dialog
        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this, R.style.AlertDialogTheme);
                builder.setMessage("Please contact help@auth.com for resetting password");
                builder.setCancelable(true);

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        // Link to SignUp Activity
        link_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Start the SignUp Activity
                Intent i = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(i);
            }


        });

    }

    /**
     * Clear all input edit text
     */
    private void clear() {
        email.setText(null);
        password.setText(null);
    }

    /**
     * Validate email and password
     */
    public boolean validate() {
        boolean valid = true;

        String eml = email.getText().toString();
        String pwd = password.getText().toString();

        if (eml.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(eml).matches()) {
            email.setError("enter a valid email address");
            valid = false;
        } else {
            email.setError(null);
        }

        if (pwd.isEmpty() || pwd.length() <= 4) {
            password.setError("enter a valid password");
            valid = false;
        } else {
            password.setError(null);
        }

        return valid;
    }

    @Override
    public void onBackPressed() {
        // disable going back to the WelcomeActivity
        moveTaskToBack(true);
    }
}
