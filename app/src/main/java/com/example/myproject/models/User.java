package com.example.myproject.models;
public class User {
    private String id;
    private String lastName;
    private String name;
    private String date;
    private String health;
    private String gender;

    public User() {
    }

    public User(String id, String lastName, String name, String date, String health, String gender) {
        this.id = id;
        this.lastName = lastName;
        this.name = name;
        this.date = date;
        this.health = health;
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
