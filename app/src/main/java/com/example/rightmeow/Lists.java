package com.example.rightmeow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Lists extends LightActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lists);

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
            if (resumedList.tasks.size() > 0){
                Log.d("test", "LIST LENGTH RESUMED IS: " + resumedList.tasks.size());
                Log.d("test", resumedList.tasks.get(0).getDescription());
                Log.d("test", String.valueOf(resumedList.tasks.get(0).getCompleted()));
            }

        }
    }

    public void onNewList(View view){
        Intent myIntent=new Intent(getBaseContext(),actualList.class);
        startActivity(myIntent);
    }

}