package com.example.rightmeow;

import java.util.ArrayList;


public class ToDoList {
    String name;
    ArrayList<Task> tasks = new ArrayList<>();

    public ToDoList(String _name, ArrayList<Task> _tasks){
        name = _name;
        tasks = _tasks;
    }

}
