package com.company.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;


public class MainActivity extends Activity {
    int locationOfCorrectAnswer;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int score = 0;
    int numberOfQuestions;
    TextView checkView;
    TextView scoreView;
    TextView sumTextView;
    TextView secondView;
    Button tag0;
    Button tag1;
    Button tag2;
    Button tag3;
    Button playAgain;
    String  zero = "0s";

    public void playAgain(View view){
        score = 0;
        numberOfQuestions = 0;
        scoreView.setText("0 / 0");
        checkView.setText("");
        playAgain.setVisibility(View.INVISIBLE);


        new CountDownTimer(30010, 10) {
            @Override
            public void onTick(long l) {
                secondView = findViewById(R.id.secondView);
                int s = (int) (l/1000);
                secondView.setText(String.valueOf(s) +"."+ String.valueOf(l/10 - s*100) + "s");
            }

            @Override
            public void onFinish() {
                playAgain.setText("Play again !");
                playAgain.setVisibility(View.VISIBLE);
                secondView.setText(zero);
                checkView.setText("Your Score : " + Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
            }
        }.start();
    }

    public void generateQuestions(){
        Random random = new Random();

        int a = random.nextInt(21);
        int b = random.nextInt(21);

        sumTextView.setText(Integer.toString(a) + "+" + Integer.toString(b));

        locationOfCorrectAnswer = random.nextInt(4);
        answers.clear();
        int incorrectAnswer;

        for (int i = 0; i < 4; i++){

            if (i == locationOfCorrectAnswer){

                answers.add(a + b);

            }else {

                incorrectAnswer = random.nextInt(41);

                while (incorrectAnswer == a + b){

                    incorrectAnswer = random.nextInt(41);

                }
                answers.add(incorrectAnswer);
            }
        }
        tag0.setText(Integer.toString(answers.get(0)));
        tag1.setText(Integer.toString(answers.get(1)));
        tag2.setText(Integer.toString(answers.get(2)));
        tag3.setText(Integer.toString(answers.get(3)));
    }


    public void answerClick(View view){
        try {
            if (!secondView.getText().equals(zero)) {
                if (view.getTag().equals(Integer.toString(locationOfCorrectAnswer))) {
                    score++;
                    checkView.setText("Bereyn !");
                } else {
                    checkView.setText("You idiot !");
                }
                numberOfQuestions++;
                scoreView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
                generateQuestions();
            }
        } catch (Exception e) {
            checkView.setText("Please click on play button");
            e.printStackTrace();
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        sumTextView = findViewById(R.id.sumTextView);

        tag0 = findViewById(R.id.tag0);
        tag1 = findViewById(R.id.tag1);
        tag2 = findViewById(R.id.tag2);
        tag3 = findViewById(R.id.tag3);
        checkView = findViewById(R.id.checkView);
        scoreView = findViewById(R.id.scoreView);
        playAgain = findViewById(R.id.playAgain);
        generateQuestions();
        playAgain.setText("Start !");

    }
}