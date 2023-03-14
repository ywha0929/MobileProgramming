package com.example.hello_goodbye;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button buttonExclamation;
    Button buttonGreeting;
    ConstraintLayout container;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonExclamation = findViewById(R.id.buttonExclamation);
        buttonGreeting = findViewById(R.id.buttonGreeting);
        container = findViewById(R.id.layoutContainer);
        container.setBackgroundResource(R.drawable.background);
        buttonGreeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                container.setBackgroundResource(R.drawable.exclamationbtn);
            }
        });
        buttonExclamation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                container.setBackgroundResource(R.drawable.greetimage);
            }
        });
    }
}