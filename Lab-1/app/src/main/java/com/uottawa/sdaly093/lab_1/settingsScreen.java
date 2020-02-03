package com.uottawa.sdaly093.lab_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class settingsScreen extends AppCompatActivity {
    public static int QUESTIONS;
    public static int PASSING_PERCENTAGE;

    private Spinner questionSpinner;
    private EditText percentageText;

    private String percentageString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_screen);

        String[] question = {"5", "6", "7", "8", "9", "10"};

        ArrayAdapter<String> questionAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, question);
        questionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        questionSpinner = findViewById(R.id.SpinnerQuestion);
        questionSpinner.setAdapter(questionAdapter);

        percentageText = findViewById(R.id.percentageText);


        //Sets status bar color
        Window window = getWindow();
        int beige = getResources().getColor(R.color.beiger);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(beige);
    }

    public void backButtonClick(View view){
        percentageString = percentageText.getText().toString();
        System.err.println(percentageString);

        if (percentageString.matches("[0-9]+") && (Integer.valueOf(percentageString) > 0 && Integer.valueOf(percentageString) <= 100)){
            QUESTIONS = Integer.valueOf(questionSpinner.getSelectedItem().toString());
            PASSING_PERCENTAGE = Integer.valueOf(percentageString);
            finish();
        }
        else{
            Toast.makeText(this, "Enter a valid percentage", Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public void finish() {


        super.finish();
        overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }
}
