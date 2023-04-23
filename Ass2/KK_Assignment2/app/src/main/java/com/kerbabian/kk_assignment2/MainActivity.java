package com.kerbabian.kk_assignment2;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.preference.PreferenceManager;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;



import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {


    private Button RockButt;
    private Button PaperButt;
    private Button ScissorsButt;
    private TextView ResultText;

    private ImageView ComputerImageView;
    private ImageView YouImageView;

    private int YouChoice = 0;
    private int ComputerChoice = 0;
    Random rnd = new Random();
    private int[][] ResultTable;

    private SharedPreferences sharedPref;
    private boolean darkthemeBoolean;
    private boolean savegameBoolean;


    private Timer timerForYouImage = null;


    private void UserChoice(View view){
        switch (view.getId()){
            case R.id.RockButton:
                YouImageView.setImageResource(R.drawable.rock);
                YouChoice = 0;
                break;
            case R.id.PaperButton:
                YouImageView.setImageResource(R.drawable.paper);
                YouChoice = 1;
                break;
            case R.id.ScissorsButton:
                YouImageView.setImageResource(R.drawable.scissors);
                YouChoice = 2;
                break;
        }
    }




    private void ComputerChose(){
        ComputerChoice = rnd.nextInt(3);
        switch (ComputerChoice){
            case 0:
                ComputerImageView.setImageResource(R.drawable.rock);
                break;
            case 1:
                ComputerImageView.setImageResource(R.drawable.paper);
                break;
            case 2:
                ComputerImageView.setImageResource((R.drawable.scissors));
                break;
        }
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {


        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        RockButt = findViewById(R.id.RockButton);
        PaperButt = findViewById(R.id.PaperButton);
        ScissorsButt = findViewById(R.id.ScissorsButton);
        ResultText = findViewById(R.id.ResultTextView);
        ComputerImageView = findViewById(R.id.ComputerImageView);
        YouImageView = findViewById(R.id.YouImageView);



        /*
        * this is a result table that defined according to the all possible result that can be found in this game
        * with giving indexes to ROCK then PAPER then SCISSORS => 0, 1, 2 accordingly
        * we can make a table of the all possible results
        *
        *      user |      rock 0   paper 1    sciss 2
        *      ----  ----------------------------------------------
        *      comp |
        *      ---- |
        *           |
        *   rock  0 |       0           2           1
        *           |
        *   paper 1 |       1           0           2
        *           |
        *   sciss 2 |       2           1           0
        *
        *
        *
        *                   0 = it's a tie.
        *                   1 = User Wins.
        *                   2 = Comp wins.
        *
        *
        *
        * */


        ComputerImageView.setAlpha(0f);
        YouImageView.setAlpha(0f);

        ResultTable = new int[][]{{0, 2, 1}, {1, 0, 2}, {2, 1, 0}};
        ResultText.setText(R.string.resualts);



        View.OnClickListener UserChoicesButtons = new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                ComputerImageView.setAlpha(0f);
                YouImageView.setAlpha(0f);
                UserChoice(view);
                YouImageView.animate().alpha(1f).setDuration(800).setListener(
                        new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                ComputerChose();
                                ComputerImageView.animate().alpha(1f).setDuration(800).setListener(
                                        new AnimatorListenerAdapter() {
                                            @Override
                                            public void onAnimationEnd(Animator animation) {
                                                howIsTheWinner();
                                                //performReset(true);
                                            }
                                        }
                                );

                            }
                        }
                );




            }


        };


        RockButt.setOnClickListener(UserChoicesButtons);
        PaperButt.setOnClickListener(UserChoicesButtons);
        ScissorsButt.setOnClickListener(UserChoicesButtons);

        startService(new Intent(getApplicationContext(), NotificationService.class));

        PreferenceManager.setDefaultValues(this,R.xml.root_preferences,false);
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);



    }

    private void howIsTheWinner(){
        final GameDataSharedApplication application;
        application = (GameDataSharedApplication) getApplication();

        // determine the winner using the all possible results table.
        switch (ResultTable[YouChoice][ComputerChoice]) {
            case 0: {
                ResultText.setText(R.string.Tie);
                application.addRecord(0, 0, 1, ComputerChoice, YouChoice, ResultText.getText().toString());
                break;
            }
            case 1: {
                ResultText.setText(R.string.Win);
                application.addRecord(1, 0, 0, ComputerChoice, YouChoice, ResultText.getText().toString());
                break;
            }
            case 2: {
                ResultText.setText(R.string.Lose);
                application.addRecord(0, 1, 0, ComputerChoice, YouChoice, ResultText.getText().toString());
                break;
            }
        }



    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.r_p_sc_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        boolean ret = true;

        switch (item.getItemId()) {

            case R.id.menu_settings:
                startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                break;
            case R.id.menu_statistics:
                startActivity(new Intent(getApplicationContext(), StateActivity.class));
                break;
            default:
                ret = super.onOptionsItemSelected(item);
                break;
        }

        return ret;
    }

    @Override
    protected void onResume() {
        super.onResume();




        darkthemeBoolean = sharedPref.getBoolean("darkTheme", false);

        if(darkthemeBoolean)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        else
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);



        savegameBoolean = sharedPref.getBoolean("saveGame", false);

        if(savegameBoolean ){
            final GameDataSharedApplication application;
            application = (GameDataSharedApplication) getApplication();
            String values  = application.getSaveGamedata();
            String [] valuesSplited = values.split("-");


            if(!valuesSplited[3].equals("none")) {
                YouImageView.setAlpha(1f);
                ComputerImageView.setAlpha(1f);

                switch (valuesSplited[0]) {

                    case "0":
                        ComputerImageView.setImageResource(R.drawable.rock);
                        break;

                    case "1":
                        ComputerImageView.setImageResource(R.drawable.paper);
                        break;

                    case "2":
                        ComputerImageView.setImageResource((R.drawable.scissors));
                        break;

                }

                Drawable res;
                switch (valuesSplited[1]) {

                    case "0":
                        res = getResources().getDrawable(R.drawable.rock);
                        YouImageView.setImageDrawable(res);
                        break;

                    case "1":
                        res = getResources().getDrawable(R.drawable.paper);
                        YouImageView.setImageDrawable(res);
                        break;

                    case "2":
                        res = getResources().getDrawable(R.drawable.scissors);
                        YouImageView.setImageDrawable(res);
                        break;
                }

                switch (valuesSplited[2]) {

                    case "0":
                        ResultText.setText(R.string.Tie);
                        break;

                    case "1":
                        ResultText.setText(R.string.Win);
                        break;

                    case "2":
                        ResultText.setText(R.string.Lose);
                        break;
                }
            }else
            {
                YouImageView.setAlpha(0f);
                ComputerImageView.setAlpha(0f);
                ResultText.setText(R.string.resualts);
            }


        }




    }


    @Override
    protected void onStop() {
        startService(new Intent(getApplicationContext(), NotificationService.class));

        SharedPreferences.Editor ed = sharedPref.edit();

        ed.putBoolean("saveGame", sharedPref.getBoolean("saveGame", false));
        ed.putBoolean("darkTheme", sharedPref.getBoolean("darkTheme", false));

        ed.commit();




        super.onStop();
    }


    @RequiresApi(26)
    private void send(){

    }
}