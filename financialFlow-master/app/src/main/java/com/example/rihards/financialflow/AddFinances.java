package com.example.rihards.financialflow;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class AddFinances extends AppCompatActivity {

    DatabaseHelper databaseHelper;

    private Button btnAdd;
    private Spinner spinnerType;
    private TextView txtDate, amount, note;

    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_finances);

        btnAdd = (Button) findViewById(R.id.button_addToDb);
        spinnerType = (Spinner) findViewById(R.id.spinnerType);
        amount = (TextView) findViewById(R.id.amount);
        txtDate = (TextView) findViewById(R.id.text_pickDate);
        note = (TextView) findViewById(R.id.piezime);

        databaseHelper = new DatabaseHelper(this);

        txtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        AddFinances.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;

                String selectedDate = month + "/" + dayOfMonth + "/" + year;
                txtDate.setText(selectedDate);
            }
        };

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String typeOutput = spinnerType.getSelectedItem().toString();
                String dateOutput = txtDate.getText().toString();
                String amountOutput = amount.getText().toString();
                String notesOutput = note.getText().toString();
                if (spinnerType.getSelectedItem().toString().length() != 0) {
                    addData(typeOutput, dateOutput, amountOutput, notesOutput);
                } else {
                    toastMessage("Ievadiet vērtību");
                }
            }
        });
    }

    // Insert data and check if it was done successfully
    /*
    * TODO
    * String messages as global messages */
    public void addData(String a, String b, String c, String d) {
        boolean insertData = databaseHelper.addData(a,b,c,d);

        if (insertData) {
            toastMessage("Dati ievadīti veiksmīgi");
        } else {
            toastMessage("Dati netika ievadīti veiksmīgi");
        }
    }

    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
