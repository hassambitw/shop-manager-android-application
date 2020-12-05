package com.example.atry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class view_purchase extends AppCompatActivity {
    TextView tv1;
    TextView tv2;
    TextView tv3;
    TextView tv4;
    DBHelper dbO;
    TextView total_tv;
    view_purchase_Adapter adapter;
    RecyclerView recyclerView;
    int shipping_quote;
    int shipment_num;
    int supplier_id;
    String shipment_date;
    private RecyclerView.LayoutManager purchase_items_LayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dbO=new DBHelper(this);
        ArrayList<Shipment_items>shipment_items_List=new ArrayList<>();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_purchase);

        Intent intent = getIntent();
        shipment_num=intent.getIntExtra("shipment_id",0);
        Cursor c = dbO.get_Shipment_Details(shipment_num);

        while(c.moveToNext()){
            supplier_id=c.getInt(c.getColumnIndex("shipment_supplier_id"));
            shipping_quote=c.getInt(c.getColumnIndex("shipment_quote"));
            shipment_date=c.getString(c.getColumnIndex("shipment_date"));
        }

        //get supplier name later when table exists

        total_tv=(TextView)findViewById(R.id.total);
        tv1=(TextView)findViewById(R.id.tv1);
        tv1.setText(Integer.toString(shipment_num));
        tv2=(TextView)findViewById(R.id.tv2);
        tv2.setText(Integer.toString(supplier_id));
        tv3=(TextView)findViewById(R.id.tv3);
        tv3.setText(shipment_date);
        tv4=(TextView)findViewById(R.id.tv4);
        tv4.setText(Integer.toString(shipping_quote));





        // dbO.insert_order_item(order_id,8,288,2,90,0.9);
        //  dbO.insert_order_item(order_id,87,28,2,30,0.2);

        Cursor c1 = dbO.get_Shipment_items(shipment_num);

        while(c1.moveToNext()){
            int item_id=c1.getInt(c1.getColumnIndex("item_id"));
            int product_id=c1.getInt(c1.getColumnIndex("shipment_product_id"));
            int quantity=c1.getInt(c1.getColumnIndex("shipment_quantity"));
            dbO.increaseQuantity(product_id,quantity);
            double price=0;
            String name="";
            Cursor c2=dbO.get_price_and_name(item_id);
            while(c2.moveToNext()){
                name=c2.getString(c2.getColumnIndex("product_name"));
                price=c2.getDouble(c2.getColumnIndex("list_price"));
            }

            shipment_items_List.add(new Shipment_items(shipment_num,quantity,product_id,item_id,price,name));
        }

        // set up the RecyclerView
        recyclerView = findViewById(R.id.puchase_items_recycler_view);
        recyclerView.setHasFixedSize(true);
        purchase_items_LayoutManager= new LinearLayoutManager(this);
        adapter = new view_purchase_Adapter(shipment_items_List);
        recyclerView.setLayoutManager(purchase_items_LayoutManager);
        recyclerView.setAdapter(adapter);

        double total_price=0;
        for(int i=0;i<shipment_items_List.size();i++){
            double p = shipment_items_List.get(i).getProduct_cost() * shipment_items_List.get(i).getShipmentQuantity();
            total_price = p+total_price;
        }
        total_price=total_price+(total_price*((double)shipping_quote/100));
        total_tv.setText(Double.toString(round(total_price,2)));


    }

    public void goBackToPurchase(View v){
        Intent i=new Intent(this,MainActivity.class);
        i.putExtra("from_purchase","purchase");
        startActivity(i);
    }
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}