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

public class SalesFragment extends Fragment {


    private RecyclerView salesRecyclerView;
    private RecyclerView.Adapter salesAdapter;
    private RecyclerView.LayoutManager salesLayoutManager;
    Button b;
    DBHelper dbO;
    int req_code = 10;
    ArrayList<Orders> ordersList = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        dbO = new DBHelper(getActivity());
        addOrders();
        addOrderItems();

        //dbO.updateTable();
        Cursor c1 = dbO.getAllFrom_Orders();
        while (c1.moveToNext()) {
            int id = c1.getInt(c1.getColumnIndex("order_id"));
            int customerID = c1.getInt(c1.getColumnIndex("customer_id"));
            String orderDate = c1.getString(c1.getColumnIndex("order_date"));
            int staff_id = c1.getInt(c1.getColumnIndex("staff_id"));

            Cursor c2=dbO.getAllFrom_Order_items(id);
            double total_price=0;
            while(c2.moveToNext()){
                int item_id=c2.getInt(c2.getColumnIndex("item_id"));
                Cursor c3= dbO.getPrice_Of_One_sale(item_id);
                while(c3.moveToNext()){
                    double price=c3.getDouble(c3.getColumnIndex("totalPrice"));
                    total_price=total_price+price;
                }
            }

            ordersList.add(new Orders(id, customerID, orderDate, staff_id,total_price));
        }

        //Double d=dbO.getAllSalesFromAMonth();
        /*ArrayList<Integer>sList=new ArrayList<Integer>();
        while(c.moveToNext()){

            sList.add(c.getInt(c.getColumnIndex("order_id")));
        }
       for(int i=0;i<sList.size();i++){
           System.out.println(sList.get(i));
       }*/
       System.out.println("IN HERE");
       //System.out.println("Double "+d);

