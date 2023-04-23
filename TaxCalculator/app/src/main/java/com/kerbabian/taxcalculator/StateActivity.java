package com.kerbabian.taxcalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StateActivity extends AppCompatActivity {

    private TextView textViewTotalTaxPaid;
    private TextView textViewTotalCostsPaid;
    private TextView textViewTransactions;
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
                ((TaxCalculatorApplication) getApplication()).resetTableStats();
                refreshStats();
            }
        });

        refreshStats();
    }

    private void refreshStats() {
        String customCurrencyPrefix;
        String decimalPointType;
        Intent intent = getIntent();
        final TaxCalculatorApplication application;

        textViewTotalTaxPaid = findViewById(R.id.textViewTaxesPaid);
        textViewTotalCostsPaid = findViewById(R.id.textViewCosts);
        textViewTransactions = findViewById(R.id.textViewTransactions);

        application = ((TaxCalculatorApplication) getApplication());

        customCurrencyPrefix = intent.getStringExtra("currency");
        decimalPointType = intent.getStringExtra("decimal");

        textViewTotalTaxPaid.setText(
                application.formatCurrency(
                        application.getTotalTaxPaid(),
                        customCurrencyPrefix,
                        decimalPointType
                )
        );
        textViewTotalCostsPaid.setText(
                application.formatCurrency(
                        application.getTotalCostsPaid(),
                        customCurrencyPrefix,
                        decimalPointType
                )
        );
        textViewTransactions.setText("" + application.getTransactions());
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