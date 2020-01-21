package com.example.rihards.financialflow;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainScreen extends AppCompatActivity {

    private static final String TAG = "Main Activity";

    DatabaseHelper databaseHelper;

    private Button btnInc, btnOut, btnExit, btnAdd;
    private TextView txtMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        txtMonth = (TextView) findViewById(R.id.text_month);
        btnAdd = (Button) findViewById(R.id.btn_add);
        btnInc = (Button) findViewById(R.id.btn_income);
        btnOut = (Button) findViewById(R.id.btn_outcome);
        btnExit = (Button) findViewById(R.id.btn_exit);

        Calendar calendar = Calendar.getInstance();
        String dayName = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US);
        txtMonth.setText(dayName);

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewData();
            }
        });
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
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddFinancesActivity();
            }
        });

        databaseHelper = new DatabaseHelper(this);
    }

    public void addNewData(){
        Intent intent = new Intent(this, AddFinances.class);
        startActivity(intent);
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

    public void openAddFinancesActivity(){
        Intent intent = new Intent(this, AddFinances.class);
        startActivity(intent);
    }
}
