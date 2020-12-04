package com.example.atry;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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

        Toolbar toolbar = findViewById(R.id.toolbar7); //makes your own toolbar, needed inorder to make your nav drawer functional
        //also remove the other actionbar using NoActionBar theme
        toolbar.setTitle("Add Item");
        toolbar.setBackgroundColor(getResources().getColor(R.color.weirdbluevariant));
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_baseline_arrow_back_24));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               onBackPressed();
            }
        });
        Window window = this.getWindow();
        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.black));

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
    public void handleOnClick_Final_price(View view)
    {
        if((!isEmpty(price)) && (!isEmpty(quantity))&&(!isEmpty(discount))){
            double d=getDiscount(Integer.parseInt(quantity.getText().toString()),Double.parseDouble(price.getText().toString()),Double.parseDouble(discount.getText().toString()));
            final_price.setText(Double.toString(d));
        }
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

    public boolean stringEmpty(EditText et){
        if(et.getText().toString().trim().length()==0){
            return true;
        }else{
            return false;
        }
    }
    public boolean idStartWithZero(EditText et){
        if(et.getText().toString().trim().startsWith("0")){
            return true;
        }else{
            return false;
        }
    }
    public void add_another_item(View v){

        if(stringEmpty(item_id)||stringEmpty(product_id)||stringEmpty(price)||stringEmpty(quantity)||stringEmpty(discount)){
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setTitle("ERROR");
            builder1.setMessage("All fields must be filled");
            builder1.show();
        }
        else if(idStartWithZero(item_id)||idStartWithZero(product_id)){
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setTitle("ERROR");
            builder1.setMessage("IDs cannot start with zero");
            builder1.show();
        }else if(Double.isNaN(Double.parseDouble(price.getText().toString()))){
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setTitle("ERROR");
            builder1.setMessage("Invalid Price");
            builder1.show();
        }else if(Double.isNaN(Double.parseDouble(discount.getText().toString()))){
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setTitle("ERROR");
            builder1.setMessage("Invalid Discount");
            builder1.show();
        }else {
            int out = dbO.insert_order_item(order_id, Integer.parseInt(item_id.getText().toString()), Integer.parseInt(product_id.getText().toString()), Integer.parseInt(quantity.getText().toString()), Double.parseDouble(price.getText().toString()), Double.parseDouble(discount.getText().toString()));
            if (out == 2) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                builder1.setTitle("ERROR");
                builder1.setMessage("Item ID should be unique");
                builder1.show();
            } else if (out == 3) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                builder1.setTitle("ERROR");
                builder1.setMessage("Product does not exist. Would you like to re-enter the product ID or add a new product?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Add Customer",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                dialog.cancel();
                                Intent i = new Intent(Add_item.this, AddProduct.class);
                                startActivity(i);
                            }
                        });

                builder1.setNegativeButton(
                        "Re-enter Customer ID",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            } else {

                count = count + 1;
                count_tv.setText(Integer.toString(count));
                item_id.setText("");
                product_id.setText("");
                quantity.setText("");
                price.setText("");
                discount.setText("");
                final_price.setText("");
            }
        }
    }
    public void view_receipt(View v) {
        if (stringEmpty(item_id) || stringEmpty(product_id) || stringEmpty(price) || stringEmpty(quantity) || stringEmpty(discount)) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setTitle("ERROR");
            builder1.setMessage("All fields must be filled");
            builder1.show();
        } else if (idStartWithZero(item_id) || idStartWithZero(product_id)) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setTitle("ERROR");
            builder1.setMessage("IDs cannot start with zero");
            builder1.show();
        } else if (Double.isNaN(Double.parseDouble(price.getText().toString()))) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setTitle("ERROR");
            builder1.setMessage("Invalid Price");
            builder1.show();
        } else if (Double.isNaN(Double.parseDouble(discount.getText().toString()))) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setTitle("ERROR");
            builder1.setMessage("Invalid Discount");
            builder1.show();
        } else {
            int out = dbO.insert_order_item(order_id, Integer.parseInt(item_id.getText().toString()), Integer.parseInt(product_id.getText().toString()), Integer.parseInt(quantity.getText().toString()), Double.parseDouble(price.getText().toString()), Double.parseDouble(discount.getText().toString()));
            if (out == 2) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                builder1.setTitle("ERROR");
                builder1.setMessage("Item ID should be unique");
                builder1.show();
            } else if (out == 3) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                builder1.setTitle("ERROR");
                builder1.setMessage("Product does not exist. Would you like to re-enter the product ID or add a new product?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Add Customer",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                dialog.cancel();
                                Intent i = new Intent(Add_item.this, AddProduct.class);
                                startActivity(i);
                            }
                        });

                builder1.setNegativeButton(
                        "Re-enter Customer ID",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            } else {
                // dbO.insert_order_item(order_id,Integer.parseInt(item_id.getText().toString()),Integer.parseInt(product_id.getText().toString()),Integer.parseInt(quantity.getText().toString()),Double.parseDouble(price.getText().toString()),Double.parseDouble(discount.getText().toString()));
                Intent i = new Intent(this, View_receipt.class);
                i.putExtra("order_id", order_id);
                startActivity(i);
            }
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Going back will erase current order and all items in it. Do you still wish to go back? ");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                        dbO.deleteOrders_and_related_items(order_id);
                        Intent i=new Intent(Add_item.this,MainActivity.class);
                        i.putExtra("from_sales","sales");
                        startActivity(i);
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();

    }
}