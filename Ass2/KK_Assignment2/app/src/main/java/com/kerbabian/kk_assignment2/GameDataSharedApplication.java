package com.kerbabian.kk_assignment2;

import android.app.Application;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Hashtable;
import java.util.Map;

public class GameDataSharedApplication extends Application
{
    private static final String DB_NAME = "db_tax_stats";
    private static final int DB_VERSION = 1;

    private SQLiteOpenHelper helper;
    private final Map<String, Integer> Resultsmap = new Hashtable<>();

    @Override
    public void onCreate() {



        helper = new SQLiteOpenHelper(this, DB_NAME, null, DB_VERSION) {
            @Override
            public void onCreate(SQLiteDatabase sqLiteDatabase) {

                sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS tbl_stats(" +
                        "win INTEGER, lost INTEGER, tile INTEGER, compchosice INTEGER, youchoice INTEGER, result INTEGER)");

            }

            @Override
            public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
                // No-op
            }
        };

        super.onCreate();

    }



    public void addRecord(int w, int l, int t, int comp, int you, String res) {
        Resultsmap.put("It's a Tie!",0);
        Resultsmap.put("You Win!", 1);
        Resultsmap.put("The Computer Wins!", 2);

        int y = 0;
        y = Resultsmap.get(res);

        SQLiteDatabase db = helper.getWritableDatabase();

        db.execSQL("INSERT INTO tbl_stats (win, lost, tile, compchosice, youchoice, result)"
                + "VALUES (" + w + ", " + l + ", " + t + ", " + comp + ", " + you + ", " + y + ")");
    }


    public String getLastRecord() {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tbl_stats", null);
        String ret;

        if(cursor.moveToLast()) {
            String w = String.valueOf(cursor.getInt(0));
            String l = String.valueOf(cursor.getInt(1));
            String t = String.valueOf(cursor.getInt(2));
            ret = w + "-" + l + "-" + t;
            cursor.close();
        }else{
            ret = "0-0-0";
        }

        return(ret);
    }

    public String getAllTimeRecords() {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT SUM(win) AS swin, SUM(lost) AS slose, SUM(tile) AS stile FROM tbl_stats", null);
        String ret;

        if(cursor.moveToFirst()) {
            String w = String.valueOf(cursor.getInt(0));
            String l = String.valueOf(cursor.getInt(1));
            String t = String.valueOf(cursor.getInt(2));
            ret = w + "-" + l + "-" + t;
            cursor.close();
        }else{
            ret = "0-0-0";
        }
        return(ret);
    }

/*
    public void saveGameData(int com, int u, String res){

        Resultsmap.put("It's a Tie!",0);
        Resultsmap.put("You Win!", 1);
        Resultsmap.put("The Computer Wins!", 2);

        int y = 0;
        SQLiteDatabase db = helper.getWritableDatabase();
        try{
            y = Resultsmap.get(res);
            db.execSQL("INSERT INTO tbl_saveGameData (comp, you, result) "
                    + "VALUES (" + com + ", " + u + ", " + y  + ")");
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }


    }

*/
    public String getSaveGamedata() {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tbl_stats", null);
        String ret;


        if(cursor.moveToLast()) {
            String w = String.valueOf(cursor.getInt(3));
            String l = String.valueOf(cursor.getInt(4));
            String t = String.valueOf(cursor.getInt(5));
            ret = w + "-" + l + "-" + t + "-hasValues";
            cursor.close();
        }else{
            ret = "0-0-0-none";
        }

        return(ret);




    }





    public void resetTableStats() {
        SQLiteDatabase db = helper.getWritableDatabase();

        db.execSQL("DELETE FROM tbl_stats;");
    }
}

