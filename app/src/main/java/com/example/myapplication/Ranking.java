package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Ranking extends AppCompatActivity {
        class Record{
            public int intents;
            public String nom;

            public Record(int _intents, String _nom ) {
                intents = _intents;
                nom = _nom;
            }
        }
    ArrayList<Record> records;
    ArrayAdapter<Record> adapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ranking_view);

        records = new ArrayList<Record>();

        ArrayList<String> names = (ArrayList<String>) getIntent().getStringArrayListExtra("userName");
        ArrayList<Integer> points = (ArrayList<Integer>) getIntent().getIntegerArrayListExtra("userPoints");

        for (int i = 0; i < names.size(); i++) {
            String name = names.get(i);
            Integer point = points.get(i);
            records.add( new Record(point,name) );
        }
        adapter = new ArrayAdapter<Record>( this, R.layout.list_item, records )
        {
            @Override
            public View getView ( int pos, View convertView, ViewGroup container)
            {
                if (convertView == null) {
                    // inicialitzem l'element la View amb el seu layout
                    convertView = getLayoutInflater().inflate(R.layout.list_item, container, false);
                }
                ((TextView) convertView.findViewById(R.id.nom)).setText(getItem(pos).nom);
                ((TextView) convertView.findViewById(R.id.punts)).setText(String.valueOf(getItem(pos).intents));
                return convertView;
            }
        };
        ListView lv = (ListView) findViewById(R.id.recordsView);
        lv.setAdapter(adapter);
    }
}
