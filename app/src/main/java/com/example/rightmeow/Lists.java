package com.example.rightmeow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Lists extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lists);
    }

    public void onNewList(View view){
        Intent myIntent=new Intent(getBaseContext(),actualList.class);
        startActivity(myIntent);
    }

}