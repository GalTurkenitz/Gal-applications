package com.katza.galapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private Switch flick;
    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();
    }

    private void initViews() {
        imageView = findViewById(R.id.imageView);
        flick = findViewById(R.id.flick);
        seekBar = findViewById(R.id.seekBar);


        flick.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                imageView.setVisibility(View.VISIBLE);
                Toast.makeText(this, "התמונה מוצגת", Toast.LENGTH_SHORT).show();
            } else {
                imageView.setVisibility(View.INVISIBLE);
                Toast.makeText(this, "התמונה הוסתרה", Toast.LENGTH_SHORT).show();
            }
        });


        seekBar.setMax(100);
        seekBar.setProgress(100);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float alphaValue = progress / 100f;
                imageView.setAlpha(alphaValue);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, "התחלת להזיז את הבר", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, "שחררת את הבר", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

