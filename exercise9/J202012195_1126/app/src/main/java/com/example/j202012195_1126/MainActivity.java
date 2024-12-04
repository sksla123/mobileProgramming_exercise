package com.example.j202012195_1126;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    MyGraphicView myGraphicView;
    final static int LINE = 1, CIRCLE = 2, RECT = 3;
    static int curShape = LINE;
    static int curColor = Color.BLACK;
    static int lastIdx = 0;

    static List<DrawObject> drawObjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_main);
        myGraphicView = new MyGraphicView(this);
        setContentView(myGraphicView);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        drawObjects = new ArrayList<>();
    }

    public static class DrawObject {
        int startX, startY, stopX, stopY, color, shape;
        public DrawObject(int _startX, int _startY, int _stopX, int _stopY, int _color, int _shape) {
            startX = _startX;
            startY = _startY;
            stopX = _stopX;
            stopY = _stopY;
            color = _color;
            shape = _shape;
        }

        public int getStartX() {
            return startX;
        }

        public void setStartX(int startX) {
            this.startX = startX;
        }

        public int getStartY() {
            return startY;
        }

        public void setStartY(int startY) {
            this.startY = startY;
        }

        public int getStopX() {
            return stopX;
        }

        public void setStopX(int stopX) {
            this.stopX = stopX;
        }

        public int getStopY() {
            return stopY;
        }

        public void setStopY(int stopY) {
            this.stopY = stopY;
        }

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }

        public int getShape() {
            return shape;
        }

        public void setShape(int shape) {
            this.shape = shape;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        Menu objectMenu = menu.addSubMenu(0,1,0, "도형 선택하기");
        objectMenu.add(0, 11, 0, "선 그리기");
        objectMenu.add(0, 12, 0, "원 그리기");
        objectMenu.add(0, 13, 0, "사각형 그리기");

        Menu colorMenu = menu.addSubMenu(0,2,0,"색깔 선택하기");
        colorMenu.add(0,21,0, "검은색");
        colorMenu.add(0,22,0, "붉은색");
        colorMenu.add(0,23,0, "녹색");
        colorMenu.add(0,24,0, "파란색");

        menu.add(0,3,0, "화면 초기화");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case 11:
                curShape = LINE;
                return true;
            case 12:
                curShape = CIRCLE;
                return true;
            case 13:
                curShape = RECT;
                return true;
            case 21:
                curColor = Color.BLACK;
                return true;
            case 22:
                curColor = Color.RED;
                return true;
            case 23:
                curColor = Color.GREEN;
                return true;
            case 24:
                curColor = Color.BLUE;
                return true;
            case 3:
                drawObjects.clear();
                lastIdx = 0;
                myGraphicView.invalidate();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private static class MyGraphicView extends View {
//        public MyGraphicView(Context context) {
//            super(context);
//        }

        public MyGraphicView(MainActivity context) {
            super(context);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    int startX = (int) event.getX();
                    int startY = (int) event.getY();
                    DrawObject tmp = new DrawObject(startX, startY, -1, -1, curColor, curShape);
                    drawObjects.add(tmp);
                    break;
                case MotionEvent.ACTION_MOVE:
                    int stopX = (int) event.getX();
                    int stopY = (int) event.getY();
                    drawObjects.get(lastIdx).setStopX(stopX);
                    drawObjects.get(lastIdx).setStopY(stopY);
                    this.invalidate(); // 화면 갱신
                    break;
                case MotionEvent.ACTION_UP:
                    int _stopX = (int) event.getX();
                    int _stopY = (int) event.getY();
                    drawObjects.get(lastIdx).setStopX(_stopX);
                    drawObjects.get(lastIdx).setStopY(_stopY);
                    lastIdx += 1;
                    this.invalidate(); // 화면 갱신
                    break;
            }
            return true;
        }

        @Override
        public boolean onFilterTouchEventForSecurity(MotionEvent event) {
            return super.onFilterTouchEventForSecurity(event);
        }

        @Override
        protected void onDraw(@NonNull Canvas canvas) {
            super.onDraw(canvas);

//            Paint paint = new Paint();
//            paint.setAntiAlias(true);
//            paint.setColor(Color.GREEN);
//            canvas.drawLine(20,20,600,20, paint);
//
//            paint.setColor(Color.BLUE);
//            paint.setStrokeWidth(10);
//            canvas.drawLine(20,60,600,60, paint);
//
//            paint.setColor(Color.RED);
//            paint.setStrokeWidth(0);
//            paint.setStyle(Paint.Style.FILL);
//
//            Rect rect1 = new Rect(20, 100, 220, 300);
//            canvas.drawRect(rect1, paint);
//
//            paint.setStyle(Paint.Style.STROKE);
//            Rect rect2 = new Rect(260, 100, 460, 300);
//            canvas.drawRect(rect2, paint);
//
//            RectF rect3 = new RectF(500, 10, 700, 300);
//            canvas.drawRoundRect(rect3, 40, 40, paint);
//
//            canvas.drawCircle(120, 440, 100, paint);
//
//            paint.setStrokeWidth(0);
//            paint.setColor(Color.BLUE);
//            paint.setTextSize(60);
//            canvas.drawText("박준용", 20, 780, paint);

            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setStrokeWidth(5);

            for(DrawObject drawObj: drawObjects) {
                int startX = drawObj.getStartX();
                int startY = drawObj.getStartY();
                int stopX = drawObj.getStopX();
                int stopY = drawObj.getStopY();

                paint.setColor(drawObj.getColor());

                switch (drawObj.getShape()) {
                    case LINE:
                        canvas.drawLine(startX, startY, stopX, stopY, paint);
                        break;
                    case CIRCLE:
                        int radius = (int) Math.sqrt(Math.pow(startX - stopX, 2) + Math.pow(startY - stopY, 2));
                        canvas.drawCircle(startX, startY, radius, paint);
                        break;
                    case RECT:
                        Rect tmpRect = new Rect(startX, startY, stopX, stopY);
                        canvas.drawRect(tmpRect, paint);
                }
            }
        }

    }
}