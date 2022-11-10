package com.example.activity9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class GameOver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameover);

        Context context = getApplicationContext();
        CharSequence text = "GAME OVER!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();


        TextView txt_overMsg = findViewById(R.id.textView_gameOverMessage);
        String score = Helper.IntToString(getIntent().getIntExtra("SCORE", 0));

        txt_overMsg.setText("CONGRATS!! YOUR FINAL SCORE IS " + score);

        findViewById(R.id.button_again).setOnClickListener(view ->{
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        findViewById(R.id.button_exit).setOnClickListener(view ->{
            finish();
            moveTaskToBack(true);
        });
    }

    @Override
    public void onBackPressed() {
        return;
    }
}