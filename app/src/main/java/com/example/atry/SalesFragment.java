package com.example.atry;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SalesFragment extends Fragment {


    private RecyclerView salesRecyclerView;
    private RecyclerView.Adapter salesAdapter;
    private RecyclerView.LayoutManager salesLayoutManager;
    Button b;
    DBHelper dbO;
    int req_code = 10;
    ArrayList<Orders> ordersList = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        dbO = new DBHelper(getActivity());


        dbO.insertOrder(6, 005, "02/10/2020", 1);
        dbO.insert_order_item(6,2,3,2,33,20);
        //dbO.updateTable();
        Cursor c1 = dbO.getAllFrom_Orders();
        while (c1.moveToNext()) {
            int id = c1.getInt(c1.getColumnIndex("order_id"));
            int customerID = c1.getInt(c1.getColumnIndex("customer_id"));
            String orderDate = c1.getString(c1.getColumnIndex("order_date"));
            int staff_id = c1.getInt(c1.getColumnIndex("staff_id"));

            Cursor c2=dbO.getAllFrom_Order_items(id);
            double total_price=0;
            while(c2.moveToNext()){
                int item_id=c2.getInt(c2.getColumnIndex("item_id"));
                Cursor c3= dbO.getPrice_Of_One_sale(item_id);
                while(c3.moveToNext()){
                    double price=c3.getDouble(c3.getColumnIndex("totalPrice"));
                    total_price=total_price+price;
                }
            }

            ordersList.add(new Orders(id, customerID, orderDate, staff_id,total_price));
        }


        View v = inflater.inflate(R.layout.fragment_sales, container, false);
        b = (Button) v.findViewById(R.id.addSale);
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), Add_Sale.class);
                startActivity(i);
            }
        });

        salesRecyclerView = v.findViewById(R.id.product_recycler_view);
        salesRecyclerView.setHasFixedSize(true);
        salesLayoutManager = new LinearLayoutManager(v.getContext());
        salesAdapter = new Sales_Adapter(ordersList);
        salesRecyclerView.setLayoutManager(salesLayoutManager);
        salesRecyclerView.setAdapter(salesAdapter);


        return v;


    }


}
