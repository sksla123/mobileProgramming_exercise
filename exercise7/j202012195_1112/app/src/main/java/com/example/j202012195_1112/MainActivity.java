package com.example.j202012195_1112;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    LinearLayout layout;
    Button button;
    TextView name, email, toastText;
    View dialogView;
    View toastView;
    EditText _name, _email;

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

        layout = (LinearLayout) findViewById(R.id.main);
        button = (Button) findViewById(R.id.button);
        name = (TextView) findViewById(R.id.textView1);
        email = (TextView) findViewById(R.id.textView2);
        registerForContextMenu(layout);
        registerForContextMenu(button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String[] versionArray = new String[] {"쫄면", "떡볶이", "김밥"};
                final boolean[] checkArray = new boolean[] {true, false, false};
                dialogView = (View) View.inflate(MainActivity.this, R.layout.dialog1, null);

                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                dlg.setTitle("사용자 정보 입력");
                dlg.setView(dialogView);
//                dlg.setTitle("제목입니다.");
//                dlg.setMessage("내용입니다.");

//                dlg.setItems(versionArray, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        Toast.makeText(getApplicationContext(), versionArray[i]+"를 선택했습니다.", Toast.LENGTH_SHORT).show();
//                    }
//                });

//                dlg.setSingleChoiceItems(versionArray, 0, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        button.setText(versionArray[i]);
//                    }
//                });

//                dlg.setMultiChoiceItems(versionArray, checkArray, new DialogInterface.OnMultiChoiceClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
//                        checkArray[i] = b;
//                    }
//                });

                dlg.setIcon(R.mipmap.ic_launcher);
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        _name = (EditText) dialogView.findViewById(R.id.editText1);
                        _email = (EditText) dialogView.findViewById(R.id.editText2);

                        name.setText(_name.getText().toString());
                        email.setText(_email.getText().toString());
//                        String str = "선택한 물품: ";
//                        for (int k = 0; k < checkArray.length; k++) {
//                            if (checkArray[k]) str += versionArray[k] + " ";
//                        }
//
//                        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
                    }
                });
                dlg.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        toastView = (View) View.inflate(MainActivity.this, R.layout.toast1, null);
                        toastText = (TextView) toastView.findViewById(R.id.textView);
//                        toastText.setText("취소합니다.");
                        Toast toast = new Toast(MainActivity.this);
                        toast.setView(toastView);
                        toast.show();
//                        Toast.makeText(getApplicationContext(), "취소 버튼 클릭", Toast.LENGTH_SHORT).show();
                    }
                });
                dlg.show();
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        if (v==layout) {
            menu.setHeaderTitle("배경색 변경");
            menuInflater.inflate(R.menu.menu2, menu);
        }
        else if(v==button) {
            menu.setHeaderTitle("버튼 변경");
            menuInflater.inflate(R.menu.menu3, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.context_red) {
            layout.setBackgroundColor(Color.RED);
            return true;
        }
        else if (item.getItemId() == R.id.context_green) {
            layout.setBackgroundColor(Color.GREEN);
            return true;
        }
        else if (item.getItemId() == R.id.context_blue) {
            layout.setBackgroundColor(Color.BLUE);
            return true;
        }
        else if (item.getItemId() == R.id.contextRotate) {
            button.setRotationX(2);
            return true;
        }
        else if (item.getItemId() == R.id.contextSize) {
            button.setScaleX(2);
            return true;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu1, menu);
//        menu.add(0,1,0, "배경색 (빨강)");
//        menu.add(0,2,0, "배경색 (파랑)");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.item_red) {
            layout.setBackgroundColor(Color.RED);
            return true;
        }
        else if (item.getItemId() == R.id.item_green) {
            layout.setBackgroundColor(Color.GREEN);
            return true;
        }
        else if (item.getItemId() == R.id.item_blue) {
            layout.setBackgroundColor(Color.BLUE);
            return true;
        }
        else if (item.getItemId() == R.id.sublocate) {
            button.setRotationX(2);
            return true;
        }
        else if (item.getItemId() == R.id.subsize) {
            button.setScaleX(2);
            return true;
        }
        return false;
    }
}