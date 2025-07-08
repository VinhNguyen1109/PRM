package com.example.demoroomdb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demoroomdb.R;
import com.example.demoroomdb.database.AppDb;
import com.example.demoroomdb.model.Product;

import java.util.List;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private final List<Product> productList;
    private final AppDb db;

    public ProductAdapter(List<Product> productList, AppDb db) {
        this.productList = productList;
        this.db = db;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, description, price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.productName);
            description = itemView.findViewById(R.id.productDescription);
            price = itemView.findViewById(R.id.productPrice);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product p = productList.get(position);
        holder.name.setText(p.getProductName());
        holder.description.setText(p.getProductDescription());
        holder.price.setText(String.valueOf(p.getProductPrice()));

        holder.itemView.setOnClickListener(v -> showEditDialog(v.getContext(), p));
    }

    private void showEditDialog(Context context, Product product) {
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_add_product, null);
        TextView edtName = dialogView.findViewById(R.id.edtName);
        TextView edtDescription = dialogView.findViewById(R.id.edtDescription);
        TextView edtPrice = dialogView.findViewById(R.id.edtPrice);

        edtName.setText(product.getProductName());
        edtDescription.setText(product.getProductDescription());
        edtPrice.setText(String.valueOf(product.getProductPrice()));

        new AlertDialog.Builder(context)
                .setTitle("Sửa/Xóa sản phẩm")
                .setView(dialogView)
                .setPositiveButton("Cập nhật", (dialog, which) -> {
                    product.setProductName(edtName.getText().toString().trim());
                    product.setProductDescription(edtDescription.getText().toString().trim());
                    product.setProductPrice(Double.parseDouble(edtPrice.getText().toString().trim()));
                    db.productDao().updateProduct(product);
                    notifyDataSetChanged();
                    Toast.makeText(context, "Đã cập nhật", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Xóa", (dialog, which) -> {
                    db.productDao().deleteProduct(product);
                    productList.remove(product);
                    notifyDataSetChanged();
                    Toast.makeText(context, "Đã xóa", Toast.LENGTH_SHORT).show();
                })
                .setNeutralButton("Huỷ", null)
                .show();
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}