package com.example.activity9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button_add).setOnClickListener(view -> LaunchGame(1));
        findViewById(R.id.button_subtraction).setOnClickListener(view -> LaunchGame(2));
        findViewById(R.id.button_multiplication).setOnClickListener(view -> LaunchGame(3));
        findViewById(R.id.button_division).setOnClickListener(view -> LaunchGame(4));
    }

    private void LaunchGame(int type){
        Intent gameIntent = new Intent(this, Game.class);
        gameIntent.putExtra("Type", type);

        startActivity(gameIntent);
    }

    @Override
    public void onBackPressed() {
        return;
    }
}