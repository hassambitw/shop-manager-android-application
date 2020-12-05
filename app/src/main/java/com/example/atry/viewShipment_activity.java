package com.example.atry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class viewShipment_activity extends AppCompatActivity {

    private RecyclerView shipmentRecyclerView;
    private RecyclerView.Adapter shipment_adapter;
    private RecyclerView.LayoutManager shipmentLayoutManager;
    Button backBtn;
    int req_code = 1;
    DBHelper dbh;
    ArrayList<ShipmentDetails> shipmentList = new ArrayList<ShipmentDetails>();
    TextView noTransactionsMsg;
    String shipment_date;
    double totalPrice;
    int shipmentID;
    LinearLayout ll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_shipment);

        shipmentRecyclerView = findViewById(R.id.shipment_recycler_view);
        ll = findViewById(R.id.empty_view);

        dbh = new DBHelper(getApplicationContext());

        Intent i = getIntent();
        int sID =  i.getIntExtra("supplier_id", 0);     //from customer adapter
        System.out.println("ID BEING SENT IS: " + sID);
        Cursor c = dbh.getShipmentDetails(sID);

        if (!(c.getCount() <= 0)) {
            ll.setVisibility(View.GONE);
            shipmentRecyclerView.setVisibility(View.VISIBLE);
        }
        else {
            ll.setVisibility(View.VISIBLE);
        }

        System.out.println(c.getCount());
        while(c.moveToNext()) {
            shipment_date = c.getString(c.getColumnIndex("shipment_date"));
            shipmentID = c.getInt(c.getColumnIndex("shipment_num"));
            totalPrice = 0;
           totalPrice = c.getDouble(c.getColumnIndex("totalPrice"));
            shipmentList.add(new ShipmentDetails(shipmentID, shipment_date, totalPrice));
        }


        backBtn = findViewById(R.id.back);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        shipmentRecyclerView.setHasFixedSize(true);
        shipmentLayoutManager = new LinearLayoutManager(getApplicationContext());
        shipment_adapter = new shipmentAdapter(shipmentList);
        shipmentRecyclerView.setLayoutManager(shipmentLayoutManager);
        shipmentRecyclerView.setAdapter(shipment_adapter);

    }
}

