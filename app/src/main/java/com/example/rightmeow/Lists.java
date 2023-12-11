package com.example.rightmeow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Lists extends AppCompatActivity {
    private ListAdapter listAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lists);

        loadLists();
    }

    //Helper method for onResume
    private boolean fileExists(String fileName) {
        File file = new File(getFilesDir(), fileName);
        return file.exists();
    }
    @Override
    protected void onResume(){
        super.onResume();
        //here

        loadLists();
    }

    public void loadLists(){
        if (fileExists("ListFilename")){

            fileFormatter ff = new fileFormatter();
            String reading = ff.readFile(this, "ListFilename");
            Log.d("test", "READING FROM FILE");
            Log.d("test", reading);
            List<String> descriptions = new ArrayList<>();
            List<String> booleans = new ArrayList<>();
            String listname = "";

            listname = ff.parseFormattedString(reading, descriptions, booleans);

            Log.d("test", "description length is: " + descriptions.size());

            ArrayList<Task> tasks = new ArrayList<>();
            for (int i = 0; i < descriptions.size(); i++) {
                Task newTask = new Task(descriptions.get(i), ff.convertStringToBoolean(booleans.get(i)));
                tasks.add(newTask);
            }

            ToDoList resumedList = new ToDoList(listname, tasks);

            RecyclerView recyclerViewLists = findViewById(R.id.recyclerViewLists);

            ListAdapt listAdapt = new ListAdapt(resumedList);
            recyclerViewLists.setLayoutManager(new LinearLayoutManager(this));
            recyclerViewLists.setAdapter(listAdapt);
            listAdapt.notifyDataSetChanged();
        }
    }


    public void onNewList(View view){
        Intent myIntent=new Intent(getBaseContext(),actualList.class);
        startActivity(myIntent);
    }


}