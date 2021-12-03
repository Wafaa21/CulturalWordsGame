package com.barmej.culturalwordsgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AnswerActivity extends AppCompatActivity {

    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        textView=findViewById(R.id.answer_text);
        String answer=getIntent().getStringExtra("Question Answer Details");
        textView.setText(answer);

    }
    public void backButtonClicked(View view) {
        finish();
    }
}