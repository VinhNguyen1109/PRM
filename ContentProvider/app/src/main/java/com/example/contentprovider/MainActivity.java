package com.example.contentprovider;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ProductAdapter adapter;

    List<Product> productList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);

        adapter = new ProductAdapter(productList);
        recyclerView.setAdapter(adapter);

        loadProductsFromContentProvider();
    }

    private void loadProductsFromContentProvider() {
        Uri uri = Uri.parse("content://com.example.goodsprovider/products");
        String[] projection = new String[]{"name", "price"};

        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);

        if (cursor != null) {
            Log.d("DEBUG-VINHNC", "Provider đã khởi tạo");
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                double price = cursor.getDouble(cursor.getColumnIndexOrThrow("price"));
                productList.add(new Product(name, price));
            }
            Log.e("DEBUG-VINHNC", "check size data db: " + productList.size());

            cursor.close();
            adapter.notifyDataSetChanged();
        }else {
            Log.e("DEBUG-VINHNC", "Cursor is null – provider not responding or permission issue");
        }

    }

}
