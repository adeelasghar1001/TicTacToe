package com.adeel1.adeelasghar.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class mainmenu extends AppCompatActivity {

    //Buttons declared for setting action
    private Button button;
    private Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(com.adeel1.adeelasghar.tictactoe.R.layout.activity_mainmenu);

        //Button set to go to multiplayer screen
        button = (Button) findViewById(com.adeel1.adeelasghar.tictactoe.R.id.twoplayer);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });

        //Button set to go to single player screen
        button1 = (Button) findViewById(com.adeel1.adeelasghar.tictactoe.R.id.single);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSingle();
            }
        });
    }

    //Opens up the Multiplayer
    public void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    //Opens up the Singleplayer
    public void openSingle(){
        Intent intent1 = new Intent(this,singleplayer.class);
        startActivity(intent1);
    }
}
