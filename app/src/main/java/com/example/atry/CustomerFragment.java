package com.example.atry;

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

public class CustomerFragment extends Fragment {


    private RecyclerView customerRecyclerView;
    private RecyclerView.Adapter customerAdapter;
    private RecyclerView.LayoutManager customerLayoutManager;
    ArrayList<Customer> customerList = new ArrayList<>();
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



        customerList.add(new Customer(001, "Muhammad Hassan", "Naseer", "hassan291999", "pepe st", "Alabama", 00000, 9441, "dubu"));
        customerList.add(new Customer(001, "Muhammad Hassan", "Naseer", "hassan291999", "pepe st", "Alabama", 00000, 9441, "dubu"));
        customerList.add(new Customer(001, "Muhammad Hassan", "Naseer", "hassan291999", "pepe st", "Alabama", 00000, 9441, "dubu"));
        customerList.add(new Customer(001, "Muhammad Hassan", "Naseer", "hassan291999", "pepe st", "Alabama", 00000, 9441, "dubu"));





        View v =  inflater.inflate(R.layout.fragment_customer,container,false);

        customerRecyclerView = v.findViewById(R.id.customer_recycler_view);
        customerRecyclerView.setHasFixedSize(true);
        customerLayoutManager = new LinearLayoutManager(v.getContext());
        customerAdapter = new CustomerAdapter(customerList);
        customerRecyclerView.setLayoutManager(customerLayoutManager);
        customerRecyclerView.setAdapter(customerAdapter);


        return v;


    }

}
