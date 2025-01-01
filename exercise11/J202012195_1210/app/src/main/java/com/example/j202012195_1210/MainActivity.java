package com.example.j202012195_1210;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText editText = (EditText) findViewById(R.id.editTextText);
        EditText editText2 = (EditText) findViewById(R.id.editTextText2);
        EditText editText3 = (EditText) findViewById(R.id.editTextText3);
        Button button = (Button) findViewById(R.id.button);

        ListView list = (ListView) findViewById(R.id.listView1);

        StudentAdapter adapter = new StudentAdapter(this);
        list.setAdapter(adapter);

        adapter.addItem(new Student("인공지능","123456789","홍길동"));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student student = new Student(
                        editText.getText().toString(),
                        editText2.getText().toString(),
                        editText3.getText().toString()
                        );

                adapter.addItem(student);
                adapter.notifyDataSetChanged();

            }
        });

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                adapter.removeItem(i);
                adapter.notifyDataSetChanged();
                return true;
            }
        });
    }
}