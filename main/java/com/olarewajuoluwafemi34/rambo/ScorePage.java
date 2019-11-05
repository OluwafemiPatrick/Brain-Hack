package com.olarewajuoluwafemi34.rambo;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import static com.olarewajuoluwafemi34.rambo.Calculator.cacHiLevel;
import static com.olarewajuoluwafemi34.rambo.Calculator.cacHiScore;
import static com.olarewajuoluwafemi34.rambo.MemoryGame.memoryHiLevel;
import static com.olarewajuoluwafemi34.rambo.MemoryGame.memoryHiScore;

public class ScorePage extends AppCompatActivity {

    SharedPreferences prefs;

    TextView calcLevel, calcScore, memLevel,
        memScore, numGuessLevel, numGuessScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_page);

        calcLevel = findViewById(R.id.calcLevel);
        calcScore = findViewById(R.id.memLevel);
        memLevel = findViewById(R.id.memLevel);
        memScore = findViewById(R.id.memScore);
        numGuessLevel = findViewById(R.id.numGuessLevel);
        numGuessScore = findViewById(R.id.numGuessScore);

        memScore.setText(""+memoryHiScore);
        memLevel.setText(""+memoryHiLevel);

        calcScore.setText(""+cacHiScore);
        calcLevel.setText(""+cacHiLevel);
    }
}
