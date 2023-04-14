package com.example.snakeandladder;

import javafx.animation.Animation;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Player {
    private String name;    //player name
    private Circle coin;    //player Coin
    private Color coinColor; //Coin color
    private int curPosition; //current position
    Player(int boardSize, Color color, String name){
        coin=new Circle(boardSize/2);
        coin.setFill(color);
        coinColor=color;
        this.name=name;
        curPosition=0;
        movePlayer(1);  //default position
    }

    //Movement of player's coin according to Dice value;
    public void movePlayer(int diceValue){
        if(curPosition+diceValue<=100) curPosition+=diceValue;
        //check that position of coin is inside the board only;
        if (1<=curPosition && curPosition<=100){
            //move a coin on basis of dice value;
            TranslateTransition move1=moveAnimation(diceValue),move2=null;
            //if snake or ladder present then move according to it;
            if(mappingBoardCoordinates.mapSnakeLadder.containsKey(curPosition)){
                curPosition=mappingBoardCoordinates.mapSnakeLadder.get(curPosition);
                move2=moveAnimation(6);
            }
            //if snake and ladder not present
            if(move2==null){
                move1.play();
            }
            //if snake or ladder present first make the normal(dice value) move then wait for 100 millisecond
            // and then move according to snake or ladder
            else {
                SequentialTransition sequentialTransition=new SequentialTransition(move1, new PauseTransition(Duration.millis(100)), move2);
                sequentialTransition.play();
            }
        }
    }
    //Animation of coins movement; time for completion of move = dice value * 100 millisecond;
    private TranslateTransition moveAnimation(int diceValue){
        TranslateTransition translateTransition=new TranslateTransition(Duration.millis(diceValue*100),coin);
        translateTransition.setToX((int) mappingBoardCoordinates.posCoordinates[curPosition].getKey());
        translateTransition.setToY((int) mappingBoardCoordinates.posCoordinates[curPosition].getValue());
        translateTransition.setAutoReverse(false);
        return translateTransition;
    }
    // Starting position of coin must be 1;
    public void StartingPosition(){
        curPosition=0;
        movePlayer(1);
    }
    //if coin reaches 100 then player is winner;
    public boolean isWinner(){
        return curPosition==100;
    }
    public String getName() {
        return name;
    }

    public Circle getCoin() {
        return coin;
    }

    public int getCurPosition() {
        return curPosition;
    }

    public Color getCoinColor() {
        return coinColor;
    }
    public void setName(String name){
        this.name=name;
    }
}
