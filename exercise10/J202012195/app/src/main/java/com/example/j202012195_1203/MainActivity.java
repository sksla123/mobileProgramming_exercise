package com.example.j202012195_1203;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button btn;
    EditText editText1,editText2;
    TextView resultText;
    ActivityResultLauncher addLauncher;
    Button btnCall, btnWeb, btnMap, btnBrowse, btnMsg, btnCamera;

    protected void callIntent(String action, String strUri){
        Uri uri = Uri.parse(strUri);
        Intent intent = new Intent(action, uri);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            int addRes = data.getIntExtra("addedResult", 0);
            resultText.setText("결과: (lauched by ARL) " + addRes);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        android.util.Log.i("MainActivity", "OnCreate()");
        // EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        btn = (Button) findViewById(R.id.button);

        editText1 = (EditText) findViewById(R.id.editText1);
        editText2 = (EditText) findViewById(R.id.editText2);
        resultText = (TextView) findViewById(R.id.textView3);
        addLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult()
                , result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        int addRes = result.getData().getIntExtra("addedResult", 0);
                        resultText.setText("결과: " + addRes);
                    }
                });

        btnCall = (Button) findViewById(R.id.button4);
        btnWeb = (Button) findViewById(R.id.button5);
        btnMap = (Button) findViewById(R.id.button6);
        btnBrowse = (Button) findViewById(R.id.button7);
        btnMsg = (Button) findViewById(R.id.button8);
        btnCamera = (Button) findViewById(R.id.button9);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                intent.putExtra("num1", Integer.parseInt(editText1.getText().toString()));
                intent.putExtra("num2", Integer.parseInt(editText2.getText().toString()));
//                startActivityForResult(intent, 0);
                addLauncher.launch(intent);
            }
        });

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callIntent(Intent.ACTION_DIAL, "tel:01088606073");
            }
        });

        btnWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callIntent(Intent.ACTION_VIEW, "https://www.jbnu.ac.kr/");
            }
        });

        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callIntent(Intent.ACTION_VIEW, "http://maps.google.co.kr/maps?q=" + 35.846085 + "," + 127.134663);
            }
        });

        btnBrowse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY, "전북대학교");
                startActivity(intent);
            }
        });

        btnMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.putExtra("sms_body", "안녕하세요?");
                intent.setData(Uri.parse("smsto:"+Uri.encode("010-8860-6073")));
                startActivity(intent);
            }
        });

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(intent);
            }
        });
    }
}