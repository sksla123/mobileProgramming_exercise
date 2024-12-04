// 간단한 읽기장 앱
package com.example.j202012195_1119;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    DatePicker datePicker;
    EditText edtDiary;
    Button btnWrite, btnRead;
    String filename;

    void readDiary(String filename) {
        String diaryStr = null;

        FileInputStream inFS;
        try{
            inFS = openFileInput(filename);
            byte[] txt = new byte[inFS.available()];
            inFS.read(txt);
            inFS.close();
            diaryStr = new String(txt).trim();
            edtDiary.setText(diaryStr);
            btnWrite.setText("수정하기");
        } catch(IOException e) {
            edtDiary.setText("");
            edtDiary.setHint("일기 없음");
            btnWrite.setText("새로 저장");
        }
        btnWrite.setEnabled(true);
//        return diaryStr;
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

        datePicker = (DatePicker) findViewById(R.id.datePicker1);
        edtDiary = (EditText) findViewById(R.id.edtDiary);
        btnWrite = (Button) findViewById(R.id.btnWrite);
        btnRead = (Button) findViewById(R.id.btnRead);

        Calendar cal = Calendar.getInstance();
        int cYear = cal.get(Calendar.YEAR);
        int cMonth = cal.get(Calendar.MONTH);
        int cDay = cal.get(Calendar.DAY_OF_MONTH);

        filename = Integer.toString(cYear) + '_' + Integer.toString(cMonth+1) + '_' + Integer.toString(cDay) + ".txt";
        readDiary(filename);

        datePicker.init(cYear, cMonth, cDay, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                filename = Integer.toString(i) + '_' + Integer.toString(i1+1) + '_' + Integer.toString(i2) + ".txt";
//                Toast.makeText(getApplicationContext(), filename, Toast.LENGTH_SHORT).show();
                readDiary(filename);
            }
        });

        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileOutputStream outFS = openFileOutput(filename, MODE_PRIVATE);
                    String str = edtDiary.getText().toString();
                    outFS.write(str.getBytes());
                    outFS.close();
                    Toast.makeText(getApplicationContext(), filename+"에 저장됨.", Toast.LENGTH_SHORT).show();
                } catch(IOException e) {
                    Toast.makeText(getApplicationContext(), "파일을 저장할 수 없습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputStream inS;
                try{
                    inS = getResources().openRawResource(R.raw.raw_test);
                    byte[] txt = new byte[inS.available()];
                    inS.read(txt);
                    inS.close();
                    edtDiary.setText(new String(txt).trim());
                } catch(IOException e) {
                    Toast.makeText(getApplicationContext(), "파일을 읽어올 수 없습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}