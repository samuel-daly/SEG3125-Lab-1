package com.uottawa.sdaly093.lab_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

public class questionActivity extends AppCompatActivity {

    private TextView questionText;
    private RadioGroup questionGroup;
    private RadioButton question1;
    private RadioButton question2;
    private RadioButton question3;
    private Button nextButton;

    private List<Question> questionList;
    private int questionCounter;
    private int questionCountTotal;
    private Question currentQuestion;

    private int score;
    private boolean answered;

    public static int SCORE;

    public int numberOfQuestions;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        int questionNumber = settingsScreen.QUESTIONS;
        System.out.println(questionNumber);

        questionText = findViewById(R.id.questionText);
        questionGroup = findViewById(R.id.questionGroup);
        question1 = findViewById(R.id.question1);
        question2 = findViewById(R.id.question2);
        question3 = findViewById(R.id.question3);
        nextButton = findViewById(R.id.nextButton);

        numberOfQuestions = settingsScreen.QUESTIONS;

        score = 0;
        SCORE = 0;

        Intent intent = getIntent();
        String category = intent.getStringExtra(MainActivity.CATEGORY);

        QuizDbHelper dbHelper = new QuizDbHelper(this);
        questionList = dbHelper.getQuestions(category);
        questionCountTotal = settingsScreen.QUESTIONS;
        Collections.shuffle(questionList);

        //Sets status bar color
        Window window = getWindow();
        int beige = getResources().getColor(R.color.beiger);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(beige);

        showNextQuestion();

    }

    public void nextButtonClick(View view){

        if (question1.isChecked() || question2.isChecked() || question3.isChecked()) {
            checkAnswer();
            showNextQuestion();
        } else {
            Toast.makeText(questionActivity.this, "Please select an answer", Toast.LENGTH_SHORT).show();
        }
    }

    private void showNextQuestion() {
        questionGroup.clearCheck();

        if (questionCounter < questionCountTotal) {
            currentQuestion = questionList.get(questionCounter);

            questionText.setText(currentQuestion.getQuestion());
            question1.setText(currentQuestion.getOption1());
            question2.setText(currentQuestion.getOption2());
            question3.setText(currentQuestion.getOption3());

            questionCounter++;
        } else {
            Intent intent = new Intent();
            intent.setClass(this, summaryScreen.class);
            startActivity(intent);
            overridePendingTransition(R.anim.right_in, R.anim.left_out);
            finish();
        }
    }


    private void checkAnswer() {
        RadioButton rbSelected = findViewById(questionGroup.getCheckedRadioButtonId());
        int answerNr = questionGroup.indexOfChild(rbSelected) + 1;

        if (answerNr == currentQuestion.getAnswerNr()) {
            score++;
            SCORE = score;
        }
    }
}
