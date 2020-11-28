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

public class PurchaseFragment extends Fragment {

    ArrayList<Shipment> purchaseList = new ArrayList<>();
    private RecyclerView purchaseRecyclerView;
    private RecyclerView.Adapter purchaseAdapter;
    private RecyclerView.LayoutManager purchaseLayoutManager;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


            purchaseList.add(new Shipment(0000,  0001, 1000, 001, 003, "13/10/2002"));
        purchaseList.add(new Shipment(0000,  0001, 1000, 001, 003, "13/10/2002"));
        purchaseList.add(new Shipment(0000,  0001, 1000, 001, 003, "13/10/2002"));




        View v =  inflater.inflate(R.layout.fragment_purchase,container,false);

        purchaseRecyclerView = v.findViewById(R.id.purchase_recycler_view);
        purchaseRecyclerView.setHasFixedSize(true);
        purchaseLayoutManager = new LinearLayoutManager(v.getContext());
        purchaseAdapter = new PurchaseAdapter(purchaseList);
        purchaseRecyclerView.setLayoutManager(purchaseLayoutManager);
        purchaseRecyclerView.setAdapter(purchaseAdapter);


        return v;


    }

}
