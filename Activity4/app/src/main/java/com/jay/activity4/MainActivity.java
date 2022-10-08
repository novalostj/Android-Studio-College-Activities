package com.jay.activity4;

import static androidx.appcompat.app.AlertDialog.*;
import static com.jay.activity4.R.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout constraintLayout;
    private ImageView bulb;
    private Builder alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        getSupportActionBar().hide();

        bulb = findViewById(id.img_bulb);
        constraintLayout = findViewById(id.parentLayout);

        alertDialog = new Builder(this)
                .setTitle("!ATTENTION GAMER!")
                .setMessage("What do you play?")
                .setPositiveButton("FLYING GORILLA", (dialogInterface, i) -> {
                    bulb.setImageResource(drawable.open);
                    toastMessage("Come in boss");
                })
                .setNegativeButton("MLBB", (dialogInterface, i) -> {
                    bulb.setImageResource(drawable.close);
                    toastMessage("Get out");
                });

        findViewById(id.btn_red).setOnClickListener(view -> toastMessage("RED, very sussy baka"));
        findViewById(id.btn_yellow).setOnClickListener(view -> toastMessage("YELLOW, that vent hopper"));
        findViewById(id.btn_green).setOnClickListener(view -> toastMessage("GREEN, i swear it's red"));

        findViewById(id.rad_1).setOnClickListener(view -> checkAnswer(1, "and you get time to sleep"));
        findViewById(id.rad_2).setOnClickListener(view -> checkAnswer(2, "at least you can finish it"));
        findViewById(id.rad_3).setOnClickListener(view -> checkAnswer(3, "MY MAN"));

        findViewById(id.btn_light).setOnClickListener(view -> alertDialog.show());
    }

    private void toastMessage(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    private void snackBarMessage(String msg){
        Snackbar.make(constraintLayout, msg, Snackbar.LENGTH_INDEFINITE).setAction("Close", view -> {}).show();
    }

    private void checkAnswer(int ansNum, String msg){
        String prefixMsg = "";

        if (ansNum == 3)
            prefixMsg = "Perfect! As all productive child should be, ";
        else
            prefixMsg = "you are a good child, keep up the work";

        String finalMsg = prefixMsg + " " + msg;

        snackBarMessage(finalMsg);
    }
}