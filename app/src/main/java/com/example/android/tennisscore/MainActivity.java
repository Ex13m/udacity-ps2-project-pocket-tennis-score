package com.example.android.tennisscore;

import android.content.pm.ActivityInfo;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.io.IOException;
import java.lang.reflect.Method;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;
import static com.daimajia.androidanimations.library.Techniques.StandUp;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // used variables and objects
    private int scorePlayerOne = 0;
    private int scorePlayerTwo = 0;
    private int gamePlayerOne = 0;
    private int gamePlayerTwo = 0;
    private int setPlayerOne = 0;
    private int setPlayerTwo = 0;
    private Button playerOnePointBtn;
    private Button playerTwoPointBtn;
    private Button resetBtn;
    private TextView scoreBigPlayerOneTxt;
    private TextView scoreBigPlayerTwoTxt;
    private TextView scoreSmallPlayerOneTxt;
    private TextView scoreSmallPlayerTwoTxt;
    private TextView gamePlayerOneTxt;
    private TextView gamePlayerTwoTxt;
    private TextView setPlayerOneTxt;
    private TextView setPlayerTwoTxt;
    private TextView appName;
//Object fo work with sound
    private SoundPool mSoundPool;
    AssetManager mAssetManager;
    int setSound,gameSound,resetSound,playerOneBtnSound,playerTwoBtnSound;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    /*portrait orientation is enable
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);*/

        setUpBtnAndTxt();
        startAnimationTitleApp(appName);

    }

