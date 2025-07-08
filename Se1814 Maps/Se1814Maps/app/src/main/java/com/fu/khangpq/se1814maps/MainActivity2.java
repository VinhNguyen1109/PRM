package com.fu.khangpq.se1814maps;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity2 extends AppCompatActivity {
    private Button btnLoad;
    private EditText edtData;
    private EditText edtShowData;


    private void bindingView() {
        btnLoad = findViewById(R.id.btnLoad);
        edtData = findViewById(R.id.edtData);
        edtShowData = findViewById(R.id.edtShowData);
    }

    private void bindingAction() {
        btnLoad.setOnClickListener(this::onBtnLoadClick);
    }

    private void onBtnLoadClick(View view) {
        String id = edtData.getText().toString();
        int postId = 0;
        try {
            postId = Integer.parseInt(id);
        } catch (Exception e) {
            postId = 0;
        }
        
        ApiServices.getPostsApiEndpoint().getPostById(postId).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                Post p = response.body();
                edtShowData.setText(p.toString());
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        bindingView();
        bindingAction();
    }
}