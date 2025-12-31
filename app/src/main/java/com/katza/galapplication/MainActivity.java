package com.katza.galapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sp;
    Button btnSave;
    EditText etFname, etLname;
    TextView tvDisplay;
    TextView tv; // ✅ הוספנו

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // SharedPreferences (שם אחד, עקבי)
        sp = getSharedPreferences("details", MODE_PRIVATE);

        btnSave = findViewById(R.id.btnSubmit);
        etFname = findViewById(R.id.etFname);
        etLname = findViewById(R.id.etLname);
        tvDisplay = findViewById(R.id.tvDisplay);
        tv = findViewById(R.id.tv); // ✅ הוספנו

        // ✅ רישום ל-Context Menu על ה-"Click me more than 2 seconds"
        registerForContextMenu(tv);

        // (אם אתה גם רוצה שיהיה תפריט גם על tvDisplay, תשאיר את זה)
        // registerForContextMenu(tvDisplay);

        // שמירה בלחיצה
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fname = etFname.getText().toString().trim();
                String lname = etLname.getText().toString().trim();

                SharedPreferences.Editor editor = sp.edit();
                editor.putString("fname", fname);
                editor.putString("lname", lname);
                editor.apply();

                tvDisplay.setText("welcome:  " + fname + " " + lname);
            }
        });

        // טעינה אם כבר שמור
        String strfname = sp.getString("fname", null);
        String strlname = sp.getString("lname", null);

        if (strfname != null && strlname != null) {
            tvDisplay.setText("welcome:  " + strfname + " " + strlname);
        }
    }

    // =======================
    // ✅ Options Menu (למעלה)
    // =======================
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_login) {
            Toast.makeText(this, "You selected login", Toast.LENGTH_SHORT).show();
            return true;

        } else if (id == R.id.action_register) {
            Toast.makeText(this, "You selected register", Toast.LENGTH_SHORT).show();
            return true;

        } else if (id == R.id.action_exit) {
            Toast.makeText(this, "You selected exit", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // =======================
    // ✅ Context Menu (לחיצה ארוכה)
    // =======================
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.contextmenu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.firstline) {
            Toast.makeText(this, "You selected first line", Toast.LENGTH_LONG).show();
            return true;

        } else if (item.getItemId() == R.id.secondline) {
            Toast.makeText(this, "You selected second line", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onContextItemSelected(item);
    }
}
