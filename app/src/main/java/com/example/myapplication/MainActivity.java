package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = findViewById(R.id.submitButton);

        EditText UserNum = (EditText) findViewById(R.id.sortNumber);

        int numRandom = (int) Math.floor(Math.random() * 99 + 1);
        TextView Intents = (TextView) findViewById(R.id.intents);

        ArrayList<String> userNameRank = new ArrayList<String>();
        ArrayList<Integer> userPointsRank = new ArrayList<Integer>();
        button.setOnClickListener(new View.OnClickListener() {
            Integer intents = 0;
            public void onClick(View v) {
                Context context = getApplicationContext();
                CharSequence textNumber;
                int numUser = Integer.parseInt(UserNum.getText().toString());
                intents = intents + 1;
                if (numRandom < numUser) {
                    textNumber = "El teu numero es mes petit \n" + numRandom;
                    UserNum.setText("");
                    Intents.setText("");
                    Intents.setText(String.valueOf(intents));
                }
                else if (numRandom > numUser){
                    textNumber = "El teu numero es mes gran \n" + numRandom;
                    UserNum.setText("");
                    Intents.setText("");
                    Intents.setText(String.valueOf(intents));
                }
                else{
                    textNumber = "El teu numero es correcte";

                    AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                    alert.setTitle("HAS GANADO. ENHORABUENA!");
                    alert.setMessage("NUMERO SECRETO: "+numRandom+"\nNUMERO DE INTENTOS: "+String.valueOf(intents)+ "\n     INTRODUCE TU NOMBRE:");

                    // Set an EditText view to get user input
                    final EditText input = new EditText(MainActivity.this);
                    alert.setView(input);
                    if(input.getText() != null) {
                        alert.setPositiveButton("RANKING", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                String userName = input.getText().toString();
                                System.out.println(userName);
                                String message = String.valueOf(userName + " " + intents);
                                userNameRank.add(userName);
                                userPointsRank.add(intents);
                                Intent intent = new Intent(v.getContext(), Ranking.class);
                                intent.putStringArrayListExtra("userName", userNameRank);
                                intent.putIntegerArrayListExtra("userPoints", userPointsRank);
                                startActivity(intent);
                            }
                        });
                    }
                    alert.show();
                }

                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, textNumber, duration);
                toast.show();
            }
        });
    }
}