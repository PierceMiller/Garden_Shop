package com.plantatree.plantatree;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.stream53.plantatree.plantatree.R;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class Quiz_Activity extends AppCompatActivity {

    public static final String EXTRA_SCORE = "extraScore";
    private static final long COUNTDOWN_IN_MILLIS = 31000;

    private TextView textViewQuestion;
    private TextView textViewScore;
    private TextView textViewQuestionCount;
    private TextView textViewQuestionCountDown;
    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private Button buttonConfirmNext;
    private ColorStateList textColorDefaultRb;
    private ColorStateList textColorDefaultCd;
    private CountDownTimer countDownTimer;
    private Quiz_Returns currentQuizQuestion;
    private List<Quiz_Returns> quizQuestionList;
    private int score;
    private int questionCounter;
    private int questionCountTotal;
    private long timeLeftInMillis;
    private boolean answered;
    public static boolean quizDone = false;
    public static int scoreCart;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.menu_Catalogue){

            Intent startTopic1 = new Intent (this, Catalogue_Activity.class);
            startActivity(startTopic1);

        }
        if(id == R.id.menu_Cart){

            Intent startTopic1 = new Intent (this, Shopping_Cart.class);
            startActivity(startTopic1);

        }
        if(id == R.id.menu_Quiz){

            Intent startTopic1 = new Intent (this, Quiz_Start.class);
            startActivity(startTopic1);

        }
        if(id == R.id.menu_Compare){

            Intent startTopic1 = new Intent (this, Image_Drag.class);
            startActivity(startTopic1);

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        /*findViewById, Finds a view that was identified by the android:id XML
        attribute that was processed in onCreate(Bundle), linking functions
        with the appropriate activity.*/

        textViewQuestion = findViewById(R.id.text_view_question);
        textViewScore = findViewById(R.id.text_view_score);
        textViewQuestionCount = findViewById(R.id.text_view_question_count);
        textViewQuestionCountDown = findViewById(R.id.text_view_count_down);

        rbGroup = findViewById(R.id.radio_group);
        rb1 = findViewById(R.id.radio_button1);
        rb2 = findViewById(R.id.radio_button2);
        rb3 = findViewById(R.id.radio_button3);
        buttonConfirmNext = findViewById(R.id.button_confirm_next);

        textColorDefaultRb = rb1.getTextColors();
        textColorDefaultCd = textViewQuestionCountDown.getTextColors();

        DBHelper_Quiz dbHelper = new DBHelper_Quiz(this);
        quizQuestionList = dbHelper.getAllQuestions();
        questionCountTotal = quizQuestionList.size();

        Collections.shuffle(quizQuestionList); //Shuffles the order of questions

        showNextQuestion();

        //NOTE TO SELF: Utilise Android Studio shortcuts more

        buttonConfirmNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!answered) {
                    if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked()) {
                        checkAnswer();
                    } else {
                        Toast.makeText(Quiz_Activity.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    showNextQuestion();
                }
            }
        });
    }

    private void showNextQuestion() {

        //The SetTextColor function sets the text colour for the specified device context to the specified colour.

        rb1.setTextColor(textColorDefaultRb);
        rb2.setTextColor(textColorDefaultRb);
        rb3.setTextColor(textColorDefaultRb);
        rbGroup.clearCheck();

        /*Rotates through all questions until the questionCounter equals
         * the questionCountTotal, which than calls the method finish,
         * and brings the user to the start of the quiz.*/

        if (questionCounter < questionCountTotal) {
            currentQuizQuestion = quizQuestionList.get(questionCounter);

            textViewQuestion.setText(currentQuizQuestion.getQuestion()); //ERROR LINE

            /*textViewQuestion = null */

            rb1.setText(currentQuizQuestion.getOption1());
            rb2.setText(currentQuizQuestion.getOption2());
            rb3.setText(currentQuizQuestion.getOption3());

            questionCounter++;
            textViewQuestionCount.setText("Question: " + questionCounter + "/" + questionCountTotal);
            answered = false;
            buttonConfirmNext.setText("Confirm");

            timeLeftInMillis = COUNTDOWN_IN_MILLIS;
            startCountDown();
        } else {
            finishQuiz();
        }
    }

    private void startCountDown() {

        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {

            @Override
            public void onTick(long l) {

                timeLeftInMillis = l;
                updateCountDownText();
            }

            @Override
            public void onFinish() {

                timeLeftInMillis = 0;
                updateCountDownText();
                checkAnswer();
            }

        }.start();
    }

    private void updateCountDownText() {

        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60; //gets what is left, after dividing by 60

        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        textViewQuestionCountDown.setText(timeFormatted);

        if (timeLeftInMillis < 11000 && timeLeftInMillis > 6000) {
            textViewQuestionCountDown.setTextColor(Color.parseColor("#FFA500")); //Orange
        }else if(timeLeftInMillis < 6000){
            textViewQuestionCountDown.setTextColor(Color.RED);
        }
        else {
            textViewQuestionCountDown.setTextColor((textColorDefaultCd));
        }
    }

    private void checkAnswer() {

        answered = true;

        countDownTimer.cancel();

        /*Checks the selected radio button, with the saved answer assigned to the question
         * saved within the 'DBHelper_Quiz'*/

        RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId());
        int answerNr = rbGroup.indexOfChild(rbSelected) + 1;

        if (answerNr == currentQuizQuestion.getAnswerNr()) {
            score++;
            textViewScore.setText("Score: " + score);
        }

        showSolution();
    }

    private void showSolution() {

        /*By default, set all text to red, upon confirmation*/

        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);

        switch (currentQuizQuestion.getAnswerNr()) {

            /* The correct answer is displayed via green text,
            while the other options, stay the default confirmation colour*/

            case 1:
                rb1.setTextColor(Color.GREEN);
                textViewQuestion.setText("Answer 1 is correct");
                break;

            case 2:
                rb2.setTextColor(Color.GREEN);
                textViewQuestion.setText("Answer 2 is correct");
                break;

            case 3:
                rb3.setTextColor(Color.GREEN);
                textViewQuestion.setText("Answer 3 is correct");
                break;
        }

        /*Changes the text button from confirm to 'Next' or 'Finish',
         * depending on where the user is, in the quiz*/

        if (questionCounter < questionCountTotal) {
            buttonConfirmNext.setText("Next");
        } else {
            buttonConfirmNext.setText("Finish");
        }
    }

    private void finishQuiz() {

        /*Calling finish() in anyway, is bad practice,
        setting a class that invokes finish is preferred*/

        Intent resultIntent = new Intent();
        resultIntent.putExtra(EXTRA_SCORE, score);
        setResult(RESULT_OK, resultIntent);

        quizDone = true;
        scoreCart = score;

        Toast.makeText(getApplicationContext(),
                "You have earned "+ score + "% off your order", Toast.LENGTH_SHORT).show();

        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}
