package com.example.itask_quizapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Quiz_Page extends AppCompatActivity implements View.OnClickListener{

    TextView totalQtv;
    TextView question_tv,qnum;
    Button ansT,ansF;

    Dialog dialog;
    int score=0;
    int totalQuestion = QuestionAnswers.question.length;
    int currentQuestionIndex=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_page);
        totalQtv=findViewById(R.id.tv_tquestions);
        question_tv=findViewById(R.id.tv_q);
        qnum=findViewById(R.id.q_num);
        ansT=findViewById(R.id.btn_true);
        ansF=findViewById(R.id.btn_false);

        dialog=new Dialog(this);

        ansF.setOnClickListener(this);
        ansT.setOnClickListener(this);
        totalQtv.setText("Total Questions : "+totalQuestion);
        loadNewQuestion();
    }

    private void loadNewQuestion() {
        if (currentQuestionIndex==totalQuestion){
            finishQuiz();
            return;
        }
        question_tv.setText(QuestionAnswers.question[currentQuestionIndex]);
        qnum.setText(currentQuestionIndex+"");
    }

    private void finishQuiz() {
        dialog.setContentView(R.layout.result_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button rbtn=dialog.findViewById(R.id.restart_btn);
        Button cont=dialog.findViewById(R.id.btn_contribution);
        TextView rst=dialog.findViewById(R.id.r_tv);
        rst.setText(score+" / "+totalQuestion);
        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),DonateActivity.class);
                startActivity(intent);
                dialog.setCancelable(false);
            }
        });
        rbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score=0;
                currentQuestionIndex=0;
                loadNewQuestion();
                dialog.dismiss();
            }
        });
        dialog.show();
        dialog.setCancelable(false);
    }

    @Override
    public void onClick(View view) {
        Button clickedButton = (Button) view;
        String ans=clickedButton.getText().toString();
        String cans=QuestionAnswers.correctAnswers[currentQuestionIndex].toString();
        if(ans.equals(cans)){
            score++;
            currentQuestionIndex++;
        }
        else {
            currentQuestionIndex++;
        }
        loadNewQuestion();
    }
}