package com.example.rightmeow;

public class Task {
    private String description;
    public Boolean completed;
    public Task(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