//  method for saving variables in memory before rotation
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("scorePlayerOne", scorePlayerOne);
        outState.putInt("scorePlayerTwo", scorePlayerTwo);
        outState.putInt("gamePlayerOne", gamePlayerOne);
        outState.putInt("gamePlayerTwo", gamePlayerTwo);
        outState.putInt("setPlayerOne", setPlayerOne);
        outState.putInt("setPlayerTwo", setPlayerTwo);
    }

    //Method for extracting variables after rotation
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        scorePlayerOne = savedInstanceState.getInt("scorePlayerOne");
        scorePlayerTwo = savedInstanceState.getInt("scorePlayerTwo");
        gamePlayerOne = savedInstanceState.getInt("gamePlayerOne");
        gamePlayerTwo = savedInstanceState.getInt("gamePlayerTwo");
        setPlayerOne = savedInstanceState.getInt("setPlayerOne");
        setPlayerTwo = savedInstanceState.getInt("setPlayerTwo");
        scoreBigPlayerOneTxt.setText(String.valueOf(scorePlayerOne));
        scoreBigPlayerTwoTxt.setText(String.valueOf(scorePlayerTwo));
        scoreSmallPlayerOneTxt.setText(String.valueOf(scorePlayerOne));
        scoreSmallPlayerTwoTxt.setText(String.valueOf(scorePlayerTwo));
        gamePlayerOneTxt.setText(String.valueOf(gamePlayerOne));
        gamePlayerTwoTxt.setText(String.valueOf(gamePlayerTwo));
        setPlayerOneTxt.setText(String.valueOf(setPlayerOne));
        setPlayerTwoTxt.setText(String.valueOf(setPlayerTwo));
        displayForPlayerOne(scorePlayerOne);
        displayForPlayerTwo(scorePlayerTwo);
        startAnimationTitleApp(appName);
    }

    //Reaction on button control.
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.playerOnePointBtn:
                //Toast.makeText(this, "point for Player 1", Toast.LENGTH_SHORT).show();
                scorePlayerOne = scorePlayerOne + 1;
                displayForPlayerOne(scorePlayerOne);
                //animation of app tittle
                startAnimationBtn(playerOnePointBtn);
                //sound after tap
                playSound(playerOneBtnSound);
                break;
            case R.id.playerTwoPointBtn:
                //Toast.makeText(this, "point for Player 2", Toast.LENGTH_SHORT).show();
                scorePlayerTwo = scorePlayerTwo + 1;
                        displayForPlayerTwo(scorePlayerTwo);
                startAnimationBtn(playerTwoPointBtn);
                playSound(playerTwoBtnSound);
                break;
            case R.id.resetBtn:
                Toast.makeText(this, "Game was reseted !", Toast.LENGTH_SHORT).show();
                gameReset();
                startAnimationBtn(resetBtn);
                playSound(resetSound);
                break;

        }
    }

    //Method for calculating scoring points for player number one.
    private void displayForPlayerOne(int scorePlayer1) {
        if (scorePlayer1 == 1) {
            scoreBigPlayerOneTxt.setText(R.string.fifteen);
            scoreSmallPlayerOneTxt.setText(R.string.fifteen);

            startAnimationBigScore(scoreBigPlayerOneTxt);
        } else if (scorePlayer1 == 2) {
            scoreBigPlayerOneTxt.setText(R.string.thirty);
            scoreSmallPlayerOneTxt.setText(R.string.thirty);

            startAnimationBigScore(scoreBigPlayerOneTxt);
        } else if (scorePlayer1 == 3) {
            scoreBigPlayerOneTxt.setText(R.string.forty);
            scoreSmallPlayerOneTxt.setText(R.string.forty);

            startAnimationBigScore(scoreBigPlayerOneTxt);
        } else if ((scorePlayer1 > 3) & ((scorePlayerTwo) < 3)) {
            gamePlayerOne = gamePlayerOne + 1;
            gamePlayerOneTxt.setText(String.valueOf(gamePlayerOne));
            startAnimationGameAndSetTxt(gamePlayerOneTxt);
            Toast.makeText(this, "Player 1 win game!", Toast.LENGTH_SHORT).show();


            scorePlayerOne = 0;
            scorePlayerTwo = 0;
            scoreBigPlayerOneTxt.setText("0");
            startAnimationBigScore(scoreBigPlayerOneTxt);
            scoreBigPlayerTwoTxt.setText("0");
            startAnimationBigScore(scoreBigPlayerTwoTxt);
            scoreSmallPlayerOneTxt.setText("0");

            scoreSmallPlayerTwoTxt.setText("0");

            if ((gamePlayerOne >= 6) & ((gamePlayerOne - gamePlayerTwo) >= 2)) {
                setPlayerOne = setPlayerOne + 1;
                setPlayerOneTxt.setText(String.valueOf(setPlayerOne));
                startAnimationGameAndSetTxt(setPlayerOneTxt);
                playSound(setSound);
                Toast.makeText(this, "Player 1 win set!", Toast.LENGTH_SHORT).show();

                scorePlayerOne = 0;
                scorePlayerTwo = 0;
                scoreBigPlayerOneTxt.setText("0");
                startAnimationBigScore(scoreBigPlayerOneTxt);
                scoreBigPlayerTwoTxt.setText("0");
                startAnimationBigScore(scoreBigPlayerTwoTxt);
                scoreSmallPlayerOneTxt.setText("0");
                scoreSmallPlayerTwoTxt.setText("0");
                gamePlayerOne = 0;
                gamePlayerTwo = 0;
                gamePlayerOneTxt.setText("0");
                startAnimationGameAndSetTxt(gamePlayerOneTxt);
                gamePlayerTwoTxt.setText("0");
                startAnimationGameAndSetTxt(gamePlayerTwoTxt);
            }
        } else if ((scorePlayer1 > 3) & (scorePlayerTwo >= 3) & ((scorePlayer1 - scorePlayerTwo) == 1)) {
            scoreBigPlayerOneTxt.setText(R.string.ad);
            startAnimationBigScore(scoreBigPlayerOneTxt);
            scoreSmallPlayerOneTxt.setText(R.string.ad);
            scoreBigPlayerTwoTxt.setText("");
            startAnimationBigScore(scoreBigPlayerTwoTxt);
            scoreSmallPlayerTwoTxt.setText("");
        } else if ((scorePlayer1 > 3) & (scorePlayerTwo > 3) & (scorePlayer1 == scorePlayerTwo)) {
            scoreBigPlayerOneTxt.setText("D");
            startAnimationBigScore(scoreBigPlayerOneTxt);
            scoreSmallPlayerOneTxt.setText(R.string.deuce);
            scoreBigPlayerTwoTxt.setText("D");
            startAnimationBigScore(scoreBigPlayerTwoTxt);
            scoreSmallPlayerTwoTxt.setText(R.string.deuce);
        } else if ((scorePlayer1 > 3) & (scorePlayerTwo >= 3) & ((scorePlayer1 - scorePlayerTwo) == 2)) {
            gamePlayerOne = gamePlayerOne + 1;
            gamePlayerOneTxt.setText(String.valueOf(gamePlayerOne));
            startAnimationGameAndSetTxt(gamePlayerOneTxt);
            Toast.makeText(this, "Player 1 win game!", Toast.LENGTH_SHORT).show();
            scorePlayerOne = 0;
            scorePlayerTwo = 0;
            scoreBigPlayerOneTxt.setText("0");
            startAnimationBigScore(scoreBigPlayerOneTxt);
            scoreBigPlayerTwoTxt.setText("0");
            startAnimationBigScore(scoreBigPlayerTwoTxt);
            scoreSmallPlayerOneTxt.setText("0");
            scoreSmallPlayerTwoTxt.setText("0");
            if ((gamePlayerOne >= 6) & (gamePlayerOne - gamePlayerTwo >= 2)) {
                setPlayerOne = setPlayerOne + 1;
                setPlayerOneTxt.setText(String.valueOf(setPlayerOne));
                startAnimationGameAndSetTxt(setPlayerOneTxt);
                playSound(setSound);
                Toast.makeText(this, "Player 1 win set!", Toast.LENGTH_SHORT).show();
                scorePlayerOne = 0;
                scorePlayerTwo = 0;
                scoreBigPlayerOneTxt.setText("0");
                startAnimationBigScore(scoreBigPlayerOneTxt);
                scoreBigPlayerTwoTxt.setText("0");
                startAnimationBigScore(scoreBigPlayerTwoTxt);
                scoreSmallPlayerOneTxt.setText("0");
                scoreSmallPlayerTwoTxt.setText("0");
                gamePlayerOne = 0;
                gamePlayerTwo = 0;
                gamePlayerOneTxt.setText("0");
                startAnimationGameAndSetTxt(gamePlayerOneTxt);
                gamePlayerTwoTxt.setText("0");
                startAnimationGameAndSetTxt(gamePlayerTwoTxt);
            }
        }

    }

    //Method for calculating scoring points for player number Two.
    private void displayForPlayerTwo(int scorePlayer2) {
        if (scorePlayer2 == 1) {
            scoreBigPlayerTwoTxt.setText(R.string.fifteen);
            startAnimationBigScore(scoreBigPlayerTwoTxt);
            scoreSmallPlayerTwoTxt.setText(R.string.fifteen);
        } else if (scorePlayer2 == 2) {
            scoreBigPlayerTwoTxt.setText(R.string.thirty);
            startAnimationBigScore(scoreBigPlayerTwoTxt);
            scoreSmallPlayerTwoTxt.setText(R.string.thirty);
        } else if (scorePlayer2 == 3) {
            scoreBigPlayerTwoTxt.setText(R.string.forty);
            startAnimationBigScore(scoreBigPlayerTwoTxt);
            scoreSmallPlayerTwoTxt.setText(R.string.forty);
        } else if ((scorePlayer2 == 4) & (scorePlayerOne < 3)) {

            gamePlayerTwo = gamePlayerTwo + 1;
            gamePlayerTwoTxt.setText(String.valueOf(gamePlayerTwo));
            startAnimationGameAndSetTxt(gamePlayerTwoTxt);
            Toast.makeText(this, "Player 2 win game!", Toast.LENGTH_SHORT).show();
            scorePlayerOne = 0;
            scorePlayerTwo = 0;
            scoreBigPlayerOneTxt.setText("0");
            startAnimationBigScore(scoreBigPlayerOneTxt);
            scoreBigPlayerTwoTxt.setText("0");
            startAnimationBigScore(scoreBigPlayerTwoTxt);
            scoreSmallPlayerOneTxt.setText("0");
            scoreSmallPlayerTwoTxt.setText("0");

            if ((gamePlayerTwo >= 6) & (gamePlayerTwo - gamePlayerOne >= 2)) {
                setPlayerTwo = setPlayerTwo + 1;
                setPlayerTwoTxt.setText(String.valueOf(setPlayerTwo));
                startAnimationGameAndSetTxt(setPlayerTwoTxt);
                playSound(setSound);
                Toast.makeText(this, "Player 2 win set!", Toast.LENGTH_SHORT).show();
                scorePlayerOne = 0;
                scorePlayerTwo = 0;
                scoreBigPlayerOneTxt.setText("0");
                startAnimationBigScore(scoreBigPlayerOneTxt);
                scoreBigPlayerTwoTxt.setText("0");
                startAnimationBigScore(scoreBigPlayerTwoTxt);
                scoreSmallPlayerOneTxt.setText("0");
                scoreSmallPlayerTwoTxt.setText("0");
                gamePlayerOne = 0;
                gamePlayerTwo = 0;
                gamePlayerOneTxt.setText("0");
                startAnimationGameAndSetTxt(gamePlayerOneTxt);

                gamePlayerTwoTxt.setText("0");
                startAnimationGameAndSetTxt(gamePlayerTwoTxt);

            }


        } else if ((scorePlayer2 > 3) & (scorePlayerOne >= 3) & ((scorePlayer2 - scorePlayerOne) == 1)) {
            scoreBigPlayerOneTxt.setText("");
            startAnimationBigScore(scoreBigPlayerOneTxt);
            scoreSmallPlayerOneTxt.setText("");
            scoreBigPlayerTwoTxt.setText(R.string.ad);
            startAnimationBigScore(scoreBigPlayerTwoTxt);
            scoreSmallPlayerTwoTxt.setText(R.string.ad);
        } else if ((scorePlayer2 > 3) & (scorePlayerOne > 3) & (scorePlayer2 == scorePlayerOne)) {
            scoreBigPlayerOneTxt.setText("D");
            startAnimationBigScore(scoreBigPlayerOneTxt);
            scoreSmallPlayerOneTxt.setText(R.string.deuce);
            scoreBigPlayerTwoTxt.setText("D");
            startAnimationBigScore(scoreBigPlayerTwoTxt);
            scoreSmallPlayerTwoTxt.setText(R.string.deuce);
        } else if ((scorePlayer2 > 3) & (scorePlayerOne >= 3) & ((scorePlayer2 - scorePlayerOne) == 2)) {
            gamePlayerTwo = gamePlayerTwo + 1;
            gamePlayerTwoTxt.setText(String.valueOf(gamePlayerTwo));
            startAnimationGameAndSetTxt(gamePlayerTwoTxt);

            Toast.makeText(this, "Player 2 win game!", Toast.LENGTH_SHORT).show();
            scorePlayerOne = 0;
            scorePlayerTwo = 0;
            scoreBigPlayerOneTxt.setText("0");
            startAnimationBigScore(scoreBigPlayerOneTxt);
            scoreBigPlayerTwoTxt.setText("0");
            startAnimationBigScore(scoreBigPlayerTwoTxt);
            scoreSmallPlayerOneTxt.setText("0");
            scoreSmallPlayerTwoTxt.setText("0");

            if ((gamePlayerTwo >= 6) & (gamePlayerTwo - gamePlayerOne == 2)) {
                setPlayerTwo = setPlayerTwo + 1;
                setPlayerTwoTxt.setText(String.valueOf(setPlayerTwo));
                startAnimationGameAndSetTxt(setPlayerTwoTxt);
                playSound(setSound);
                Toast.makeText(this, "Player 2 win set!", Toast.LENGTH_SHORT).show();
                scorePlayerOne = 0;
                scorePlayerTwo = 0;
                scoreBigPlayerOneTxt.setText("0");
                startAnimationBigScore(scoreBigPlayerOneTxt);
                scoreBigPlayerTwoTxt.setText("0");
                startAnimationBigScore(scoreBigPlayerTwoTxt);
                scoreSmallPlayerOneTxt.setText("0");
                scoreSmallPlayerTwoTxt.setText("0");
                gamePlayerOne = 0;
                gamePlayerTwo = 0;
                gamePlayerOneTxt.setText("0");
                startAnimationGameAndSetTxt(gamePlayerOneTxt);
                gamePlayerTwoTxt.setText("0");
                startAnimationGameAndSetTxt(gamePlayerTwoTxt);

            }
        }
    }

    //Method for restarting the game.
    private void gameReset() {
        scorePlayerOne = 0;
        scorePlayerTwo = 0;
        gamePlayerOne = 0;
        gamePlayerTwo = 0   ;
        setPlayerOne = 0;
        setPlayerTwo = 0;
        scoreBigPlayerOneTxt.setText("0");
        startAnimationBigScore(scoreBigPlayerOneTxt);
        scoreBigPlayerTwoTxt.setText("0");
        startAnimationBigScore(scoreBigPlayerTwoTxt);
        scoreSmallPlayerOneTxt.setText("0");
        startAnimationGameAndSetTxt(scoreSmallPlayerOneTxt);
        scoreSmallPlayerTwoTxt.setText("0");
        startAnimationGameAndSetTxt(scoreSmallPlayerTwoTxt);
        gamePlayerOneTxt.setText("0");
        startAnimationGameAndSetTxt(gamePlayerOneTxt);
        gamePlayerTwoTxt.setText("0");
        startAnimationGameAndSetTxt(gamePlayerTwoTxt);
        setPlayerOneTxt.setText("0");
        startAnimationGameAndSetTxt(setPlayerOneTxt);
        setPlayerTwoTxt.setText("0");
        startAnimationGameAndSetTxt(setPlayerTwoTxt);
        startAnimationTitleApp(appName);
    }


    //The method searches for objects from by ID and connects them to the implements View.OnClickListener .
    //And SOUND setup

    private void setUpBtnAndTxt() {

        playerOnePointBtn = (Button) findViewById(R.id.playerOnePointBtn);
        playerTwoPointBtn = (Button) findViewById(R.id.playerTwoPointBtn);
        resetBtn = (Button) findViewById(R.id.resetBtn);
        scoreBigPlayerOneTxt = (TextView) findViewById(R.id.scoreBigPlayerOneTxt);
        scoreBigPlayerTwoTxt = (TextView) findViewById(R.id.scoreBigPlayerTwoTxt);
        scoreSmallPlayerOneTxt = (TextView) findViewById(R.id.scoreSmallPlayerOneTxt);
        scoreSmallPlayerTwoTxt = (TextView) findViewById(R.id.scoreSmallPlayerTwoTxt);
        gamePlayerOneTxt = (TextView) findViewById(R.id.gamePlayerOneTxt);
        gamePlayerTwoTxt = (TextView) findViewById(R.id.gamePlayerTwoTxt);
        setPlayerOneTxt = (TextView) findViewById(R.id.setPlayerOnwTxt);
        setPlayerTwoTxt = (TextView) findViewById(R.id.setPlayerTwoTxt);
        appName = (TextView) findViewById(R.id.appName);

        playerOnePointBtn.setOnClickListener(this);
        playerTwoPointBtn.setOnClickListener(this);
        resetBtn.setOnClickListener(this);
        scoreBigPlayerOneTxt.setOnClickListener(this);
        scoreBigPlayerTwoTxt.setOnClickListener(this);
        scoreSmallPlayerOneTxt.setOnClickListener(this);
        scoreSmallPlayerTwoTxt.setOnClickListener(this);
        gamePlayerOneTxt.setOnClickListener(this);
        gamePlayerTwoTxt.setOnClickListener(this);
        setPlayerOneTxt.setOnClickListener(this);
        setPlayerTwoTxt.setOnClickListener(this);

        //       ***************************
        //        sound setup
        mSoundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
        mAssetManager = getAssets();
        playerOneBtnSound = loadSound("player1.ogg");
        playerTwoBtnSound = loadSound("player2.ogg");
        resetSound = loadSound("kassa.mp3");
        setSound = loadSound("applause.mp3");
    }


    //**************************SOME ANIMATIONS***********************

    //View animation library - https://github.com/daimajia/AndroidViewAnimations
