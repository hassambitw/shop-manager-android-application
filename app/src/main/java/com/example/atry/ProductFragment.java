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

public class ProductFragment extends Fragment {


    private RecyclerView productRecyclerView;
    private RecyclerView.Adapter productAdapter;
    private RecyclerView.LayoutManager productLayoutManager;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ArrayList<Product> productList = new ArrayList<>();
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

        productRecyclerView = v.findViewById(R.id.product_recycler_view);
        productRecyclerView.setHasFixedSize(true);
        productLayoutManager = new LinearLayoutManager(v.getContext());
        productAdapter = new ProductAdapter(productList);
        productRecyclerView.setLayoutManager(productLayoutManager);
        productRecyclerView.setAdapter(productAdapter);


        return v;


    }

}
