package com.example.imageview;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {


    private ImageView imageView;

    private Button btnChange;

    private Button btnLoad;

    private EditText edtlink;


    private void bindingView(){
        imageView = findViewById(R.id.imageView);
        btnChange = findViewById(R.id.btnChange);
        btnLoad = findViewById(R.id.btnLoad);
        edtlink = findViewById(R.id.edtLink);
    }

    private void bindingAction(){
        btnChange.setOnClickListener(this::onBtnChangeClick);
        btnLoad.setOnClickListener(this::onBtnLoadClick);
    }

    private void onBtnLoadClick(View view) {
        String url = edtlink.getText().toString();
        Glide.with(this)
                .load(url)
                .error(R.drawable.img_2)
                .into(imageView);
    }

    private void onBtnChangeClick(View view) {
        imageView.setImageResource(R.drawable.img_1);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public  boolean 

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        bindingView();
        bindingAction();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}