package com.example.atry;

import android.content.Intent;
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

public class CustomerFragment extends Fragment {


    private RecyclerView customerRecyclerView;
    private RecyclerView.Adapter customerAdapter;
    private RecyclerView.LayoutManager customerLayoutManager;
    Button addBtn;
    int req_code = 1;
    ArrayList<Customer> customerList = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        customerList.add(new Customer(001, "Muhammad", "Naseer", "hassan291999", 056-1017939));
        customerList.add(new Customer(001, "Muhammad", "Naseer", "hassan291999", 056-1017939));
        customerList.add(new Customer(001, "Muhammad", "Naseer", "hassan291999", 056-1017939));
        customerList.add(new Customer(001, "Muhammad", "Naseer", "hassan291999", 056-1017939));



        View v = inflater.inflate(R.layout.fragment_customer, container, false);

        addBtn = v.findViewById(R.id.addCustomer);
        addBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent (getActivity(), AddCustomer_Activity.class);
                startActivityForResult(i, req_code);
            }
        });

        customerRecyclerView = v.findViewById(R.id.customer_recycler_view);
        customerRecyclerView.setHasFixedSize(true);
        customerLayoutManager = new LinearLayoutManager(v.getContext());
        customerAdapter = new CustomerAdapter(customerList);
        customerRecyclerView.setLayoutManager(customerLayoutManager);
        customerRecyclerView.setAdapter(customerAdapter);


        return v;


    }

}
