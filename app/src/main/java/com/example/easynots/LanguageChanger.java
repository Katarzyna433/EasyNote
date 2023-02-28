package com.example.easynots;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.button.MaterialButton;

public class LanguageChanger extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_changer);

        MaterialButton pl= findViewById(R.id.btn_pl);
        MaterialButton en= findViewById(R.id.btn_en);
        MaterialButton sp= findViewById(R.id.btn_sp);

        LanguageManager lang=new LanguageManager(this);
        Intent intent = new Intent(LanguageChanger.this, MainActivity.class);

        en.setOnClickListener(view->
        {
            lang.updateResource("en");
            recreate();
            startActivity(intent);
        });
        pl.setOnClickListener(view->
        {
            lang.updateResource("pl");
            recreate();
            startActivity(intent);
        });
        sp.setOnClickListener(view->
        {
            lang.updateResource("es");
            recreate();
            startActivity(intent);
        });
    }
}