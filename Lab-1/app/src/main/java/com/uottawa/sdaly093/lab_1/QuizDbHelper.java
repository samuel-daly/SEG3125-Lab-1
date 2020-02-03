package com.uottawa.sdaly093.lab_1;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.uottawa.sdaly093.lab_1.QuizContract.*;

import java.util.ArrayList;
import java.util.List;


public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "SamQuiz";
    private static final int DATABASE_VERSION = 6;

    private SQLiteDatabase db;

    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER, " +
                QuestionsTable.COLUMN_CATEGORY_ID + " TEXT" +
                ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    private void fillQuestionsTable() {
        Question q1 = new Question("Which of the following classes of vehicles may carry a red light visible from the front?",
                "Emergency vehicles responding to calls",
                "Pleasure-type motor vehicles",
                "Commercial motor vehicles", 1, Question.CATEGORY_G1);
        addQuestion(q1);
        Question q2 = new Question("The use of alcohol or drugs affects the driver by impairing",
                "Nothing",
                "Vision, Judgment and Reaction Time",
                "Sense of humour", 2, Question.CATEGORY_G1);
        addQuestion(q2);
        Question q3 = new Question("At an intersection where there is a flashing amber (yellow) traffic light, you must",
                "Stop if making a right turn",
                "Continue at the same speed",
                "Slow down and proceed with caution", 3, Question.CATEGORY_G1);
        addQuestion(q3);
        Question q4 = new Question("You are driving up to an intersection where there is no signal light or police officer. " +
                "A pedestrian is in the crosswalk on your side of the street. You should",
                "Increase your speed and take the right of way",
                "Sound the horn to warn the pedestrian",
                "Stop and yield the right-of-way to the pedestrian", 3, Question.CATEGORY_G1);
        addQuestion(q4);
        Question q5 = new Question("When the traffic signal light facing you is red and you intend to go straight through the intersection, what must you do?",
                "Stop; then proceed only when the signal turns green and when the way is clear",
                "Stop; then proceed when the way is clear",
                "Slow down; then proceed when the way is clear", 1, Question.CATEGORY_G1);
        addQuestion(q5);
        Question q6 = new Question("As a Level 2 (G2) driver, your blood alcohol level must not be over",
                "0.05%",
                "0.00%",
                "0.02%", 2, Question.CATEGORY_G1);
        addQuestion(q6);
        Question q7 = new Question("On a highway, when may you drive at the maximum speed limit?",
                "At any time",
                "On any highway designed for one-way traffic",
                "If the condition of traffic and the highway permit such a speed to be driven safely", 3, Question.CATEGORY_G1);
        addQuestion(q7);
        Question q8 = new Question("As a G1 driver, you must be accompanied by a fully licensed driver with at least ____ of driving experience.",
                "five years",
                "three years",
                "four years", 3, Question.CATEGORY_G1);
        addQuestion(q8);
        Question q9 = new Question("When lights are required, drivers must switch from high-beam lights to low beams when following another vehicle",
                "within 60 meters (200 feet)",
                "within 120 meters (400 feet)",
                "within 130 meters (420 feet)", 2, Question.CATEGORY_G1);
        addQuestion(q9);
        Question q10 = new Question("If you want to pass a motorcycle, you should",
                "Pass just as you would with another car",
                "Turn on your high-beam lights before you pass",
                "use half of their lane to pass", 1, Question.CATEGORY_G1);
        addQuestion(q10);

        //Quiz2
        Question q11 = new Question("Which is the largest land animal in the world?",
                "Elephant",
                "Camel",
                "Hippo", 1, Question.CATEGORY_WORLD);
        addQuestion(q11);
        Question q12 = new Question("Which is the longest bridge in the world?",
                "Changhua-Kaohsiung Viaduct in Taiwan",
                "Dhola-Sadiya Bridge in India",
                "Danyang-Kunshan Grand Bridge in China", 3, Question.CATEGORY_WORLD);
        addQuestion(q12);
        Question q13 = new Question("Which is the highest mountain peak in the world?",
                "Mount Everest",
                "K2",
                "Kanchenjunga", 1, Question.CATEGORY_WORLD);
        addQuestion(q13);
        Question q14 = new Question("Which is the longest river in the world?",
                "Amazon",
                "Nile",
                "Brahamputra", 2, Question.CATEGORY_WORLD);
        addQuestion(q14);
        Question q15 = new Question("Which is the largest country in the world by area?",
                "China",
                "Russia",
                "Canada", 2, Question.CATEGORY_WORLD);
        addQuestion(q15);
        Question q16 = new Question("Which is the largest ocean in the world?",
                "Pacific Ocean",
                "Atlantic Ocean",
                "Indian Ocean", 1, Question.CATEGORY_WORLD);
        addQuestion(q16);
        Question q17 = new Question("Which is the largest continent by area?",
                "Africa",
                "Europe",
                "Asia", 3, Question.CATEGORY_WORLD);
        addQuestion(q17);
        Question q18 = new Question("Which country/region has the highest population density by area?",
                "Monaco",
                "Macau",
                "Hong Kong", 2, Question.CATEGORY_WORLD);
        addQuestion(q18);
        Question q19 = new Question("Which country has the highest population?",
                "China",
                "Russia",
                "India", 1, Question.CATEGORY_WORLD);
        addQuestion(q19);
        Question q20 = new Question("Which is the tallest building in the world?",
                "Eiffel Tower",
                "Burj Khalifa",
                "Sanghai Tower", 2, Question.CATEGORY_WORLD);
        addQuestion(q20);
    }

    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        cv.put(QuestionsTable.COLUMN_CATEGORY_ID, question.getCategoryID());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setCategoryID(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }

    public ArrayList<Question> getQuestions(String category) {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();

        String[] selectionArgs = new String[]{category};
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME +
                " WHERE " + QuestionsTable.COLUMN_CATEGORY_ID + " = ?", selectionArgs);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setCategoryID(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }
}