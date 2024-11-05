package com.example.j202012195_mid;

import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    TabLayout tl;
    int[] tabIds = {R.id.layout_cal, R.id.layout_viewer, R.id.layout_color};
    LinearLayout[] layouts = new LinearLayout[tabIds.length];


    EditText edit1, edit2;
    Button btnRes;
    RadioGroup rgSel;
    RadioButton radAdd, radSub, radMul, radDiv;
    String num1, num2;
    double res;
    String div0errmsg, calOutMsg;
    TextView resOut;
    Double n1, n2;
    String op;

    ViewFlipper vf;
    int[] imgIds = {R.id.imageView,
            R.id.imageView2,
            R.id.imageView3,
            R.id.imageView4,
            R.id.imageView5,
            R.id.imageView6,
            R.id.imageView7,
            R.id.imageView8,
            R.id.imageView9,
            R.id.imageView10,
            R.id.imageView11,
            R.id.imageView12,
            R.id.imageView13,
            R.id.imageView14,
            R.id.imageView15,
    };
    ImageView[] images = new ImageView[imgIds.length];
    TextView imgName, page;
    String imageText, pageText;
    Integer pnum;
    Button btnPrev, btnNext;

    SeekBar rsb, gsb, bsb;
    Integer cr, cg, cb;
    TextView tr, tg, tb;
    ImageView iv;

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
        tl = (TabLayout) findViewById(R.id.tabLayout);
        for(int i = 0; i < tabIds.length; i++) {
            layouts[i] = (LinearLayout) findViewById(tabIds[i]);
            layouts[i].setVisibility(View.GONE);
        }
        layouts[0].setVisibility(View.VISIBLE);

        div0errmsg = "0으로 나누는 오류입니다.";

        tl.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int i = tab.getPosition();
                layouts[i].setVisibility(View.VISIBLE);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                int i = tab.getPosition();
                layouts[i].setVisibility(View.GONE);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        rgSel = (RadioGroup) findViewById(R.id.calSel);
        radAdd = (RadioButton) findViewById(R.id.radioButton); radSub = (RadioButton) findViewById(R.id.radioButton2);
        radMul = (RadioButton) findViewById(R.id.radioButton3); radDiv = (RadioButton) findViewById(R.id.radioButton4);

        edit1 = (EditText) findViewById(R.id.editTextText);
        edit2 = (EditText) findViewById(R.id.editTextText2);
        btnRes = (Button) findViewById(R.id.button);
        resOut = (TextView) findViewById(R.id.textView3);

        btnRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();

                try {
                    n1 = Double.parseDouble(num1);
                } catch (Exception e) {
                    resOut.setText("정수 1의 입력 오류입니다.");
                    return;
                }

                try {
                    n2 = Double.parseDouble(num2);
                } catch (Exception e) {
                    resOut.setText("정수 2의 입력 오류입니다.");
                    return;
                }

                try{
                    if (radAdd.isChecked()) {
                        op = "+";
                        res = n1 + n2;
                    } else if (radSub.isChecked()) {
                        op = "-";
                        res = n1 - n2;
                    } else if (radMul.isChecked()) {
                        op = "*";
                        res = n1 * n2;
                    } else if (radDiv.isChecked()) {
                        if (n2 == 0.0) {
                            throw new Exception(div0errmsg);
                        }
                        op = "/";
                        res = n1 / n2;
                    } else {
                        resOut.setText("알 수 없는 입력 오류입니다.");
                    }
                } catch(Exception e) {
                    if (Objects.equals(e.getMessage(), div0errmsg)) {
                        resOut.setText(div0errmsg);
                    }
                    else {
                        resOut.setText("연산을 선택하지 않았습니다.");
                    }
                    return;
                }
                calOutMsg = String.format("%d%s%d = %.2f", Integer.parseInt(num1), op, Integer.parseInt(num2), res);
                resOut.setText(calOutMsg);
            }
        });

        //
        vf = (ViewFlipper) findViewById(R.id.viewFlipper1);
        imgName = (TextView) findViewById(R.id.textView4);
        page = (TextView) findViewById(R.id.textView5);
        btnPrev= (Button) findViewById(R.id.button2);
        btnNext = (Button) findViewById(R.id.button3);

        pnum = 1;
        imageText = String.format("jeju%d", pnum);
        imgName.setText(imageText);
        pageText = (String.format("%d / 15", pnum));
        page.setText(pageText);
        btnPrev.setClickable(false);

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vf.showPrevious();
                pnum -= 1;
                imageText = String.format("jeju%d", pnum);
                imgName.setText(imageText);
                pageText = (String.format("%d / 15", pnum));
                page.setText(pageText);
                if (pnum== 1) {
                    btnPrev.setClickable(false);
                }
                else {
                    btnNext.setClickable(true);
                }
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vf.showNext();
                pnum += 1;
                imageText = String.format("jeju%d", pnum);
                imgName.setText(imageText);
                pageText = (String.format("%d / 15", pnum));
                page.setText(pageText);
                if (pnum== 15) {btnNext.setClickable(false);}
                else {btnPrev.setClickable(true);}
            }
        });

        rsb = (SeekBar) findViewById(R.id.seekBar);
        gsb = (SeekBar) findViewById(R.id.seekBar2);
        bsb = (SeekBar) findViewById(R.id.seekBar3);
        tr = (TextView) findViewById(R.id.textView10);
        tg = (TextView) findViewById(R.id.textView11);
        tb = (TextView) findViewById(R.id.textView12);
        cr = 0;
        cg = 0;
        cb = 0;

        iv = (ImageView) findViewById(R.id.imageView16);

        rsb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tr.setText(""+i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        gsb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tg.setText(""+i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        bsb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tb.setText(""+i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}