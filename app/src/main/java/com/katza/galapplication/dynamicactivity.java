package com.katza.galapplication;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class dynamicactivity extends AppCompatActivity {
    LinearLayout linearLayout;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dynamicactivity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        linearLayout = findViewById(R.id.main);

        // Horizontal Scroll
        HorizontalScrollView horizontalScrollView = new HorizontalScrollView(this);
        LinearLayout.LayoutParams hsPararms = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        horizontalScrollView.setLayoutParams(hsPararms);

        LinearLayout llscroll = new LinearLayout(this);
        LinearLayout.LayoutParams llparams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        llscroll.setLayoutParams(llparams);
        llscroll.setOrientation(LinearLayout.HORIZONTAL);

        for (int i = 1; i <= 100; i++) {
            imageView = new ImageView(this);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(100, 100);
            layoutParams.setMargins(5, 5, 5, 5);
            imageView.setLayoutParams(layoutParams);

            int imagekey = getResources().getIdentifier("img" + i % 3, "drawable", getPackageName());
            imageView.setImageResource(imagekey);

            llscroll.addView(imageView);
        }
        horizontalScrollView.addView(llscroll);
        linearLayout.addView(horizontalScrollView);

        // Vertical Scroll
        ScrollView verticalScrollView = new ScrollView(this);
        LinearLayout.LayoutParams ssPararms = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        verticalScrollView.setLayoutParams(ssPararms);

        LinearLayout llVertical = new LinearLayout(this);
        LinearLayout.LayoutParams llparamsV = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        llVertical.setLayoutParams(llparamsV);
        llVertical.setOrientation(LinearLayout.VERTICAL);

        for (int i = 1; i <= 100; i++) {
            ImageView imageView = new ImageView(this);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(100, 100);
            layoutParams.setMargins(5, 5, 5, 5);
            imageView.setLayoutParams(layoutParams);

            int imagekey = getResources().getIdentifier("img" + i % 3, "drawable", getPackageName());
            imageView.setImageResource(imagekey);

            llVertical.addView(imageView);
        }

        // פה הייתה הטעות – תיקון:
        verticalScrollView.addView(llVertical);

        linearLayout.addView(verticalScrollView);
    }
}

