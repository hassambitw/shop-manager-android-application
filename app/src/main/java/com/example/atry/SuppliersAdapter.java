package com.example.atry;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SuppliersAdapter extends RecyclerView.Adapter<SuppliersAdapter.SuppliersViewHolder> {

    private ArrayList<Supplier> suppList;
    public class SuppliersViewHolder extends RecyclerView.ViewHolder{
        public TextView mTextView1;
        public TextView mTextView2;
        public TextView mTextView3;

        public SuppliersViewHolder(@NonNull View itemView) {
            super(itemView);


            mTextView1 = itemView.findViewById(R.id.supplierName);
            mTextView2 = itemView.findViewById(R.id.supplierEmail);
            mTextView3 = itemView.findViewById(R.id.supplierNumber);
        }
    }

    public SuppliersAdapter(ArrayList<Supplier> prodList) {
        this.suppList = prodList;
    }

    @NonNull
    @Override
    public SuppliersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.supplier_item,parent,false);
        SuppliersViewHolder pvh = new SuppliersViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull SuppliersViewHolder holder, int position) {

        Supplier curItem = suppList.get(position);

        holder.mTextView1.setText(curItem.getSupplierName());
        holder.mTextView2.setText(curItem.getSupplierEmail());
        holder.mTextView3.setText(Integer.toString(curItem.getSupplierNumber()));

    }

    @Override
    public int getItemCount() {
        return suppList.size();
    }
}
