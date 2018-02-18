package com.example.android.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //** This method start the Quizz activity and pass the player name to other activities
    public void startQuiz(View v) {
        EditText name = findViewById(R.id.name_editText);
        String playerName = name.getText().toString();
        Intent quiz = new Intent(this, QuizActivity.class);
        quiz.putExtra("player_name", playerName);
        startActivity(quiz);
    }
}
