package com.example.btvn_recyclerview;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    ListView listViewCart;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        listViewCart = findViewById(R.id.listViewCart);
        btnBack = findViewById(R.id.btnBack);

        // Nhận dữ liệu từ Intent
        ArrayList<String> cartItems = getIntent().getStringArrayListExtra("cartItems");
        if (cartItems == null) cartItems = new ArrayList<>();

        // Gắn dữ liệu vào ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cartItems);
        listViewCart.setAdapter(adapter);

        // Xử lý quay lại
        btnBack.setOnClickListener(v -> finish());
    }
}