//Method for animated text (Big Score)
    private void startAnimationBigScore(View view) {
//without params
        YoYo.with(Techniques.StandUp).playOn(view);
//with params
//        YoYo.with(Techniques.FadeInUp)
//                .duration(900)
//                .repeat(1)
//                .playOn(view);
//
    }

    //Method for animated buttons
    private void startAnimationBtn(View view) {
        YoYo.with(Techniques.FadeIn).playOn(view);
    }

    //Method for animated Game and Set View
    private void startAnimationGameAndSetTxt(View view) {
        YoYo.with(Techniques.BounceInDown).playOn(view);
    }

    //Method for animated Title
    private void startAnimationTitleApp(View view) {
        YoYo.with(Techniques.SlideInUp).playOn(view);
    }
//************************************************************************


//*********************************SOME SOUND*****************************
    //Tutorial http://ru-code-android.livejournal.com/5268.html
    //Method for control sound file in TennisScore\app\src\main\assets
    private int loadSound(String fileName) {
        AssetFileDescriptor afd = null;
        try {
            afd = mAssetManager.openFd(fileName);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Couldn't load file '" + fileName + "'", Toast.LENGTH_SHORT).show();
            return -1;
        }
        return mSoundPool.load(afd, 1);
    }

//    Method for play sound
    protected void playSound(int sound) {
        if (sound > 0)
            mSoundPool.play(sound, 1, 1, 1, 0,1);

    }
//***************************************************************************
}
