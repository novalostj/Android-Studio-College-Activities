package com.example.project;

import java.io.Serializable;
import java.util.Random;

public class BaseStatus implements Serializable {
    public Random rnd;

    private int health = 100;
    public int maxHealth = 100;

    public int damage = 10;

    private ActionType currentAction = ActionType.Null;

    public boolean AddHealth(int amount){
        health += amount;

        if (health > maxHealth)
            health = maxHealth;

        return health > 0;
    }

    public boolean DecreaseHealth(int amount){
        health -= amount;

        if (health < 0)
            health = 0;

        return health > 0;
    }

    public int GetHealth(){
        return health;
    }

    public void SetCurrentAction(ActionType actionType){
        currentAction = actionType;
    }

    public ActionType GetCurrentAction(){
        return currentAction;
    }

    public boolean IsDead(){
        return health <= 0;
    }

    public void SetRandomAction(){
        if (rnd == null)
            rnd = new Random();

        int randomNum = rnd.nextInt(3) + 1;

        switch (randomNum){
            case 1:
                SetCurrentAction(ActionType.Rock);
                break;
            case 2:
                SetCurrentAction(ActionType.Paper);
                break;
            case 3:
                SetCurrentAction(ActionType.Scissor);
                break;
        }
    }
}
