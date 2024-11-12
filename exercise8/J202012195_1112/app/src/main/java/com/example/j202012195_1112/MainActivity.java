package com.example.j202012195_1112;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Button btn_read, btn_write;
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

        btn_write = (Button) findViewById(R.id.button);

        btn_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    FileOutputStream outfs = openFileOutput("file.txt", MODE_PRIVATE);
                    String str = "202012195 박준용";
                    outfs.write(str.getBytes());
                    outfs.close();
                    Toast.makeText(getApplicationContext(), "파일이 정상적으로 생성됨.", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(), "파일 출력 에러", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_read = (Button) findViewById(R.id.button2);

        btn_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    FileInputStream infs = openFileInput("file.txt");
                    byte[] txt = new byte[infs.available()];
                    infs.read(txt);
                    String str = new String(txt);
                    infs.close();
                    Toast.makeText(getApplicationContext(), "파일 내용: " + str, Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(), "파일 읽기 오류", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}