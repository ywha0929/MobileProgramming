package com.example.restaurantbooking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    Button resButton;
    CalendarView calendarView;
    TimePicker timePicker;
    EditText editTextDate;
    EditText editTextTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resButton = findViewById(R.id.resBtn);
        calendarView = findViewById(R.id.calendarView);
        timePicker = findViewById(R.id.timePicker);
        editTextDate = findViewById(R.id.editText1);
        editTextTime = findViewById(R.id.editText2);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                editTextDate.setText(""+i+"년 "+(i1+1)+"월 "+ i2+"일");
            }
        });
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i1) {
                editTextTime.setText(""+i+"시 "+i1+ "분");
            }
        });
        resButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("예약");
                builder.setMessage(editTextDate.getText().toString() +" "+ editTextTime.getText().toString()+ "에 예약하시겠습니까?");
                builder.setPositiveButton("예약", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(),
                                editTextDate.getText().toString() +" "+ editTextTime.getText().toString()+ "에 예약되었습니다.",Toast.LENGTH_LONG).show();

                    }
                });
                builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(),
                                "예약이 취소되었습니다.",Toast.LENGTH_LONG).show();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_option,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch(id) {
            case R.id.clear:
                Calendar c = Calendar.getInstance();
                calendarView.setDate(c.getTimeInMillis());
                timePicker.setHour(c.get(Calendar.HOUR));
                timePicker.setMinute(c.get(Calendar.MINUTE));
                editTextDate.setText(""+c.get(Calendar.YEAR)+"년 "+(c.get(Calendar.MONTH)+1)+"월 "+ (c.get(Calendar.DATE))+"일");
        }
        return super.onOptionsItemSelected(item);
    }

}