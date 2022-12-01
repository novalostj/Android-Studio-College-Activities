package com.example.project;

public class Player extends BaseStatus {

    public static Player player;

    public Player(){
        AddHealth(maxHealth);
        SetCurrentAction(ActionType.Null);
    }
}
