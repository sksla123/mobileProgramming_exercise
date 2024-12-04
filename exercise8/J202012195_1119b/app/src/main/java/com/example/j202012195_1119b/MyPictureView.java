package com.example.j202012195_1119b;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyPictureView extends View {
    String imagePath = null;
    public MyPictureView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);


    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
//            canvas.drawBitmap(bitmap, 0,0, null);
            //가운데에 그리기
            int left = (canvas.getWidth() - bitmap.getWidth()) / 2;
            int top = (canvas.getHeight() - bitmap.getHeight()) / 2;
            canvas.drawBitmap(bitmap, left,top, null);
            bitmap.recycle();
        }
    }
}
