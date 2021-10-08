package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.HashMap;

public class Ranking extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        //Bucle a partir de aqui
        TableRow.LayoutParams  params1=new TableRow.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT,1.0f);
        TableRow.LayoutParams params2=new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        TableLayout tbl=(TableLayout) findViewById(R.id.rankingTable);

        //Datos de la lista de usuarios-ranking

        HashMap<String, Integer> nombre = getIntent().getStringExtra("User");
        String intentos = Integer.toString(getIntent().getIntExtra("Intents", 1));
        HashMap<String, Integer> map = getIntent();

        TableRow row=new TableRow(this);
        TextView txt1=new TextView(this);
        TextView txt2=new TextView(this);
        //Un txt por cada  variable
        txt1.setText(nombre);
        txt2.setText(intentos);

        //Añadir parametros
        txt1.setLayoutParams(params1);
        txt2.setLayoutParams(params1);

        row.addView(txt1);
        row.addView(txt2);

        row.setLayoutParams(params2);
        tbl.addView(row);
        //Hasta aqui el bucle

        /*final Button button = findViewById(R.id.playAgainButton);
        button.setOnClickListener(new View.OnClickListener() {
            Intent intent = new Intent (v.getContext(), MainActivity.class);
            startActivity(intent);
        }*/
    }
}