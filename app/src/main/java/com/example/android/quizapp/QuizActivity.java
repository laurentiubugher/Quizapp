package com.example.android.quizapp;

import android.content.Intent;
import android.content.res.Resources;
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
    private RadioButton q1_rb_Tesla, q2_rb_i8, q7_rb_Lamborghini;
    private CheckBox q5CheckBox_RollsRoyce, q5CheckBox_Bentley, q5CheckBox_manufacturedInEngland, q5CheckBox_manufacturedInGermany, q6CheckBox_Porsche, q6CheckBox_Audi, q6CheckBox_i8, q6CheckBox_R8, q8CheckBox_RangeRover, q8CheckBox_Volvo, q8CheckBox_Sport, q8CheckBox_SUV;
    private EditText q3_EditText, q4_EditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //This gets the player name from the MainActivity

        Intent quiz = getIntent();
        playerName = quiz.getStringExtra("player_name");

        //This uses findViewById to assign the values to the global variables
        //Radio Buttons
        q1_rb_Tesla = findViewById(R.id.q1_rb_Tesla);
        q2_rb_i8 = findViewById(R.id.q2_rb_i8);
        q7_rb_Lamborghini = findViewById(R.id.q7_rb_Lamborghini);

        //Check Boxes
        q5CheckBox_RollsRoyce = findViewById(R.id.q5CheckBox_RollsRoyce);
        q5CheckBox_Bentley = findViewById(R.id.q5CheckBox_Bentley);
        q5CheckBox_manufacturedInEngland = findViewById(R.id.q5CheckBox_manufacturedInEngland);
        q5CheckBox_manufacturedInGermany = findViewById(R.id.q5CheckBox_manufacturedInGermany);
        q6CheckBox_Porsche = findViewById(R.id.q6CheckBox_Porsche);
        q6CheckBox_Audi = findViewById(R.id.q6CheckBox_Audi);
        q6CheckBox_i8 = findViewById(R.id.q6CheckBox_i8);
        q6CheckBox_R8 = findViewById(R.id.q6CheckBox_R8);
        q8CheckBox_RangeRover = findViewById(R.id.q8CheckBox_RangeRover);
        q8CheckBox_Volvo = findViewById(R.id.q8CheckBox_Volvo);
        q8CheckBox_Sport = findViewById(R.id.q8CheckBox_Sport);
        q8CheckBox_SUV = findViewById(R.id.q8CheckBox_SUV);

        //Edit texts
        q3_EditText = findViewById(R.id.q3_editText);
        q4_EditText = findViewById(R.id.q4_editText);
    }

    //This method shows the Toast message, when the check answers button is pressed
    public void submitAnswers(View v) {

        //Value answer for question 1
        if(q1_rb_Tesla.isChecked()){
            score += 1;
        }

        //Value answer for question 2
        if(q2_rb_i8.isChecked()){
            score += 1;
        }

        //Value answer for question 3
        String answerQ3 = q3_EditText.getText().toString();
        String correctAnswerQ3 = "Mercedes";
        if(answerQ3.matches(correctAnswerQ3)){
            score += 1;
        }

        //Value answer for question 4
        String answerQ4 = q4_EditText.getText().toString();
        String correctAnswerQ4 = "Lamborghini";
        if(answerQ4.matches(correctAnswerQ4)){
            score += 1;
        }

        //Value answer for question 5
        if(q5CheckBox_Bentley.isChecked() && q5CheckBox_manufacturedInEngland.isChecked() && !q5CheckBox_RollsRoyce.isChecked() && !q5CheckBox_manufacturedInGermany.isChecked()) {
            score += 1;
        }

        //Value answer for question 6
        if(q6CheckBox_Audi.isChecked() && q6CheckBox_R8.isChecked() && !q6CheckBox_Porsche.isChecked() && !q6CheckBox_i8.isChecked()) {
            score += 1;
        }

        //Value answer for question 7
        if(q7_rb_Lamborghini.isChecked()){
            score += 1;
        }

        //Value answer for question 8
        if(q8CheckBox_Volvo.isChecked() && q8CheckBox_SUV.isChecked() && !q8CheckBox_RangeRover.isChecked() && !q8CheckBox_Sport.isChecked()){
            score += 1;
        }

        //This shows the toast message when the check answers button is pressed.
        Resources resCongratsToast = getResources();
        String congratsToast = resCongratsToast.getString(R.string.congratsToastMessage, playerName, score);
        Resources resTryAgainToast = getResources();
        String tryAgainToast = resTryAgainToast.getString(R.string.tryAgainToastMessage, playerName, score);
        if(score < 6) {
            Toast.makeText(getApplicationContext(), tryAgainToast, Toast.LENGTH_LONG).show();
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
