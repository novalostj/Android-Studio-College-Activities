package com.example.project;

import java.util.Random;

public class Enemy extends BaseStatus{

    public String name;
    public int img;
    public int music;

    public Enemy(String name, int img, int music, int maxHp){
        this.maxHealth = maxHp;
        AddHealth(maxHealth);
        SetCurrentAction(ActionType.Null);

        this.name = name;
        this.img = img;
        this.music = music;
    }
}
