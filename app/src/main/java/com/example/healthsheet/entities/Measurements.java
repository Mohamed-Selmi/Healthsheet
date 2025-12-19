package com.example.healthsheet.entities;

public class Measurements {

    private int id;
    private User user;
    private int weight;
    private String date;
    private int height;
    private int waist;
    private int age;

    private int hip;

    public Measurements(User user, int weight, String date, int height, int waist, int age, int hip) {
        this.user = user;
        this.weight = weight;
        this.date = date;
        this.height = height;
        this.waist = waist;
        this.age = age;
        this.hip = hip;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWaist() {
        return waist;
    }

    public void setWaist(int waist) {
        this.waist = waist;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHip() {
        return hip;
    }

    public void setHip(int hip) {
        this.hip = hip;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
