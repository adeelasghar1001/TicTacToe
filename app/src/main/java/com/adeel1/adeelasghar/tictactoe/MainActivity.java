package com.adeel1.adeelasghar.tictactoe;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends Activity implements View.OnClickListener{

    //Button array for the tic tac toe board
    private Button[][] buttons = new Button[3][3];

    //For whoever's turn it is
    private boolean player1Turn = true;

    //Round count which is limited to 9
    private int roundCount;

    //Player points
    private int player1Points;
    private int player2Points;

    //Display player points
    private TextView textViewP1;
    private TextView textViewP2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //Ensures layout is fullscreen
        setContentView(com.adeel1.adeelasghar.tictactoe.R.layout.activity_main);

        //Links textviews to the xml file
        textViewP1 = findViewById(com.adeel1.adeelasghar.tictactoe.R.id.textView_p1);
        textViewP2 = findViewById(com.adeel1.adeelasghar.tictactoe.R.id.textView_p2);

        //Assigns id to each button in button array
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                String buttonID= "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }

        //Declares reset button
        Button buttonrest = findViewById(com.adeel1.adeelasghar.tictactoe.R.id.reset_button);
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

        //If player 1 turn, set the selected button to X
        if(player1Turn){
            ((Button) v).setTextColor(Color.parseColor("#FF0000"));
            ((Button) v).setText("X");
        }
        //If player 2 turn, set the selected button to O
        else{
            ((Button) v).setTextColor(Color.parseColor("#0000FF"));
            ((Button) v).setText("O");
        }

        //Increment round count
        roundCount++;

        //Checks for win
        if(checkForWin()){
            if(player1Turn){ //Player 1 has won
                player1Wins();
            }else{ //Player 2 has won
                player2Wins();
            }
        }else if(roundCount==9){ //Draw because round count has reached 9
            draw();
        }else{ //Change player 1 turn to player 2 turn
            player1Turn = !player1Turn;
        }

    }

    //Checks for the win
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

        //Return false is no win
        return false;
    }

    //If player 1 has won
    private void player1Wins(){
        player1Points++; //Increment player 1 points
        Toast.makeText(this,"Player 1 wins!", Toast.LENGTH_SHORT).show(); //Display pop up message
        updatePointsText(); //Update the points

        //Wait 1 second before resetting board
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                resetBoard();
            }
        }, 1000);

    }

    //If player 2 has won
    private void player2Wins(){
        player2Points++; //Increment player 2 points
        Toast.makeText(this,"Player 2 wins!", Toast.LENGTH_SHORT).show(); //Display pop up message
        updatePointsText(); //Update the points

        //Wait 1 second before resetting board
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                resetBoard();
            }
        }, 1000);
    }

    //If game is a draw
    private void draw(){
        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show(); //Display pop up message

        //Wait 1 second before resetting board
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                resetBoard();
            }
        }, 1000);
    }

    //Updates the player points on the GUI
    private void updatePointsText(){
        textViewP1.setText("" + player1Points);
        textViewP2.setText("" + player2Points);
    }

    //Clears all the buttons of input and resets the round count
    private void resetBoard(){
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                buttons[i][j].setText("");
            }
        }

        roundCount=0;
        player1Turn=true;
    }

    //Resets the entire game and calls resetBoard() while also setting player points to 0
    private void resetGame(){
        player1Points=0;
        player2Points=0;
        updatePointsText();
        resetBoard();
        Toast.makeText(this, "Game Reset!", Toast.LENGTH_SHORT).show();
    }
}
