package com.olarewajuoluwafemi34.rambo;

import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Random;

public class MemoryGame extends AppCompatActivity implements View.OnClickListener {

    SharedPreferences prefs3, prefs4;
    SharedPreferences.Editor editor3, editor4;

    public String memoryDataName = "MyMemoryData";
    public String memoryDataLevel = "MyMemoryLevel";
    public String memoryIntName = "MyMemoryString";
    public String memoryIntLevel = "MyMemoryLevel";
    public int defaultMemoryLevel = 3;
    public int defaultMemoryInt = 0;
    public static int memoryHiScore;
    public static int memoryHiLevel;


    private SoundPool soundPool;
    int sample1 = -1;
    int sample2 = -1;
    int sample3 = -1;
    int sample4 = -1;

    Animation wobble;
    TextView score, difficulty, watchGo;
    Button button1, button2, button3, button4, bttnReplay;

    int difficultyLevel = 3;
    int[] sequenceToCopy = new int[100];     // to hold the randomly-generated sequence

    private Handler myHandler;
    boolean playSequence = false;    //are we playing a sequence at the moment?
    int elementToPlay = 0;   //which element of the sequence are we on?

    //for checking the players answer
    int playerResponse, playerScore;
    boolean isResponding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_game);

        prefs3 = getSharedPreferences(memoryDataName, MODE_PRIVATE);
        prefs4 = getSharedPreferences(memoryDataLevel, MODE_PRIVATE);
        editor3 = prefs3.edit();
        editor4 = prefs4.edit();
        memoryHiScore = prefs3.getInt(memoryIntName, defaultMemoryInt);
        memoryHiLevel = prefs4.getInt(memoryIntLevel, defaultMemoryLevel);

        wobble = AnimationUtils.loadAnimation(this, R.anim.wobble);
        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        try {
            AssetManager assetManager = getAssets();
            AssetFileDescriptor descriptor;

            descriptor = assetManager.openFd("MGJump1.ogg");
            sample1 = soundPool.load(descriptor, 0);

            descriptor = assetManager.openFd("MGPowerUp1.ogg");
            sample2 = soundPool.load(descriptor, 0);

            descriptor = assetManager.openFd("MGJump2.ogg");
            sample3 = soundPool.load(descriptor, 0);

            descriptor = assetManager.openFd("MGPowerUp2.ogg");
            sample4 = soundPool.load(descriptor, 0);
        } catch (IOException e) { }

        score = findViewById(R.id.textScore);
        score.setText("Score: " + playerScore);
        difficulty = findViewById(R.id.textDifficulty);
        difficulty.setText("Level: " + difficultyLevel);
        watchGo = findViewById(R.id.textWatchGO);

        button1 = findViewById(R.id.bttn1);
        button2 = findViewById(R.id.bttn2);
        button3 = findViewById(R.id.bttn3);
        button4 = findViewById(R.id.bttn4);
        bttnReplay = findViewById(R.id.bttnReplay);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        bttnReplay.setOnClickListener(this);

        myHandler = new Handler(){
            public void handleMessage(Message msg){
                super.handleMessage(msg);

                if (playSequence){

                    /* since playSequence is false, all buttons are set to wobble.
                     * We switch based on what number is next in the sequence, wobble the
                     * appropriate button, and play the appropriate sound. elementToPlay
                     * is then incremented by 1 when the thread plays again in 1200ms*/

                    switch (sequenceToCopy[elementToPlay]){
                        case 1:
                            button1.startAnimation(wobble);
                            soundPool.play(sample1, 1, 1, 0, 0, 1);
                            break;
                        case 2:
                            button2.startAnimation(wobble);
                            soundPool.play(sample2, 1, 1, 0, 0, 1);
                            break;
                        case 3:
                            button3.startAnimation(wobble);
                            soundPool.play(sample3, 1, 1, 0, 0, 1);
                            break;
                        case 4:
                            button4.startAnimation(wobble);
                            soundPool.play(sample4, 1, 1, 0, 0, 1);
                            break;
                    }
                    elementToPlay ++;
                    if(elementToPlay == difficultyLevel){
                        sequenceFinished();
                    }
                }
                myHandler.sendEmptyMessageDelayed(0, 1200);
            }};

        myHandler.sendEmptyMessage(0);
        playASequence();
    }

    @Override
    public void onClick(View v) {

        if(!playSequence){
            switch (v.getId()){

                case R.id.bttn1:
                    soundPool.play(sample1, 1, 1, 0, 0, 1);
                    checkElement(1);
                    break;
                case R.id.bttn2:
                    soundPool.play(sample2, 1, 1, 0, 0, 1);
                    checkElement(2);
                    break;
                case R.id.bttn3:
                    soundPool.play(sample3, 1, 1, 0, 0, 1);
                    checkElement(3);
                    break;
                case R.id.bttn4:
                    soundPool.play(sample4, 1, 1, 0, 0, 1);
                    checkElement(4);
                    break;

                case R.id.bttnReplay:
                    difficultyLevel = 3;
                    playerScore = 0;
                    score.setText("Score: " + playerScore);
                    playASequence();
                    break;
            } }
    }

    private void checkElement(int thisElement) {

        if (isResponding) {
            int femi = difficultyLevel;

            playerResponse++;
            if (sequenceToCopy[playerResponse - 1] == thisElement) {
                playerScore = playerScore + ((thisElement + 1) * 2);
                score.setText("Score: " + playerScore);
                if (playerResponse == difficultyLevel) {
                    isResponding = false;
                    femi = difficultyLevel++;
                    difficulty.setText("Level: " + difficultyLevel);
                    playASequence();
                }
            } else {
                watchGo.setText("FAILED ");
                isResponding = false;

                if (playerScore > memoryHiScore) {
                    memoryHiScore = playerScore;
                    memoryHiLevel = femi;
                    editor3.putInt(memoryIntName, memoryHiScore);
                    editor4.putInt(memoryIntLevel, memoryHiLevel);
                    editor3.commit();
                    editor4.commit();
                    Toast.makeText(getApplicationContext(), "NEW HIGH SCORE", Toast.LENGTH_SHORT).show();
                }
            } }
    }

    private void playASequence() {

        /* we call createSequence to load our sequenceToCopy array, isResponding to
         * stop players from pressing keys while sequence is playing, playSequence
         * allows the code in the thread to run every 900ms*/

        createSequence();
        isResponding = false;
        elementToPlay = 0;
        playerResponse = 0;
        watchGo.setText("WATCH!");
        playSequence = true;
    }

    private void createSequence() {

        /* this method creates a sequence of random numbers from 1 to 4
         * which loops until length of difficultyLevel has been created.
         * The sequence is then stored in an array 'sequenceToCopy which
         * will be used to compare player's response.*/

        Random randInt = new Random();
        int ourRandom;

        for (int i=0; i<difficultyLevel; i++){
            ourRandom = randInt.nextInt(4);
            ourRandom ++;
            sequenceToCopy[i] = ourRandom;
        }
    }

    private void sequenceFinished() {

        /* playSequence to stop the code in the main thread from running, buttons are
         * set to visible, and isResponding is set to true*/

        playSequence = false;
        watchGo.setText("GO ");
        isResponding = true;
    }
}
