
package com.example.rightmeow;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class actualList extends AppCompatActivity {

    private String listName = "";
    private ArrayList<Task> taskList;
    private TaskAdapter taskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actual_list);

        EditText editTextName = findViewById(R.id.editTextText4);
        EditText editTextTask = findViewById(R.id.editTextTask);
        Button buttonAdd = findViewById(R.id.buttonAdd);
        RecyclerView recyclerViewTasks = findViewById(R.id.recyclerViewTasks);

        taskList = new ArrayList<>();
        taskAdapter = new TaskAdapter(taskList);
        recyclerViewTasks.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewTasks.setAdapter(taskAdapter);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listName.equals("")){
                    listName = editTextName.getText().toString().trim();
                }
                String taskDescription = editTextTask.getText().toString().trim();
                if (!taskDescription.isEmpty()) {
                    Task newTask = new Task(taskDescription);
                    taskList.add(newTask);
                    taskAdapter.notifyDataSetChanged();
                    editTextTask.setText("");
                }
            }
        });
    }
    public void onSave(View view){
        ToDoList newList = new ToDoList(listName, taskList);
        fileFormatter ff = new fileFormatter();
        String writingContent = listName + "\n";

        for (int i = 0; i<newList.tasks.size(); i++){
            String taskString = newList.tasks.get(i).getDescription();
            writingContent += taskString;
            if (i < newList.tasks.size()-1) {
                 writingContent += "\n";
            }
        }

        ff.WriteToFile(this, writingContent, "ListFilename");
    }


}
