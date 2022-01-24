package com.example.multiplicationtable;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

public class CustomView extends View {
    Paint paint;
    Context context;
    private double table = 100;
    private int modulo = 500;

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
        ValueAnimator animation = ValueAnimator.ofFloat(0, 100);
        animation.setDuration(100000l); //one second
        animation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                table+=0.01;
                CustomView.this.invalidate();
            }
        });
        animation.start();
    }

    private void init() {
        paint = new Paint();
        int color = Color.WHITE;
        paint.setColor(color);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int r = Math.min(getWidth(), getHeight())-10;
        float startX = getWidth()/2-r/2;
        float startY = getHeight()/2-r/2;
        /*canvas.drawCircle(getWidth()/2,getHeight()/2,r/2,paint);*/
        /*canvas.drawOval(startX, startY, startX+r, startY+r,paint);*/
        for(int i=0;i<modulo;i++) {
            float x1 = (float)(getWidth()/2 + r/2*Math.sin(i*2*Math.PI/modulo));
            float y1 = (float)(getHeight()/2 - r/2*Math.cos(i*2*Math.PI/modulo));

            float x2 = (float)(getWidth()/2+r/2*Math.sin(i*2*Math.PI*table/modulo));
            float y2 = (float)(getHeight()/2 - r/2*Math.cos(i*2*Math.PI*table/modulo));
            canvas.drawLine(x1,y1,x2,y2,paint);
        }
    }
}
