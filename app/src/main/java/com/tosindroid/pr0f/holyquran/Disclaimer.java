package com.tosindroid.pr0f.holyquran;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Disclaimer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_disclaimer);

        TextView disclaimer = findViewById(R.id.disclaimer);
        disclaimer.setMovementMethod(new ScrollingMovementMethod());
    }

}
