package com.example.anotherapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private final List<Product> productList;

    public ProductAdapter(List<Product> list) {
        this.productList = list;
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView name, price;

        public ProductViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.productName);
            price = itemView.findViewById(R.id.productPrice);
        }
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_item, parent, false);
        return new ProductViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.name.setText(product.name);
        holder.price.setText("Price: $" + product.price);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}
