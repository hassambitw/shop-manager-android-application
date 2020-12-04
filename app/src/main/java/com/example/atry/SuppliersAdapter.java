package com.example.atry;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SuppliersAdapter extends RecyclerView.Adapter<SuppliersAdapter.SuppliersViewHolder> {

    private ArrayList<Supplier> supplierAdapterList;

    public class SuppliersViewHolder extends RecyclerView.ViewHolder {
        public TextView supplier_id_tv;
        public TextView supplier_name_tv;
        public TextView phoneTV;
        public TextView emailTV;
        Button shipmentsBtn;
        Button editBtn;

            public SuppliersViewHolder (@NonNull View itemView) {
                super(itemView);

                supplier_id_tv = itemView.findViewById(R.id.supplierID);
                supplier_name_tv = itemView.findViewById(R.id.supplier_name);
                phoneTV = itemView.findViewById(R.id.supplier_phone);
                emailTV = itemView.findViewById(R.id.supplier_email);
                shipmentsBtn = itemView.findViewById(R.id.viewShipment);
                editBtn = itemView.findViewById(R.id.editSupplier);
            }

    }

        public SuppliersAdapter (ArrayList<Supplier> supplierAdapterList) {
            this.supplierAdapterList = supplierAdapterList;
        }

        @NonNull
        @Override
        public SuppliersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.supplier_item,parent,false);
            SuppliersViewHolder svh = new SuppliersViewHolder(v);
            return svh;
        }

        @Override
        public void onBindViewHolder(@NonNull SuppliersViewHolder holder, int position) {

            Supplier curItem = supplierAdapterList.get(position);
            int supplierID = curItem.getSupplierId();
            String supplier_name = curItem.getSupplierName();
            String supplier_num = curItem.getSupplierNumber();
            String supplier_email = curItem.getSupplierEmail();

            holder.supplier_id_tv.setText(Integer.toString(supplierID));
            holder.supplier_name_tv.setText(supplier_name);
            holder.phoneTV.setText(supplier_num);
            holder.emailTV.setText(supplier_email);

            //CustomerAdapter.this.notifyDataSetChanged();

            holder.shipmentsBtn.setOnClickListener((v)->{
                Intent i = new Intent(v.getContext(), viewShipment_activity.class);
                i.putExtra("supplier_id",Integer.parseInt(holder.supplier_id_tv.getText().toString()));
//            i.putExtra("customer_id",Integer.parseInt(holder.mTextView2.getText().toString()));
//            i.putExtra("order_date",holder.mTextView3.getText().toString());
//            i.putExtra("staff_id",Integer.parseInt(holder.mTextView4.getText().toString()));
                v.getContext().startActivity(i);
            });

            holder.editBtn.setOnClickListener((v)->{
                Intent i = new Intent(v.getContext(), editSupplier_activity.class);
                i.putExtra("supplier_id",  Integer.parseInt(holder.supplier_id_tv.getText().toString()));
                i.putExtra("supplier_name", holder.supplier_name_tv.getText().toString());
                i.putExtra("supplier_num", holder.phoneTV.getText().toString());
                i.putExtra("supplier_email", holder.emailTV.getText().toString());
                v.getContext().startActivity(i);
            });


        }

        public void setSuppliers(ArrayList<Supplier> suppliers) {
            this.supplierAdapterList = suppliers;
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            return supplierAdapterList.size();
        }
    }