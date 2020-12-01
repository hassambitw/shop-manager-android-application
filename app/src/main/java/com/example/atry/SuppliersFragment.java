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

public class SuppliersFragment extends Fragment {

    ArrayList<Supplier> supplierList = new ArrayList<>();
    private RecyclerView supplierRecyclerView;
    private RecyclerView.Adapter supplierAdapter;
    private RecyclerView.LayoutManager supplierLayoutManager;

    int req_code=14;

    Button addSupplier;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //addProduct = container.findViewById(R.id.addProduct);
//        productList.add(new Product(001, "SmartPhones", "Samsung", "Galaxy 2077 Doomesday Edition", 2077, 202020, 55));
//        productList.add(new Product(001, "SmartPhones", "Samsung", "Galaxy 2077 Doomesday Edition", 2077, 202020, 55));
//        productList.add(new Product(001, "SmartPhones", "Samsung", "Galaxy 2077 Doomesday Edition", 2077, 202020, 55));
//        productList.add(new Product(001, "SmartPhones", "Samsung", "Galaxy 2077 Doomesday Edition", 2077, 202020, 55));
//        productList.add(new Product(001, "SmartPhones", "Samsung", "Galaxy 2077 Doomesday Edition", 2077, 202020, 55));
//        productList.add(new Product(001, "SmartPhones", "Samsung", "Galaxy 2077 Doomesday Edition", 2077, 202020, 55));
//        productList.add(new Product(001, "SmartPhones", "Samsung", "Galaxy 2077 Doomesday Edition", 2077, 202020, 55));
//        productList.add(new Product(001, "SmartPhones", "Samsung", "Galaxy 2077 Doomesday Edition", 2077, 202020, 55));
//        productList.add(new Product(001, "SmartPhones", "Samsung", "Galaxy 2077 Doomesday Edition", 2077, 202020, 55));
//        productList.add(new Product(001, "SmartPhones", "Samsung", "Galaxy 2077 Doomesday Edition", 2077, 202020, 55));
//        productList.add(new Product(001, "SmartPhones", "Samsung", "Galaxy 2077 Doomesday Edition", 2077, 202020, 55));

        supplierList.add(new Supplier("Eros Entertainment",001,995554545,"eros@err.com"));

        View v =  inflater.inflate(R.layout.fragment_suppliers,container,false);

        addSupplier = (Button)v.findViewById(R.id.addSupplier);
        addSupplier.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i=new Intent(getActivity(),AddSupplier.class);
                startActivityForResult(i,req_code);
            }
        });

        supplierRecyclerView = v.findViewById(R.id.product_recycler_view);
        supplierRecyclerView.setHasFixedSize(true);
        supplierLayoutManager = new LinearLayoutManager(v.getContext());
        supplierAdapter = new SuppliersAdapter(supplierList);
        supplierRecyclerView.setLayoutManager(supplierLayoutManager);
        supplierRecyclerView.setAdapter(supplierAdapter);


        return v;


    }

}
