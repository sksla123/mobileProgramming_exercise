package com.example.a1220_last;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    ListView list;
    Button btnAdd, btnBackup, btnReset;
    myDBHelper myHelper;
    SQLiteDatabase sqlDB;

    int[] ids = {};
    String[] names = {};

    ActivityResultLauncher addLauncher;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            sqlDB = myHelper.getReadableDatabase();
            Cursor cursor;
            cursor = sqlDB.rawQuery("SELECT * FROM phoneBookTBL;", null);

            while (cursor.moveToNext()) {
                ids = Arrays.copyOf(ids, ids.length + 1);
                ids[ids.length -1] = cursor.getInt(0);
                names = Arrays.copyOf(names, names.length + 1);
                names[names.length -1] = cursor.getString(1);
            }

            cursor.close();
            sqlDB.close();
        }
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

        ActionBar ac = getSupportActionBar();
        ac.setTitle("202012195 기말고사");

        list = (ListView) findViewById(R.id.listView1);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnBackup = (Button) findViewById(R.id.btnBackup);
        btnReset = (Button) findViewById(R.id.btnReset);

        myHelper = new myDBHelper(this, "phoneBookTbl", null, 1);

        sqlDB = myHelper.getReadableDatabase();
        Cursor cursor;
        cursor = sqlDB.rawQuery("SELECT * FROM phoneBookTBL;", null);

        while (cursor.moveToNext()) {
            ids = Arrays.copyOf(ids, ids.length + 1);
            ids[ids.length -1] = cursor.getInt(0);
            names = Arrays.copyOf(names, names.length + 1);
            names[names.length -1] = cursor.getString(1);
        }

        cursor.close();
        sqlDB.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, names);
        list.setAdapter(adapter);

        addLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult()
                , result-> {
            if (result.getResultCode()==RESULT_OK) {
                sqlDB = myHelper.getReadableDatabase();
                Cursor _cursor;
                _cursor = sqlDB.rawQuery("SELECT * FROM phoneBookTBL;", null);

                while (_cursor.moveToNext()) {
                    ids = Arrays.copyOf(ids, ids.length + 1);
                    ids[ids.length -1] = cursor.getInt(0);
                    names = Arrays.copyOf(names, names.length + 1);
                    names[names.length -1] = cursor.getString(1);
                }

                _cursor.close();
                sqlDB.close();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddActivity.class);
                addLauncher.launch(intent);
            }
        });
    }
}