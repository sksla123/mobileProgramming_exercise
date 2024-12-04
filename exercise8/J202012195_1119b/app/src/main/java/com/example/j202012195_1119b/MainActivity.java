package com.example.j202012195_1119b;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    Button btnPrev, btnNext;
    MyPictureView myPictureView1;

    int maxNum, curNum=0;
    File[] imageFiles = new File[0];
    String imageFName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);

        btnPrev = (Button) findViewById(R.id.btnPrev);
        btnNext = (Button) findViewById(R.id.btnNext);
        myPictureView1 = (MyPictureView) findViewById(R.id.myPictureView1);

        File[] allFiles = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Pictures").listFiles();
        for (File allFile : allFiles) {
            if (allFile.isFile()) {
                imageFiles = Arrays.copyOf(imageFiles, imageFiles.length + 1);
                imageFiles[imageFiles.length - 1] = allFile;
            }
        }

        maxNum = imageFiles.length;
        imageFName = imageFiles[curNum].toString();
        myPictureView1.imagePath = imageFName;

        if (curNum <= 0) {
            btnPrev.setEnabled(false);
        }
        if (maxNum <= 1) {
            btnNext.setEnabled(false);
        }

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                curNum += maxNum - 1;
//                curNum %= maxNum; // 원형으로 돌려서 에러 해결
////                Toast.makeText(getApplicationContext(), "현 페이지: " + curNum, Toast.LENGTH_SHORT).show();
//                imageFName = imageFiles[curNum].toString();
//                myPictureView1.imagePath = imageFName;
//                myPictureView1.invalidate();
                if (curNum <= 0) {
                    Toast.makeText(getApplicationContext(), "첫번째 그림입니다.", Toast.LENGTH_SHORT).show();
                }
                else {
                    btnNext.setEnabled(true);
                    curNum --;
                    imageFName = imageFiles[curNum].toString();
                    myPictureView1.imagePath = imageFName;
                    myPictureView1.invalidate();
                }
                if (curNum <= 0) {
                    btnPrev.setEnabled(false);
                }
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                curNum += 1;
//                curNum %= maxNum; // 원형으로 돌려서 에러 해결
////                Toast.makeText(getApplicationContext(), "현 페이지: " + curNum, Toast.LENGTH_SHORT).show();
//                imageFName = imageFiles[curNum].toString();
//                myPictureView1.imagePath = imageFName;
//                myPictureView1.invalidate();

                if (curNum >= maxNum - 1) {
                    Toast.makeText(getApplicationContext(), "마지막 그림입니다.", Toast.LENGTH_SHORT).show();
                }
                else {
                    btnPrev.setEnabled(true);
                    curNum ++;
                    imageFName = imageFiles[curNum].toString();
                    myPictureView1.imagePath = imageFName;
                    myPictureView1.invalidate();
                }
                if (curNum >= maxNum - 1) {
                    btnNext.setEnabled(false);
                }
            }
        });
    }
}