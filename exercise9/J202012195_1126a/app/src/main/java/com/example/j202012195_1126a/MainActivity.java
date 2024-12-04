package com.example.j202012195_1126a;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.EmbossMaskFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    ImageButton ibZoomin, ibZoomout, ibRotate, ibBright, ibDark, ibGray;
    MyGraphicView myGraphicView;

    static float scale = 1;
    static int angle = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
//        setContentView(new MyGraphicView(this));
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        myGraphicView = new MyGraphicView(this);
        LinearLayout pictureLayout = (LinearLayout) findViewById(R.id.pictureLayout);
        pictureLayout.addView(myGraphicView);

        ibZoomin = (ImageButton) findViewById(R.id.ibZoomin);
        ibZoomout = (ImageButton) findViewById(R.id.ibZoomout);
        ibRotate = (ImageButton) findViewById(R.id.ibRotate);
        ibBright = (ImageButton) findViewById(R.id.ibBright);
        ibDark = (ImageButton) findViewById(R.id.ibDark);
        ibGray = (ImageButton) findViewById(R.id.ibGray);

        ibZoomin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scale += 0.1f;
                myGraphicView.invalidate();
            }
        });

        ibZoomout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scale -= 0.1f;
                myGraphicView.invalidate();
            }
        });

        ibRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                angle += 10;
                myGraphicView.invalidate();
            }
        });

        ibBright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private static class MyGraphicView extends View {
        public MyGraphicView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(@NonNull Canvas canvas) {
            super.onDraw(canvas);

            Bitmap picture = BitmapFactory.decodeResource(getResources(), R.drawable.lena256);
            int picX = ( getWidth() - picture.getWidth() ) / 2;
            int picY = ( getHeight() - picture.getHeight() ) / 2;
            int cenX = getWidth() / 2;
            int cenY = getHeight() / 2;

//            canvas.rotate(45, cenX, cenY);
//            canvas.translate(-100, -300);
//            canvas.scale(0.5f, 0.5f, cenX, cenY);
//            canvas.skew(0.3f, 0);
//            Paint paint = null;

//            Paint paint = new Paint();
////            BlurMaskFilter bMask = new BlurMaskFilter(30, BlurMaskFilter.Blur.NORMAL);
////            paint.setMaskFilter(bMask);
//
//            EmbossMaskFilter eMask = new EmbossMaskFilter(new float[] {3,3,3}, 0.5f, 5, 10);
//            paint.setColor(Color.GRAY);
//            paint.setMaskFilter(eMask);
//            canvas.drawCircle(cenX, cenY, 150, paint);
////            canvas.drawBitmap(picture, picX, picY, paint);
//            picture.recycle();

            Paint paint = new Paint();
            float[] array = {
                    2,0,0,0,-25,
                    0,2,0,0,-25,
                    0,0,2,0,-25,
                    0,0,0,1,0
            };
            ColorMatrix cm = new ColorMatrix(array);
            paint.setColorFilter(new ColorMatrixColorFilter(cm));

            paint = null;

            canvas.rotate(angle, cenX, cenY);
            canvas.scale(scale, scale, cenX, cenY);

            canvas.drawBitmap(picture, picX, picY, paint);
            picture.recycle();
        }
    }
}