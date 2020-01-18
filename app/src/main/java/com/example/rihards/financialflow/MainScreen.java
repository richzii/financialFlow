package com.example.rihards.financialflow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainScreen extends AppCompatActivity {

    private Button btnInc, btnOut, btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        btnInc = (Button) findViewById(R.id.btn_income);
        btnOut = (Button) findViewById(R.id.btn_outcome);
        btnExit = (Button) findViewById(R.id.btn_exit);

        btnInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openIncomeActivity();
            }
        });
        btnOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOutcomeActivity();
            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeFinancialFlowApp();
            }
        });
    }

    public void closeFinancialFlowApp(){
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

    public void openIncomeActivity(){
        Intent intent = new Intent(this, IncomeActivity.class);
        startActivity(intent);
    }

    public void openOutcomeActivity(){
        Intent intent = new Intent(this, OutcomeActivity.class);
        startActivity(intent);
    }
}
