package com.example.project;

import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class MainMenu extends AppCompatActivity {

    Player player = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        PlayClickEvent();
    }

    private void PlayClickEvent() {
        findViewById(R.id.b_play).setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Play.class);
            finish();
            startActivity(intent);
        });

        findViewById(R.id.b_exit).setOnClickListener(view ->{
            finish();
            System.exit(0);
        });
    }
}