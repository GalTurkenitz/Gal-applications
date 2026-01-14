package com.katza.galapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class firstActivity extends AppCompatActivity {

    EditText etFirstName, etAge;
    CheckBox cbMale;
    Button btnSend;                 // זה הכפתור שלך לעמוד הבא
    TextView tvAgeDisplayValue;     // מציג גיל שחזר מה-second

    ActivityResultLauncher<Intent> secondLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_first);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etFirstName = findViewById(R.id.etFirstName);
        etAge = findViewById(R.id.etAge);
        cbMale = findViewById(R.id.cbMale);
        btnSend = findViewById(R.id.btnSend);

        // חייב להיות קיים ב-XML של first
        tvAgeDisplayValue = findViewById(R.id.tvAgeDisplayValue);

        // Launcher שמקבל תוצאה מה-second
        secondLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        int calculatedAge = result.getData().getIntExtra("calculatedAge", -1);
                        if (calculatedAge != -1) {
                            tvAgeDisplayValue.setText(String.valueOf(calculatedAge));

                            // אם אתה גם רוצה לעדכן את ה-EditText של גיל:
                            etAge.setText(String.valueOf(calculatedAge));
                        }
                    }
                }
        );

        // כפתור "לעמוד הבא"
        btnSend.setOnClickListener(v -> {
            String firstName = etFirstName.getText().toString().trim();
            String ageStr = etAge.getText().toString().trim();
            boolean isMale = cbMale.isChecked();

            if (firstName.isEmpty()) {
                Toast.makeText(this, "נא למלא שם פרטי", Toast.LENGTH_SHORT).show();
                return;
            }

            // לא חובה למלא גיל כאן (כי מחזירים גיל מה-second)
            int age = 0;
            if (!ageStr.isEmpty()) {
                try { age = Integer.parseInt(ageStr); } catch (Exception ignored) {}
            }

            Intent i = new Intent(firstActivity.this, secondActivity.class);
            i.putExtra("firstName", firstName);
            i.putExtra("age", age);
            i.putExtra("isMale", isMale);

            secondLauncher.launch(i); // מעבר ל-second דרך Launcher
        });
    }
}


