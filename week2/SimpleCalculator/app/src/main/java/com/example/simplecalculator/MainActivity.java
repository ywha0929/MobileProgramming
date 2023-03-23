package com.example.simplecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Button> buttonNumber = new ArrayList<>();
    List<Button> buttonOperator = new ArrayList<>();
    Button buttonC;
    Button buttonEqual;
    Integer Operand0;
    Integer Operand1;
    String Mode;
    int inputMode;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Operand0 = 0;
        Operand1 = 0;
        Mode = "None";
        inputMode = 0;
        textView = (TextView) findViewById(R.id.textView);
        for(int i = 0; i< 10;i++)
        {
            String buttonID = "button"+i;
            int resID = getResources().getIdentifier(buttonID,"id",getPackageName());
            Button thisButton = (Button) findViewById(resID);
            thisButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(textView.getText().toString().equals("Welcome") ||inputMode == 0)
                    {
                        textView.setText("");
                        inputMode = 1;
                    }
                    textView.append(thisButton.getText());
                }
            });
            buttonNumber.add(thisButton);

        }
        buttonOperator.add((Button) findViewById(R.id.buttonPlus));
        buttonOperator.add((Button) findViewById(R.id.buttonMinus));
        buttonOperator.add((Button) findViewById(R.id.buttonTimes));
        buttonOperator.add((Button) findViewById(R.id.buttonDivide));
        for(int i =0; i<buttonOperator.size(); i++)
        {
            buttonOperator.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Button thisButton = (Button) view;
                    Operand0 = Integer.parseInt(textView.getText().toString());
                    textView.setText("");
                    Mode = thisButton.getText().toString();
                }
            });
        }
        buttonEqual = (Button) findViewById(R.id.buttonEqual);
        buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Operand1 = Integer.parseInt(textView.getText().toString());
                String result = null;
                switch(Mode)
                {
                    case "+":
                        result = String.valueOf((Operand0+Operand1));
                        break;
                    case "-":
                        result = String.valueOf((Operand0-Operand1));
                        break;
                    case "/":
                        result = String.valueOf(((double)Operand0/(double)Operand1));
                        break;
                    case "*":
                        result = String.valueOf((Operand0*Operand1));
                        break;
                }
                textView.setText(result);
                Operand0 = 0;
                Operand1 = 0;
                inputMode = 0;
                Mode = "None";
            }
        });
        buttonC = (Button) findViewById(R.id.buttonC);
        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Operand0 = 0;
                Operand1 = 0;
                Mode = "None";
                textView.setText("");
            }
        });
    }
}