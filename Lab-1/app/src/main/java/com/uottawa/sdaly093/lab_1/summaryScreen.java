package com.uottawa.sdaly093.lab_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class summaryScreen extends AppCompatActivity {

    private TextView scoreText, passOrFailText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_screen);

        scoreText = findViewById(R.id.scoreText);
        passOrFailText = findViewById(R.id.passOrFailText);

        //Sets status bar color
        Window window = getWindow();
        int beige = getResources().getColor(R.color.beiger);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(beige);

        int passing_score = settingsScreen.PASSING_PERCENTAGE;
        int numberOfQuestion = settingsScreen.QUESTIONS;
        int score = questionActivity.SCORE;

        System.out.println(passing_score);
        System.out.println(numberOfQuestion);
        System.out.println(score);

        scoreText.setText(score + " / " + numberOfQuestion);

        double percentage = (Double.valueOf(score)/Double.valueOf(numberOfQuestion))*100.0;
        System.out.println(percentage);

        if (percentage >= passing_score){
            passOrFailText.setText("PASSED");
            passOrFailText.setTextColor(Color.GREEN);
        }
        else{
            passOrFailText.setText("FAILED");
            passOrFailText.setTextColor(Color.RED);
        }



    }

    public void backButtonClick(View view){
        finish();
    }

}
