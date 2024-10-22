package com.example.j202012195_1008;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    // (전역 선언)
    // 텍스트 뷰 선언
    // TextView textView01;
    // 버튼 변수 선언
    // Button button01;
    // EditText 용 변수 선언
    // EditText editText01;
    // int button_click_counter = 0;

    // 계산기용 변수 선언 (전역 선언)
    EditText inputEdit1, inputEdit2;
    Button btnAdd, btnSub, btnMul, btnDiv;
    TextView textResult;
    String inputNum1, inputNum2;
    Double result; // 소수점 고려를 위해선 변경 필요

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setTitle("간단한 계산기");

        // 계산기용 변수 연결
        inputEdit1 = (EditText) findViewById(R.id.edit1);
        inputEdit2 = (EditText) findViewById(R.id.edit2);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnSub = (Button) findViewById(R.id.btnSub);
        btnMul = (Button) findViewById(R.id.btnMul);
        btnDiv = (Button) findViewById(R.id.btnDiv);
        textResult = (TextView) findViewById(R.id.textResult);

        // 더하기 버튼 클릭 리스너 설정
        btnAdd.setOnClickListener(new View.OnClickListener() {
            // 클릭 이벤트 호출
            @Override
            public void onClick(View view){
                // 키보드 내리기 함수
                hideKeyboard();
                
                // 입력된 숫자를 가져온다.
                inputNum1 = inputEdit1.getText().toString();
                inputNum2 = inputEdit2.getText().toString();
                try{
                    // 정수 덧셈 실행 및 결과 출력
                    result = Double.parseDouble(inputNum1) + Double.parseDouble(inputNum2);
                    textResult.setText(String.format("계산 결과: %.2f", result));
                } catch(Exception e) {
                    Toast.makeText(getApplicationContext(), "입력 오류입니다.", Toast.LENGTH_SHORT).show();
                    textResult.setText("계산 결과: ");
                }
            }
        });

        // 빼기 버튼 클릭 리스너 설정
        btnSub.setOnClickListener(new View.OnClickListener() {
            // 클릭 이벤트 호출
            @Override
            public void onClick(View view){
                // 입력된 숫자를 가져온다.
                inputNum1 = inputEdit1.getText().toString();
                inputNum2 = inputEdit2.getText().toString();
                // 정수 덧셈 실행 및 결과 출력
                try {
                    result = Double.parseDouble(inputNum1) - Double.parseDouble(inputNum2);
                    textResult.setText(String.format("계산 결과: %.2f", result));
                } catch(Exception e) {
                    Toast.makeText(getApplicationContext(), "입력 오류입니다.", Toast.LENGTH_SHORT).show();
                    textResult.setText("계산 결과: ");
                }
            }
        });

        // 곱하기 버튼 클릭 리스너 설정
        btnMul.setOnClickListener(new View.OnClickListener() {
            // 클릭 이벤트 호출
            @Override
            public void onClick(View view){
                // 입력된 숫자를 가져온다.
                inputNum1 = inputEdit1.getText().toString();
                inputNum2 = inputEdit2.getText().toString();
                // 정수 덧셈 실행 및 결과 출력
                try {
                    result = Double.parseDouble(inputNum1) * Double.parseDouble(inputNum2);
                    textResult.setText(String.format("계산 결과: %.2f", result));
                } catch(Exception e) {
                    Toast.makeText(getApplicationContext(), "입력 오류입니다.", Toast.LENGTH_SHORT).show();
                    textResult.setText("계산 결과: ");
                }
            }
        });


        // 나누기 버튼 클릭 리스너 설정
        btnDiv.setOnClickListener(new View.OnClickListener() {
            // 클릭 이벤트 호출
            @Override
            public void onClick(View view){
                // 입력된 숫자를 가져온다.
                inputNum1 = inputEdit1.getText().toString();
                inputNum2 = inputEdit2.getText().toString();
                // 정수 덧셈 실행 및 결과 출력

                try {
                    result = Double.parseDouble(inputNum1) / Double.parseDouble(inputNum2);
                    textResult.setText(String.format("계산 결과: %.2f", result));
                } catch(Exception e) {
                    Toast.makeText(getApplicationContext(), "입력 오류입니다.", Toast.LENGTH_SHORT).show();
                    textResult.setText("계산 결과: ");
                }
            }
        });
        // textView01 = (TextView) findViewById(R.id.textView01);
        // button01 = (Button) findViewById(R.id.button01);
        // editText01 = (EditText) findViewById(R.id.editText01);

        // 텍스트 뷰 텍스트 색깔 설정
        // textView01.setTextColor(Color.BLUE);

        // 버튼 클릭 대기 중
        // button01.setOnClickListener(new View.OnClickListener() {
        // 버튼 클릭시 이벤트 발생
        // @Override
        // public void onClick(View view){
        // button_click_counter += 1;
        // if (button_click_counter % 2 == 1) {
        // 텍스트 뷰 텍스트 설정
        // textView01.setText("컴퓨터인공지능학부");
        // }
        // else textView01.setText("전북대학교");
        // EditText에 입력한 값을 받아서 text 뷰의 값을 변경하라.
        //      String inputText01 = editText01.getText().toString();
        //     textView01.setText(inputText01);
        //  }
        //    });
        }

   void hideKeyboard() {
        InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
