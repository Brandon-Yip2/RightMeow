package com.example.rightmeow;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class fileFormatter {

    void WriteToFile(Context context, String content, String filename) {
        String inputString = content;
        try {
            byte[] byteArray = inputString.getBytes("UTF-8");
            try (FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE)) {
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

    String readFile(Context context, String filename) {
        FileInputStream fis = null;
        try {
            Log.d("test", "Reading new data");
            fis = context.openFileInput(filename);
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

    public static String parseFormattedString(String formattedString, List<String> items, List<String> values) {
        String[] lines = formattedString.split("\n");
        String listnameReturn = "";

        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if (i == 0){
                listnameReturn = line;
            }
            else{
                String[] parts = line.split(" ");
                if (parts.length == 2) {
                    items.add(parts[0]);
                    values.add(parts[1]);
                }
            }

        }
        return listnameReturn;
    }

    public static boolean convertStringToBoolean(String input) {
        if ("0".equals(input)) {
            return false;
        } else if ("1".equals(input)) {
            return true;
        } else {
            throw new IllegalArgumentException("Invalid input. Only '0' and '1' are allowed.");
        }
    }




}
