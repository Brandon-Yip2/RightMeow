package com.example.rightmeow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    //**********************Light sensor code******************
    private SensorManager sensorManager;
    private Sensor lightSensor;
    //private LightSensorManager LSM;


    @Override
    public void onSensorChanged(SensorEvent event) {
        /*
        if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
            float lux = event.values[0];

            int light_level = LSM.getLightLevel(lux);
            LSM.setBrightness(this, light_level);

        }

        */
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do nothing for now
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //LSM = new LightSensorManager();

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager != null) {
            lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
            if (lightSensor != null) {
                sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
            }
        }


        //Creating a file with contents
        fileFormatter ff = new fileFormatter();
        List<String> testListNames = Arrays.asList("Milk", "Eggs", "Pizza");
        List<String> testListBooleans = Arrays.asList("0", "1", "0");
        String filename = "listfile";

        String listContent = ff.createFormattedString(testListNames, testListBooleans);
        Log.d("test", listContent);

        File file = new File(this.getFilesDir(), filename);

        ff.WriteToFile(this, listContent, filename);

        String recieved = ff.readFile(this, filename);

        List<String> itemsRecieved = new ArrayList<>();
        List<String> booleansRecieved = new ArrayList<>();
        ff.parseFormattedString(recieved, itemsRecieved, booleansRecieved);

        Log.d("test", itemsRecieved.get(1));

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



