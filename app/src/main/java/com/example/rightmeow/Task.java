package com.example.rightmeow;

public class Task {
    private String description;
    public Boolean completed;
    public Task(String description, Boolean completed) {
        this.description = description;
        this.completed = completed;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getCompleted(){return completed;}
}

