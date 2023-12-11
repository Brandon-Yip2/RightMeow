package com.example.rightmeow;
import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import android.view.WindowManager;

import androidx.core.content.ContextCompat;
public class LightSensorManager {

    // ID for the permission request
    private static final int PERMISSION_REQUEST_WRITE_SETTINGS = 100;
    private static final float SUNNY_LIGHT_LUX = 7000;
    private static final float OVERCAST_LIGHT_LUX = 1000;
    private static final float INDOORS_HIGH_LIGHT_LUX = 100;
    private static final float INDOORS_MEDIUM_LIGHT_LUX = 50;
    private static final float INDOORS_LOW_LIGHT_LUX = 10;
    private static final float INDOORS_VERYLOW_LIGHT_LUX = 1;

    public int getLightLevel(float lux){

        //Conditions to change brightness
        if (lux >= SUNNY_LIGHT_LUX){
            return 255;
        }
        else if (lux < SUNNY_LIGHT_LUX && lux >= OVERCAST_LIGHT_LUX){
            return 210;
        }
        else if (lux <OVERCAST_LIGHT_LUX && lux >= INDOORS_HIGH_LIGHT_LUX){
            return 170;
        }
        else if (lux < INDOORS_HIGH_LIGHT_LUX && lux >= INDOORS_MEDIUM_LIGHT_LUX){
            return 128;
        }
        else if (lux < INDOORS_MEDIUM_LIGHT_LUX && lux >= INDOORS_LOW_LIGHT_LUX){
            return 85;
        }
        else if (lux < INDOORS_LOW_LIGHT_LUX && lux >= INDOORS_VERYLOW_LIGHT_LUX){
            return 42;
        }
        return -1;
    }

    public void setBrightness(Context context, int brightnessValue) {

        try{
            Settings.System.putInt(context.getContentResolver(),
                    Settings.System.SCREEN_BRIGHTNESS_MODE,
                    Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);

            brightnessValue = Settings.System.getInt(context.getContentResolver(),
                    Settings.System.SCREEN_BRIGHTNESS);
        }
        catch(Settings.SettingNotFoundException e){
            Log.e("Error", "Cannot access system brightness");
            e.printStackTrace();
        }

    }

}
