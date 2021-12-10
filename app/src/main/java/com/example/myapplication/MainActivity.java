package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    static ArrayList<String> userNameRank = new ArrayList<String>();
    static ArrayList<Integer> userPointsRank = new ArrayList<Integer>();
    static ArrayList<String> userUriRank = new ArrayList<String>();
    private Integer intents = 0;
    private File image = null;
    private String photoPath;
    private String userName;
    private Intent intent;
    public static final String EXTRA_MESSAGE = "com.example.endevinanumero";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = findViewById(R.id.submitButton);

        EditText UserNum = (EditText) findViewById(R.id.sortNumber);

        int numRandom = (int) Math.floor(Math.random() * 99 + 1);
        TextView Intents = (TextView) findViewById(R.id.intents);

        button.setOnClickListener(new View.OnClickListener() {
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
                    alert.setMessage("Numero Secreto: "+numRandom+"\nNumero de intentos: "+String.valueOf(intents)+ "\n     INTRODUCE TU NOMBRE:");

                    // Set an EditText view to get user input
                    final EditText input = new EditText(MainActivity.this);
                    alert.setView(input);

                    if(input.getText() != null) {
                        alert.setPositiveButton("RANKING", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                userName = input.getText().toString();
                                System.out.println(userName + " " + intents);
                                intent = new Intent(v.getContext(), Ranking.class);
                                try {
                                    abrirCamara();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
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

    private File createImageFile() throws IOException {
        String timeStamp = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        }
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        photoPath = image.getAbsolutePath();
        return image;
    }
    private void abrirCamara() throws IOException {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        File photoFile = createImageFile();

        Uri photoURI = FileProvider.getUriForFile(this,
                "com.alexlarios.android.fileprovider",
                photoFile);
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
        startActivityForResult(takePictureIntent, 1);
        System.out.println("Si entra en la camara");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri fileUri = null;
        System.out.println("---Image:"+image.toString());
//        fileUri = Uri.fromFile(image);

        //Intent intent = new Intent (v.getContext(), RankingActivity.class);
        userUriRank.add(image.toString());
        userNameRank.add(userName);
        userPointsRank.add(intents);
        intent.putStringArrayListExtra("userName", userNameRank);
        intent.putIntegerArrayListExtra("userPoints", userPointsRank);
        intent.putStringArrayListExtra("userPhoto", userUriRank);
        startActivity(intent);
    }
}