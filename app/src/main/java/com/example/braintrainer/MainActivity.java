package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    Button goButton,button0,button1,button2,button3,playAgainButton;
    ArrayList<Integer> answer=new ArrayList<Integer>();
    int locationOfCurrectAnswer;
    TextView resultTextView,scoreTextView,sumTextView,timerTextView;
    int score=0;
    int numberOfQuestion=0;
    ConstraintLayout gameLayout;
    boolean gameState=false;


    public void playAgain(View view)
    {
        gameState=true;
        score = 0;
        numberOfQuestion = 0;
        timerTextView.setText("30s");
        scoreTextView.setText(score + " / " + numberOfQuestion);
        resultTextView.setText(null);
        newQuestion();
        playAgainButton.setVisibility(View.INVISIBLE);
        new CountDownTimer(30000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf((millisUntilFinished / 1000)+1) + "s");
            }

            @Override
            public void onFinish() {
                resultTextView.setText("Done!");
                timerTextView.setText("0s");
                playAgainButton.setVisibility(View.VISIBLE);
                gameState=false;
            }
        }.start();
    }


    public void chooseAnswer(View view){
        if(gameState)
        {
            if (Integer.toString(locationOfCurrectAnswer).equals(view.getTag().toString())) {
                resultTextView.setText("Correct :)");
                score++;
            } else {
                resultTextView.setText("Wrong :(");
            }
            numberOfQuestion++;
            scoreTextView.setText(score + " / " + numberOfQuestion);
            newQuestion();
        }
    }


    public void start(View view){
        goButton.animate().scaleX(0f).scaleY(0f).setDuration(200).start();
        goButton.setEnabled(false);
        gameState=true;
        gameLayout.setScaleX(0);
        gameLayout.setScaleY(0);
        gameLayout.setVisibility(View.VISIBLE);
        gameLayout.animate().scaleX(1f).scaleY(1f).setDuration(200).start();
        playAgain(findViewById(R.id.playAgainButton));
    }


    public void newQuestion()
    {
        Random rand=new Random();
        int a=rand.nextInt(41);
        int b=rand.nextInt(41);
        sumTextView.setText(a + " + " + b);
        locationOfCurrectAnswer=rand.nextInt(4);
        answer.clear();
        for(int i=0;i<4;i++)
        {
            if(i==locationOfCurrectAnswer)
                answer.add(a+b);
            else
            {
                int wrongAnswer=rand.nextInt(81);
                while(wrongAnswer==a+b)
                {
                    wrongAnswer=rand.nextInt(81);
                }
                answer.add(wrongAnswer);
            }
        }
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for(int i=0; i<gridLayout.getChildCount(); i++) {

            Button button = (Button) gridLayout.getChildAt(i);
            button.setText(Integer.toString(answer.get(i)));
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sumTextView=findViewById(R.id.sumTextView);
        button0=findViewById(R.id.button0);
        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
        resultTextView=findViewById(R.id.resultTextView);
        scoreTextView=findViewById(R.id.scoreTextView);
        timerTextView=findViewById(R.id.timerTextView);
        goButton=findViewById(R.id.goButton);
        goButton.setVisibility(View.VISIBLE);
        playAgainButton=findViewById(R.id.playAgainButton);
        gameLayout=findViewById(R.id.gameLayout);
        gameLayout.setVisibility(View.INVISIBLE);
    }
}
