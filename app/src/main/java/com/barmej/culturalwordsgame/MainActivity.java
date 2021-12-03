package com.barmej.culturalwordsgame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ImageView mImageViewQuestion;
    private Drawable questionChange;
    private Random random;
    private int randomQuestionIndex;
    private int [] QUESTIONS={
            R.drawable.icon_1,
            R.drawable.icon_2,
            R.drawable.icon_3,
            R.drawable.icon_4,
            R.drawable.icon_5,
            R.drawable.icon_6,
            R.drawable.icon_7,
            R.drawable.icon_8,
            R.drawable.icon_9,
            R.drawable.icon_10,
            R.drawable.icon_11,
            R.drawable.icon_12,
            R.drawable.icon_13};
    private String [] ANSWERS_DETAILS;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sh=getSharedPreferences("app_pref", MODE_PRIVATE);
        String appLang=sh.getString("app_lang","");
        if(!appLang.equals(""))// اول مايشتغل التطبيق بيتم استخدام لغة الجهاز ثم بعدها نحفظ اللغة اللي يختارها المستخدم
            LocalHelper.setLocale(this,appLang);

        setContentView(R.layout.activity_main);
        mImageViewQuestion=findViewById(R.id.questionImage);
        ANSWERS_DETAILS=getResources().getStringArray(R.array.answer_details);
        random=new Random();
    }

    private void showLanguageDialog(){
        AlertDialog alert=new AlertDialog.Builder(this)
                .setTitle("تغيير اللغة")
                .setItems(R.array.languages, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        String language = "ar";
                        switch (which) {
                            case 0:
                                language = "ar";
                                break;
                            case 1:
                                language = "en";
                                break;
                        }
                        saveLanguage(language);
                        LocalHelper.setLocale(MainActivity.this, language);
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);// ازالة كل الاكتفيتي السابقة
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);// تشغيل الاكتفيتي الجديدة
                        startActivity(intent);

                    }
                }).create();
        alert.show();
    }
    private void saveLanguage(String lang){ // حفظ اللغة الي اختارها المستخدم
        SharedPreferences sh=getSharedPreferences("app_pref", MODE_PRIVATE);
        SharedPreferences.Editor editor=sh.edit();
        editor.putString("app_lnag","lang");
        editor.apply();

    }



    public void showNewQuestion(){
        randomQuestionIndex=random.nextInt(QUESTIONS.length);
        questionChange= ContextCompat.getDrawable(this,QUESTIONS[randomQuestionIndex]);
        mImageViewQuestion.setImageDrawable(questionChange);
    }

    public void onChanegQuestionOnClick(View view){
        showNewQuestion();
    }
    public void onSolvClicked(View view){
        Intent intent=new Intent(MainActivity.this, AnswerActivity.class);
        intent.putExtra("Question Answer Details", ANSWERS_DETAILS[randomQuestionIndex] );
        startActivity(intent);
    }
    public void onShareClicked(View view){
        Intent intent = new Intent(MainActivity.this, ShareActivity.class);
        intent.putExtra("resId", QUESTIONS[randomQuestionIndex]);
        startActivity(intent);
    }
    public void changeLangClicked(View view){
        showLanguageDialog();
    }

}