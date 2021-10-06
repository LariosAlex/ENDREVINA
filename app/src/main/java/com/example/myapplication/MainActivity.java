package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = findViewById(R.id.submitButton);

        EditText UserNum;
        UserNum = (EditText) findViewById(R.id.sortNumber);
        String textUserNum = UserNum.getText().toString();

        int numRandom = (int) Math.floor(Math.random() * 99 + 1);
        TextView Intents = (TextView) findViewById(R.id.intents);

        button.setOnClickListener(new View.OnClickListener() {
            int intents = 0;
            public void onClick(View v) {
                Context context = getApplicationContext();
                CharSequence textNumber = "";
                int numUser = Integer.parseInt(UserNum.getText().toString());
                intents = intents + 1;
                if (numRandom < numUser) {
                    textNumber = "El teu numero es mes petit \n" + numRandom;
                    UserNum.setText("");
                    Intents.setText("");
                    Intents.setText("Intents totals:" + intents);
                }
                else if (numRandom > numUser){
                    textNumber = "El teu numero es mes gran \n" + numRandom;
                    UserNum.setText("");
                    Intents.setText("");
                    Intents.setText("Intents totals:" + intents);
                }
                else{
                    textNumber = "El teu numero es correcte";
                    Intent intent = new Intent (v.getContext(), Ranking.class);
                    intent.putExtra("Pepe", intents);
                    startActivity(intent);
                }
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, textNumber, duration);
                toast.show();
            }
        });
    }
}