package com.example.j202012195_1022;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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
    Double result;

    private void hideKeyboard() {
        InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

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
                        // 기능 추가 마우스 selection 위치에 삽입하도록 설정
                        edit1.getText().insert(edit1.getSelectionStart(), numBtns[idx].getText().toString());
                    }
                    else if (edit2.isFocused()) {
                        // 기능 추가 마우스 selection 위치에 삽입하도록 설정
                        edit2.getText().insert(edit2.getSelectionStart(), numBtns[idx].getText().toString());
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

        // 더하기 버튼 클릭 리스너 설정
        btnAdd.setOnClickListener(new View.OnClickListener() {
            // 클릭 이벤트 호출
            @Override
            public void onClick(View view){
                // 키보드 내리기 함수
                hideKeyboard();

                // 입력된 숫자를 가져온다.
                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();

                try{
                    // 정수 덧셈 실행 및 결과 출력
                    result = Double.parseDouble(num1) + Double.parseDouble(num2);
                    textResult.setText(String.format("%s + %s = %f", num1, num2, result));
                } catch(Exception e) {
                    Toast.makeText(getApplicationContext(), "입력 오류입니다.", Toast.LENGTH_SHORT).show();
                    textResult.setText("계산 결과 : ");
                }
            }
        });

        // 빼기 버튼 클릭 리스너 설정
        btnSub.setOnClickListener(new View.OnClickListener() {
            // 클릭 이벤트 호출
            @Override
            public void onClick(View view){
                // 키보드 내리기 함수
                hideKeyboard();

                // 입력된 숫자를 가져온다.
                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();
                // 정수 빼기 실행 및 결과 출력
                try {
                    result = Double.parseDouble(num1) - Double.parseDouble(num2);
                    textResult.setText(String.format("%s - %s = %f", num1, num2, result));
                } catch(Exception e) {
                    Toast.makeText(getApplicationContext(), "입력 오류입니다.", Toast.LENGTH_SHORT).show();
                    textResult.setText("계산 결과 : ");
                }
            }
        });

        // 곱하기 버튼 클릭 리스너 설정
        btnMul.setOnClickListener(new View.OnClickListener() {
            // 클릭 이벤트 호출
            @Override
            public void onClick(View view){
                // 키보드 내리기 함수
                hideKeyboard();

                // 입력된 숫자를 가져온다.
                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();
                // 정수 곱하기 실행 및 결과 출력
                try {
                    result = Double.parseDouble(num1) * Double.parseDouble(num2);
                    textResult.setText(String.format("%s * %s = %.2f", num1, num2, result));
                } catch(Exception e) {
                    Toast.makeText(getApplicationContext(), "입력 오류입니다.", Toast.LENGTH_SHORT).show();
                    textResult.setText("계산 결과 : ");
                }
            }
        });

        // 나누기 버튼 클릭 리스너 설정
        btnDiv.setOnClickListener(new View.OnClickListener() {
            // 클릭 이벤트 호출
            @Override
            public void onClick(View view){
                // 키보드 내리기 함수
                hideKeyboard();

                // 입력된 숫자를 가져온다.
                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();
                // 정수 나누기 실행 및 결과 출력
                try {
                    // divisionbyzero 에러 확인
                    if (num2.equals("0")) {
                        throw new ArithmeticException("0으로 나눌 수 없습니다."); // 명시적으로 예외를 발생시킴
                    }
                    result = Double.parseDouble(num1) / Double.parseDouble(num2);
                    textResult.setText(String.format("%s / %s = %.2f", num1, num2, result));
                } catch (ArithmeticException e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    textResult.setText("계산 결과 : ");
                } catch(Exception e) {
                    Toast.makeText(getApplicationContext(), "입력 오류입니다.", Toast.LENGTH_SHORT).show();
                    textResult.setText("계산 결과 : ");
                }
            }
        });
    }
}