package com.Jay.activity3;

import static com.Jay.activity3.R.*;
import androidx.appcompat.app.AppCompatActivity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private static class GroupMeme {
        public Meme meme;
        public CheckBox checkBox;

        public GroupMeme(Meme meme, CheckBox checkBox){
            this.meme = meme;
            this.checkBox = checkBox;
        }
    }

    private ImageView bgImageView, memeImg;

    private TextView headerNameTxt, originTxt, birthYearTxt, ratingTxt, isAliveTxt;

    private GroupMeme[] groupMemeArr;

    private Meme slapsRoof, crowder, distractedBoyfriend, isThisAPigeon,
            mockingSpongeBob, thisIsFine, rickRoll, blank;
    private MediaPlayer rickRollSnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // For Organization
        {
            groupMemeArr = new GroupMeme[7];

            rickRollSnd = MediaPlayer.create(this, raw.nvrgnnagivuup);

            bgImageView = findViewById(id.bgImg);
            memeImg = findViewById(id.memeImg);

            headerNameTxt = findViewById(id.memeNameDisplayText);
            originTxt = findViewById(id.originDisplayTxt);
            birthYearTxt = findViewById(id.dateOfBirthDisplayText);
            ratingTxt = findViewById(id.ratingDisplayText);
            isAliveTxt = findViewById(id.aliveOrDeadDisplayText);

            createMemes();
            groupMemeArr[0] = new GroupMeme(slapsRoof, findViewById(id.slapsCarChkBx));
            groupMemeArr[1] = new GroupMeme(crowder, findViewById(id.crowderChkBx));
            groupMemeArr[2] = new GroupMeme(distractedBoyfriend, findViewById(id.distractedBfChkBx));
            groupMemeArr[3] = new GroupMeme(isThisAPigeon, findViewById(id.isThisAPigeon));
            groupMemeArr[4] = new GroupMeme(mockingSpongeBob, findViewById(id.mockingSpngBChkBx));
            groupMemeArr[5] = new GroupMeme(thisIsFine, findViewById(id.thisIsFineChkBx));
            groupMemeArr[6] = new GroupMeme(rickRoll, findViewById(id.rickRollChkBx));
        }

        findViewById(id.themeRadBtn1).setOnClickListener(view -> changeBackgroundImage(drawable.pepesad));
        findViewById(id.themeRadBtn2).setOnClickListener(view -> changeBackgroundImage(drawable.flyinggorilla));
        findViewById(id.themeRadBtn3).setOnClickListener(view -> changeBackgroundImage(drawable.whereismom));

        for (GroupMeme groupMeme: groupMemeArr)
            groupMeme.checkBox.setOnClickListener(view -> updateCurrentMeme(groupMeme));
    }

    private void createMemes() {
        slapsRoof = new Meme();
        slapsRoof.imageBitMap = drawable.car;
        slapsRoof.name = "Slaps Roof Of Car";
        slapsRoof.origin = "Twitter";
        slapsRoof.birthYear = "2014";
        slapsRoof.rating = "7/10";
        slapsRoof.state = "Dead";

        crowder = new Meme();
        crowder.imageBitMap = drawable.crowder;
        crowder.name = "Steven Crowder's Change My Mind Campus Sign";
        crowder.origin = "Twitter";
        crowder.birthYear = "2018";
        crowder.rating = "8/10";
        crowder.state = "Will be seen once in a while";

        distractedBoyfriend = new Meme();
        distractedBoyfriend.imageBitMap = drawable.damngina;
        distractedBoyfriend.name = "Distracted Boyfriend";
        distractedBoyfriend.origin = "Facebook";
        distractedBoyfriend.birthYear = "2017";
        distractedBoyfriend.rating = "7/10";
        distractedBoyfriend.state = "Will be seen once in a while";

        isThisAPigeon = new Meme();
        isThisAPigeon.imageBitMap = drawable.isthisapigeon;
        isThisAPigeon.name = "Is This A Pigeon?";
        isThisAPigeon.origin = "The Brave Fighter of Sun Fighbird";
        isThisAPigeon.birthYear = "1991";
        isThisAPigeon.rating = "6/10";
        isThisAPigeon.state = "Dead";

        mockingSpongeBob = new Meme();
        mockingSpongeBob.imageBitMap = drawable.mockingspongebobbb;
        mockingSpongeBob.name = "Mocking SpongeBob?";
        mockingSpongeBob.origin = "Twitter";
        mockingSpongeBob.birthYear = "2017";
        mockingSpongeBob.rating = "8/10";
        mockingSpongeBob.state = "Dead";

        thisIsFine = new Meme();
        thisIsFine.imageBitMap = drawable.this_is_fine;
        thisIsFine.name = "This Is Fine";
        thisIsFine.origin = "Gunshow";
        thisIsFine.birthYear = "2016";
        thisIsFine.rating = "6/10";
        thisIsFine.state = "Dead";

        rickRoll = new Meme();
        rickRoll.imageBitMap = drawable.rickroll;
        rickRoll.name = "Rickroll";
        rickRoll.origin = "4chan";
        rickRoll.birthYear = "2006";
        rickRoll.rating = "0/10";
        rickRoll.state = "DIE ALREADY PLS";

        blank = new Meme();
        blank.imageBitMap = drawable.lolmissing;
        blank.name = "The Meme is Missing";
        blank.origin = "";
        blank.birthYear = "";
        blank.rating = "";
        blank.state = "";

        setCurrentMeme(blank);
    }

    private void uncheckOtherCheckBoxes(CheckBox currentCheckBox){
        for (GroupMeme memeGroup: groupMemeArr) {
            if (memeGroup.checkBox == currentCheckBox) continue;

            memeGroup.checkBox.setChecked(false);
        }
    }

    private void updateCurrentMeme(GroupMeme memeGroup){
        uncheckOtherCheckBoxes(memeGroup.checkBox);

        if (memeGroup.checkBox.isChecked()){
            setCurrentMeme(memeGroup.meme);

            if (Objects.equals(memeGroup.meme.name, "Rickroll"))
                rickRollSnd.start();

            return;
        }

        setCurrentMeme(blank);
    }

    private void setCurrentMeme(Meme meme){
        memeImg.setImageResource(meme.imageBitMap);
        headerNameTxt.setText(meme.name);
        originTxt.setText(meme.origin);
        birthYearTxt.setText(meme.birthYear);
        ratingTxt.setText(meme.rating);
        isAliveTxt.setText(meme.state);
    }

    private void changeBackgroundImage(int bitmap){
        bgImageView.setImageResource(bitmap);
    }
}