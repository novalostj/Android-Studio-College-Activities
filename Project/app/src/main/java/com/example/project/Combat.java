package com.example.project;

public class Combat {

    private Player player;
    public Enemy enemy;

    public Result lastFightResult;

    private boolean gameIsOver;

    public Combat(Player player, Enemy enemy){
        this.player = player;
        this.enemy = enemy;

        player.AddHealth(player.maxHealth);
    }

    public static Result ActionCheck(ActionType player, ActionType enemy){
        if (player == ActionType.Paper){
            if (enemy == ActionType.Rock)
                return Result.Win;
            else if (enemy == ActionType.Scissor)
                return Result.Lost;
        }
        else if (player == ActionType.Rock){
            if (enemy == ActionType.Scissor)
                return Result.Win;
            else if (enemy == ActionType.Paper)
                return Result.Lost;
        }
        else if (player == ActionType.Scissor){
            if (enemy == ActionType.Paper)
                return Result.Win;
            else if (enemy == ActionType.Rock)
                return Result.Lost;
        }

        return Result.Tie;
    }

    public void Fight(){
        enemy.SetRandomAction();
        ActionType enemyAction = enemy.GetCurrentAction();
        ActionType playerAction = player.GetCurrentAction();

        lastFightResult = Combat.ActionCheck(playerAction, enemyAction);

        ResultEvent();
    }

    public void ResultEvent(){
        if (lastFightResult == Result.Win) {
            enemy.DecreaseHealth(player.damage);
        }
        else if (lastFightResult == Result.Lost) {
            player.DecreaseHealth(enemy.damage);
        }
        else{
            enemy.DecreaseHealth(player.damage / 2);
            player.DecreaseHealth(enemy.damage / 2);
        }
    }

    public boolean CheckIfFinished(){
        gameIsOver = player.IsDead() || enemy.IsDead();
        return gameIsOver;
    }
}
