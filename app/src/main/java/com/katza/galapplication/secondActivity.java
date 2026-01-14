package com.katza.galapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class secondActivity extends AppCompatActivity {

    TextView tvOutFirstName, tvOutAge, tvOutMale;
    EditText etBirthYear;
    Button btnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvOutFirstName = findViewById(R.id.tvOutFirstName);
        tvOutAge = findViewById(R.id.tvOutAge);
        tvOutMale = findViewById(R.id.tvOutMale);

        etBirthYear = findViewById(R.id.etBirthYear);
        btnOk = findViewById(R.id.btnOk);

        Intent intent = getIntent();
        String firstName = intent.getStringExtra("firstName");
        int age = intent.getIntExtra("age", 0);
        boolean isMale = intent.getBooleanExtra("isMale", false);

        tvOutFirstName.setText("שם פרטי: " + firstName);
        tvOutAge.setText("גיל: " + age);
        tvOutMale.setText("זכר?: " + (isMale ? "כן" : "לא"));

        btnOk.setOnClickListener(v -> {
            String birthYearStr = etBirthYear.getText().toString().trim();

            if (birthYearStr.isEmpty()) {
                Toast.makeText(this, "נא להכניס שנת לידה", Toast.LENGTH_SHORT).show();
                return;
            }

            int birthYear;
            try {
                birthYear = Integer.parseInt(birthYearStr);
            } catch (Exception e) {
                Toast.makeText(this, "שנת לידה חייבת להיות מספר", Toast.LENGTH_SHORT).show();
                return;
            }

            int currentYear = Calendar.getInstance().get(Calendar.YEAR);
            int calculatedAge = currentYear - birthYear;

            if (calculatedAge < 0 || calculatedAge > 130) {
                Toast.makeText(this, "שנת לידה לא הגיונית", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent result = new Intent();
            result.putExtra("calculatedAge", calculatedAge);
            setResult(RESULT_OK, result);
            finish();
        });
    }
}

