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

public class ProductFragment extends Fragment {

    ArrayList<Product> productList = new ArrayList<>();
    private RecyclerView productRecyclerView;
    private RecyclerView.Adapter productAdapter;
    private RecyclerView.LayoutManager productLayoutManager;

    int req_code=11;

    Button addProduct;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //addProduct = container.findViewById(R.id.addProduct);
        productList.add(new Product(001, "SmartPhones", "Samsung", "Galaxy 2077 Doomesday Edition", 2077, 202020, 55));
        productList.add(new Product(001, "SmartPhones", "Samsung", "Galaxy 2077 Doomesday Edition", 2077, 202020, 55));
        productList.add(new Product(001, "SmartPhones", "Samsung", "Galaxy 2077 Doomesday Edition", 2077, 202020, 55));
        productList.add(new Product(001, "SmartPhones", "Samsung", "Galaxy 2077 Doomesday Edition", 2077, 202020, 55));
        productList.add(new Product(001, "SmartPhones", "Samsung", "Galaxy 2077 Doomesday Edition", 2077, 202020, 55));
        productList.add(new Product(001, "SmartPhones", "Samsung", "Galaxy 2077 Doomesday Edition", 2077, 202020, 55));
        productList.add(new Product(001, "SmartPhones", "Samsung", "Galaxy 2077 Doomesday Edition", 2077, 202020, 55));
        productList.add(new Product(001, "SmartPhones", "Samsung", "Galaxy 2077 Doomesday Edition", 2077, 202020, 55));
        productList.add(new Product(001, "SmartPhones", "Samsung", "Galaxy 2077 Doomesday Edition", 2077, 202020, 55));
        productList.add(new Product(001, "SmartPhones", "Samsung", "Galaxy 2077 Doomesday Edition", 2077, 202020, 55));
        productList.add(new Product(001, "SmartPhones", "Samsung", "Galaxy 2077 Doomesday Edition", 2077, 202020, 55));


        View v =  inflater.inflate(R.layout.fragment_products,container,false);

        addProduct = (Button)v.findViewById(R.id.addProduct);
        addProduct.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i=new Intent(getActivity(),AddProduct.class);
                startActivityForResult(i,req_code);
            }
        });

        productRecyclerView = v.findViewById(R.id.product_recycler_view);
        productRecyclerView.setHasFixedSize(true);
        productLayoutManager = new LinearLayoutManager(v.getContext());
        productAdapter = new ProductAdapter(productList);
        productRecyclerView.setLayoutManager(productLayoutManager);
        productRecyclerView.setAdapter(productAdapter);


        return v;


    }

}
