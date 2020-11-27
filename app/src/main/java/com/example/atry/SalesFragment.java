package com.example.atry;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    DBHelper dbO;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ArrayList<Orders> ordersList = new ArrayList<>();

        dbO=new DBHelper(getActivity());
        dbO.insertOrder(2,001,"12/12/2020");

        Cursor c1=dbO.getAllFrom_Orders();
        while(c1.moveToNext()){
            int id=c1.getInt(c1.getColumnIndex("order_id"));

            int customerID=c1.getInt(c1.getColumnIndex("customer_id"));
            String orderDate=c1.getString(c1.getColumnIndex("order_date"));
            ordersList.add(new Orders(id,customerID,orderDate));
        }



        View v =  inflater.inflate(R.layout.fragment_sales,container,false);

        salesRecyclerView = v.findViewById(R.id.product_recycler_view);
        salesRecyclerView.setHasFixedSize(true);
        salesLayoutManager = new LinearLayoutManager(v.getContext());
        salesAdapter = new Sales_Adapter(ordersList);
        salesRecyclerView.setLayoutManager(salesLayoutManager);
        salesRecyclerView.setAdapter(salesAdapter);


        return v;


    }

}
