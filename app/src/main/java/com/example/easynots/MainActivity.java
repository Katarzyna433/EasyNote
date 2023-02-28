package com.example.easynots;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    String value = "Hello it is your notes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initListeners();
    }

    private void initListeners() {
        Button loginbtn = findViewById(R.id.loginbtn);
        Button changelang=findViewById(R.id.changelanguage);
        TextView username = findViewById(R.id.username);
        TextView password = findViewById(R.id.password);
        //admin and admin

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, NoteActivity.class);
                if (username.getText().toString().equals("admin") && password.getText().toString().equals("admin")) {
                    Toast.makeText(MainActivity.this, R.string.login_success, Toast.LENGTH_SHORT).show();
                    intent.putExtra("key", value);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, R.string.login_failed, Toast.LENGTH_SHORT).show();
                }
            }
        });

        changelang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LanguageChanger.class);
                startActivity(intent);
            }
        });
    }
}