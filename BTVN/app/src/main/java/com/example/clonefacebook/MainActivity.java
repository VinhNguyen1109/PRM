package com.example.clonefacebook;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText emailField;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        emailField = findViewById(R.id.email);
        loginBtn = findViewById(R.id.buttonLoginIg);
        onLogin();

        Button loginFB = findViewById(R.id.LoginFB);
        ImageView logoFB = findViewById(R.id.logoFB);

        onClickToFacebook(loginFB);
        onClickToFacebook(logoFB);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void onLogin(){
        loginBtn.setOnClickListener(v -> {
            if (loginBtn == null) {
                Log.e("vinhnc", "loginBtn is null!");
                return;
            }
            String name = emailField.getText().toString().trim();
            if (!name.isEmpty()) {
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                intent.putExtra("USERNAME", name);
                startActivity(intent);
                emailField.setText("");
                finish();
            } else {
                Toast.makeText(this, "Please enter a username", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void onClickToFacebook(View view) {
        view.setOnClickListener(v -> {
            Log.d("VinhNC", "View clicked: " + v.getId());
            Intent intent = new Intent(MainActivity.this, FacebookActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        emailField.setText(""); // Clear when reopen the app
    }
}
