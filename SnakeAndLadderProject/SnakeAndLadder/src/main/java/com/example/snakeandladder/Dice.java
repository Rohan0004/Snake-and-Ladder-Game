package com.example.snakeandladder;

public class Dice {
    public static int getDiceValue(){
        //random number in range [1,6];
        return (int) (Math.random()*6+1);
    }
}
