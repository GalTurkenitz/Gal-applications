package com.katza.galapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
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

