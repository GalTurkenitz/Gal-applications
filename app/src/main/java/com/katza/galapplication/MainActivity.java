package com.katza.galapplication;



import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sp;
    Button btnSave;
    EditText etFname, etLname;
    TextView tvDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp = getSharedPreferences("MyData", 0);

        btnSave = findViewById(R.id.btnSubmit);
        etFname = findViewById(R.id.etFname);
        etLname = findViewById(R.id.etLname);
        tvDisplay = findViewById(R.id.tvDisplay);
        sp = getSharedPreferences("details", 0);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("fname", null);
                editor.putString("lname", null);

                editor.commit();   // בדוגמה בספר בדרך כלל משתמשים ב־commit()
            }
        });


        String strfname = sp.getString("fname", null);
        String strlname = sp.getString("fname", null);
        if (strlname != null && strfname != null) {
            tvDisplay.setText("welcome:  " + strfname + " " + strlname);
        }
    }


}



