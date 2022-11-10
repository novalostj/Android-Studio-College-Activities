package com.example.activity9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;

public class Game extends AppCompatActivity {
    private CountDownTimer countDownTimer;
    private Random rand = new Random();

    private TextView txt_Score, txt_Life, txt_Time, txt_Question;
    private EditText txt_Answer;

    private int type = 0, score, life, time;
    private int correctAnswer;

    private boolean isPaused = false;
    private boolean gameOver = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        gameOver = isPaused = false;

        score = 0;
        life = 3;
        time = 20;

        type = getIntent().getIntExtra("Type", 0);

        txt_Score = findViewById(R.id.textView_score);
        txt_Life = findViewById(R.id.textView_life);
        txt_Time = findViewById(R.id.textView_time);
        txt_Question = findViewById(R.id.textView_question);
        txt_Answer = findViewById(R.id.editText_answer);

        findViewById(R.id.button_next).setOnClickListener(view ->{
            if (gameOver) {
                Intent intent = new Intent(this, GameOver.class);
                intent.putExtra("SCORE", score);
                startActivity(intent);

                finish();
                return;
            }

            if (!isPaused) return;

            isPaused = false;
            txt_Answer.setText("");
            reset();
        });

        findViewById(R.id.button_ok).setOnClickListener(view ->{
            if (gameOver || isPaused) return;

            String answer = txt_Answer.getEditableText().toString();

            if (answer.length() == 0) return;

            checkAnswer(Integer.parseInt(answer));
            isPaused = true;
        });

        txt_Score.setText(Helper.IntToString(score));
        txt_Life.setText(Helper.IntToString(life));
        txt_Time.setText(Helper.IntToString(time));

        reset();

        countDownTimer = new CountDownTimer(999999999, 1000) {

            @Override
            public void onTick(long l) {
                if (isPaused || gameOver) return;

                time--;
                txt_Time.setText(Helper.IntToString(time));

                if (time <= 0) {
                    txt_Question.setText("TIME'S UP!!");

                    Life(-1);
                    ResetTime();

                    isPaused = true;
                }
            }

            @Override
            public void onFinish() {
            }
        };

        countDownTimer.start();
    }

    private void GameOver() {
        gameOver = true;
        countDownTimer.cancel();
    }

    private void reset() {
        int var1 = rand.nextInt(100);
        int var2 = rand.nextInt(100);;

        while (var1 == 0)
            var1 = rand.nextInt(100);

        while(var2 == 0)
            var2 = rand.nextInt(100);

        String symbol = "";

        switch (type){
            case 1:
                symbol = " + ";
                break;
            case 2:
                while (var2 > var1)
                    var2 = rand.nextInt(100);

                symbol = " - ";
                break;
            case 3:
                symbol = " × ";
                break;
            case 4:

                while ((float)var1 % (float)var2 != 0 || var2 == 0)
                    var2 = rand.nextInt(100);

                symbol = " ÷ ";
                break;
        }

        txt_Question.setText(var1 + symbol + var2);

        switch (type){
            case 1:
                 correctAnswer = var1 + var2;
                break;
            case 2:
                correctAnswer = var1 - var2;
                break;
            case 3:
                correctAnswer = var1 * var2;
                break;
            case 4:
                correctAnswer = var1 / var2;
                break;
        }
    }

    private void checkAnswer(int answer){
        if (isPaused || gameOver) return;

        isPaused = true;

        ResetTime();

        if (answer == correctAnswer){
            score += 10;
            txt_Score.setText(Helper.IntToString(score));
            txt_Question.setText("CORRECT!!");
            return;
        }

        Life(-1);
        txt_Question.setText("WRONG!!");

        if (life == 0){
            GameOver();
        }
    }

    private void Life(int value){
        life += value;
        txt_Life.setText(Helper.IntToString(life));

        if (life == 0){
            GameOver();
        }
    }

    private void ResetTime()
    {
        time = 20;
        txt_Time.setText(Helper.IntToString(time));
    }
    @Override
    public void onBackPressed() {
        return;
    }
}