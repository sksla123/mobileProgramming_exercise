package com.example.j202012195_1022;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText edit1, edit2;
    String num1, num2;

    Button[] numBtns = new Button[10];
    Integer[] numBtnID = {R.id.BtnNum0, R.id.BtnNum1, R.id.BtnNum2, R.id.BtnNum3, R.id.BtnNum4,
            R.id.BtnNum5, R.id.BtnNum6, R.id.BtnNum7, R.id.BtnNum8, R.id.BtnNum9};

    Button btnAdd, btnSub, btnMul, btnDiv;
    TextView textResult;
    Float result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 상태바 edge부터 시작할 것인가?
        EdgeToEdge.enable(this);
        // activity_main 기반으로 레이아웃을 불러와라
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

//        // activity_main 없이 코드로만 레이아웃을 만들어보자(activity_main 호출 코드 주석 처리)
//
//        // 레이아웃 기본 파라미터 설정
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.MATCH_PARENT);
//        // 레이아웃 구조 생성
//        LinearLayout baseLayout = new LinearLayout(this);
//        baseLayout.setOrientation(LinearLayout.VERTICAL);
//        baseLayout.setBackgroundColor(Color.GREEN);
//        // 생성한 레이아웃을 실제로 view로 띄움
//        setContentView(baseLayout, params);
//
//        // 버튼 기본 구조 생성
//        Button btn = new Button(this);
//        btn.setText("버튼입니다.");
//        btn.setBackgroundColor(Color.MAGENTA);
//        baseLayout.addView(btn);
//        // 버튼 클릭 리스너 생성
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(), "버튼을 클릭했습니다.", Toast.LENGTH_SHORT).show();
//            }
//        });

        // 실습 5-2
        // Edit 연결
        edit1 = (EditText) findViewById(R.id.Edit1);
        edit2 = (EditText) findViewById(R.id.Edit2);

        // for 문으로 button 연결
        for(int i = 0; i < numBtnID.length; i++) {
            final int idx = i;
            numBtns[i] = (Button) findViewById(numBtnID[i]);
            numBtns[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(edit1.isFocused()) {
                        String tempText = edit1.getText().toString();
                        String editText = tempText + numBtns[idx].getText().toString();
                        try{
                            Integer number = Integer.parseInt(tempText);
                            editText = number.toString();
                            edit1.setText(editText);
                        }
                        catch (NumberFormatException ex){
                            Toast.makeText(getApplicationContext(), "숫자만 입력해주세요.", Toast.LENGTH_SHORT).show();
                            edit1.setText(tempText);
                        }
                    }
                    else if (edit2.isFocused()) {
                        String tempText = edit2.getText().toString();
                        String editText = tempText + numBtns[idx].getText().toString();
                        try{
                            Integer number = Integer.parseInt(tempText);
                            editText = number.toString();
                            edit2.setText(editText);
                        }
                        catch (NumberFormatException ex){
                            Toast.makeText(getApplicationContext(), "숫자만 입력해주세요.", Toast.LENGTH_SHORT).show();
                            edit2.setText(tempText);
                        }
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "입력할 EditText를 선택하세요.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        btnAdd = (Button) findViewById(R.id.BtnAdd);
        btnSub = (Button) findViewById(R.id.BtnSub);
        btnMul = (Button) findViewById(R.id.BtnMul);
        btnDiv = (Button) findViewById(R.id.BtnDiv);

        textResult = (TextView) findViewById(R.id.TextResult);
    }
}