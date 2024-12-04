package com.example.j202012195_1119a;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Button btnRead, btnMkdir, btnRmdir, btnFilelist;
    EditText edtSD, edtFilelist;

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

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);

        btnRead = (Button) findViewById(R.id.btnRead);
        btnMkdir = (Button) findViewById(R.id.btnMkdir);
        btnRmdir = (Button) findViewById(R.id.btnRmdir);
        btnFilelist = (Button) findViewById(R.id.btnFilelist);
        edtSD = (EditText) findViewById(R.id.edtSD);
        edtFilelist = (EditText) findViewById(R.id.edtFilelist);

        final String strSDpath = Environment.getExternalStorageDirectory().getAbsolutePath();
        final File mydir = new File(strSDpath + "/mydir");

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    FileInputStream inFS = new FileInputStream("/storage/emulated/0/sd_test.txt");
                    byte[] txt = new byte[inFS.available()];
                    inFS.read(txt);
                    inFS.close();
                    edtSD.setText(new String(txt));
                } catch(IOException e) {
                    Toast.makeText(getApplicationContext(), "파일을 읽어올 수 없습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnMkdir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean f = mydir.mkdir();
                if (f) {
                    Toast.makeText(getApplicationContext(), mydir.getAbsolutePath() + "에 디렉토리가 생성되었습니다.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), mydir.getAbsolutePath() + "에 디렉토리를 생성하지 못했습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnRmdir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean f = mydir.delete();
                if (f) {
                    Toast.makeText(getApplicationContext(), mydir.getAbsolutePath() + "에 해당하는 디렉토리를 삭제했습니다.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), mydir.getAbsolutePath() + "에 해당하는 디렉토리를 삭제하지 못했습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnFilelist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sysDir = Environment.getRootDirectory().getAbsolutePath();
                File[] sysFiles = (new File(sysDir).listFiles());
                String strFname;
                for(int i = 0; i < sysFiles.length; i++) {
                    if (sysFiles[i].isDirectory()) {
                        strFname = "<폴더> ";
                    } else {
                        strFname = "<파일> ";
                    }
                    strFname += sysFiles[i].toString();

                    edtFilelist.setText(edtFilelist.getText() + "\n" + strFname);
                }
            }
        });
    }
}