package com.example.atry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class viewTransaction_activity extends AppCompatActivity {

    private RecyclerView transactionsRecyclerView;
    private RecyclerView.Adapter transaction_Adapter;
    private RecyclerView.LayoutManager transactionsLayoutManager;
    Button backBtn;
    int req_code = 1;
    DBHelper dbh;
    ArrayList<TransactionDetails> custTransactionList = new ArrayList<TransactionDetails>();
    //ArrayList<Orders> ordersTransactionList = new ArrayList<>();
    ArrayList<TransactionDetails> transactions = new ArrayList<>();
    String fname;
    String lname;
    double totalPrice;
    int orderID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_transaction);

        dbh = new DBHelper(getApplicationContext());

        Intent i = getIntent();
        int cID =  i.getIntExtra("customer_id", 0);     //from customer adapter
        System.out.println("ID BEING SENT IS: " + cID);
        Cursor c = dbh.getCustomerTransactions(cID);                                                    //customer ID with order ID
                                                                                                        //items with order ID


//        make a new transaction class with the attributes that i need to show in the recycler view
//        in the viewtransaction class,

        while(c.moveToNext()){
//            String firstColName = c.getString(Integer.parseInt(c.getColumnName(0)));
//            System.out.println(firstColName);
             fname = c.getString(c.getColumnIndex("first_name"));
             lname = c.getString(c.getColumnIndex("last_name"));
             //price, quantity, discount
             totalPrice = c.getDouble(c.getColumnIndex("totalPrice"));
             orderID = c.getInt(c.getColumnIndex("order_id"));
            custTransactionList.add(new TransactionDetails(fname, lname, orderID, totalPrice));
        }

        custTransactionList.add(new TransactionDetails("Hassam", "Minhas", 4, 165));

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
        transaction_Adapter = new transactionsAdapter(custTransactionList);
        transactionsRecyclerView.setLayoutManager(transactionsLayoutManager);
        transactionsRecyclerView.setAdapter(transaction_Adapter);

    }
}

