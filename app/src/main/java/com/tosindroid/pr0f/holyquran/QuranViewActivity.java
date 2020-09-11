
package com.tosindroid.pr0f.holyquran;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.SharedMemory;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import androidx.preference.PreferenceManager;
import androidx.viewpager2.widget.ViewPager2;


public class QuranViewActivity extends AppCompatActivity {
    int data;
    int bookIcon = 0;
    String data2, data3;
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
            case R.id.fav_menu:

                if(bookIcon == 0){
                    item.setIcon(R.drawable.ic_menu_bookmark2);
                    bookIcon = 1;
                }else{
                    item.setIcon(R.drawable.ic_menu_bookmark1);
                    bookIcon = 0;
                }
                addToFavorite();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void addToFavorite() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int a = viewPager2.getCurrentItem();
        data = a;
        sharedPreferences.edit().putString("pageNumber", Integer.toString(data)).apply();
        sharedPreferences.edit().putString("chapterNumber", data2).apply();
        sharedPreferences.edit().putString("chapterName", data3).apply();
        QuranViewActivity activity = this;



        Log.d("cd", sharedPreferences.getString("pageNumber", ""));
        Log.d("cd", sharedPreferences.getString("chapterNumber", ""));
        Log.d("cd", sharedPreferences.getString("chapterName", ""));

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
        data = getIntent().getExtras().getInt("pageNumber");
        data2 = getIntent().getExtras().getString("chapterNumber");
        data3 = getIntent().getExtras().getString("chapterName");

        viewPager2 =  findViewById(R.id.quranViewPager);
        viewPager2.setAdapter(new QuranViewPagerAdapter(this, viewPager2));
        viewPager2.setCurrentItem(data, true);
        viewPager2.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);


    }
}

