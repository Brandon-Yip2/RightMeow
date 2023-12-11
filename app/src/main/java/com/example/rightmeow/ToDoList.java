package com.example.rightmeow;

import java.io.Serializable;
import java.util.ArrayList;


public class ToDoList implements Serializable {
    String name;
    ArrayList<Task> tasks = new ArrayList<>();

    public ToDoList(String _name, ArrayList<Task> _tasks){
        name = _name;
        tasks = _tasks;
    }

    public String getName() {
        return name;
    }
}
