package com.example.snakeandladder;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class SnakeAndLadder extends Application {
    //size of each cell of board,total height and width of window/frame,position of buttons;
    public static final int boardCell=60,height=12,width=12,buttonPos=boardCell*(height-1),buttonSizeX=80,buttonSizeY=30;
    public static Font font;
    // 2 players;
    private Player player1,player2;
    // info about first move;
    Button info;
    private Pane createContent(){
        //set the board, fill coordinate array,SnakeAndLadder map;
        new mappingBoardCoordinates();
        font=new Font("Arial",20);

        Pane root=new Pane();

        //background of window;
        Image bgImg = new Image("C:\\Users\\Admin\\OneDrive\\Desktop\\SnakeAndLadderProject\\SnakeAndLadder\\src\\main\\java\\com\\example\\snakeandladder\\backGround.bmp");
        BackgroundImage backgroundImage=new BackgroundImage(bgImg,BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,new BackgroundSize(1.0,1.0,true,true,false,false));
        root.setBackground(new Background(backgroundImage));
        root.setPrefSize(height*boardCell,width*boardCell);

        //Creating 10*10 matrix/board;
        //this is required when board image is not there,and want to use this matrix as board,
        // but i have included board image and map the image with all coordinates so this matrix is not required now;
//        for (int i = 0; i < height-2; i++) {
//            for (int j = 1; j < width-1; j++) {
//                Board board=new Board(boardCell);
//                board.setTranslateX(boardCell*j);
//                board.setTranslateY(boardCell*i);
//                root.getChildren().add(board);
//            }
//        }

        //Important:Including board image and setting appropriate height, width and position so that mapping of coordinates possible;
        Image image=new Image("C:\\Users\\Admin\\OneDrive\\Desktop\\SnakeAndLadderProject\\SnakeAndLadder\\src\\main\\java\\com\\example\\snakeandladder\\Board.jpg");
        ImageView imageView = new ImageView(image);
        imageView.setTranslateX(boardCell);
        imageView.setFitHeight(boardCell*(height-2));
        imageView.setFitWidth(boardCell*(width-2));

        // buttons -> info button,player 1 and 2 buttons, Start button;
        info=new Button("");
        Button player1button=new Button("Player 1");
        Button player2button=new Button("Player 2");
        Button Startbutton=new Button("Start!!!");

        //position of buttons:->
        //info about first move;
        info.setPrefSize(boardCell/2,boardCell/2);
        info.setTranslateY(boardCell*(height-2.6));
        info.setTranslateX(boardCell*0.2);
        //when info button clicked, dialog box will open and inside that, there will be message and ok button to close dialog box;
        info.setOnAction(e->{
            Dialog dialog = new Dialog();
            ButtonType type=new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.setContentText("Get 6 to make 1st move !!!!");
            dialog.showAndWait();
        });
        //info icon image in info button;
        ImageView infoView = new ImageView(new Image("C:\\Users\\Admin\\OneDrive\\Desktop\\SnakeAndLadderProject\\SnakeAndLadder\\src\\main\\java\\com\\example\\snakeandladder\\infoButton.png"));
        infoView.setFitWidth(boardCell/2);
        infoView.setFitHeight(boardCell/2);
        info.setGraphic(infoView);

        //positions of buttons;
        player1button.setTranslateX(boardCell); player1button.setTranslateY(buttonPos);
        player2button.setTranslateX(boardCell*(width-4)); player2button.setTranslateY(buttonPos);
        Startbutton.setTranslateX(boardCell*4.2); Startbutton.setTranslateY(buttonPos); //center of board
        //size of buttons;
        Startbutton.setPrefSize(buttonSizeX*2.5,buttonSizeY);
        player1button.setPrefSize(buttonSizeX*2,buttonSizeY);
        player2button.setPrefSize(buttonSizeX*2,buttonSizeY);
        //font of text in buttons
        Startbutton.setFont(font);
        player1button.setFont(font);
        player2button.setFont(font);


        //Labels -> Which player turn and dice value;
        Label player1turn=new Label("");
        Label player2turn=new Label("");
        Label diceLabel=new Label("Start Game!");
        //set font;
        player1turn.setFont(font);
        player2turn.setFont(font);
        diceLabel.setFont(font);

        //before game starts players button will be disabled;
        player1button.setDisable(true);
        player2button.setDisable(true);

        //position of Labels
        player1turn.setTranslateX(boardCell);           player1turn.setTranslateY(buttonPos-30);
        player2turn.setTranslateX(boardCell*(width-4)); player2turn.setTranslateY(buttonPos-30);
        diceLabel.setTranslateX(boardCell*(width-7));   diceLabel.setTranslateY(buttonPos-30);

        //initialize players -> size and colour of player coin and player name;
        player1=new Player(boardCell-15,Color.DARKBLUE,"Raj");
        player2=new Player(boardCell-20,Color.DARKRED,"Yash");

        //Set players button and turnLabel colour as players coin colour;
        player1button.setTextFill(player1.getCoinColor());  player2turn.setTextFill(player2.getCoinColor());
        player2button.setTextFill(player2.getCoinColor());  player1turn.setTextFill(player1.getCoinColor());

        //start the game on pressing start button:
        Startbutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // Starting Position of players when game starts;
                player1.StartingPosition();
                player2.StartingPosition();

                diceLabel.setText("Roll the Dice!!!");
                diceLabel.setTextFill(Color.BLACK);
                //set the position of diceLabel where dice value will be shown;
                diceLabel.setTranslateX(boardCell*(width-7));
                diceLabel.setTranslateY(buttonPos-30);

                Startbutton.setText("Game Started!!!");
                Startbutton.setTextFill(Color.TEAL);
                Startbutton.setDisable(true); //start button will be disabled when game starts;
                //player 1 gets the first chance to roll the dice;
                player1turn.setText("Your Turn " + player1.getName()+" !!!!");
                player1button.setDisable(false);
                info.setDisable(false); //info button will be visible when new game begins;
            }
        });

        //Rolling the dice and moving coin for player 1;
        player1button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                int diceVal=Dice.getDiceValue(); //dice value
                diceLabel.setText("Dice value :-> " + diceVal+ ((diceVal==6)?" !!!":""));
                if(player1.getCurPosition()!=1 || diceVal==6) player1.movePlayer(diceVal); //for first move 6 is compulsory;

                //winning condition;
                if(player1.isWinner()){
                    //set text and position so that text is correctly visible;
                    diceLabel.setText("Congrats! "+player1.getName()+" you are Winner...!\n Press start button to Play again");
                    diceLabel.setTranslateX(boardCell*(width-8));
                    diceLabel.setTranslateY(buttonPos-50);

                    //disable all labels and button;
                    player1button.setDisable(true); player1turn.setText("");
                    player2button.setDisable(true); player2turn.setText("");

                    //Enable start button to restart the game;
                    Startbutton.setDisable(false);
                    Startbutton.setText("Start");
                }
                //if dice value is 6 then player gets another chance;
                else if(diceVal!=6){
                    //Now player2 turn, So disable player1 and enable player2 buttons and labels;
                    player1button.setDisable(true);     player1turn.setText("");
                    player2button.setDisable(false);    player2turn.setText("Your Turn " + player2.getName()+" !!!!");
                }
                //if both players make their first move disable info button;
                if (player1.getCurPosition()>1 && player2.getCurPosition()>1) info.setDisable(true);
            }
        });

        //Rolling the dice and moving coin for player 2;
        player2button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                int diceVal=Dice.getDiceValue(); //dice value;
                diceLabel.setText("Dice value :-> " + diceVal+ ((diceVal==6)?" !!!":""));
                if(player2.getCurPosition()!=1 || diceVal==6) player2.movePlayer(diceVal);  //for first move 6 is compulsory;

                //winning condition;
                if(player2.isWinner()){
                    //set text and position so that text is correctly visible;
                    diceLabel.setText("Congrats! "+player2.getName()+" you are Winner...!\n Press Start button to play again");
                    diceLabel.setTranslateX(boardCell*(width-8));
                    diceLabel.setTranslateY(buttonPos-50);

                    //disable all labels and button;
                    player1button.setDisable(true); player1turn.setText("");
                    player2button.setDisable(true); player2turn.setText("");

                    //Enable start button to restart the game;
                    Startbutton.setDisable(false);
                    Startbutton.setText("Start");
                }
                //if dice value is 6 then player gets another chance;
                else if (diceVal!=6){
                    //Now player1 turn, So disable player2 and enable player1 buttons and labels;
                    player2button.setDisable(true);     player2turn.setText("");
                    player1button.setDisable(false);    player1turn.setText("Your Turn " + player1.getName()+" !!!!");

                }
                //if both players make their first move disable info button;
                if (player1.getCurPosition()>1 && player2.getCurPosition()>1) info.setDisable(true);
            }
        });

      // add all components in window/frame
        root.getChildren().addAll(imageView,player1button,player2button,Startbutton,
                player1turn,player2turn,diceLabel,player1.getCoin(),player2.getCoin(),info);

        return root;
    }
    public void getPlayersName(int n){
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setHeaderText("Enter Players Name");

        dialog.setContentText("Player " + n + " Name: ");
        Optional<String> option=dialog.showAndWait();
        String tmpName=dialog.getEditor().getText().toLowerCase();
        if(n==1){
            //changing player 1 name if name provided is not empty and not equals to player2 name and OK is pressed;
            if(option.isPresent() && !tmpName.isEmpty() && !tmpName.equals(player2.getName().toLowerCase())) player1.setName(dialog.getEditor().getText());
        }
        if(n==2){
            //changing player 2 name if name provided is not empty and not equals to player1 name and OK is pressed;
            if(option.isPresent() && !tmpName.isEmpty() && !tmpName.equals(player1.getName().toLowerCase())) player2.setName(tmpName);
        }

    }
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createContent()); //making GUI;
        //title and icon of window/stage;
        stage.setTitle("Snake and Ladder");

        //Not resizeable
        stage.setResizable(false);

        stage.getIcons().add(new Image("C:\\Users\\Admin\\OneDrive\\Desktop\\SnakeAndLadderProject\\SnakeAndLadder\\src\\main\\java\\com\\example\\snakeandladder\\icon.png"));
        stage.setScene(scene);
        stage.show();

        //updating players Name by using dialog box;
        getPlayersName(1);
        getPlayersName(2);

    }

    public static void main(String[] args) {
        launch();
    }
}