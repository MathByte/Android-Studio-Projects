package com.kerbabian.kk_assignment2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class StateActivity extends AppCompatActivity {
    private TextView textViewAllTime;
    private TextView textViewLastMinute;
    private Button buttonResetStats;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_state);





        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        buttonResetStats = findViewById(R.id.buttonResetStats);

        buttonResetStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((GameDataSharedApplication) getApplication()).resetTableStats();
                refreshStats();
                Toast.makeText(StateActivity.this, R.string.notification_message, Toast.LENGTH_SHORT).show();
            }
        });

        refreshStats();
    }

    private void refreshStats() {


        final GameDataSharedApplication application;
        application = ((GameDataSharedApplication) getApplication());

        textViewAllTime = findViewById(R.id.textViewAllTime);
        textViewLastMinute = findViewById(R.id.textViewLastMin);
        textViewLastMinute.setText(application.getLastRecord());
        textViewAllTime.setText(application.getAllTimeRecords());
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        boolean ret = true;

        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                break;
            default:
                ret = super.onOptionsItemSelected(item);
                break;
        }

        return ret;
    }


}