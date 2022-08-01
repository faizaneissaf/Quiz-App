package com.example.itask_quizapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Quiz_Page extends AppCompatActivity implements View.OnClickListener{

    TextView totalQtv;
    TextView question_tv;
    Button ansT,ansF;

    int score=0;
    int totalQuestion = QuestionAnswers.question.length;
    int currentQuestionIndex=0;
    String selectedAnswer="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_page);
        totalQtv=findViewById(R.id.tv_tq);
        question_tv=findViewById(R.id.tv_q);
        ansT=findViewById(R.id.btn_true);
        ansF=findViewById(R.id.btn_false);

        ansF.setOnClickListener(this);
        ansT.setOnClickListener(this);
        totalQtv.setText("Total Questions : "+totalQuestion);
        loadNewQuestion();
    }


    @Override
    public void onClick(View view) {
        Button clickedButton = (Button) view;
        if (clickedButton.getId()==R.id.btn_true){
            if (selectedAnswer.equals("True")){
                score++;
            }
            selectedAnswer = clickedButton.getText().toString();
            currentQuestionIndex++;
            loadNewQuestion();

        }else{
            selectedAnswer = clickedButton.getText().toString();
            currentQuestionIndex++;
            loadNewQuestion();
        }
    }
    void loadNewQuestion(){
        question_tv.setText(QuestionAnswers.question[currentQuestionIndex]);
        ansT.setText(QuestionAnswers.choices[currentQuestionIndex][0]);
        ansF.setText(QuestionAnswers.choices[currentQuestionIndex][1]);
        if (currentQuestionIndex==totalQuestion){
            finishQuiz();
            return;
        }
    }
    void finishQuiz(){
        String passStatus="";
        if (score>totalQuestion*0.60){
            passStatus="Passed";
        }else {
            passStatus="Failed";
        }
        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Score is "+score+" out of "+totalQuestion)
                .setPositiveButton("Restart", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        restartQuiz();
                    }
                })
                .setCancelable(false)
                .show();
        restartQuiz();

    }
    void restartQuiz(){
        score=0;
        currentQuestionIndex=0;
        loadNewQuestion();
    }
}