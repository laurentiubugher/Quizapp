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
    public void startQuiz(View view) {
        EditText name = findViewById(R.id.name_editText);
        String playerName = name.getText().toString();
        Intent QuizzActivity = new Intent(this, Quiz.class);
        QuizzActivity.putExtra("player_name", playerName);
        if (QuizzActivity.resolveActivity(getPackageManager()) != null) {
            MainActivity.this.startActivity(QuizzActivity);
        }
        MainActivity.this.startActivity(QuizzActivity);
    }
}
