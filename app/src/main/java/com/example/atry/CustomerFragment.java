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

public class CustomerFragment extends Fragment {


    private RecyclerView customerRecyclerView;
    private RecyclerView.Adapter customerAdapter;
    private RecyclerView.LayoutManager customerLayoutManager;
    Button addBtn;
    Button refreshBtn;
    int req_code = 1;
    DBHelper dbh;
    ArrayList<Customer> customerList = new ArrayList<>();
    int customer_id;
    String customer_fname;
    String customer_lname;
    String customer_email;
    String customer_phone;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        dbh = new DBHelper(getContext());

        //adding customers
        addCustomers();

        addToArrayList();

        View v = inflater.inflate(R.layout.fragment_customer, container, false);

        refreshBtn = v.findViewById(R.id.refreshbtn);
        addBtn = v.findViewById(R.id.addCustomer);

        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customerList.clear();
                addToArrayList();
                customerAdapter.notifyDataSetChanged();
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent (getActivity(), AddCustomer_Activity.class);
                startActivity(i);
            }
        });


        buildRecyclerView(v);
        return v;
    }


    public void addCustomers() {
        dbh.insertCustomer(01, "John", "Williams", "+971561017939", "johnwilliams@hotmail.com");
        dbh.insertCustomer(02, "Jason", "King", "+971561017939", "jasonking@hotmail.com");
        dbh.insertCustomer(03, "Elizabeth", "Becks", "+971561017939", "elibeck@hotmail.com");
        dbh.insertCustomer(04, "Kenny", "Smith", "+971561017939", "kennysmith@hotmail.com");
        dbh.insertCustomer(05, "Soda", "Poppins", "+971561017939", "sodapoppins@hotmail.com");
    }

    public void buildRecyclerView(View v) {
        customerRecyclerView = v.findViewById(R.id.customer_recycler_view);
        customerRecyclerView.setHasFixedSize(true);
        customerLayoutManager = new LinearLayoutManager(getActivity());
        customerAdapter = new CustomerAdapter(customerList);
        customerRecyclerView.setLayoutManager(customerLayoutManager);
        customerRecyclerView.setAdapter(customerAdapter);
        customerAdapter.notifyDataSetChanged();
    }

    public void addToArrayList() {
        //retrieving customers
        Cursor c = dbh.getCustomers();

        //adding to arrayList
        while (c.moveToNext()) {
            customer_id = c.getInt(c.getColumnIndex("customer_id"));
            customer_fname = c.getString(c.getColumnIndex("first_name"));
            customer_lname = c.getString(c.getColumnIndex("last_name"));
            customer_email = c.getString(c.getColumnIndex("email"));
            customer_phone = c.getString(c.getColumnIndex("phone"));
            customerList.add(new Customer(customer_id, customer_fname, customer_lname, customer_email, customer_phone));
        }
    }

}
