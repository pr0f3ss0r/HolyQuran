package com.tosindroid.pr0f.holyquran;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;


public class MainActivity extends AppCompatActivity {


    private TabLayout tabLayout;
    private TabLayoutMediator tableLayoutMediator;
    private ViewPager2 viewPager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager2) findViewById(R.id.viewpager);
        viewPager.setAdapter(new ViewPagerAdapter(this));

        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tableLayoutMediator = new TabLayoutMediator(
                tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0: {
                        tab.setText("Surahs");
                        break;
                    }
                    case 1: {
                        tab.setText("Juz");
                        break;
                    }
                }
            }
        }
        );
        tableLayoutMediator.attach();
    }
}
