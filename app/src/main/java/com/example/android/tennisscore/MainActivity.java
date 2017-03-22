package com.example.android.tennisscore;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    /*portrait orientation is enable
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);*/

        setUpBtnAndTxt();

    }


    //Reaction to button control.
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.playerOnePointBtn:
                //Toast.makeText(this, "point for Player 1", Toast.LENGTH_SHORT).show();
                scorePlayerOne = scorePlayerOne + 1;
                displayForPlayerOne(scorePlayerOne);
                startAnimationBtn(playerOnePointBtn);
                break;
            case R.id.playerTwoPointBtn:
                //Toast.makeText(this, "point for Player 2", Toast.LENGTH_SHORT).show();
                scorePlayerTwo = scorePlayerTwo + 1;
                displayForPlayerTwo(scorePlayerTwo);
                startAnimationBtn(playerTwoPointBtn);
                break;
            case R.id.resetBtn:
                Toast.makeText(this, "Game was reseted !", Toast.LENGTH_SHORT).show();
                gameReset();
                startAnimationBtn(resetBtn);
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

    //Method for calculating scoring points for player number one.
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
        gamePlayerTwo = 0;
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
    }

    //View animation library - https://github.com/daimajia/AndroidViewAnimations
//Method for animated text (Big Score)
    void startAnimationBigScore(View view) {
//without params
        YoYo.with(Techniques.StandUp).playOn(view);

//with params
//        YoYo.with(Techniques.FadeInUp)
//                .duration(900)
//                .repeat(1)
//                .playOn(view);
//
    }

    //View animation library - https://github.com/daimajia/AndroidViewAnimations
//Method for animated buttons
    void startAnimationBtn(View view) {


        YoYo.with(Techniques.FadeIn).playOn(view);


    }

    void startAnimationGameAndSetTxt(View view) {


        YoYo.with(Techniques.BounceInDown).playOn(view);


    }


    //The method searches for objects from by ID and connects them to the implements View.OnClickListener .

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

    }

}