        View v = inflater.inflate(R.layout.fragment_sales, container, false);
        b = (Button) v.findViewById(R.id.addSale);
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), Add_Sale.class);
                startActivity(i);
            }
        });

        salesRecyclerView = v.findViewById(R.id.product_recycler_view);
        salesRecyclerView.setHasFixedSize(true);
        salesLayoutManager = new LinearLayoutManager(v.getContext());
        salesAdapter = new Sales_Adapter(ordersList);
        salesRecyclerView.setLayoutManager(salesLayoutManager);
        salesRecyclerView.setAdapter(salesAdapter);


        return v;


    }

    public void addOrders() {
        dbO.insertOrder(1, 1, "2020-01-01", 1);
        dbO.insertOrder(2, 2, "2020-01-15", 1);
        dbO.insertOrder(3, 4, "2020-01-25", 2);
        dbO.insertOrder(4, 3, "2020-01-19", 2);
        dbO.insertOrder(5, 1, "2020-01-10", 4);

        dbO.insertOrder(6, 2, "2020-02-05", 5);
        dbO.insertOrder(7, 3, "2020-02-18", 2);
        dbO.insertOrder(8, 5, "2020-02-21", 4);
        dbO.insertOrder(9, 4, "2020-02-12", 5);
        dbO.insertOrder(10, 2, "2020-02-16", 7);

        dbO.insertOrder(11, 3, "2020-03-05", 6);
        dbO.insertOrder(12, 4, "2020-03-18", 6);
        dbO.insertOrder(13, 6, "2020-03-21", 3);
        dbO.insertOrder(14, 5, "2020-03-12", 4);
        dbO.insertOrder(15, 3, "2020-03-16", 2);

        dbO.insertOrder(16, 4, "2020-04-05", 3);
        dbO.insertOrder(17, 5, "2020-04-18", 6);
        dbO.insertOrder(18, 7, "2020-04-21", 2);
        dbO.insertOrder(19, 6, "2020-04-12", 2);
        dbO.insertOrder(20, 4, "2020-04-16", 8);

        dbO.insertOrder(21, 5, "2020-05-05", 4);
        dbO.insertOrder(22, 6, "2020-05-18", 1);
        dbO.insertOrder(23, 8, "2020-05-21", 8);
        dbO.insertOrder(24, 7, "2020-05-12", 2);
        dbO.insertOrder(25, 5, "2020-05-16", 3);

        dbO.insertOrder(26, 6, "2020-06-05", 5);
        dbO.insertOrder(27, 7, "2020-06-18", 2);
        dbO.insertOrder(28, 9, "2020-06-21", 4);
        dbO.insertOrder(29, 8, "2020-06-12", 5);
        dbO.insertOrder(30, 6, "2020-06-16", 7);

        dbO.insertOrder(31, 7, "2020-07-05", 7);
        dbO.insertOrder(32, 8, "2020-07-18", 4);
        dbO.insertOrder(33, 10, "2020-07-21", 8);
        dbO.insertOrder(34, 9, "2020-07-12", 3);
        dbO.insertOrder(35, 7, "2020-07-16", 2);

        dbO.insertOrder(36, 8, "2020-08-05", 8);
        dbO.insertOrder(37, 9, "2020-08-18", 1);
        dbO.insertOrder(38, 1, "2020-08-21", 2);
        dbO.insertOrder(39, 10, "2020-08-12", 9);
        dbO.insertOrder(40, 8, "2020-08-16", 5);

        dbO.insertOrder(41, 9, "2020-09-05", 9);
        dbO.insertOrder(42, 10, "2020-09-18", 2);
        dbO.insertOrder(43, 2, "2020-09-21", 3);
        dbO.insertOrder(44, 1, "2020-09-12", 4);
        dbO.insertOrder(45, 9, "2020-09-16", 5);

        dbO.insertOrder(46, 10, "2020-10-05", 10);
        dbO.insertOrder(47, 1, "2020-10-18", 3);
        dbO.insertOrder(48, 3, "2020-10-21", 4);
        dbO.insertOrder(49, 2, "2020-10-12", 1);
        dbO.insertOrder(50, 10, "2020-10-16", 7);

        dbO.insertOrder(51, 1, "2020-11-05", 2);
        dbO.insertOrder(52, 2, "2020-11-18", 3);
        dbO.insertOrder(53, 4, "2020-11-21", 4);
        dbO.insertOrder(54, 3, "2020-11-12", 6);
        dbO.insertOrder(55, 1, "2020-11-16", 6);

        dbO.insertOrder(56, 2, "2020-12-02", 2);
        dbO.insertOrder(57, 2, "2020-12-01", 5);
        dbO.insertOrder(58, 4, "2020-12-03", 1);
        dbO.insertOrder(59, 7, "2020-12-04", 8);
        dbO.insertOrder(60, 9, "2020-12-02", 9);
    }

    public void addOrderItems() {
        dbO.insert_order_item(1,1,1,6,1000,6);
        dbO.insert_order_item(5,2,2,5,1000,6);
        dbO.insert_order_item(2,3,3,3,900,25);
        dbO.insert_order_item(3,4,4,6,1000,60);
        dbO.insert_order_item(4,5,5,6,1000,6);

        dbO.insert_order_item(6,6,6,6,1000,6);
        dbO.insert_order_item(7,7,7,5,1000,6);
        dbO.insert_order_item(8,8,8,3,900,25);
        dbO.insert_order_item(9,9,9,6,1000,60);
        dbO.insert_order_item(10,10,10,6,1000,6);

        dbO.insert_order_item(11,11,1,6,1000,6);
        dbO.insert_order_item(12,12,2,3,1000,6);
        dbO.insert_order_item(13,13,3,2,60,6);
        dbO.insert_order_item(14,14,4,1,1000,6);
        dbO.insert_order_item(15,15,5,2,1000,6);

        dbO.insert_order_item(16,16,6,1,1000,6);
        dbO.insert_order_item(17,17,7,2,1000,6);
        dbO.insert_order_item(18,18,8,3,1000,6);
        dbO.insert_order_item(19,19,9,4,1000,6);
        dbO.insert_order_item(20,20,10,5,1000,6);

        dbO.insert_order_item(21,21,1,4,1000,6);
        dbO.insert_order_item(22,22,2,5,1000,6);
        dbO.insert_order_item(23,23,3,2,1000,6);
        dbO.insert_order_item(24,24,4,3,1000,6);
        dbO.insert_order_item(25,25,5,4,1000,6);

        dbO.insert_order_item(26,26,6,1,800,6);
        dbO.insert_order_item(27,27,7,2,1000,6);
        dbO.insert_order_item(28,28,8,4,900,6);
        dbO.insert_order_item(29,29,9,5,1000,6);
        dbO.insert_order_item(30,30,10,6,1000,6);

        dbO.insert_order_item(31,31,1,8,1000,6);
        dbO.insert_order_item(32,32,2,1,900,6);
        dbO.insert_order_item(33,33,3,4,1000,6);
        dbO.insert_order_item(34,34,4,3,950,6);
        dbO.insert_order_item(35,35,5,1,1000,6);

        dbO.insert_order_item(36,36,6,2,1000,6);
        dbO.insert_order_item(37,37,7,3,1000,6);
        dbO.insert_order_item(38,38,8,1,1000,6);
        dbO.insert_order_item(39,39,9,4,1000,6);
        dbO.insert_order_item(40,40,10,1,1000,6);

        dbO.insert_order_item(41,41,1,1,1000,6);
        dbO.insert_order_item(42,42,2,2,1000,6);
        dbO.insert_order_item(43,43,3,3,1000,6);
        dbO.insert_order_item(44,44,4,6,1000,6);
        dbO.insert_order_item(45,45,5,4,1000,6);

        dbO.insert_order_item(46,46,6,1,1000,6);
        dbO.insert_order_item(47,47,7,3,1000,6);
        dbO.insert_order_item(48,48,8,4,1000,6);
        dbO.insert_order_item(49,49,9,3,1000,6);
        dbO.insert_order_item(50,50,10,1,1000,6);

        dbO.insert_order_item(51,51,1,3,1000,6);
        dbO.insert_order_item(52,52,2,4,1000,6);
        dbO.insert_order_item(53,53,3,3,1000,6);
        dbO.insert_order_item(54,54,4,4,1000,6);
        dbO.insert_order_item(55,55,5,6,1000,6);

        dbO.insert_order_item(56,56,6,6,1000,6);
        dbO.insert_order_item(57,57,7,3,1000,6);
        dbO.insert_order_item(58,58,8,2,1000,6);
        dbO.insert_order_item(59,59,9,1,1000,6);
        dbO.insert_order_item(60,60,10,3,1000,6);

        //extra order items

        dbO.insert_order_item(51,61,2,3,1000,6);
        dbO.insert_order_item(52,62,4,4,1000,6);
        dbO.insert_order_item(53,63,9,3,1000,6);
        dbO.insert_order_item(54,64,10,4,1000,6);
        dbO.insert_order_item(55,65,8,6,1000,6);

        dbO.insert_order_item(56,66,8,6,1000,6);
        dbO.insert_order_item(57,67,9,3,1000,6);
        dbO.insert_order_item(58,68,10,2,1000,6);
        dbO.insert_order_item(59,69,2,1,1000,6);
        dbO.insert_order_item(60,70,1,3,1000,6);

        dbO.insert_order_item(41,71,10,1,1000,6);
        dbO.insert_order_item(42,72,3,2,1000,6);
        dbO.insert_order_item(43,73,1,3,1000,6);
        dbO.insert_order_item(44,74,10,6,1000,6);
        dbO.insert_order_item(45,75,9,4,1000,6);

        dbO.insert_order_item(46,76,8,1,1000,6);
        dbO.insert_order_item(47,77,4,3,1000,6);
        dbO.insert_order_item(48,78,5,4,1000,6);
        dbO.insert_order_item(49,79,1,3,1000,6);
        dbO.insert_order_item(50,80,2,1,1000,6);
    }

}
