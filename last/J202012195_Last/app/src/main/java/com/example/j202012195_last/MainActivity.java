package com.example.j202012195_last;

import android.app.ActionBar;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    ListView list;
    ArrayAdapter<String> adapter;

    Button btnAdd;

    MyHelper myDbHelper;
    SQLiteDatabase sqlDB;

    ArrayList<String> listItems = new ArrayList<String>();
    ArrayList<String> names = new ArrayList<String>();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,0,0, "초기화");
        menu.add(0,1,0, "백업");

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        myDbHelper = new MyHelper(this, "phoneTBL", null,1);
        switch (item.getItemId()) {
            case 0:
                sqlDB = myDbHelper.getWritableDatabase();
                myDbHelper.onUpgrade(sqlDB, 1,2);
                onStart();
                return true;
            case 1:
                String _name;
                String _phoneNum;
                sqlDB = myDbHelper.getWritableDatabase();
                Cursor cursor;
                cursor = sqlDB.rawQuery("SELECT * FROM phoneTBL;", null);
                String backup = "name:phoneNum\n";
                while (cursor.moveToNext()) {
                    _name = cursor.getString(0);
                    _phoneNum = cursor.getString(1);
                    backup += _name + ":" + _phoneNum + "\n";
                }
                cursor.close();
                sqlDB.close();

                
                return true;
        }
        return super.onOptionsItemSelected(item);

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

        setTitle("202012195 박준용");

        list = (ListView) findViewById(R.id.listView1);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
        list.setAdapter(adapter);

        myDbHelper = new MyHelper(this, "phoneTBL", null,1);

        btnAdd = (Button) findViewById(R.id.button);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddActivity.class);
                startActivity(intent);
            }
        });



        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                String key = names.get(i);
                Toast.makeText(getApplicationContext(), key, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), EditActivity.class);
                intent.putExtra("key", key);
                startActivity(intent);
                return false;
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String phoneNum = "Error";

                String key = names.get(i);
                sqlDB = myDbHelper.getReadableDatabase();
                Cursor cursor;
                cursor = sqlDB.rawQuery("SELECT * FROM phoneTBL WHERE name='"+ key +"';", null);

                while(cursor.moveToNext()) {
                    phoneNum = cursor.getString(1);
                }

                cursor.close();
                sqlDB.close();

                Uri uri = Uri.parse("tel:"+ phoneNum);
                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        sqlDB = myDbHelper.getReadableDatabase();
        Cursor cursor;
        cursor = sqlDB.rawQuery("SELECT * FROM phoneTBL;", null);

        String listItem;
        listItems.clear();
        names.clear();
        String name;
        while (cursor.moveToNext()) {
            name = cursor.getString(0);
            names.add(name);
            listItem = name + "          " + cursor.getString(1);
            listItems.add(listItem);
        }

        adapter.notifyDataSetChanged();

        cursor.close();
        sqlDB.close();
        super.onStart();
    }
}