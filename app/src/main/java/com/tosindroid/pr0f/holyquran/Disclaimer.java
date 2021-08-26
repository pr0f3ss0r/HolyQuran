package com.tosindroid.pr0f.holyquran;

import android.os.Bundle;
import android.webkit.WebView;


import androidx.appcompat.app.AppCompatActivity;

public class Disclaimer extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WebView disclaimer = new WebView(getApplicationContext());
        setContentView(disclaimer);

            disclaimer.loadUrl("https://cluetechweb.com/quran_app/privacy-policy.html");
    }

}
