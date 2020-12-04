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

public class PurchaseFragment extends Fragment {


    private RecyclerView purchaseRecyclerView;
    private RecyclerView.Adapter purchaseAdapter;
    private RecyclerView.LayoutManager purchaseLayoutManager;
    Button b;
    DBHelper dbO;
    int req_code = 10;
    ArrayList<Shipment> shipmentList = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        dbO = new DBHelper(getActivity());


        dbO.insertShipment(1,12,5,"02/12/2020");
       dbO.insert_shipment_items(1,99,1,8);
        dbO.insert_shipment_items(1,98,2,3);
        dbO.insertShipment(2,1,5,"04/12/2020");
        dbO.insert_shipment_items(2,45,3,2);

        Cursor c1 = dbO.getShipments();

        while (c1.moveToNext()) {
            int shipment_num = c1.getInt(c1.getColumnIndex("shipment_num"));
            int supplier_id = c1.getInt(c1.getColumnIndex("shipment_supplier_id"));
            String shipment_date = c1.getString(c1.getColumnIndex("shipment_date"));
            int shipping_quote = c1.getInt(c1.getColumnIndex("shipment_quote"));
            Cursor c2=dbO.get_Shipment_items(shipment_num);
            double total_price=0;
            while(c2.moveToNext()){
                int item_id=c2.getInt(c2.getColumnIndex("item_id"));
                Cursor c3= dbO.getPrice_Of_One_purchase(item_id);
                while(c3.moveToNext()){
                    double price=c3.getDouble(c3.getColumnIndex("totalPrice"));
                    total_price=total_price+price;
                }
            }
            total_price=total_price+(total_price*((double)shipping_quote/100));

            shipmentList.add(new Shipment(shipping_quote,shipment_num,supplier_id,shipment_date,total_price));
        }


        View v = inflater.inflate(R.layout.fragment_purchase, container, false);
        b = (Button) v.findViewById(R.id.addPurchase);
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), Add_Sale.class);
                startActivity(i);
            }
        });

        purchaseRecyclerView = v.findViewById(R.id.purchase_recycler_view);
        purchaseRecyclerView.setHasFixedSize(true);
        purchaseLayoutManager = new LinearLayoutManager(v.getContext());
        purchaseAdapter = new PurchaseAdapter(shipmentList);
        purchaseRecyclerView.setLayoutManager(purchaseLayoutManager);
        purchaseRecyclerView.setAdapter(purchaseAdapter);


        return v;


    }


}
