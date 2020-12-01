package com.example.atry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class viewTransaction_activity extends AppCompatActivity {

    private RecyclerView transactionsRecyclerView;
    private RecyclerView.Adapter transactionsAdapter;
    private RecyclerView.LayoutManager transactionsLayoutManager;
    Button backBtn;
    int req_code = 1;
    ArrayList<Customer> custTransactionList = new ArrayList<>();
//    ArrayList<Orders> custTransactionList = new ArrayList<>();
//    ArrayList<Customer> custTransactionList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_transaction);

        backBtn = findViewById(R.id.back);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        transactionsRecyclerView = findViewById(R.id.transaction_recycler_view);
        transactionsRecyclerView.setHasFixedSize(true);
        transactionsLayoutManager = new LinearLayoutManager(getApplicationContext());
        transactionsAdapter = new CustomerAdapter(custTransactionList);
        transactionsRecyclerView.setLayoutManager(transactionsLayoutManager);
        transactionsRecyclerView.setAdapter(transactionsAdapter);




    }
}