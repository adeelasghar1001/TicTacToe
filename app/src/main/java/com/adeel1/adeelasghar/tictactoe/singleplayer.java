package com.adeel1.adeelasghar.tictactoe;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class singleplayer extends Activity implements View.OnClickListener{

    //Declares button array
    private Button[][] buttons = new Button[3][3];


    int first;
    int second;
    int counter=0;
    private boolean player1Turn = true;

    private int roundCount;

    private int player1Points;
    private int player2Points;

    private TextView textViewP1;
    private TextView textViewP2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_singleplayer);

        textViewP1 = findViewById(R.id.textView_p1);
        textViewP2 = findViewById(R.id.textView_p2);

        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                String buttonID= "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }

        Button buttonrest = findViewById(R.id.reset_button);
        buttonrest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(!((Button) v).getText().toString().equals("")){
            return;
        }

        //Player 1's turn
        if(player1Turn){
            ((Button) v).setTextColor(Color.parseColor("#FF0000"));
            ((Button) v).setText("X");

            //Perform click so that CPU can use their turn
            if(counter<4 && !(checkForWin())) {
                int y = 5;
                while (y == 5) {
                    Random rand = new Random();
                    first = rand.nextInt(3);
                    second = rand.nextInt(3);
                    if (buttons[first][second].getText().toString().equals("")) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                buttons[first][second].performClick();
                                counter++;
                            }
                        }, 400);
                        y++;
                    }
                }
            }

        //CPU Turn. Algorithm to place "O" anywhere based on current board
        }else{

            if(buttons[0][0].getText().toString().equals("") &&
                    ((buttons[0][1].getText().toString().equals("X") && buttons[0][2].getText().toString().equals("X")) ||
                            (buttons[1][1].getText().toString().equals("X") && buttons[2][2].getText().toString().equals("X")) ||
                            (buttons[1][0].getText().toString().equals("X") && buttons[2][0].getText().toString().equals("X")))) {
                buttons[0][0].setTextColor(Color.parseColor("#0000FF"));
                buttons[0][0].setText("O");
            } else if (buttons[0][1].getText().toString().equals("") &&
                    ((buttons[1][1].getText().toString().equals("X") && buttons[2][1].getText().toString().equals("X")) ||
                            (buttons[0][0].getText().toString().equals("X") && buttons[0][2].getText().toString().equals("X")))) {
                buttons[0][1].setTextColor(Color.parseColor("#0000FF"));
                buttons[0][1].setText("O");
            } else if(buttons[0][2].getText().toString().equals("") &&
                    ((buttons[0][0].getText().toString().equals("X") && buttons[1][2].getText().toString().equals("X")) ||
                            (buttons[2][0].getText().toString().equals("X") && buttons[1][1].getText().toString().equals("X")) ||
                            (buttons[1][2].getText().toString().equals("X") && buttons[2][2].getText().toString().equals("X")))) {
                buttons[0][2].setTextColor(Color.parseColor("#0000FF"));
                buttons[0][2].setText("O");
            } else if(buttons[1][0].getText().toString().equals("") &&
                    ((buttons[1][1].getText().toString().equals("X") && buttons[1][2].getText().toString().equals("X")) ||
                            (buttons[0][0].getText().toString().equals("X") && buttons[2][0].getText().toString().equals("X")))){
                buttons[1][0].setTextColor(Color.parseColor("#0000FF"));
                buttons[1][0].setText("O");
            } else if(buttons[1][1].getText().toString().equals("") &&
                    ((buttons[0][0].getText().toString().equals("X") && buttons[2][2].getText().toString().equals("X")) ||
                            (buttons[0][1].getText().toString().equals("X") && buttons[2][1].getText().toString().equals("X")) ||
                            (buttons[2][0].getText().toString().equals("X") && buttons[0][2].getText().toString().equals("X")) ||
                            (buttons[1][0].getText().toString().equals("X") && buttons[1][2].getText().toString().equals("X")))) {
                buttons[1][1].setTextColor(Color.parseColor("#0000FF"));
                buttons[1][1].setText("O");
            } else if(buttons[1][2].getText().toString().equals("") &&
                    ((buttons[1][0].getText().toString().equals("X") && buttons[1][1].getText().toString().equals("X")) ||
                            (buttons[0][2].getText().toString().equals("X") && buttons[2][2].getText().toString().equals("X")))) {
                buttons[1][2].setTextColor(Color.parseColor("#0000FF"));
                buttons[1][2].setText("O");
            } else if(buttons[2][0].getText().toString().equals("") &&
                    ((buttons[0][0].getText().toString().equals("X") && buttons[1][0].getText().toString().equals("X")) ||
                            (buttons[2][1].getText().toString().equals("X") && buttons[2][2].getText().toString().equals("X")) ||
                            (buttons[1][1].getText().toString().equals("X") && buttons[0][2].getText().toString().equals("X")))){
                buttons[2][0].setTextColor(Color.parseColor("#0000FF"));
                buttons[2][0].setText("O");
            } else if(buttons[2][0].getText().toString().equals("") &&
                    ((buttons[0][1].getText().toString().equals("X") && buttons[1][1].getText().toString().equals("X")) ||
                            (buttons[2][0].getText().toString().equals("X") && buttons[2][2].getText().toString().equals("X")))) {
                buttons[2][1].setTextColor(Color.parseColor("#0000FF"));
                buttons[2][1].setText("O");
            }else if( buttons[2][2].getText().toString().equals("") &&
                    ((buttons[0][0].getText().toString().equals("X") && buttons[1][1].getText().toString().equals("X")) ||
                            (buttons[0][2].getText().toString().equals("X") && buttons[1][2].getText().toString().equals("X")) ||
                            (buttons[2][0].getText().toString().equals("X") && buttons[2][1].getText().toString().equals("X")))) {
                buttons[2][2].setTextColor(Color.parseColor("#0000FF"));
                buttons[2][2].setText("O");
            } else {
                int x = 5;
                while (x == 5) {
                    Random rand = new Random();

                    int a = rand.nextInt(3);
                    int b = rand.nextInt(3);
                    if (buttons[a][b].getText().toString().equals("")) {
                        buttons[a][b].setTextColor(Color.parseColor("#0000FF"));
                        buttons[a][b].setText("O");
                        x++;
                    }
                }
            }
        }

        //Increment rount count
        roundCount++;

        //Check for the win
        if(checkForWin()){
            if(player1Turn){ //If player 1 wins
                player1Wins();
            }else{ //If CPU wins
                player2Wins();
            }
        }else if(roundCount==9){ //Draw game
            draw();
        }else{ //Change turns
            player1Turn = !player1Turn;
        }

    }

    //Checks for win
    private boolean checkForWin(){
        String[][] field = new String[3][3];

        //Sets external string array to the results
        for (int i=0 ; i<3; i++){
            for(int j=0; j<3; j++){
                field[i][j] = buttons[i][j].getText().toString();
            }
        }
        //Checks for horizontal win
        for (int i=0 ; i<3; i++){
            if(field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")){
                return true;
            }
        }

        //Checks for vertical win
        for (int i=0 ; i<3; i++){
            if(field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")){
                return true;
            }
        }

        //Checks for -45 degree diagonal win "\"
        if(field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")){
            return true;
        }

        //Checks for 45 degree diagonal win "/"
        if(field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")){
            return true;
        }
        return false;
    }

    //If player 1 has won
    private void player1Wins(){
        player1Points++; //Increment player points
        Toast.makeText(this,"Player 1 wins!", Toast.LENGTH_SHORT).show(); //Display pop up message
        updatePointsText(); //Update the points

        //Delay reset by 1 second
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                resetBoard();
            }
        }, 1000);

    }

    //CPU has won
    private void player2Wins(){
        player2Points++; //Increment points
        Toast.makeText(this,"CPU wins!", Toast.LENGTH_SHORT).show(); //Display pop up message
        updatePointsText(); //Update the points

        //Delay reset by 1 second
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                resetBoard();
            }
        }, 1000);
    }

    //Game is a draw
    private void draw(){
        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show(); //Display pop up message

        //Delay reset by 1 second
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                resetBoard();
            }
        }, 1000);
    }

    //Update the points on the GUI
    private void updatePointsText(){
        textViewP1.setText("" + player1Points);
        textViewP2.setText("" + player2Points);
    }

    //Clears the board to start a new game
    private void resetBoard(){
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                buttons[i][j].setText("");
            }
        }
        counter=0;
        roundCount=0;
        player1Turn=true;
    }

    //Resets the entire game to start all over
    private void resetGame(){
        player1Points=0;
        player2Points=0;
        updatePointsText();
        resetBoard();
        Toast.makeText(this, "Game Reset!", Toast.LENGTH_SHORT).show();
    }
}
