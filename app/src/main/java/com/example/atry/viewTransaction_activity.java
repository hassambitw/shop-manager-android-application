package com.example.atry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class viewTransaction_activity extends AppCompatActivity {

    private RecyclerView transactionsRecyclerView;
    private RecyclerView.Adapter transaction_Adapter;
    private RecyclerView.LayoutManager transactionsLayoutManager;
    Button backBtn;
    int req_code = 1;
    DBHelper dbh;
    ArrayList<TransactionDetails> custTransactionList = new ArrayList<TransactionDetails>();
    TextView noTransactionsMsg;
    String fname;
    String lname;
    double totalPrice;
    int orderID;
    LinearLayout ll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_transaction);

        transactionsRecyclerView = findViewById(R.id.transaction_recycler_view);
        ll = findViewById(R.id.empty_view);

        dbh = new DBHelper(getApplicationContext());

        Intent i = getIntent();
        int cID =  i.getIntExtra("customer_id", 0);     //from customer adapter
        System.out.println("ID BEING SENT IS: " + cID);
        Cursor c = dbh.getCustomerTransactions(cID);

        if (!(c.getCount() <= 0)) {
            ll.setVisibility(View.GONE);
            transactionsRecyclerView.setVisibility(View.VISIBLE);
        }
        else {
            ll.setVisibility(View.VISIBLE);
        }

        System.out.println(c.getCount());
        while(c.moveToNext()){
             fname = c.getString(c.getColumnIndex("first_name"));
             lname = c.getString(c.getColumnIndex("last_name"));
             totalPrice = c.getDouble(c.getColumnIndex("totalPrice"));
             orderID = c.getInt(c.getColumnIndex("order_id"));
            custTransactionList.add(new TransactionDetails(fname, lname, orderID, totalPrice));
        }


        backBtn = findViewById(R.id.back);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        transactionsRecyclerView.setHasFixedSize(true);
        transactionsLayoutManager = new LinearLayoutManager(getApplicationContext());
        transaction_Adapter = new transactionsAdapter(custTransactionList);
        transactionsRecyclerView.setLayoutManager(transactionsLayoutManager);
        transactionsRecyclerView.setAdapter(transaction_Adapter);

    }
}

