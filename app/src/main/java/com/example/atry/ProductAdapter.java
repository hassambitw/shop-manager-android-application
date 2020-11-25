package com.example.atry;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private ArrayList<Product> prodList;
    public class ProductViewHolder extends RecyclerView.ViewHolder{
        public TextView mTextView1;
        public TextView mTextView2;
        public TextView mTextView3;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);


            mTextView1 = itemView.findViewById(R.id.category);
            mTextView2 = itemView.findViewById(R.id.brandName);
            mTextView3 = itemView.findViewById(R.id.productName);
        }
    }

    public ProductAdapter (ArrayList<Product> prodList) {
        this.prodList = prodList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item,parent,false);
        ProductViewHolder pvh = new ProductViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

        Product curItem = prodList.get(position);

        holder.mTextView1.setText(curItem.getCategory());
        holder.mTextView2.setText(curItem.getBrandName());
        holder.mTextView3.setText(curItem.getProductName());

    }

    @Override
    public int getItemCount() {
        return prodList.size();
    }
}
