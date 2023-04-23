package com.kerbabian.timero.ui.Settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import com.kerbabian.timero.Iprefernace;
import com.kerbabian.timero.R;
import com.kerbabian.timero.TimeroDataBase.TimeroDataBase;

public class SettingsFragment extends PreferenceFragmentCompat{

    private SharedPreferences sharedPref;
    private Iprefernace myPref;

    String settingsDarkTheme = "xmlDarkTheme";
    String settingsBackroundRun = "xmlBackground";
    String settingsTimerNotification = "xmlNotification";
    String settingsAlarmSound = "xmlAlarmSound";
    String settingsAlarmVolume = "xmlAlarmVolum";
    String settingsVibration = "xmlVibration";
    String settingsFlashLight = "xmlFlashLight";



    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {

        setPreferencesFromResource(R.xml.root_preferences, rootKey);
    }



    @Override
    public void onStop() {
        super.onStop();



    }

    @Override
    public void onResume() {
        super.onResume();


    }






    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        myPref = (Iprefernace) context;

        if(myPref.SomoneLoggedIn()){

            AppCompatActivity activity = (AppCompatActivity) getActivity();
            final TimeroDataBase applicationDB;
            applicationDB = (TimeroDataBase) activity.getApplicationContext();

            Object [] settingsOfUser = applicationDB.getUserSettings(myPref.getUserID());

            sharedPref = PreferenceManager.getDefaultSharedPreferences(activity.getApplicationContext());


            sharedPref.edit().putBoolean(settingsDarkTheme, ((int) settingsOfUser[0]) == 0 ? false : true ).commit();
            sharedPref.edit().putBoolean(settingsBackroundRun, ((int) settingsOfUser[1]) == 0 ? false : true ).commit();
            sharedPref.edit().putBoolean(settingsTimerNotification, ((int) settingsOfUser[2]) == 0 ? false : true ).commit();
            sharedPref.edit().putBoolean(settingsAlarmSound, ((int) settingsOfUser[3]) == 0 ? false : true ).commit();
            sharedPref.edit().putBoolean(settingsVibration, ((int) settingsOfUser[4]) == 0 ? false : true ).commit();
            sharedPref.edit().putInt(settingsAlarmVolume, (int)settingsOfUser[5] ).commit();
            sharedPref.edit().putBoolean(settingsFlashLight, ((int) settingsOfUser[6]) == 0 ? false : true).commit();


        }


    }

    @Override
    public void onDetach() {
        super.onDetach();

        if(myPref.SomoneLoggedIn()){


            AppCompatActivity activity = (AppCompatActivity) getActivity();
            final TimeroDataBase applicationDB;
            applicationDB = (TimeroDataBase) activity.getApplicationContext();

            int[] values = new int[7];
            values[0] = sharedPref.getBoolean(settingsDarkTheme, false) == true ? 1 : 0;
            values[1] = sharedPref.getBoolean(settingsBackroundRun, false) == true ? 1 : 0;
            values[2] = sharedPref.getBoolean(settingsTimerNotification, false) == true ? 1 : 0;
            values[3] = sharedPref.getBoolean(settingsAlarmSound, false) == true ? 1 : 0;
            values[4] = sharedPref.getInt(settingsAlarmVolume, 0);
            values[5] = sharedPref.getBoolean(settingsVibration, false) == true ? 1 : 0;
            values[6] = sharedPref.getBoolean(settingsFlashLight, false) == true ? 1 : 0;


            Boolean dis = applicationDB.setUserSettings(myPref.getUserID(), values);

        }



        myPref = null;
    }


}