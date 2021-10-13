package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class Ranking extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        ArrayList<String> names = (ArrayList<String>) getIntent().getStringArrayListExtra("userName");
        ArrayList<Integer> points = (ArrayList<Integer>) getIntent().getIntegerArrayListExtra("userPoints");

        for (int i = 0; i < names.size(); i++) {
            String name = names.get(i);
            Integer point = points.get(i);

            TableRow.LayoutParams params1 = new TableRow.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT, 1.0f);
            TableRow.LayoutParams params2 = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
            TableLayout tbl = (TableLayout) findViewById(R.id.rankingTable);

            TableRow row = new TableRow(this);
            TextView txt1 = new TextView(this);
            TextView txt2 = new TextView(this);

            txt1.setText(name);
            txt2.setText(point);

            txt1.setLayoutParams(params1);
            txt2.setLayoutParams(params1);

            row.addView(txt1);
            row.addView(txt2);

            row.setLayoutParams(params2);
            tbl.addView(row);
        }
    }
}