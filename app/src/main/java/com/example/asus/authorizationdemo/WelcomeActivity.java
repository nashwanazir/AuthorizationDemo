package com.example.asus.authorizationdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {

    TextView welcome, logout;
    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // Initialize views
        welcome = (TextView) findViewById(R.id.welcome);
        logout = (TextView) findViewById(R.id.logout);

        session = new Session(WelcomeActivity.this);

        Bundle extras = getIntent().getExtras();
        String name = extras.getString("name");
        welcome.append(" " + name);


        logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                logout();

            }
        });

    }

    private void logout() {

        // set loggedin as false
        session.setLoggedin(false);
        finish();
        Intent i = new Intent(WelcomeActivity.this, LoginActivity.class);
        startActivity(i);
    }
}
