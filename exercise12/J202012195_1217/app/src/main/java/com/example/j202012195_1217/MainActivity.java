package com.example.j202012195_1217;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    myDBHelper myHelper;
    EditText edtName, edtNumber, edtNameResult, edtNumberResult;
    Button btnInit, btnInsert, btnUpdate, btnDelete, btnSelect;
    SQLiteDatabase sqlDB;

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

        edtName = (EditText) findViewById(R.id.edtName);
        edtNumber = (EditText) findViewById(R.id.edtNumber);
        edtNameResult = (EditText) findViewById(R.id.edtNameResult);
        edtNumberResult = (EditText) findViewById(R.id.edtNumberResult);

        btnInit = (Button) findViewById(R.id.btnInit);
        btnInsert = (Button) findViewById(R.id.btnInsert);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnSelect = (Button) findViewById(R.id.btnSelect);

        myHelper = new myDBHelper(this, "groupDB", null, 1);

        btnInit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlDB = myHelper.getWritableDatabase();
                myHelper.onUpgrade(sqlDB,1,2);
                sqlDB.close();
                Toast.makeText(getApplicationContext(), "초기화됨", Toast.LENGTH_SHORT).show();
                btnSelect.callOnClick();
            }
        });

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlDB = myHelper.getWritableDatabase();
                String groupName = edtName.getText().toString().trim();
                String groupNumber = edtNumber.getText().toString().trim();

                if (groupName.length() == 0 || groupNumber.length() == 0) {
                    Toast.makeText(getApplicationContext(), "이름, 인원 모두 값을 넣어주세요!", Toast.LENGTH_SHORT).show();
                    return;
                }

                String strSQLInsertIntGroupTABLE = "INSERT INTO groupTBL VALUES";
                strSQLInsertIntGroupTABLE += "( '" + groupName + "' , " + groupNumber + ") ";
                strSQLInsertIntGroupTABLE += ";";

                Log.d("SQL", strSQLInsertIntGroupTABLE);
                try {
                    sqlDB.execSQL(strSQLInsertIntGroupTABLE);
                    Toast.makeText(getApplicationContext(), "정상적으로 입력됨", Toast.LENGTH_SHORT).show();
                } catch(SQLException e) {
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                }
                sqlDB.close();
                btnSelect.callOnClick();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlDB = myHelper.getWritableDatabase();
                String groupName = edtName.getText().toString().trim();
                String groupNumber = edtNumber.getText().toString().trim();

                if (groupName.length() == 0 || groupNumber.length() == 0) {
                    Toast.makeText(getApplicationContext(), "이름, 인원 모두 값을 넣어주세요!", Toast.LENGTH_SHORT).show();
                    return;
                }

                String strSQLUpdateGroupTable = "UPDATE groupTBL SET ";
                strSQLUpdateGroupTable += "gNumber=" + groupNumber;
                strSQLUpdateGroupTable += " WHERE gName=='" + groupName + "'";
                strSQLUpdateGroupTable += ";";

                Log.d("SQL", strSQLUpdateGroupTable);
                try {
                    sqlDB.execSQL(strSQLUpdateGroupTable);
                    Toast.makeText(getApplicationContext(), "정상적으로 수정됨", Toast.LENGTH_SHORT).show();
                } catch(SQLException e) {
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                }

                sqlDB.close();
                btnSelect.callOnClick();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlDB = myHelper.getWritableDatabase();
                String groupName = edtName.getText().toString().trim();

                if (groupName.length() == 0) {
                    Toast.makeText(getApplicationContext(), "이름에 값을 넣어주세요!", Toast.LENGTH_SHORT).show();
                    return;
                }

                String strSQLDeleteGroupByName = "DELETE FROM groupTBL";
                strSQLDeleteGroupByName += " WHERE gName=='" + groupName + "'";
                strSQLDeleteGroupByName += ";";

                Log.d("SQL", strSQLDeleteGroupByName);
                try {
                    sqlDB.execSQL(strSQLDeleteGroupByName);
                    Toast.makeText(getApplicationContext(), "정상적으로 삭제됨", Toast.LENGTH_SHORT).show();
                } catch(SQLException e) {
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                }

                sqlDB.close();
                btnSelect.callOnClick();
            }
        });

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlDB = myHelper.getWritableDatabase();
                String strSQLSelectFromGroupTable = "SELECT * FROM groupTBL;";

                Cursor cursor;
                cursor = sqlDB.rawQuery(strSQLSelectFromGroupTable, null);

                String strNames = "그룹 이름\n-----------------\n";
                String strNumbers = "인원수\n-----------------\n";

                while (cursor.moveToNext()) {
                    strNames += cursor.getString(0) + "\r\n";
                    strNumbers += cursor.getString(1) + "\r\n";
                }

                edtNameResult.setText(strNames);
                edtNumberResult.setText(strNumbers);

                cursor.close();
                cursor.close();
            }
        });

        btnSelect.callOnClick();
    }
}