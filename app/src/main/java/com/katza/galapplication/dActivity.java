package com.katza.galapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class dActivity extends AppCompatActivity implements View.OnClickListener {

    SharedPreferences sp;
    Dialog d;

    EditText etUserName, etPass;
    Button btnCustomLogin, btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        btnLogin = findViewById(R.id.btnCustomLogin);
        btnLogin.setOnClickListener(this);

        sp = getSharedPreferences("details1", 0);
    }

    @Override
    public void onClick(View v) {
        if (v == btnLogin) {
            createLoginDialog();
        }
    }
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

    public void createLoginDialog() {
        d = new Dialog(this);

        d.setContentView(R.layout.activity_dialog);
        d.setTitle("Login");
        d.setCancelable(true);

        etUserName = d.findViewById(R.id.etUserName);
        etPass = d.findViewById(R.id.etPass);

        btnCustomLogin = d.findViewById(R.id.btnCustomLogin);
        btnCustomLogin.setOnClickListener(this);

        d.show();
    }
}

