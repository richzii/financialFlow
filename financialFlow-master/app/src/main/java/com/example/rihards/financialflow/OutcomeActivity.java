package com.example.rihards.financialflow;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class OutcomeActivity extends AppCompatActivity {

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

        Cursor data = databaseHelper.getData();
        ArrayList<String> listData = new ArrayList<>();
        while (data.moveToNext()) {
            if (data.getString(1).equals("Izdevumi")) {
                listData.add("Datums:" + data.getString(2) + " | Summa:" + data.getString(3) + "€ | Piezīmes: " + data.getString(4));
            }
        }
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        listView.setAdapter(adapter);

    }
}
