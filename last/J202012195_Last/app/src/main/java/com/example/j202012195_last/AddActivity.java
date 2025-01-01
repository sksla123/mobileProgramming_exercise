package com.example.j202012195_last;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {
    Button btnAdd, btnCancel;
    EditText inpName, inpPhoneNum;

    MyHelper myDbHelper;
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
        setTitle("202012195 박준용");

        inpName = (EditText) findViewById(R.id.editTextText);
        inpPhoneNum = (EditText) findViewById(R.id.editTextText2);

        btnAdd = (Button) findViewById(R.id.button2);
        btnCancel = (Button) findViewById(R.id.button3);

        myDbHelper = new MyHelper(this, "phoneTBL", null,1);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = inpName.getText().toString().trim();
                String phoneNum = inpPhoneNum.getText().toString().trim();

                // Insert
                sqlDB = myDbHelper.getWritableDatabase();
                sqlDB.execSQL("INSERT INTO phoneTBL VALUES('"+ name + "', '" + phoneNum+ "');");

                sqlDB.close();

                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}