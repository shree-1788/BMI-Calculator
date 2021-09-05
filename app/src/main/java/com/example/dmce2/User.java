package com.example.dmce2;

public class User {
    private String fname,email;

    public User(){}
    public User(String fname,String email)
    {
        this.fname=fname;
        this.email=email;
    }

    public String getFname() {
        return fname;
    }

    public String getEmail() {
        return email;
    }
}
