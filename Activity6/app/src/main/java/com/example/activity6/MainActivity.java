package com.example.activity6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.GridView;


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        GridView gridView = findViewById(R.id.gridView);

        GridCard gridCard = new GridCard(
                this,
                getResources().getStringArray(R.array.ChampionNames),
                getResources().obtainTypedArray(R.array.ChampionResImage),
                displayMetrics);

        gridView.setAdapter(gridCard);
    }
}