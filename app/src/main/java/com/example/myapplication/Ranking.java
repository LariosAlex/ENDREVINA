package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Ranking extends AppCompatActivity{
    class Record {
            public int intents;
            public String nom;
            public Uri photoUri;

            public Record(int _intents, String _nom, Uri uri) {
                intents = _intents;
                nom = _nom;
                photoUri = uri;
            }

            public int getIntents() {
                return intents;
            }

            public void setIntents(int intents) {
                this.intents = intents;
            }
    }

    ArrayList<Record> records;
    ArrayAdapter<Record> adapter;
    @RequiresApi(api = Build.VERSION_CODES.N)
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ranking_view);

        records = new ArrayList<Record>();

        ArrayList<String> names = (ArrayList<String>) getIntent().getStringArrayListExtra("userName");
        ArrayList<Integer> points = (ArrayList<Integer>) getIntent().getIntegerArrayListExtra("userPoints");
        ArrayList<String> photos = (ArrayList<String>) getIntent().getStringArrayListExtra("userPhoto");
        for (int i = 0; i < names.size(); i++) {
            String name = names.get(i);
            int point = points.get(i);
            Uri uri = Uri.fromFile(new File(photos.get(i)));
            records.add( new Record(point,name,uri));
        }
        for (int i = 0; i < records.size(); i++) {
            for (int j = 0; j < records.size(); j++) {
                if(records.get(i).getIntents() < records.get(j).getIntents()) {
                    Record aux = records.get(i);
                    records.set(i, records.get(j));
                    records.set(j, aux);
                }
            }
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
                ((ImageView) convertView.findViewById(R.id.foto)).setImageURI(getItem(pos).photoUri);
                return convertView;
            }
        };
        ListView lv = (ListView) findViewById(R.id.recordsView);
        lv.setAdapter(adapter);

        Button newGame = findViewById(R.id.newGame);
        newGame.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
    protected File getFile(){
        File path = getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File photo = new File(path,"imatge.jpg");
        return photo;
    }
}
