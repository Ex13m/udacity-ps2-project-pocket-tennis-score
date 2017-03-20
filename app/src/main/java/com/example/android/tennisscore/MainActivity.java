package com.example.android.tennisscore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


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

        setUpBtnAndTxt();

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.playerOnePointBtn:
                //Toast.makeText(this, "point for Player 1", Toast.LENGTH_SHORT).show();
                scorePlayerOne = scorePlayerOne + 1;
                displayForPlayerOne(scorePlayerOne);
                break;
            case R.id.playerTwoPointBtn:
                //Toast.makeText(this, "point for Player 2", Toast.LENGTH_SHORT).show();
                scorePlayerTwo = scorePlayerTwo + 1;
                displayForPlayerTwo(scorePlayerTwo);
                break;
            case R.id.resetBtn:
                Toast.makeText(this, "Game was reseted !", Toast.LENGTH_SHORT).show();
                gameReset();
                break;

        }
    }

    private void displayForPlayerOne(int scorePlayer1) {
        if (scorePlayer1 == 1) {
            scoreBigPlayerOneTxt.setText("15");
            scoreSmallPlayerOneTxt.setText("15");
        } else if (scorePlayer1 == 2) {
            scoreBigPlayerOneTxt.setText("30");
            scoreSmallPlayerOneTxt.setText("30");
        } else if (scorePlayer1 == 3) {
            scoreBigPlayerOneTxt.setText("40");
            scoreSmallPlayerOneTxt.setText("40");
        } else if ((scorePlayer1> 3) & ((scorePlayer1-scorePlayerTwo)>1)){
            gamePlayerOne=gamePlayerOne+1;
            gamePlayerOneTxt.setText(String.valueOf(gamePlayerOne));
            Toast.makeText(this, "Player 1 win game!", Toast.LENGTH_SHORT).show();
            scorePlayerOne = 0;
            scorePlayerTwo = 0;
            scoreBigPlayerOneTxt.setText("0");
            scoreBigPlayerTwoTxt.setText("0");
            scoreSmallPlayerOneTxt.setText("0");
            scoreSmallPlayerTwoTxt.setText("0");
        }else if ((scorePlayer1> 3) & (scorePlayerTwo>3)&(scorePlayer1>scorePlayerTwo)){
            scoreBigPlayerOneTxt.setText("AD");
            scoreSmallPlayerOneTxt.setText("AD");
            scoreBigPlayerTwoTxt.setText("");
            scoreSmallPlayerTwoTxt.setText("");
        }else if ((scorePlayer1> 3) & (scorePlayerTwo>3)&(scorePlayer1==scorePlayerTwo)){
            scoreBigPlayerOneTxt.setText("");
            scoreSmallPlayerOneTxt.setText("");
            scoreBigPlayerTwoTxt.setText("");
            scoreSmallPlayerTwoTxt.setText("");}
        else{}

    }

    private void displayForPlayerTwo(int scorePlayer2) {
        if (scorePlayer2 == 1) {
            scoreBigPlayerTwoTxt.setText("15");
            scoreSmallPlayerTwoTxt.setText("15");
        } else if (scorePlayer2 == 2) {
            scoreBigPlayerTwoTxt.setText("30");
            scoreSmallPlayerTwoTxt.setText("30");
        } else if (scorePlayer2 == 3) {
            scoreBigPlayerTwoTxt.setText("40");
            scoreSmallPlayerTwoTxt.setText("40");
        } else if ((scorePlayer2== 4) & (scorePlayerOne<3)){

            gamePlayerTwo=gamePlayerTwo+1;
            gamePlayerTwoTxt.setText(String.valueOf(gamePlayerTwo));
            Toast.makeText(this, "Player 2 win game!", Toast.LENGTH_SHORT).show();
            scorePlayerOne = 0;
            scorePlayerTwo = 0;
            scoreBigPlayerOneTxt.setText("0");
            scoreBigPlayerTwoTxt.setText("0");
            scoreSmallPlayerOneTxt.setText("0");
            scoreSmallPlayerTwoTxt.setText("0");
        }else {}


    }



    private void gameReset() {
        scorePlayerOne = 0;
        scorePlayerTwo = 0;
        gamePlayerOne = 0;
        gamePlayerTwo = 0;
        setPlayerOne = 0;
        setPlayerTwo = 0;
        scoreBigPlayerOneTxt.setText("0");
        scoreBigPlayerTwoTxt.setText("0");
        scoreSmallPlayerOneTxt.setText("0");
        scoreSmallPlayerTwoTxt.setText("0");
        gamePlayerOneTxt.setText("0");
        gamePlayerTwoTxt.setText("0");
        setPlayerOneTxt.setText("0");
        setPlayerTwoTxt.setText("0");

    }

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


    private void oneSecondPause(){
        try {
            Thread.sleep(1000); //Приостанавливает поток на 1 секунду
        } catch (Exception e) {

        }
    }
}
