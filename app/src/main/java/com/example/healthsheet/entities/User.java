package com.example.healthsheet.entities;

import java.util.List;

public class User {
    private int id;
    private String email;
    private String username;
    private String password;

    private List<CalorieLog> caloriesLog;
    public User(String email,String username,String password){

        this.email=email;
        this.password=password;
        this.username=username;
    }
    public User(String email,String password){
        this.email=email;
        this.password=password;
    }
    public User(int id,String username,String password){
        this.id=id;
        this.email=email;
        this.password=password;
        this.username=username;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
