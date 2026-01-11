package com.katza.galapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DynamicActivity extends AppCompatActivity {

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
            ImageView iv = new ImageView(this);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(100, 100);
            layoutParams.setMargins(5, 5, 5, 5);
            iv.setLayoutParams(layoutParams);

            int imagekey = getResources().getIdentifier("img" + i % 3, "drawable", getPackageName());
            iv.setImageResource(imagekey);

            llVertical.addView(iv);
        }

        verticalScrollView.addView(llVertical);
        linearLayout.addView(verticalScrollView);
    }

    // טעינת התפריט
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    // טיפול בלחיצה על פריטי התפריט
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_login) {
            Intent intent = new Intent(this, dActivity.class);
            startActivity(intent);
            finish();

        } else if (id == R.id.action_register) {
            Intent intent = new Intent(this, DynamicActivity.class);
            startActivity(intent);
            finish();

        } else if (id == R.id.action_exit) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

            finish();
        }

        return super.onOptionsItemSelected(item);
    }



}


