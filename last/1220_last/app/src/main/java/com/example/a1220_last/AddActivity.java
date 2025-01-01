package com.example.a1220_last;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddActivity extends AppCompatActivity {
    Button btnAdd, btnCancel;
    EditText inputName, inputPhoneNum;

    myDBHelper myHelper;
    SQLiteDatabase sqlDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        ActionBar ac = getSupportActionBar();
        ac.setTitle("연락처 추가");

        btnAdd = (Button) findViewById(R.id.button);
        btnCancel = (Button) findViewById(R.id.button2);
        inputName = (EditText) findViewById(R.id.editTextText);
        inputPhoneNum = (EditText) findViewById(R.id.editTextText2);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strName = inputName.getText().toString().trim();
                String strPhoneNum = inputPhoneNum.getText().toString().trim();

                sqlDB = myHelper.getWritableDatabase();
                String strSQLInsert="INSERT INTO phoneBookTBL(name, phoneNum) VALUES('"+strName+"', '"+strPhoneNum+"');";

                try{
                    sqlDB.execSQL(strSQLInsert);
                } catch(SQLException e){
                    Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
                    mainIntent.putExtra("isUpdated", 0);
                    setResult(RESULT_OK, mainIntent);
                    finish();
                }
                Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
                mainIntent.putExtra("isUpdated", 1);
                setResult(RESULT_OK, mainIntent);
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
                mainIntent.putExtra("isUpdated", 0);
                setResult(RESULT_OK, mainIntent);
                finish();
            }
        });
    }
}