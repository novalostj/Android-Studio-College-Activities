package com.example.project;

import android.content.Intent;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;

public class Play extends AppCompatActivity {

    private Combat combat;

    private Player player;
    private Enemy enemy1, enemy2, enemy3;

    private ImageView enemyImg;
    private TextView t_enemyHealth, t_playerHealth, t_result, t_enemyName;

    private MediaPlayer mediaPlayer;

    private int win = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        enemyImg = findViewById(R.id.img_enemy);

        player = new Player();
        enemy1 = new Enemy("Carl Wheezer", R.drawable.bosscarl_1, R.raw.bossmusic_1, 20);
        enemy2 = new Enemy("Emo Carl + Dr Doofenshmirtz", R.drawable.bosscarl_2, R.raw.bossmusic_2, 100);
        enemy3 = new Enemy("Top G Carl", R.drawable.bosscarl_3, R.raw.bossmusic_3, 140);

        combat = new Combat(player, enemy1);

        mediaPlayer = MediaPlayer.create(this, enemy1.music);
        mediaPlayer.start();
        enemyImg.setImageResource(enemy1.img);

        SetButtonsListener();
        SetTexts();
        RefreshDisplays();
    }

    private void RefreshDisplays() {
        int healthy = ContextCompat.getColor(getApplicationContext(), R.color.green);
        int unhealthy = ContextCompat.getColor(getApplicationContext(), R.color.red);

        float enemyInterpolateValue = (float) combat.enemy.GetHealth()/ (float) combat.enemy.maxHealth;
        float playerInterpolateValue = (float)player.GetHealth()/ (float)player.maxHealth;

        t_enemyName.setText(combat.enemy.name);

        t_enemyHealth.setText(combat.enemy.GetHealth() + "/" + combat.enemy.maxHealth);
        t_enemyHealth.setBackgroundColor(ColorUtils.blendARGB(unhealthy, healthy, enemyInterpolateValue));

        t_playerHealth.setText(player.GetHealth() + "/" + player.maxHealth);
        t_playerHealth.setBackgroundColor(ColorUtils.blendARGB(unhealthy, healthy, playerInterpolateValue));

        t_result.setText("ENEMY: " + combat.enemy.GetCurrentAction().toString() + "\n" +
                        "VS\n" +
                        "PLAYER: " + player.GetCurrentAction().toString());
    }

    private void SetTexts() {
        t_enemyName = findViewById(R.id.t_enemyName);
        t_result = findViewById(R.id.t_result);
        t_playerHealth = findViewById(R.id.t_playerHealth);
        t_enemyHealth = findViewById(R.id.t_enemyHealth);
    }

    public void SetButtonsListener(){
        findViewById(R.id.b_play_paper).setOnClickListener(view -> {
            player.SetCurrentAction(ActionType.Paper);
            Combat();
        });

        findViewById(R.id.b_play_rock).setOnClickListener(view -> {
            player.SetCurrentAction(ActionType.Rock);
            Combat();
        });

        findViewById(R.id.b_play_scissors).setOnClickListener(view -> {
            player.SetCurrentAction(ActionType.Scissor);
            Combat();
        });
    }

    private void Combat(){
        combat.Fight();
        RefreshDisplays();

        if (combat.CheckIfFinished()) {
            if (player.IsDead()){
                Toast.makeText(getApplicationContext(), "YOU LOST TO CARL WHEEZER LMAO", Toast.LENGTH_SHORT).show();
                Exit();
            }
            else{
                if (win == 0){
                    combat = new Combat(player, enemy2);
                    mediaPlayer.stop();
                    mediaPlayer = MediaPlayer.create(this, enemy2.music);
                    mediaPlayer.start();
                    enemyImg.setImageResource(enemy2.img);
                    Toast.makeText(this, "You can defeat me, but not with my man dr doofen", Toast.LENGTH_LONG).show();
                }
                else if (win == 1){
                    combat = new Combat(player, enemy3);
                    Toast.makeText(this, "Let me end your career", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this, "I will come back", Toast.LENGTH_SHORT).show();
                    Exit();
                    return;
                }
                win++;

                RefreshDisplays();
                mediaPlayer.stop();
                mediaPlayer = MediaPlayer.create(this, combat.enemy.music);
                mediaPlayer.start();
                enemyImg.setImageResource(combat.enemy.img);
            }
        }
    }

    private void Dialog(String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("GAME OVER")
                .setMessage(msg)
                .setPositiveButton("Exit", (dialogInterface, i) -> Exit());

        builder.show();
    }

    private void Exit(){
        finish();
        mediaPlayer.stop();
        startActivity(new Intent(getApplicationContext(), MainMenu.class));
    }


}