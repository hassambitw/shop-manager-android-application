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

public class View_receipt extends AppCompatActivity {
    TextView tv1;
    TextView tv2;
    TextView tv3;
    TextView tv4;
    DBHelper dbO;
    TextView total_tv;
    View_items_Adapter adapter;
    RecyclerView recyclerView;
    int customer_id;
    int staff_id;
    String order_date;
    private RecyclerView.LayoutManager itemsLayoutManager;
    // hello
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dbO=new DBHelper(this);
        ArrayList<Order_Items>itemList=new ArrayList<>();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_receipt);

        Intent intent = getIntent();
        int order_id=intent.getIntExtra("order_id",0);
        Cursor c = dbO.get_Order_Details(order_id);

        while(c.moveToNext()){
            customer_id=c.getInt(c.getColumnIndex("customer_id"));
            staff_id=c.getInt(c.getColumnIndex("staff_id"));
            order_date=c.getString(c.getColumnIndex("order_date"));
        }


        total_tv=(TextView)findViewById(R.id.total);
        tv1=(TextView)findViewById(R.id.tv1);
        tv1.setText(Integer.toString(order_id));
        tv2=(TextView)findViewById(R.id.tv2);
        tv2.setText(Integer.toString(customer_id));
        tv3=(TextView)findViewById(R.id.tv3);
        tv3.setText(order_date);
        tv4=(TextView)findViewById(R.id.tv4);
        tv4.setText(Integer.toString(staff_id));





       // dbO.insert_order_item(order_id,8,288,2,90,0.9);
      //  dbO.insert_order_item(order_id,87,28,2,30,0.2);

        Cursor c1 = dbO.getAllFrom_Order_items(order_id);

        while(c1.moveToNext()){
            int item_id=c1.getInt(c1.getColumnIndex("item_id"));
            int product_id=c1.getInt(c1.getColumnIndex("product_id"));

            int quantity=c1.getInt(c1.getColumnIndex("quantity"));
            double price=c1.getDouble(c1.getColumnIndex("price"));
            double discount=c1.getDouble(c1.getColumnIndex("discount"));
            dbO.decreaseQuantity(product_id,quantity);
            Cursor c2=dbO.getProductName(product_id);
            String name="";
            while(c2.moveToNext()){
                name=c2.getString(c2.getColumnIndex("product_name"));
            }
            itemList.add(new Order_Items(order_id,item_id,product_id,quantity,price,discount,name));
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
            double p = itemList.get(i).getPrice() * itemList.get(i).getQuantity() * itemList.get(i).getDiscount();
            total_price = p+total_price;
        }
        total_tv.setText(Double.toString(round(total_price,2)));


    }

    public void goBackToSales(View v){
        Intent i=new Intent(this,MainActivity.class);
        i.putExtra("from_sales","sales");
        startActivity(i);
    }
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

//    @Override
//    public void onBackPressed() {
//        Intent i=new Intent(this,MainActivity.class);
//        i.putExtra("from_sales","sales");
//        startActivity(i);
//    }
}