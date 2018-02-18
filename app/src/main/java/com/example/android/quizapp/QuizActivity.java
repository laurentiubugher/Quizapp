package com.example.android.quizapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {
    //Create variables
    String playerName;
    int score = 0;
    private RadioButton q1C, q2B, q7D;
    private CheckBox q5CB1, q5CB2, q5CB3, q5CB4, q6CB1, q6CB2, q6CB3, q6CB4, q8CB1, q8CB2, q8CB3, q8CB4;
    private EditText q3eT, q4eT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //This gets the player name from the MainActivity

        Intent quiz = getIntent();
        playerName = quiz.getStringExtra("player_name");

        //This uses findViewById to assign the values to the global variables
        //Radio Buttons
        q1C = findViewById(R.id.q1C);
        q2B = findViewById(R.id.q2B);
        q7D = findViewById(R.id.q7D);

        //Check Boxes
        q5CB1 = findViewById(R.id.q5CB1);
        q5CB2 = findViewById(R.id.q5CB2);
        q5CB3 = findViewById(R.id.q5CB3);
        q5CB4 = findViewById(R.id.q5CB4);
        q6CB1 = findViewById(R.id.q6CB1);
        q6CB2 = findViewById(R.id.q6CB2);
        q6CB3 = findViewById(R.id.q6CB3);
        q6CB4 = findViewById(R.id.q6CB4);
        q8CB1 = findViewById(R.id.q8CB1);
        q8CB2 = findViewById(R.id.q8CB2);
        q8CB3 = findViewById(R.id.q8CB3);
        q8CB4 = findViewById(R.id.q8CB4);

        //Edit texts
        q3eT = findViewById(R.id.q3_editText);
        q4eT = findViewById(R.id.q4_editText);
    }

    //This method shows the Toast message, when the check answers button is pressed
    public void submitAnswers(View v) {

        //Value answer for question 1
        if(q1C.isChecked()){
            score += 1;
        }

        //Value answer for question 2
        if(q2B.isChecked()){
            score += 1;
        }

        //Value answer for question 3
        String answerQ3 = q3eT.getText().toString();
        String correctAnswerQ3 = "Mercedes";
        if(answerQ3.matches(correctAnswerQ3)){
            score += 1;
        }

        //Value answer for question 4
        String answerQ4 = q4eT.getText().toString();
        String correctAnswerQ4 = "Lamborghini";
        if(answerQ4.matches(correctAnswerQ4)){
            score += 1;
        }

        //Value answer for question 5
        if(q5CB2.isChecked() && q5CB3.isChecked() && !q5CB1.isChecked() && !q5CB4.isChecked()) {
            score += 1;
        }

        //Value answer for question 6
        if(q6CB2.isChecked() && q6CB4.isChecked() && !q6CB1.isChecked() && !q6CB3.isChecked()) {
            score += 1;
        }

        //Value answer for question 7
        if(q7D.isChecked()){
            score += 1;
        }

        //Value answer for question 8
        if(q8CB2.isChecked() && q8CB4.isChecked() && !q8CB1.isChecked() && !q8CB3.isChecked()){
            score += 1;
        }

        //This shows the toast message when the check answers button is pressed.
        String congratsToast = "Congratulations, " + playerName + "!";
        congratsToast += "\n\tYour score is " + score + " out of 8.";
        congratsToast += "\n\t\t\t\t\tWell done!";
        String tryAgain = "\t" + playerName + ", you failed!";
        tryAgain += "\nYour score is " + score + " out of 8.";
        tryAgain += "\n\t\t\t\t\tTry again!";
        if(score < 6) {
            Toast.makeText(getApplicationContext(), tryAgain, Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(getApplicationContext(), congratsToast, Toast.LENGTH_LONG).show();
        }
        //This prevents the score to add up if check answers button is pressed multiple times
        score = 0;
    }

    //This method restart the quiz, when the user presses the repeat button
    public void restartApp (View v) {
        Intent repeat = new Intent(this, MainActivity.class);
        startActivity(repeat);
    }

    //This method transfer the user to email, in order to share the results
    public void shareResults(View v) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));  // This ensures only email apps respond
        intent.putExtra(Intent.EXTRA_SUBJECT, "Cars Quiz score for " + playerName);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
