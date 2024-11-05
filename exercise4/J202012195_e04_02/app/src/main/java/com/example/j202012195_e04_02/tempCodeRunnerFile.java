package com.example.j202012195_e04_02;

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

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    EditText inpNum1, inpNum2;
    String num1, num2;
    Button btnAdd, btnSub, btnMul, btnDiv;
    TextView outRes;
    Double res;

    private void hideKeyboard() {
        InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

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

        inpNum1 = (EditText) findViewById(R.id.editTextText); inpNum2 = (EditText) findViewById(R.id.editTextText2);
        btnAdd = (Button) findViewById(R.id.button); btnSub = (Button) findViewById(R.id.button2); btnMul = (Button) findViewById(R.id.button3); btnDiv = (Button) findViewById(R.id.button4);
        outRes = (TextView) findViewById(R.id.textView);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard();

                num1 = inpNum1.getText().toString();
                num2 = inpNum2.getText().toString();

                try {
                    res = Double.parseDouble(num1) + Double.parseDouble(num2);
                    outRes.setText(String.format("계산 결과: %.3f", res));
                } catch(Exception e) {
                    Toast.makeText(getApplicationContext(), "입력 오류 입니다.", Toast.LENGTH_SHORT).show();
                    outRes.setText("계산 결과:");
                }
            }
        });

        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard();

                num1 = inpNum1.getText().toString();
                num2 = inpNum2.getText().toString();

                try {
                    res = Double.parseDouble(num1) - Double.parseDouble(num2);
                    outRes.setText(String.format("계산 결과: %.3f", res));
                } catch(Exception e) {
                    Toast.makeText(getApplicationContext(), "입력 오류 입니다.", Toast.LENGTH_SHORT).show();
                    outRes.setText("계산 결과:");
                }
            }
        });

        btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard();

                num1 = inpNum1.getText().toString();
                num2 = inpNum2.getText().toString();

                try {
                    res = Double.parseDouble(num1) * Double.parseDouble(num2);
                    outRes.setText(String.format("계산 결과: %.3f", res));
                } catch(Exception e) {
                    Toast.makeText(getApplicationContext(), "입력 오류 입니다.", Toast.LENGTH_SHORT).show();
                    outRes.setText("계산 결과:");
                }
            }
        });

        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard();

                num1 = inpNum1.getText().toString();
                num2 = inpNum2.getText().toString();

                try {
                    if (Double.parseDouble(num2) == 0.0) {
                        throw new Exception("0으로 나눌 수 없습니다.");
                    }
                    res = Double.parseDouble(num1) / Double.parseDouble(num2);
                    outRes.setText(String.format("계산 결과: %.3f", res));
                } catch(Exception e) {
                    if (Objects.equals(e.getMessage(), "0으로 나눌 수 없습니다.")) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    } else Toast.makeText(getApplicationContext(), "입력 오류 입니다.", Toast.LENGTH_SHORT).show();
                    outRes.setText("계산 결과:");
                }
            }
        });
    }
}