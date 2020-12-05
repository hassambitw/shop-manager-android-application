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


        addProducts();
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

    public void addProducts() {
        dbh.insertProduct(1,"Galaxy ZFold","Samsung","mobile phones",2020,900,900);
        dbh.insertProduct(2,"Galaxy s20","Samsung","mobile phones",2020,1000,800);
        dbh.insertProduct(3,"Galaxy Note 10","Samsung","mobile phones",2020,3000,100);
        dbh.insertProduct(4,"iPhone 12","Apple","mobile phones",2020,920,90);
        dbh.insertProduct(5,"iPhone 11","Apple","mobile phones",2020,920,900);
        dbh.insertProduct(6,"iPhone 10","Apple","mobile phones",2020,920,900);
        dbh.insertProduct(7,"Huawei P40","Huawei","mobile phones",2020,820,900);
        dbh.insertProduct(8,"OnePlus 8","OnePlus","mobile phones",2020,820,90);
        dbh.insertProduct(9,"OnePlus 6","OnePlus","mobile phones",2020,820,900);
        dbh.insertProduct(10,"Mi9T","Motorola","mobile phones",2020,920,900);
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
