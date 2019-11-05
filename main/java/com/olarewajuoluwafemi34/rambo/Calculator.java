package com.olarewajuoluwafemi34.rambo;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Calculator extends AppCompatActivity implements View.OnClickListener{

    SharedPreferences prefs1, prefs2;
    SharedPreferences.Editor editor1, editor2;

    public String cacDataName = "MyCacData";
    public String cacDataLevel = "MyCacLevel";
    public String cacIntName = "MyCacString";
    public String cacIntLevel = "MyCacLevel";

    public static int cacHiScore;
    public static int cacHiLevel;

    private TextView num1, num2, displayLevel, operatorDis, displayScore;
    private Button choice1, choice2, choice3, choice4;
    int correctAnswer;
    double correctAnswer2;
    public static int currentScore = 0;
    public static int currentLevel = 1;

    int partA, partB, numberRange;
    int wrongAnswer1, wrongAnswer2, wrongAnswer3;
    double wrongAnswer4, wrongAnswer5, wrongAnswer6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        prefs1 = getSharedPreferences(cacDataName, MODE_PRIVATE);
        prefs2 = getSharedPreferences(cacDataLevel, MODE_PRIVATE);
        editor1 = prefs1.edit();
        editor2 = prefs2.edit();
        cacHiScore = prefs1.getInt(cacIntName, currentScore);
        cacHiLevel = prefs2.getInt(cacIntLevel, currentLevel);

        operatorDis = findViewById(R.id.operatorView);
        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
        displayLevel = findViewById(R.id.displayLevel);
        displayScore = findViewById(R.id.displayScore);
        choice1 = findViewById(R.id.ans1);
        choice2 = findViewById(R.id.ans2);
        choice3 = findViewById(R.id.ans3);
        choice4 = findViewById(R.id.ans4);

        choice1.setOnClickListener(this);
        choice2.setOnClickListener(this);
        choice3.setOnClickListener(this);
        choice4.setOnClickListener(this);

        displayLevel.setText("Level " + currentLevel);
        displayScore.setText("Score: " + currentScore);
        setQuestion();

    }

    private void setQuestion() {

        Random randInt = new Random();
        int chooseOperator = randInt.nextInt(3);

        switch (chooseOperator){
            case 0:
                numberRange = currentLevel * 3;
                partA = randInt.nextInt(numberRange)+1;
                partB = randInt.nextInt(numberRange)+1;
                num1.setText("" + partA);
                num2.setText("" + partB);
                operatorDis.setText("*");

                correctAnswer = partA * partB;
                wrongAnswer1 = correctAnswer + 10;
                wrongAnswer2 = correctAnswer - 10;
                wrongAnswer3 = correctAnswer + 4;
                buttonLayout();
                break;

            case 1:
                numberRange = 3 * ((currentLevel+5)*(currentLevel+4));
                partA = randInt.nextInt(numberRange)+1;
                partB = randInt.nextInt(numberRange)+2;
                num1.setText("" + partA);
                num2.setText("" + partB);
                operatorDis.setText("+");

                correctAnswer = partA + partB;
                wrongAnswer1 = correctAnswer + 4;
                wrongAnswer2 = correctAnswer - 4;
                wrongAnswer3 = correctAnswer + 10;
                buttonLayout();
                break;

            case 2:
                numberRange = 2 * ((currentLevel*3)*(currentLevel+2));
                partA = numberRange;
                partB = randInt.nextInt(numberRange)+2;
                num1.setText("" + partA);
                num2.setText("" + partB);
                operatorDis.setText("-");

                correctAnswer = partA - partB;
                wrongAnswer1 = correctAnswer + 4;
                wrongAnswer2 = correctAnswer - 4;
                wrongAnswer3 = correctAnswer + 10;
                buttonLayout();
                break;

           /* case 3:
                numberRange = currentLevel * (currentLevel * 3);
                partC = numberRange;
                partD = randInt.nextInt(numberRange)+1;
                num1.setText("" + partC);
                num2.setText("" + partD);
                operatorDis.setText("/");

                correctAnswer2 = partC / partD;
                wrongAnswer4 = correctAnswer + 4;
                wrongAnswer5 = correctAnswer - 4;
                wrongAnswer6 = correctAnswer + 10;
                divisionButtonLayout();
                break; */
        }
    }

    private void buttonLayout() {

            /* the Random object is used to create a new int variable(buttonLayout)
        which  then switch between its values(0-3). This ensures that correct and
        incorrect answers are ordered differently, and users don't keep tapping on
        the same button.
         */

        Random randInt = new Random();
        int buttonLayout = randInt.nextInt(4);
        switch (buttonLayout){

            case 0:
                choice1.setText("" + wrongAnswer1);
                choice2.setText("" + wrongAnswer2);
                choice3.setText("" + wrongAnswer3);
                choice4.setText("" + correctAnswer);
                break;
            case 1:
                choice1.setText("" + wrongAnswer2);
                choice2.setText("" + wrongAnswer3);
                choice3.setText("" + correctAnswer);
                choice4.setText("" + wrongAnswer1);
                break;
            case 2:
                choice1.setText("" + wrongAnswer3);
                choice2.setText("" + correctAnswer);
                choice3.setText("" + wrongAnswer1);
                choice4.setText("" + wrongAnswer2);
                break;
            case 3:
                choice1.setText("" + correctAnswer);
                choice2.setText("" + wrongAnswer1);
                choice3.setText("" + wrongAnswer2);
                choice4.setText("" + wrongAnswer3);
        }
    }

    private void divisionButtonLayout(){
        Random randInt = new Random();
        int buttonLayout = randInt.nextInt(4);
        switch (buttonLayout){

            case 0:
                choice1.setText("" + wrongAnswer4);
                choice2.setText("" + wrongAnswer5);
                choice3.setText("" + wrongAnswer6);
                choice4.setText("" + correctAnswer2);
                break;
            case 1:
                choice1.setText("" + wrongAnswer5);
                choice2.setText("" + wrongAnswer6);
                choice3.setText("" + correctAnswer2);
                choice4.setText("" + wrongAnswer4);
                break;
            case 2:
                choice1.setText("" + wrongAnswer6);
                choice2.setText("" + correctAnswer2);
                choice3.setText("" + wrongAnswer4);
                choice4.setText("" + wrongAnswer5);
                break;
            case 3:
                choice1.setText("" + correctAnswer2);
                choice2.setText("" + wrongAnswer4);
                choice3.setText("" + wrongAnswer5);
                choice4.setText("" + wrongAnswer6);
        }
    }

    @Override
    public void onClick(View v) {

        int answerGiven = 0;
        switch (v.getId()) {

            case R.id.ans1:
                answerGiven = Integer.parseInt("" + choice1.getText());
                break;

            case R.id.ans2:
                answerGiven = Integer.parseInt("" + choice2.getText());
                break;

            case R.id.ans3:
                answerGiven = Integer.parseInt("" + choice3.getText());
                break;

            case R.id.ans4:
                answerGiven = Integer.parseInt("" + choice4.getText());
        }
        updateScoreAndLevel(answerGiven);
        setQuestion();
    }

    private void updateScoreAndLevel(int answerGiven) {

        if(isCorrect(answerGiven)){
            for (int i = 1; i <= currentLevel; i++){
                currentScore = currentScore + i;
            }
            currentLevel++;
        }else {
            if (currentScore > cacHiScore){
                cacHiScore = currentScore;
                cacHiLevel = currentLevel;
                editor1.putInt(cacIntName, cacHiScore);
                editor1.commit();
                editor2.putInt(cacIntLevel, cacHiLevel);
                editor2.commit();
                Toast.makeText(getApplicationContext(), "NEW HIGH SCORE", Toast.LENGTH_SHORT).show();
            }
            currentScore = 0;
            currentLevel = 1;
        }
        displayLevel.setText("Level " + currentLevel);
        displayScore.setText("Score: " + currentScore);
    }

    private boolean isCorrect(int answerGiven) {

        boolean trueOrFalse;
        if(answerGiven == correctAnswer){
            trueOrFalse = true;
            Toast.makeText(getApplicationContext(), "CORRECT.", Toast.LENGTH_SHORT).show();
        }else {
            trueOrFalse = false;
            Toast.makeText(getApplicationContext(), "Oops,that was wrong!", Toast.LENGTH_SHORT).show();
        }
        return trueOrFalse;
    }
}
