package com.example.atry;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class transactionsAdapter extends RecyclerView.Adapter<transactionsAdapter.TransactionsViewHolder> {

    private ArrayList<TransactionDetails> transactionList;
    private RecyclerView salesRecyclerView;
    private RecyclerView.Adapter salesAdapter;
    private RecyclerView.LayoutManager salesLayoutManager;
    DBHelper dbh;
    //private ArrayList<Orders> _transactionList;

    public class TransactionsViewHolder extends RecyclerView.ViewHolder{
        public TextView fname_tv;
        public TextView lname_tv;
        public TextView total_price_tv;
        public TextView order_id_tv;
        public CardView item_transaction;


        public TransactionsViewHolder(@NonNull View itemView) {
            super(itemView);

            fname_tv = itemView.findViewById(R.id.firstName);
            lname_tv = itemView.findViewById(R.id.lastName);
            total_price_tv = itemView.findViewById(R.id.totalPrice);
            order_id_tv=itemView.findViewById(R.id.order_id);
            item_transaction = itemView.findViewById(R.id.transaction_item);

        }
    }

    public transactionsAdapter (ArrayList<TransactionDetails> transactionList) {
        this.transactionList = transactionList;
    }

    @NonNull
    @Override
    public TransactionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_item,parent,false);
        TransactionsViewHolder tvh = new TransactionsViewHolder(v);
        return tvh;
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionsViewHolder holder, int position) {

//        Orders curItem = orders_transactionList.get(position);
//        Customer curItem_cust = customers_transactionList.get(position);
          TransactionDetails curTransaction = transactionList.get(position);


        holder.fname_tv.setText(curTransaction.getCust_fname());
        holder.lname_tv.setText(curTransaction.getCust_lname());
        holder.total_price_tv.setText(Double.toString(round(curTransaction.getTotal_price(), 2)));
        holder.order_id_tv.setText(Integer.toString(curTransaction.getOrder_id()));

        holder.item_transaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), View_receipt.class);
                i.putExtra("order_id", Integer.parseInt(holder.order_id_tv.getText().toString()));
                v.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }

    public static double round (double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
