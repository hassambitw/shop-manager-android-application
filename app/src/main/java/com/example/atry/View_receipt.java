package com.example.atry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import java.util.ArrayList;

public class View_receipt extends AppCompatActivity {
    TextView tv1;
    TextView tv2;
    TextView tv3;
    TextView tv4;
    DBHelper dbO;
    TextView total_tv;
    View_items_Adapter adapter;
    RecyclerView recyclerView;
    private RecyclerView.LayoutManager itemsLayoutManager;
    // hello
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ArrayList<Order_Items>itemList=new ArrayList<>();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_receipt);

        Intent intent = getIntent();
        int order_id=intent.getIntExtra("order_id",0);
        int customer_id=intent.getIntExtra("customer_id",0);
        String order_date = intent.getStringExtra("order_date");
        int staff_id=intent.getIntExtra("staff_id",0);

        total_tv=(TextView)findViewById(R.id.total);
        tv1=(TextView)findViewById(R.id.tv1);
        tv1.setText(Integer.toString(order_id));
        tv2=(TextView)findViewById(R.id.tv2);
        tv2.setText(Integer.toString(customer_id));
        tv3=(TextView)findViewById(R.id.tv3);
        tv3.setText(order_date);
        tv4=(TextView)findViewById(R.id.tv4);
        tv4.setText(Integer.toString(staff_id));

        dbO=new DBHelper(this);

       // dbO.insert_order_item(order_id,8,288,2,90,0.9);
        dbO.insert_order_item(order_id,87,28,2,30,0.2);

        Cursor c1 = dbO.getAllFrom_Order_items(order_id);

        while(c1.moveToNext()){
            int item_id=c1.getInt(c1.getColumnIndex("item_id"));
            int product_id=c1.getInt(c1.getColumnIndex("product_id"));

            int quantity=c1.getInt(c1.getColumnIndex("quantity"));
            double price=c1.getDouble(c1.getColumnIndex("price"));
            double discount=c1.getDouble(c1.getColumnIndex("discount"));

            itemList.add(new Order_Items(order_id,item_id,product_id,quantity,price,discount));
        }
        // set up the RecyclerView
        recyclerView = findViewById(R.id.items_recycler_view);
        recyclerView.setHasFixedSize(true);
        itemsLayoutManager= new LinearLayoutManager(this);
        adapter = new View_items_Adapter(itemList);
        recyclerView.setLayoutManager(itemsLayoutManager);
        recyclerView.setAdapter(adapter);

        double total_price=0;
        for(int i=0;i<itemList.size();i++){
            double p=itemList.get(i).getPrice()*itemList.get(i).getQuantity()*itemList.get(i).getDiscount();
            total_price=p+total_price;
        }
        total_tv.setText(Double.toString(total_price));

    }
}