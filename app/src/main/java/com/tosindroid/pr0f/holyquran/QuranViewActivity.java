
package com.tosindroid.pr0f.holyquran;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import androidx.viewpager2.widget.ViewPager2;


public class QuranViewActivity extends AppCompatActivity {

    ViewPager2 viewPager2;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.quran_view_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.go_btn_menu:
                openDialogue();
                return true;
            case R.id.about_menu:
                Intent intent = new Intent(QuranViewActivity.this, About.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void openDialogue() {
        GoToDialogue goToDialogue = new GoToDialogue();
        goToDialogue.show(getSupportFragmentManager(), "Go to page");
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quran_view);
        int data = getIntent().getExtras().getInt("fatir");
        viewPager2 =  findViewById(R.id.quranViewPager);
        viewPager2.setAdapter(new QuranViewPagerAdapter(this, viewPager2));
        viewPager2.setCurrentItem(data, true);
        viewPager2.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);


    }
}

