package com.example.atry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Add_item extends AppCompatActivity {

    TextView count_tv;
    EditText item_id;
    EditText product_id;
    EditText quantity;
    EditText price;
    EditText discount;
    TextView final_price;
    int count=1;
    DBHelper dbO;
    int order_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dbO=new DBHelper(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        Intent intent = getIntent();
        order_id=intent.getIntExtra("order_id",0);
        count_tv=(TextView)findViewById(R.id.count);
        item_id=(EditText)findViewById(R.id.item_id_tv);
        product_id=(EditText)findViewById(R.id.product_id_tv);
        quantity=(EditText)findViewById(R.id.quantity_tv);
        price=(EditText)findViewById(R.id.price_tv);
        discount=(EditText)findViewById(R.id.discount_tv);
        final_price=(TextView)findViewById(R.id.final_price_tv);
        discount.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    //do nothing
                } else {
                    if((!isEmpty(price)) && (!isEmpty(quantity))&&(!isEmpty(discount))){
                        double d=getDiscount(Integer.parseInt(quantity.getText().toString()),Double.parseDouble(price.getText().toString()),Double.parseDouble(discount.getText().toString()));
                        final_price.setText(Double.toString(d));
                    }
                }
            }
        });

        price.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    //do nothing
                } else {
                    if((!isEmpty(price)) && (!isEmpty(quantity))&&(!isEmpty(discount))){
                        double d=getDiscount(Integer.parseInt(quantity.getText().toString()),Double.parseDouble(price.getText().toString()),Double.parseDouble(discount.getText().toString()));
                        final_price.setText(Double.toString(d));
                    }
                }
            }
        });

        quantity.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    //do nothing
                } else {
                    if((!isEmpty(price)) && (!isEmpty(quantity))&&(!isEmpty(discount))){
                        double d=getDiscount(Integer.parseInt(quantity.getText().toString()),Double.parseDouble(price.getText().toString()),Double.parseDouble(discount.getText().toString()));
                        final_price.setText(Double.toString(d));
                    }
                }
            }
        });

    }
    public double getDiscount(int quantity, double price, double discount){
        double d=(100-discount)/100;
        double final_price=d*quantity*price;
        return final_price;
    }
    private boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0)
            return false;

        return true;
    }
    public void add_another_item(View v){
        dbO.insert_order_item(order_id,Integer.parseInt(item_id.getText().toString()),Integer.parseInt(product_id.getText().toString()),Integer.parseInt(quantity.getText().toString()),Double.parseDouble(price.getText().toString()),Double.parseDouble(discount.getText().toString()));
        count=count+1;
        count_tv.setText(Integer.toString(count));
        item_id.setText("");
        product_id.setText("");
        quantity.setText("");
        price.setText("");
        discount.setText("");
    }
    public void view_receipt(View v){
        dbO.insert_order_item(order_id,Integer.parseInt(item_id.getText().toString()),Integer.parseInt(product_id.getText().toString()),Integer.parseInt(quantity.getText().toString()),Double.parseDouble(price.getText().toString()),Double.parseDouble(discount.getText().toString()));
        Intent i=new Intent(this,View_receipt.class);
        i.putExtra("order_id",order_id);
        startActivity(i);
    }
}