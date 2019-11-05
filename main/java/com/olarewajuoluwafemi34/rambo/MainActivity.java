package com.olarewajuoluwafemi34.rambo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button bttnCalculator, numberGuess, memoryGame,
        aboutGame, instructions, bttnScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bttnCalculator = findViewById(R.id.bttnCalculator);
        bttnScore = findViewById(R.id.scoreButton);
        numberGuess = findViewById(R.id.bttnNumberGuess);
        memoryGame = findViewById(R.id.bttnMemoryGame);
        aboutGame = findViewById(R.id.bttnAbout);
        instructions = findViewById(R.id.bttnInstructions);

        bttnCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Calculator.class);
                startActivity(intent);
            }
        });

        bttnScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ScorePage.class);
                startActivity(intent);
            }
        });

        memoryGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MemoryGame.class);
                startActivity(intent);
            }
        });

        numberGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NumberGuess.class);
                startActivity(intent);
            }
        });

        aboutGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AboutGame.class);
                startActivity(intent);
            }
        });

        instructions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Instruction.class);
                startActivity(intent);
            }
        });

    }
}
