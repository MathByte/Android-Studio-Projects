package com.kerbabian.kk_assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Map;
import java.util.Random;
import java.util.function.Function;

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




        ResultTable = new int[][]{{0, 2, 1}, {1, 0, 2}, {2, 1, 0}};



        ResultText.setText("Choose rock, paper, or scissors.");
        ComputerImageView.setVisibility(View.INVISIBLE);
        YouImageView.setVisibility(View.INVISIBLE);











        View.OnClickListener UserChoicesButtons = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ComputerImageView.setVisibility(View.VISIBLE);
                YouImageView.setVisibility(View.VISIBLE);
                ComputerChose();
                UserChoice(view);




                // determine the winner using the all possible results table.
                switch (ResultTable[YouChoice][ComputerChoice]){
                    case 0:{
                        ResultText.setText("It's Tie.");
                        break;
                    }
                    case 1:{
                        ResultText.setText("It's a Win!");
                        break;
                    }
                    case 2:{
                        ResultText.setText("The Computer Wins!");
                        break;
                    }
                }

            }
        };


        RockButt.setOnClickListener(UserChoicesButtons);
        PaperButt.setOnClickListener(UserChoicesButtons);
        ScissorsButt.setOnClickListener(UserChoicesButtons);



    }
}