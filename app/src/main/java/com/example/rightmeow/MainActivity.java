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
    private static final float LIGHT_THRESHOLD = 50.0f;



    // Function to convert lists of strings and integers to a formatted string
    public static String createFormattedString(List<String> items, List<String> values) {
        if (items.size() != values.size()) {
            throw new IllegalArgumentException("Lists must have the same size");
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < items.size(); i++) {
            result.append(items.get(i)).append(" ").append(values.get(i)).append("\n");
        }
        return result.toString();
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_LIGHT) {

            //current light level in SI lex units
            float lightValue = event.values[0];

            //Conditions to change brightness
            if (lightValue < LIGHT_THRESHOLD){

            }

        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do nothing for now
    }

    //*********************Parse + file management code************************
    // Function to parse a formatted string into lists of items and values
    public static void parseFormattedString(String formattedString, List<String> items, List<String> values) {
        String[] lines = formattedString.split("\n");

        for (String line : lines) {
            String[] parts = line.split(" ");

            if (parts.length == 2) {
                items.add(parts[0]);
                values.add(parts[1]);
            }
        }
    }
    void WriteToFile(String content, String filename) {
        String inputString = content;
        try {
            byte[] byteArray = inputString.getBytes("UTF-8");
            try (FileOutputStream fos = this.openFileOutput(filename, Context.MODE_PRIVATE)) {
                fos.write(byteArray);
                Log.d("test", "Writing new data");

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
    String readFile(String filename) {
        FileInputStream fis = null;
        try {
            Log.d("test", "Reading new data");
            fis = this.openFileInput(filename);
            InputStreamReader inputStreamReader = new InputStreamReader(fis, StandardCharsets.UTF_8);
            StringBuilder stringBuilder = new StringBuilder();

            try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
                String line = reader.readLine();
                while (line != null) {
                    stringBuilder.append(line).append('\n');
                    line = reader.readLine();
                }
            }

            String contents = stringBuilder.toString();
            Log.d("test", contents);
            return contents;

        } catch (FileNotFoundException e) {
            // Handle FileNotFoundException
            Log.e("test", "File not found: " + filename);
            // Optionally, you might throw a specific runtime exception if needed.
            // throw new YourCustomRuntimeException("File not found: " + filename, e);

        } catch (IOException e) {
            // Handle any other I/O-related issues
            Log.e("test", "Error reading file: " + filename, e);
            // Optionally, you might throw a specific runtime exception if needed.
            // throw new YourCustomRuntimeException("Error reading file: " + filename, e);

        } finally {
            // Close the FileInputStream in the finally block to ensure it's closed regardless of exceptions.
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    Log.e("test", "Error closing FileInputStream", e);
                }
            }
        }

        return "Nothing Recieved";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creating a sensor for light
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager != null) {
            lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
            if (lightSensor != null) {
                sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
            }
        }

        //Creating a file with contents
        List<String> testListNames = Arrays.asList("Milk", "Eggs", "Pizza");
        List<String> testListBooleans = Arrays.asList("0", "1", "0");
        String filename = "listfile";

        String listContent = createFormattedString(testListNames, testListBooleans);
        Log.d("test", listContent);


        File file = new File(this.getFilesDir(), filename);

        WriteToFile(listContent, filename);

        String recieved = readFile(filename);

        List<String> itemsRecieved = null;
        List<String> booleansRecieved = null;
        parseFormattedString(recieved, itemsRecieved, booleansRecieved);

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



