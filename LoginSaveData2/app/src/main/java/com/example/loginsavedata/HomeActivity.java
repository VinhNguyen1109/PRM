package com.example.loginsavedata;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    private SharedPreferenceHelper sharedPref;
    private TextView txtWelcome;
    private Button btnLogout;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPref = new SharedPreferenceHelper(this);

        if (!sharedPref.isLoggedIn()) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
            return;
        }

        setContentView(R.layout.activity_home);

        username = getIntent().getStringExtra("username");
        onBinding();
        onAction();
    }

    private void onBinding() {
        txtWelcome = findViewById(R.id.txtWelcome);
        btnLogout = findViewById(R.id.btnLogout);

        txtWelcome.setText("Xin chào, " + username + "!");
    }

    private void onAction() {
        btnLogout.setOnClickListener(v -> {
            sharedPref.logout();
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });
    }
}
