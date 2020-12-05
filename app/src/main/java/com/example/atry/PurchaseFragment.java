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
    DBHelper dbh;
    int req_code = 10;
    ArrayList<Shipment> shipmentList = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        dbh = new DBHelper(getActivity());
        addShipments();
        addShipmentItems();

        Cursor c1 = dbh.getShipments();

        while (c1.moveToNext()) {
            int shipment_num = c1.getInt(c1.getColumnIndex("shipment_num"));
            int supplier_id = c1.getInt(c1.getColumnIndex("shipment_supplier_id"));
            String shipment_date = c1.getString(c1.getColumnIndex("shipment_date"));
            int shipping_quote = c1.getInt(c1.getColumnIndex("shipment_quote"));
            Cursor c2=dbh.get_Shipment_items(shipment_num);
            double total_price=0;
            while(c2.moveToNext()){
                int item_id=c2.getInt(c2.getColumnIndex("item_id"));
                Cursor c3= dbh.getPrice_Of_One_purchase(item_id);
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
                Intent i = new Intent(getActivity(), AddPurchase.class);
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

    public void addShipments() {
        dbh.insertShipment(1,1,5,"2020-01-01");
        dbh.insertShipment(2,1,5,"2020-01-07");
        dbh.insertShipment(3,3,5,"2020-01-12");
        dbh.insertShipment(4,4,5,"2020-01-19");
        dbh.insertShipment(5,5,5,"2020-01-30");

        dbh.insertShipment(6,2,5,"2020-02-01");
        dbh.insertShipment(7,3,5,"2020-02-07");
        dbh.insertShipment(8,5,5,"2020-02-12");
        dbh.insertShipment(9,3,5,"2020-02-19");
        dbh.insertShipment(10,1,5,"2020-02-24");

        dbh.insertShipment(11,5,5,"2020-03-01");
        dbh.insertShipment(12,4,5,"2020-03-07");
        dbh.insertShipment(13,3,5,"2020-03-12");
        dbh.insertShipment(14,2,5,"2020-03-19");
        dbh.insertShipment(15,1,5,"2020-03-30");

        dbh.insertShipment(16,1,5,"2020-04-01");
        dbh.insertShipment(17,2,5,"2020-04-07");
        dbh.insertShipment(18,3,5,"2020-04-12");
        dbh.insertShipment(19,4,5,"2020-04-19");
        dbh.insertShipment(20,5,5,"2020-04-30");

        dbh.insertShipment(21,1,5,"2020-05-01");
        dbh.insertShipment(22,3,5,"2020-05-07");
        dbh.insertShipment(23,5,5,"2020-05-12");
        dbh.insertShipment(24,3,5,"2020-05-19");
        dbh.insertShipment(25,2,5,"2020-05-30");

        dbh.insertShipment(26,5,5,"2020-06-01");
        dbh.insertShipment(27,3,5,"2020-06-07");
        dbh.insertShipment(28,1,5,"2020-06-12");
        dbh.insertShipment(29,3,5,"2020-06-19");
        dbh.insertShipment(30,5,5,"2020-06-30");

        dbh.insertShipment(31,3,5,"2020-07-01");
        dbh.insertShipment(32,2,5,"2020-07-07");
        dbh.insertShipment(33,4,5,"2020-07-12");
        dbh.insertShipment(34,4,5,"2020-07-19");
        dbh.insertShipment(35,1,5,"2020-07-30");

        dbh.insertShipment(36,2,5,"2020-08-01");
        dbh.insertShipment(37,2,5,"2020-08-07");
        dbh.insertShipment(38,2,5,"2020-08-12");
        dbh.insertShipment(39,5,5,"2020-08-19");
        dbh.insertShipment(40,5,5,"2020-08-30");

        dbh.insertShipment(41,4,5,"2020-09-01");
        dbh.insertShipment(42,3,5,"2020-09-07");
        dbh.insertShipment(43,3,5,"2020-09-12");
        dbh.insertShipment(44,3,5,"2020-09-19");
        dbh.insertShipment(45,2,5,"2020-09-30");

        dbh.insertShipment(46,3,5,"2020-10-01");
        dbh.insertShipment(47,2,5,"2020-10-07");
        dbh.insertShipment(48,1,5,"2020-10-12");
        dbh.insertShipment(49,3,5,"2020-10-19");
        dbh.insertShipment(50,4,5,"2020-10-30");

        dbh.insertShipment(51,2,5,"2020-11-01");
        dbh.insertShipment(52,2,5,"2020-11-07");
        dbh.insertShipment(53,4,5,"2020-11-12");
        dbh.insertShipment(54,3,5,"2020-11-19");
        dbh.insertShipment(55,5,5,"2020-11-30");

        dbh.insertShipment(56,2,5,"2020-01-01");
        dbh.insertShipment(57,3,5,"2020-01-02");
        dbh.insertShipment(58,4,5,"2020-01-03");
        dbh.insertShipment(59,5,5,"2020-01-04");
        dbh.insertShipment(60,6,5,"2020-01-02");
    }

    public void addShipmentItems() {
        dbh.insert_shipment_items(1,1,1,80);
        dbh.insert_shipment_items(1,2,1,10);
        dbh.insert_shipment_items(2,3,2,25);
        dbh.insert_shipment_items(3,4,3,46);
        dbh.insert_shipment_items(4,5,4,40);
        dbh.insert_shipment_items(5,6,5,31);
        dbh.insert_shipment_items(6,7,6,80);
        dbh.insert_shipment_items(7,8,7,70);
        dbh.insert_shipment_items(8,9,8,84);
        dbh.insert_shipment_items(9,10,9,50);
        dbh.insert_shipment_items(10,11,10,87);
        dbh.insert_shipment_items(11,12,9,64);
        dbh.insert_shipment_items(12,13,8,32);
        dbh.insert_shipment_items(13,14,3,38);
        dbh.insert_shipment_items(14,15,2,84);
        dbh.insert_shipment_items(15,16,7,82);
        dbh.insert_shipment_items(16,17,4,89);
        dbh.insert_shipment_items(17,18,5,90);
        dbh.insert_shipment_items(18,19,3,93);
        dbh.insert_shipment_items(19,20,2,47);
        dbh.insert_shipment_items(20,21,2,47);

        dbh.insert_shipment_items(21,22,1,80);
        dbh.insert_shipment_items(22,23,7,10);
        dbh.insert_shipment_items(23,24,2,25);
        dbh.insert_shipment_items(24,25,3,46);
        dbh.insert_shipment_items(25,26,4,40);
        dbh.insert_shipment_items(26,27,5,31);
        dbh.insert_shipment_items(27,28,6,80);
        dbh.insert_shipment_items(28,29,7,70);
        dbh.insert_shipment_items(29,30,8,84);
        dbh.insert_shipment_items(30,31,9,50);
        dbh.insert_shipment_items(31,32,10,87);
        dbh.insert_shipment_items(32,33,9,64);
        dbh.insert_shipment_items(33,34,8,32);
        dbh.insert_shipment_items(34,35,3,38);
        dbh.insert_shipment_items(35,36,2,84);
        dbh.insert_shipment_items(36,37,7,82);
        dbh.insert_shipment_items(37,38,4,89);
        dbh.insert_shipment_items(38,39,5,90);
        dbh.insert_shipment_items(39,40,3,93);
        dbh.insert_shipment_items(40,41,2,47);

        dbh.insert_shipment_items(41,42,1,10);
        dbh.insert_shipment_items(42,43,2,25);
        dbh.insert_shipment_items(43,44,3,46);
        dbh.insert_shipment_items(44,45,4,40);
        dbh.insert_shipment_items(45,46,5,31);
        dbh.insert_shipment_items(46,47,6,80);
        dbh.insert_shipment_items(47,48,7,70);
        dbh.insert_shipment_items(48,49,8,84);
        dbh.insert_shipment_items(49,50,9,50);
        dbh.insert_shipment_items(50,51,10,87);
        dbh.insert_shipment_items(51,52,9,64);
        dbh.insert_shipment_items(52,53,8,32);
        dbh.insert_shipment_items(53,54,3,38);
        dbh.insert_shipment_items(54,55,2,84);
        dbh.insert_shipment_items(55,56,7,82);
        dbh.insert_shipment_items(56,57,4,89);
        dbh.insert_shipment_items(57,58,5,90);
        dbh.insert_shipment_items(58,59,3,93);
        dbh.insert_shipment_items(59,60,2,47);
        dbh.insert_shipment_items(60,61,2,47);
    }


}
