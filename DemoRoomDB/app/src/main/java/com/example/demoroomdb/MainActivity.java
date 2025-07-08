package com.example.demoroomdb;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demoroomdb.adapter.ProductAdapter;
import com.example.demoroomdb.database.AppDb;
import com.example.demoroomdb.model.Product;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private List<Product> productList;
    private AppDb db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = AppDb.getInstance(this);
        recyclerView = findViewById(R.id.recyclerView);
        Button btnAdd = findViewById(R.id.btnAdd);

        productList = new ArrayList<>(db.productDao().getAllProduct());
        adapter = new ProductAdapter(productList, db);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        btnAdd.setOnClickListener(view -> showAddDialog());
    }

    private void showAddDialog() {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_product, null);
        EditText edtName = dialogView.findViewById(R.id.edtName);
        EditText edtDescription = dialogView.findViewById(R.id.edtDescription);
        EditText edtPrice = dialogView.findViewById(R.id.edtPrice);

        new AlertDialog.Builder(this)
                .setTitle("Thêm sản phẩm")
                .setView(dialogView)
                .setPositiveButton("Lưu", (dialog, which) -> {
                    String name = edtName.getText().toString().trim();
                    String desc = edtDescription.getText().toString().trim();
                    String priceStr = edtPrice.getText().toString().trim();

                    if (TextUtils.isEmpty(name) || TextUtils.isEmpty(priceStr)) {
                        Toast.makeText(this, "Vui lòng nhập đủ tên và giá", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    double price = Double.parseDouble(priceStr);
                    Product p = new Product(name, desc, price);
                    db.productDao().insertProduct(p);
                    reloadData();
                })
                .setNegativeButton("Huỷ", null)
                .show();
    }

    private void reloadData() {
        productList.clear();
        productList.addAll(db.productDao().getAllProduct());
        adapter.notifyDataSetChanged();
    }
}