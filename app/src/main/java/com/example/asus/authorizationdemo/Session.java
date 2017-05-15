package com.example.asus.authorizationdemo;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Asus on 5/15/2017.
 */

/**
 * Session class for student session management
 */
public class Session {

    SharedPreferences loginprefs;
    SharedPreferences.Editor editor;
    Context c;

    public Session(Context c) {
        this.c = c;
        loginprefs = c.getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        editor = loginprefs.edit();
    }

    public void setName(String name) {
        editor.putString("name", name);
        editor.commit();
    }

    public void setEmail(String email) {
        editor.putString("email", email);
        editor.commit();
    }

    public void setPass(String pass) {
        editor.putString("pass", pass);
        editor.commit();
    }

    public String getName() {
        String n = loginprefs.getString("name", null);
        return n;
    }

    public String getEmail() {
        String e = loginprefs.getString("email", null);
        return e;
    }

    public String getPass() {
        String p = loginprefs.getString("pass", null);
        return p;
    }

    public void setLoggedin(boolean logggedin) {
        editor.putBoolean("loggedIn", logggedin);
        editor.commit();
    }

    public boolean loggedin() {
        return loginprefs.getBoolean("loggedIn", false);
    }
}
