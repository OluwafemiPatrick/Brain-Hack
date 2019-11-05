package com.olarewajuoluwafemi34.rambo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class NumberGuess extends AppCompatActivity {

    TextView levelNum, line1, rightOrWrong, line4, line5;
    EditText userInput;
    Button bttn0, bttn1, bttn2, bttn3, bttn4,bttn5,
        bttn6, bttn7, bttn8, bttn9, bttnEnter;
    ImageButton bttnBack;

    int randomNumber;
    int buttonClick = 0;
    int currentLevel = 2;
    int currentScore = 0;
    int userGuess;
    int attemptsLeft;
    int numberOfTrials;

    String text2 = "the number is less than ";
    String text3 = "the number is greater than ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_guess);

        levelNum = findViewById(R.id.levelNumber);
        line1 = findViewById(R.id.line1);
        userInput = findViewById(R.id.userInput);
        rightOrWrong = findViewById(R.id.rightOrWrong);
        line4 = findViewById(R.id.line4);
        line5 = findViewById(R.id.line5);

      //  levelNum.setText("LEVEL 2");

        bttn0 = findViewById(R.id.bttn0);
        bttn1 = findViewById(R.id.bttn1);
        bttn2 = findViewById(R.id.bttn2);
        bttn3 = findViewById(R.id.bttn3);
        bttn4 = findViewById(R.id.bttn4);
        bttn5 = findViewById(R.id.bttn5);
        bttn6 = findViewById(R.id.bttn6);
        bttn7 = findViewById(R.id.bttn7);
        bttn8 = findViewById(R.id.bttn8);
        bttn9 = findViewById(R.id.bttn9);
        bttnBack = findViewById(R.id.bttnBack);
        bttnEnter = findViewById(R.id.bttnEnter);

        bttnEnter.setOnClickListener(onClickListener);
        bttnBack.setOnClickListener(BackButton);

        numberButtonsHandler();
        generateRandom();
    }

    private void numberButtonsHandler(){
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                userInput.append(b.getText().toString());
            }
        };
        bttn0.setOnClickListener(listener);
        bttn1.setOnClickListener(listener);
        bttn2.setOnClickListener(listener);
        bttn3.setOnClickListener(listener);
        bttn4.setOnClickListener(listener);
        bttn5.setOnClickListener(listener);
        bttn6.setOnClickListener(listener);
        bttn7.setOnClickListener(listener);
        bttn8.setOnClickListener(listener);
        bttn9.setOnClickListener(listener);
    }

    View.OnClickListener BackButton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            int length = userInput.getText().length();
            if (length > 0){
                userInput.getText().delete(length-1, length);
            } } };


    private void generateRandom(){

        levelNum.setText("Level "+currentLevel);
        numberOfTrials = currentLevel + 1;

        Random randInt = new Random();
        int numberRange = (int)Math.pow(2, currentLevel);
        randomNumber = randInt.nextInt(numberRange)+1;
        line4.setText(""+randomNumber);

        String text1 = "I have generated a number between 1 and " + numberRange +
                "\n You have " + numberOfTrials + " attempts to guess" ;
        line1.setText(text1);

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            userGuess = Integer.parseInt(userInput.getText().toString());
            numberOfTrials = currentLevel + 1;
            buttonClick = buttonClick + 1;

                if (userGuess == randomNumber){
                    Toast.makeText(getApplicationContext(), "Right ", Toast.LENGTH_SHORT).show();
                    currentLevel++;
                    generateRandom();
                }if (userGuess < randomNumber){
                    Toast.makeText(getApplicationContext(), "no is greater than guess ", Toast.LENGTH_SHORT).show();
                    numberOfTrials--;
                }
                if (userGuess > randomNumber){
                    Toast.makeText(getApplicationContext(), "no is less than guess ", Toast.LENGTH_SHORT).show();
                    numberOfTrials--;
                }

            }
    };

}


