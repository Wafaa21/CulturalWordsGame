package com.barmej.culturalwordsgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class ShareActivity extends AppCompatActivity {
    private ImageView mQuestionImage;
    public EditText mEditTextShareTitle;
  

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        mEditTextShareTitle=findViewById(R.id.edit_text_share_title);
        mQuestionImage=findViewById(R.id.image_view_question);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            int resId = bundle.getInt("resId");
            mQuestionImage.setImageResource(resId);
        }

        SharedPreferences sharep=getSharedPreferences("app pref", MODE_PRIVATE);
        String questionTitle=sharep.getString("share title","");// لحفظ الكلام اللي كتبه المستخدم سابقًا
        mEditTextShareTitle.setText(questionTitle);
    }
    public void shareClick(View view) {
        String questionTitle = mEditTextShareTitle.getText().toString();
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, questionTitle + "\n" + mEditTextShareTitle);
        shareIntent.setType("text/plain");
        startActivity(shareIntent);

        SharedPreferences sharep = getSharedPreferences("app pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharep.edit();
        editor.putString("share title", questionTitle);
        editor.apply();
    }
}