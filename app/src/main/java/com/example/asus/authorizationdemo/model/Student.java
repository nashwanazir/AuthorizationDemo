package com.example.asus.authorizationdemo.model;

/**
 * Created by Asus on 5/15/2017.
 */

// Student model class to maintain single student as an object.

public class Student {

    int id;
    String name, email, password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
