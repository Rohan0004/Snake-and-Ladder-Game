package com.example.snakeandladder;

import javafx.util.Pair;

import java.util.HashMap;

public class mappingBoardCoordinates {
    // co-ordinates of each cell from 1 to 100;
    static Pair[] posCoordinates;
    //Snake and Ladder present in board;
    //key(from) -> Starting position of ladder and mouth of snake;
    //value(to) -> Ending position of ladder and tail of snake;
    static HashMap<Integer,Integer> mapSnakeLadder;
    mappingBoardCoordinates(){
        posCoordinates=new Pair[101];
        mapSnakeLadder=new HashMap<>();
        getCoordinates();
        getSnakeLadder();
    }
    private void getSnakeLadder(){
        //ladders
        mapSnakeLadder.put(4,25);
        mapSnakeLadder.put(13,46);
        mapSnakeLadder.put(33,49);
        mapSnakeLadder.put(42,63);
        mapSnakeLadder.put(50,69);
        mapSnakeLadder.put(62,81);
        mapSnakeLadder.put(74,92);

        //snakes
        mapSnakeLadder.put(27,5);
        mapSnakeLadder.put(40,3);
        mapSnakeLadder.put(43,18);
        mapSnakeLadder.put(54,31);
        mapSnakeLadder.put(66,45);
        mapSnakeLadder.put(76,58);
        mapSnakeLadder.put(89,53);
        mapSnakeLadder.put(99,41);
    }
    private void getCoordinates(){
        posCoordinates[0]=new Pair(0,0);
        int idx=1; //number on board cell ie 1 to 100;
        for (int i = 0; i < SnakeAndLadder.height-2; i++) {
            for (int j = 1; j < SnakeAndLadder.width-1; j++) {
                int posX=0,posY=0;
                posX=(SnakeAndLadder.boardCell*j)+(SnakeAndLadder.boardCell/2);
                posY=(SnakeAndLadder.boardCell*(SnakeAndLadder.height-2))-(int)(SnakeAndLadder.boardCell*(i+0.5));
                posCoordinates[idx]=new Pair<>(posX,posY);
                if((i&1)==0) idx++;
                else idx--;
            }
            //zigzag order of numbers in board;
            if((i&1)==0) idx+=9;
            else idx+=11;
        }
    }
}
