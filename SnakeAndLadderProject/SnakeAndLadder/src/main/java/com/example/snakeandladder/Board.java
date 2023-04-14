package com.example.snakeandladder;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Board extends Rectangle {
    public Board(int boardSize){
        //set board height, width, color and border;
        setHeight(boardSize);
        setWidth(boardSize);
        setFill(Color.SEAGREEN);
        setStroke(Color.BLACK);
    }
}
