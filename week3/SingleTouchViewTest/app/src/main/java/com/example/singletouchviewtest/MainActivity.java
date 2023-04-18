package com.example.singletouchviewtest;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button buttonBlack;
    Button buttonRed;
    Button buttonGreen;
    Button buttonClear;
    SingleTouchView singleTouchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonBlack = findViewById(R.id.buttonBlack);
        buttonRed = findViewById(R.id.buttonRed);
        buttonGreen = findViewById(R.id.buttonGreen);
        buttonClear = findViewById(R.id.buttonClear);
        singleTouchView = findViewById(R.id.whiteBoard);
        buttonBlack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                singleTouchView.setColor(Color.BLACK);
            }
        });
        buttonRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                singleTouchView.setColor(Color.RED);
            }
        });
        buttonGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                singleTouchView.setColor(Color.GREEN);
            }
        });
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                singleTouchView.clearView();
            }
        });

    }
}