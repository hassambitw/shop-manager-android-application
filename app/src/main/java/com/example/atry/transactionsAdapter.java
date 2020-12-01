package com.example.atry;

import android.view.LayoutInflater;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class transactionsAdapter extends RecyclerView.Adapter<transactionsAdapter.TransactionsViewHolder> {

    private ArrayList<Orders> orders_transactionList;
    private ArrayList<Customer> customers_transactionList;
    //private ArrayList<Orders> _transactionList;

    public class TransactionsViewHolder extends RecyclerView.ViewHolder{
        public TextView mTextView1;
        public TextView mTextView2;
        public TextView mTextView3;
        public TextView mTextView4;


        public TransactionsViewHolder(@NonNull View itemView) {
            super(itemView);


            mTextView1 = itemView.findViewById(R.id.firstName);
            mTextView2 = itemView.findViewById(R.id.lastName);
            mTextView3 = itemView.findViewById(R.id.totalPrice);
            mTextView4=itemView.findViewById(R.id.order_id);

        }
    }

    public transactionsAdapter (ArrayList<Orders> orders_transactionsList, ArrayList<Customer> customers_transactionsList) {
        this.orders_transactionList = orders_transactionList;
        this.customers_transactionList = customers_transactionsList;
    }

    @NonNull
    @Override
    public TransactionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_item,parent,false);
        TransactionsViewHolder svh = new TransactionsViewHolder(v);
        return svh;
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionsViewHolder holder, int position) {

        Orders curItem = orders_transactionList.get(position);
        Customer curItem_cust = customers_transactionList.get(position);

        holder.mTextView1.setText(curItem_cust.getFirstName());
        holder.mTextView2.setText(curItem_cust.getLastName());
        holder.mTextView3.setText(Integer.toString(curItem.getOrderId()));
        holder.mTextView4.setText(Integer.toString(curItem.getOrderId()));
//        holder.b.setOnClickListener((v)->{
//            Intent i=new Intent(v.getContext(), View_receipt.class);
//            i.putExtra("order_id",Integer.parseInt(holder.mTextView1.getText().toString()));
//            i.putExtra("customer_id",Integer.parseInt(holder.mTextView2.getText().toString()));
//            i.putExtra("order_date",holder.mTextView3.getText().toString());
//            i.putExtra("staff_id",Integer.parseInt(holder.mTextView4.getText().toString()));
//            v.getContext().startActivity(i);
//        });
    }

    @Override
    public int getItemCount() {
        return orders_transactionList.size();
    }
}
