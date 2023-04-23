package com.kerbabian.timero.TimeroDataBase;

import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.kerbabian.timero.TimeroDataBase.entities.User;

import java.util.LinkedList;
import java.util.List;


public class TimeroDataBase extends Application {
    private static final String DB_NAME = "db_timero";
    private static final int DB_VERSION = 1;

    private SQLiteOpenHelper helper;


    private static  String tbl_users = "tbl_users";
    private static  String tbl_timer = "tbl_timer";
    private static  String tbl_settings = "tbl_settings";


    private static  String userID = "userID";
    private static  String username = "username";
    private static  String password = "password";
    private static  String taskID = "taskID";
    private static  String taskLable = "taskLable";
    private static  String taskTime = "taskTime";
    private static  String taskActive = "taskActive";
    private static  String settingsID = "settingsID";
    private static  String settingsDarkTheme = "settingsDarkTheme";
    private static  String settingsBackroundRun = "settingsBackroundRun";
    private static  String settingsTimerNotification = "settingsTimerNotification";
    private static  String settingsAlarmSound = "settingsAlarmSound";
    private static  String settingsVibration = "settingsVibration";
    private static  String settingsAlarmVolume = "settingsAlarmVolume";
    private static  String settingsFlashLight = "settingsFlashLight";



    @Override
    public void onCreate() {

        helper = new SQLiteOpenHelper(this, DB_NAME, null, DB_VERSION) {
            @Override
            public void onCreate(SQLiteDatabase sqLiteDatabase) {
                sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS tbl_users("
                        + userID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + username + " TEXT, "
                        + password + " TEXT )");

                sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS tbl_task("
                        + taskLable + " TEXT, "
                        + taskTime + " TEXT, "
                        + taskActive + " INTEGER, "
                        + userID + " NUMBER REFERENCES tbl_users(userID))");

                sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS tbl_settings("
                        + settingsDarkTheme + " INTEGER, "
                        + settingsBackroundRun + " INTEGER, "
                        + settingsTimerNotification + " INTEGER, "
                        + settingsAlarmSound + " INTEGER, "
                        + settingsAlarmVolume + " INTEGER, "
                        + settingsVibration + " INTEGER, "
                        + settingsFlashLight + " INTEGER, "
                        + userID + " NUMBER REFERENCES tbl_users(userID))");
            }

            @Override
            public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
                // No-op
            }
        };

        super.onCreate();
    }

    public Boolean createAccount(User user) {
        String Sname;

        boolean exist = false;
        try{
            SQLiteDatabase db = helper.getWritableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM " + tbl_users, null);
            if(cursor.moveToFirst()){
                do{
                    Sname = cursor.getString(1);
                    if(Sname.equals(user.getName())){
                        exist = true;
                        break;
                    }
                }while (cursor.moveToNext());

                cursor.close();

                if (!exist) {
                    ContentValues contectVal = new ContentValues();
                    contectVal.put(username, user.getName());
                    contectVal.put(password, user.getPassword());
                    long rowId = db.insert(tbl_users, null, contectVal);
                    if(rowId > 0){
                        ContentValues contactValSetiings = new ContentValues();
                        contactValSetiings.put(settingsDarkTheme, 0);
                        contactValSetiings.put( settingsBackroundRun , 0);
                        contactValSetiings.put(settingsTimerNotification, 0);
                        contactValSetiings.put( settingsAlarmSound , 0);
                        contactValSetiings.put( settingsAlarmVolume , 0);
                        contactValSetiings.put( settingsVibration , 0);
                        contactValSetiings.put( settingsFlashLight , 0);
                        contactValSetiings.put(  userID  , rowId);
                        db.insert(tbl_settings, null, contactValSetiings);
                        return rowId > 0;
                    }

                }
                else{
                    return false;
                }
            }



            ContentValues contectVal = new ContentValues();
            contectVal.put(username, user.getName());
            contectVal.put(password, user.getPassword());
            long rowId = db.insert(tbl_users, null, contectVal);
            if(rowId > 0){
                ContentValues contactValSetiings = new ContentValues();
                contactValSetiings.put(settingsDarkTheme, 0);
                contactValSetiings.put( settingsBackroundRun , 0);
                contactValSetiings.put(settingsTimerNotification, 0);
                contactValSetiings.put( settingsAlarmSound , 0);
                contactValSetiings.put( settingsAlarmVolume , 0);
                contactValSetiings.put( settingsVibration , 0);
                contactValSetiings.put( settingsFlashLight , 0);
                contactValSetiings.put(  userID  , rowId);
                db.insert(tbl_settings, null, contactValSetiings);
                return rowId > 0;
            }


            return false;



        }catch (Exception e){
            Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_LONG).show();
            return false;
        }

    }




    public Object[] getUserSettings(int userIdd) {
        Object[] ob = new Object[7];
        int usId;
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + tbl_settings, null);
        if (cursor.moveToFirst()) {
            do {
                usId = cursor.getInt(7);
                if (usId  == userIdd) {
                    ob[0] = cursor.getInt(0);
                    ob[1] = cursor.getInt(1);
                    ob[2] = cursor.getInt(2);
                    ob[3] = cursor.getInt(3);
                    ob[4] = cursor.getInt(4);
                    ob[5] = cursor.getInt(5);
                    ob[6] = cursor.getInt(6);
                    break;
                }
            } while (cursor.moveToNext());

            cursor.close();


            return ob;
        }
        return ob;
    }


    public boolean setUserSettings(int userIdd, int [] values ) {


        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues contactValSetiings = new ContentValues();
        contactValSetiings.put(settingsDarkTheme, values[0]);
        contactValSetiings.put( settingsBackroundRun , values[1]);
        contactValSetiings.put(settingsTimerNotification, values[2]);
        contactValSetiings.put( settingsAlarmSound , values[3]);
        contactValSetiings.put( settingsAlarmVolume , values[4]);
        contactValSetiings.put( settingsVibration , values[5]);
        contactValSetiings.put( settingsFlashLight , values[6]);
        contactValSetiings.put(  userID  , userIdd);

        return db.update(tbl_settings,  contactValSetiings, userID + " = " + String.valueOf(userIdd), null) > 0;
    }



    public int loginUser(User user) {

        int ID;
        String Sname;
        String Spass;
        try{
            SQLiteDatabase db = helper.getReadableDatabase();

            Cursor cursor = db.rawQuery("SELECT * FROM " + tbl_users, null);

            if(cursor.moveToFirst()){
                ID = -1;
                do{

                    Sname = cursor.getString(1);
                    Spass = cursor.getString(2);
                    if(Sname.equals(user.getName()) && Spass.equals(user.getPassword())){
                        ID = cursor.getInt(0);
                        break;
                    }


                }while (cursor.moveToNext());

                cursor.close();
                return(ID);
            }
            else {
                return(-1);
            }

        }catch (Exception e){

            Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_LONG).show();
            return -2;
        }




    }




}


