package com.example.j202012195_1008;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    // 텍스트 뷰 선언(전역 선언)
    TextView textView01;
    // 버튼 변수 선언
    Button button01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textView01 = (TextView) findViewById(R.id.textView01);
        button01 = (Button) findViewById(R.id.button01);

        // 텍스트 뷰 텍스트 색깔 설정
        textView01.setTextColor(Color.BLUE);

        // 버튼 클릭 대기 중
        button01.setOnClickListener(new View.OnClickListener() {
            // 버튼 클릭시 이벤트 발생
            @Override
            public void onClick(View view){
                // 텍스트 뷰 텍스트 설정
                textView01.setText("컴퓨터인공지능학부");
            }
        });
    }

}