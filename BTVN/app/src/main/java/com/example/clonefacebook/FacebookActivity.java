package com.example.clonefacebook;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FacebookActivity extends AppCompatActivity {


    EditText emailField;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.facebook);

        emailField = findViewById(R.id.email);
        loginBtn = findViewById(R.id.button);

        onLogin();

    }

    private void onLogin(){
        loginBtn.setOnClickListener(v -> {
            if (loginBtn == null) {
                Log.e("vinhnc", "loginBtn is null!");
                return;
            }
            String name = emailField.getText().toString().trim();
            if (!name.isEmpty()) {
                Intent intent = new Intent(FacebookActivity.this, HomeActivity.class);
                intent.putExtra("USERNAME", name);
                startActivity(intent);
                emailField.setText("");
                finish();
            } else {
                Toast.makeText(this, "Please enter a username", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        emailField.setText(""); // Clear when reopen the app
    }
}