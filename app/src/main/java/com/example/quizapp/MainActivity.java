package com.example.quizapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView totalQuestionsTextView;
    TextView questionTextView;
    Button optA, optB, optC, optD;
    Button submitBtn;

    int score=0;
    int totalQuestion = QnA.question.length;
    int i = 0;
    String selectedAnswer = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalQuestionsTextView = findViewById(R.id.total_question);
        questionTextView = findViewById(R.id.question);
        optA = findViewById(R.id.ans_A);
        optB = findViewById(R.id.ans_B);
        optC = findViewById(R.id.ans_C);
        optD = findViewById(R.id.ans_D);
        submitBtn = findViewById(R.id.submit_btn);

        optA.setOnClickListener(this);
        optB.setOnClickListener(this);
        optC.setOnClickListener(this);
        optD.setOnClickListener(this);
        submitBtn.setOnClickListener(this);

        totalQuestionsTextView.setText("Total questions : "+totalQuestion);

        loadNewQuestion();




    }

    @Override
    public void onClick(View view) {

        optA.setBackgroundColor(Color.WHITE);
        optB.setBackgroundColor(Color.WHITE);
        optC.setBackgroundColor(Color.WHITE);
        optD.setBackgroundColor(Color.WHITE);

        Button clickedButton = (Button) view;
        if(clickedButton.getId()==R.id.submit_btn){
            if(selectedAnswer.equals(QnA.correctAnswers[i])){
                score++;
            }
            i++;
            loadNewQuestion();


        }else{
            //choices button clicked
            selectedAnswer  = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.MAGENTA);

        }

    }

    void loadNewQuestion(){

        if(i == totalQuestion ){
            finishQuiz();
            return;
        }

        questionTextView.setText(QnA.question[i]);
        optA.setText(QnA.choices[i][0]);
        optB.setText(QnA.choices[i][1]);
        optC.setText(QnA.choices[i][2]);
        optD.setText(QnA.choices[i][3]);

    }

    void finishQuiz(){
        String passStatus = "";
        if(score > totalQuestion*0.60){
            passStatus = "Passed";
        }else{
            passStatus = "Failed";
        }

        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Score is "+ score+" out of "+ totalQuestion)
                .setPositiveButton("Restart",(dialogInterface, i) -> restartQuiz() )
                .setCancelable(false)
                .show();


    }

    void restartQuiz(){
        score = 0;
        i =0;
        loadNewQuestion();
    }

}