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

public class ProductFragment extends Fragment {

    ArrayList<Product> productList = new ArrayList<>();
    private RecyclerView productRecyclerView;
    private RecyclerView.Adapter productAdapter;
    private RecyclerView.LayoutManager productLayoutManager;
    DBHelper dbh;

    int req_code=11;

    int prodID;
    String ProductName;
    String ProductBrand;
    String ProductCategory;
    double ProductListPrice;
    int ProductYear;
    int ProductQuantity;

    Button addProduct;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        dbh = new DBHelper(getContext());


        AddProducts();
        addToProductArrayList();

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
        productAdapter.notifyDataSetChanged();

        return v;


    }

    public void AddProducts() {
        dbh.insertProduct (1, "Galaxy S20+ 5G", "Samsung", "Smartphones", 2020,  3799 , 59);
        dbh.insertProduct (2, "QC35 II", "Bose", "Headphones", 2018,  1349 , 22);
        dbh.insertProduct (3, "QiCharge++", "Anker", "Accessories", 2019,  250 , 222);
        dbh.insertProduct (4, "OnePlus 8T", "OnePlus", "Smartphones", 2020,  2699 , 18);

    }

    public void addToProductArrayList() {
        //retrieving customers
        Cursor c = dbh.getProducts();

        //adding to arrayList
        while (c.moveToNext()) {
            int i = productList.indexOf(c);
            prodID = c.getInt(c.getColumnIndex("product_id"));
            ProductName = c.getString(c.getColumnIndex("product_name"));
            ProductBrand = c.getString(c.getColumnIndex("brand"));
            ProductCategory = c.getString(c.getColumnIndex("category"));
            ProductListPrice = c.getDouble(c.getColumnIndex("list_price"));
            ProductQuantity = c.getInt(c.getColumnIndex("stock"));
            ProductYear = c.getInt(c.getColumnIndex("model_year"));
            productList.add(new Product(prodID, ProductCategory, ProductBrand, ProductName, ProductYear, ProductListPrice, ProductQuantity));
        }
    }

}
