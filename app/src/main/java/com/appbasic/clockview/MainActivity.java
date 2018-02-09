package com.appbasic.clockview;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private int w, h;
    private int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
    private int fontSize = 0;
    private Rect rect = new Rect();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        h = metrics.heightPixels;
        w = metrics.widthPixels;


        RelativeLayout clock_img = (RelativeLayout) findViewById(R.id.clock_img);
        clock_img.getLayoutParams().height = (w * 60) / 100;
        clock_img.getLayoutParams().width = (w * 60) / 100;

        RelativeLayout clock_view = (RelativeLayout) findViewById(R.id.clock_view);
        clock_view.getLayoutParams().height = (w * 60) / 100;
        clock_view.getLayoutParams().width = (w * 60) / 100;
        clock_view.addView(new ClockView(getApplicationContext()));


        findViewById(R.id.click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NextActivity.class));
            }
        });
    }


    /*public class ClockView extends View {

        private int[] colors = { 0xFFFF0000, 0xFF0000FF, 0xFFA2BC13 };
        private Paint paint;
        private int radius = 0;
        private int height, width = 0;
        private boolean isInit;
        public ClockView(Context context) {
            super(context);
            //paint = new Paint();

        }

        public ClockView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public ClockView(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
        }


        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);


        }
        private void initClock() {
            height = getHeight();
            width = getWidth();
            int min = Math.min(height, width);
            radius = min / 2 - 50;
            paint = new Paint();
            isInit = true;


        }
        @Override
        protected void onDraw(Canvas canvas) {

            if (!isInit) {
                initClock();
            }

            drawHands(canvas);
            drawinnercircle(canvas);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(canvas.getWidth() / 2, canvas.getHeight() / 2, 12, paint);
            postInvalidateDelayed(500);
            invalidate();
        }

        private void drawinnercircle(Canvas canvas) {
            paint.reset();
            paint.setColor(getResources().getColor(android.R.color.white));
            paint.setStrokeWidth(5);
            paint.setStyle(Paint.Style.STROKE);
            paint.setAntiAlias(true);
            paint.setDither(true);
            int radious=width/3;
            canvas.drawCircle(width / 2, height / 2, radious  - 10, paint);
        }










        private void drawHands(Canvas canvas) {
            Calendar cal = Calendar.getInstance();

            float sec = cal.get(Calendar.SECOND);
            float min = cal.get(Calendar.MINUTE);
            float hour = cal.get(Calendar.HOUR_OF_DAY);



            paint.setColor(colors[0]);
            canvas.drawLine(width / 2, width / 2, (float) (width / 2 + (radius * 0.5f) * Math.cos(Math.toRadians((hour / 12.0f * 360.0f) - 90f))),
                    (float) (width / 2 + (radius * 0.5f) * Math.sin(Math.toRadians((hour / 12.0f * 360.0f) - 90f))), paint);
            canvas.save();

            paint.setColor(colors[1]);
            canvas.drawLine(width / 2, width / 2, (float) (width / 2 + (radius * 0.6f) * Math.cos(Math.toRadians((min / 60.0f * 360.0f) - 90f))),
                    (float) (width / 2 + (radius * 0.6f) * Math.sin(Math.toRadians((min / 60.0f * 360.0f) - 90f))), paint);
            canvas.save();

            paint.setColor(colors[2]);
            canvas.drawLine(width / 2, width / 2, (float) (width / 2 + (radius * 0.7f) * Math.cos(Math.toRadians((sec / 60.0f * 360.0f) - 90f))),
                    (float) (width / 2 + (radius * 0.7f) * Math.sin(Math.toRadians((sec / 60.0f * 360.0f) - 90f))), paint);
        }












    }*/


    public class ClockView extends View {

        private int height, width = 0;
        private int padding = 0;

        private int numeralSpacing = 0;
        private int radius = 0;
        private Paint paint;
        private boolean isInit;

        public ClockView(Context context) {
            super(context);
        }

        public ClockView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public ClockView(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
        }

        private void initClock() {
            height = getHeight();
            width = getWidth();
            fontSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 13,
                    getResources().getDisplayMetrics());
            padding = numeralSpacing + 50;
            int min = Math.min(height, width);
            radius = min / 2 - padding;
            paint = new Paint();
            isInit = true;


        }

        @Override
        protected void onDraw(Canvas canvas) {
            if (!isInit) {
                initClock();
            }
            drawCenter(canvas);
            drawHands(canvas);
            drawNumeral(canvas);
            drawinnercircle(canvas);
            postInvalidateDelayed(500);
            invalidate();
        }

        private void drawHands(Canvas canvas) {
            Calendar cal = Calendar.getInstance();
            float sec = cal.get(Calendar.SECOND);
            float min = cal.get(Calendar.MINUTE);
            float hour = cal.get(Calendar.HOUR_OF_DAY);

            canvas.drawLine(width / 2, height / 2, (float) (width / 2 + (radius * 0.5f) * Math.cos(Math.toRadians((hour / 12.0f * 360.0f) - 90f))),
                    (float) (width / 2 + (radius * 0.5f) * Math.sin(Math.toRadians((hour / 12.0f * 360.0f) - 90f))), paint);
            canvas.save();
            canvas.drawLine(width / 2, height / 2, (float) (width / 2 + (radius * 0.7f) * Math.cos(Math.toRadians((min / 60.0f * 360.0f) - 90f))),
                    (float) (width / 2 + (radius * 0.6f) * Math.sin(Math.toRadians((min / 60.0f * 360.0f) - 90f))), paint);
            canvas.drawLine(width / 2, height / 2, (float) (width / 2 + (radius * 0.7f) * Math.cos(Math.toRadians((sec / 60.0f * 360.0f) - 90f))),
                    (float) (height / 2 + (radius * 0.7f) * Math.sin(Math.toRadians((sec / 60.0f * 360.0f) - 90f))), paint);


            canvas.save();
        }

        private void drawCenter(Canvas canvas) {
            paint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(width / 2, height / 2, 8, paint);
        }

        private void drawinnercircle(Canvas canvas) {
            paint.reset();
            paint.setColor(getResources().getColor(android.R.color.white));
            paint.setStrokeWidth(3);
            paint.setStyle(Paint.Style.STROKE);
            paint.setAntiAlias(true);
            int radious = width / 3;
            canvas.drawCircle(width / 2, height / 2, radious + 2, paint);
        }

        private void drawNumeral(Canvas canvas) {
            paint.setTextSize(fontSize);

            for (int number : numbers) {
                String tmp = String.valueOf(number);
                paint.getTextBounds(tmp, 0, tmp.length(), rect);
                double angle = Math.PI / 6 * (number - 3);
                int radious = (width * 30) / 100;

                int x = (int) (width / 2 + Math.cos(angle) * radious - rect.width() / 2);
                int y = (int) (height / 2 + Math.sin(angle) * radious + rect.height() / 2);
                canvas.drawText(tmp, x, y, paint);
            }
        }


    }


}
