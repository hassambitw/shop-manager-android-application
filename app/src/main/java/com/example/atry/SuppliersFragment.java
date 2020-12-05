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

public class SuppliersFragment extends Fragment {

    ArrayList<Supplier> supplierList = new ArrayList<>();
    private RecyclerView supplierRecyclerView;
    private RecyclerView.Adapter supplierAdapter;
    private RecyclerView.LayoutManager supplierLayoutManager;
    int supplier_id;
    String supplier_name;
    String supplier_num;
    String supplier_email;
    DBHelper dbh;
    Button addBtn;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        dbh = new DBHelper(getContext());


        addToArrayList();

        View v = inflater.inflate(R.layout.fragment_suppliers, container, false);

        addBtn = v.findViewById(R.id.addSupplier);

        addBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent (getActivity(), AddSupplier.class);
                startActivity(i);
            }
        });

        buildRecyclerView(v);
        return v;


    }


    public void buildRecyclerView(View v) {
        supplierRecyclerView = v.findViewById(R.id.supplier_recycler_view);
        supplierRecyclerView.setHasFixedSize(true);
        supplierLayoutManager = new LinearLayoutManager(getActivity());
        supplierAdapter = new SuppliersAdapter(supplierList);
        supplierRecyclerView.setLayoutManager(supplierLayoutManager);
        supplierRecyclerView.setAdapter(supplierAdapter);
        supplierAdapter.notifyDataSetChanged();
    }

    public void addToArrayList() {
        //retrieving customers
        Cursor c = dbh.getSuppliers();

        //adding to arrayList
        while (c.moveToNext()) {
            supplier_id = c.getInt(c.getColumnIndex("supplier_id"));
            supplier_name = c.getString(c.getColumnIndex("supplier_name"));
            supplier_num = c.getString(c.getColumnIndex("supplier_number"));
            supplier_email = c.getString(c.getColumnIndex("supplier_email"));
            supplierList.add(new Supplier(supplier_name, supplier_id, supplier_num, supplier_email));
        }
    }

}
