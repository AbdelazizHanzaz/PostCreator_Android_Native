package com.example.postcreator.pojo;

public class Task {
    private int id;
    private String name;

    public Task(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
