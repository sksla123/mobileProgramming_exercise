package com.example.j202012195_e06_01_fragments;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentContainerView;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    FragmentContainerView[] fragments = new FragmentContainerView[3];
    int[] frg_ids = {R.id.fragmentContainerView1, R.id.fragmentContainerView2, R.id.fragmentContainerView3};

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

        for (int i = 0; i < frg_ids.length; i++) {
            fragments[i] = (FragmentContainerView) findViewById(frg_ids[i]);
            fragments[i].setVisibility(FragmentContainerView.GONE);
        }

        fragments[0].setVisibility(FragmentContainerView.VISIBLE);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos = tab.getPosition();
                fragments[pos].setVisibility(FragmentContainerView.VISIBLE);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                int pos = tab.getPosition();
                fragments[pos].setVisibility(FragmentContainerView.GONE);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}