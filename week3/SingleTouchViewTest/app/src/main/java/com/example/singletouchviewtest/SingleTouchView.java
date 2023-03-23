package com.example.singletouchviewtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SingleTouchView extends View {
//    private Path path = new Path();
    private List<Path> listPath = new ArrayList<>();
    private List<Paint> listPaint = new ArrayList<>();
    private Integer color = Color.BLACK;

    public SingleTouchView(Context context) {
        super(context);
    }

    public SingleTouchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SingleTouchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SingleTouchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for(int i = 0; i<listPath.size(); i++)
        {
            canvas.drawPath(listPath.get(i),listPaint.get(i));
        }
    }
    public void setColor(Integer Color)
    {
        this.color = Color;
        invalidate();
    }
    public void clearView()
    {
        listPath.clear();
        listPaint.clear();
        invalidate();
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float eventX = event.getX();
        float eventY = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Path thisPath = new Path();

                thisPath.moveTo(eventX,eventY);
                listPath.add(thisPath);
                Paint thisPaint = new Paint();
                thisPaint.setAntiAlias(true);
                thisPaint.setStrokeWidth(10f);
                thisPaint.setColor(color);
                thisPaint.setStyle(Paint.Style.STROKE);
                thisPaint.setStrokeJoin(Paint.Join.ROUND);
                listPaint.add(thisPaint);
                break;
            case MotionEvent.ACTION_MOVE:
                listPath.get(listPath.size()-1).lineTo(eventX,eventY);
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                return false;


        }
        invalidate();
        return true;
    }
}
