package com.example.rihards.financialflow;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class IncomeActivity extends AppCompatActivity {

    private static final String TAG = "Income Activity";

    DatabaseHelper databaseHelper;

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);

        listView = (ListView) findViewById(R.id.listView);
        databaseHelper = new DatabaseHelper(this);



        populateListView();
    }

    private void populateListView() {
        Log.d(TAG, "populateListView: Displaying data in list view");

        final Cursor data = databaseHelper.getData();

        ArrayList<String> listData = new ArrayList<>();
        while (data.moveToNext()) {
            if (data.getString(1).equals("Ienākumi")) {
                listData.add("Datums:" + data.getString(2) + " | Summa:" + data.getString(3) + "€ | Piezīmes: " + data.getString(4));
            }
        }
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String name = adapterView.getItemAtPosition(i).toString();
                toastMessage(name);
                /*Cursor data = databaseHelper.getItemID(name);
                int itemId = -1;
                while (data.moveToNext()) {
                    itemId = data.getInt(0);

                    if (itemId > 1) {

                    } else {
                        toastMessage("Nepareizs ID");
                    }
                }*/
            }
        });
    }

    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
