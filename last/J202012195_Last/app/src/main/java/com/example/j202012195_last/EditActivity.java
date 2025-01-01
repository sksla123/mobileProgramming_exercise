package com.example.j202012195_last;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EditActivity extends AppCompatActivity {
    Button btnEdit, btnDelete, btnCancel;
    EditText inpName, inpPhoneNum;
    MyHelper myDbHelper;
    SQLiteDatabase sqlDB;

    String name = "Error";
    String phoneNum = "Error";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
        inpName = (EditText) findViewById(R.id.editTextText3);
        inpPhoneNum = (EditText) findViewById(R.id.editTextText4);

        btnEdit = (Button) findViewById(R.id.button2);
        btnDelete = (Button) findViewById(R.id.button3);
        btnCancel = (Button) findViewById(R.id.button7);

        Intent i = getIntent();
        String key = i.getStringExtra("key");
//        Log.d("key", key);

        myDbHelper = new MyHelper(this, "phoneTBL", null,1);
        sqlDB = myDbHelper.getReadableDatabase();
//
        Cursor cursor;
        cursor = sqlDB.rawQuery("SELECT * FROM phoneTBL WHERE name='"+ key +"';", null);
//        Log.d("SQL", sql);
//


        while(cursor.moveToNext()) {
            name = cursor.getString(0);
            phoneNum = cursor.getString(1);
//            Log.d("name", name);
//            Log.d("num", phoneNum);
        }

        cursor.close();
        sqlDB.close();

        inpName.setText(name);
        inpPhoneNum.setText(phoneNum);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newPhoneNum = inpPhoneNum.getText().toString().trim();
                AlertDialog.Builder dlg = new AlertDialog.Builder(EditActivity.this);
                dlg.setTitle("수정 여부 확인");
                dlg.setMessage("이름 " + name +"의 연락처, " + phoneNum + "을(를) " + newPhoneNum + "으로 수정할까요?");
                dlg.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        return;
                    }
                });
                dlg.setPositiveButton("수정", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sqlDB = myDbHelper.getReadableDatabase();
                        sqlDB.execSQL("UPDATE phoneTBL SET phoneNum='" + newPhoneNum + "' WHERE name='" + name + "';");
                        sqlDB.close();
                        finish();
                    }
                });
                dlg.show();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(EditActivity.this);
                dlg.setTitle("삭제 여부 확인");
                dlg.setMessage( name +"의 연락처를 삭제할까요?");
                dlg.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        return;
                    }
                });
                dlg.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sqlDB = myDbHelper.getWritableDatabase();
                        sqlDB.execSQL("DELETE FROM phoneTBL WHERE name='" + name + "';");
                        sqlDB.close();
                        finish();
                    }
                });
                dlg.show();
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