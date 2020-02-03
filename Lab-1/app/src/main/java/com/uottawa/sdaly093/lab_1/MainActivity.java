package com.uottawa.sdaly093.lab_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class MainActivity extends AppCompatActivity {
    public static final String CATEGORY = "category";
    public static final int REQUEST_CODE_QUIZ = 1;

    private Spinner spinnerCategory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinnerCategory = findViewById(R.id.categoriesSpinner);

        String[] difficultyLevels = Question.getCategories();

        ArrayAdapter<String> adapterDifficulty = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, difficultyLevels);
        adapterDifficulty.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapterDifficulty);




    }

    public void settingsClick(View view){
        Intent intent = new Intent();
        intent.setClass(this, settingsScreen.class);
        startActivity(intent);
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
    }



    public void startClick(View view){
        if (settingsScreen.PASSING_PERCENTAGE == 0 || settingsScreen.QUESTIONS == 0){
            settingsScreen.QUESTIONS = 10;
            settingsScreen.PASSING_PERCENTAGE = 50;
        }

        String category = spinnerCategory.getSelectedItem().toString();
        System.out.println(category);
        Intent intent = new Intent(MainActivity.this, questionActivity.class);
        intent.putExtra(CATEGORY, category);
        startActivityForResult(intent, REQUEST_CODE_QUIZ);
        overridePendingTransition(R.anim.right_in, R.anim.left_out);


    }


}
