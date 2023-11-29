package com.example.rightmeow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickLists(View view){
        Intent myIntent = new Intent(getBaseContext(),Lists.class);
        startActivity(myIntent);
    }

    public void onClickSettings(View view){
        Intent myIntent=new Intent(getBaseContext(),Settings.class);
        startActivity(myIntent);

    }

}