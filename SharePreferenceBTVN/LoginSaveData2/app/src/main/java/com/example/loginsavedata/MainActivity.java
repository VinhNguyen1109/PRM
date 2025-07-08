package com.example.loginsavedata;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private SharedPreferenceHelper sharedPref;

    private EditText edtUsername, edtPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPref = new SharedPreferenceHelper(this);

        if (sharedPref.isLoggedIn()) {
            Intent intent = new Intent(this, HomeActivity.class);
            intent.putExtra("username", sharedPref.getUsername());
            startActivity(intent);
            finish();
            return;
        }

        setContentView(R.layout.activity_main);
        onBinding();
        onAction();
    }

    private void onBinding() {
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
    }

    private void onAction() {
        btnLogin.setOnClickListener(v -> {
            String username = edtUsername.getText().toString().trim();
            String password = edtPassword.getText().toString();

            if (username.equals("admin") && password.equals("1234")) {
                sharedPref.setLoggedIn(true);
                sharedPref.setUsername(username);

                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(MainActivity.this, "Sai tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
