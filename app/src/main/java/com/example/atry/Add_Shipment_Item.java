package com.example.atry;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Add_Shipment_Item extends AppCompatActivity {

    TextView count_tv;
    EditText item_id;
    EditText product_id;
    EditText quantity;
    TextView price;

    TextView final_price;
    int count=1;
    DBHelper dbO;
    int shipment_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dbO=new DBHelper(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__shipment__item);

        Toolbar toolbar = findViewById(R.id.toolbar7); //makes your own toolbar, needed inorder to make your nav drawer functional
        //also remove the other actionbar using NoActionBar theme
        toolbar.setTitle("Add Shipment Item");
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
        shipment_id=intent.getIntExtra("shipment_id",0);
        count_tv=(TextView)findViewById(R.id.count);
        item_id=(EditText)findViewById(R.id.item_id_tv);
        product_id=(EditText)findViewById(R.id.product_id_tv);
        quantity=(EditText)findViewById(R.id.quantity_tv);
        price=(TextView) findViewById(R.id.price_tv);

        final_price=(TextView)findViewById(R.id.final_price_tv);

        product_id.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    //do nothing
                } else {
                    if ((!isEmpty(product_id))) {
                        if (!(dbO.checkProductExists(Integer.parseInt(product_id.getText().toString())))) {
                            product_id_alert();
                        }
                    }
                }

            }
        });





    }

    public void handleOnClick_Final_price(View view)
    {
        double price_p=0;


        if((!isEmpty(product_id)) && (!isEmpty(quantity))){
            System.out.println("INSINDD");
            int i=Integer.parseInt(product_id.getText().toString());
            Cursor c2=dbO.get_price_only(i);

            System.out.println(i);
            double b=0;
               // Cursor c2=dbO.get_price_only(Integer.parseInt(item_id.getText().toString()));
                System.out.println(c2.getCount());
                while(c2.moveToNext()){
                    System.out.println("IN CURSOR");
                    b=c2.getDouble(c2.getColumnIndex("list_price"));
                }
                System.out.println(b);
                price.setText(Double.toString(round(b, 2)));
                double d = b * (double)Integer.parseInt(quantity.getText().toString());
                final_price.setText(Double.toString(d));
        }
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
    public void product_id_alert(){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setTitle("ERROR");
        builder1.setMessage("Product does not exist. Would you like to re-enter the product ID or add a new product?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Add Product",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                        Intent i = new Intent(Add_Shipment_Item.this, AddProduct.class);
                        startActivity(i);
                    }
                });

        builder1.setNegativeButton(
                "Re-enter Product ID",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        product_id.requestFocus();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
    public void add_another_item(View v){

        if(stringEmpty(item_id)||stringEmpty(product_id)||stringEmpty(quantity)){
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
        }else {
            int out = dbO.insert_shipment_items(shipment_id, Integer.parseInt(item_id.getText().toString()), Integer.parseInt(product_id.getText().toString()), Integer.parseInt(quantity.getText().toString()));
            if (out == 2) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                builder1.setTitle("ERROR");
                builder1.setMessage("Item ID should be unique");
                builder1.show();
            } else if (out == 3) {
                product_id_alert();
            } else {

                count = count + 1;
                count_tv.setText(Integer.toString(count));
                item_id.setText("");
                product_id.setText("");
                quantity.setText("");
                price.setText("");

                final_price.setText("");
            }
        }
    }
    public void view_receipt(View v){

        if(stringEmpty(item_id)||stringEmpty(product_id)||stringEmpty(quantity)){
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
        }else {
            int out = dbO.insert_shipment_items(shipment_id, Integer.parseInt(item_id.getText().toString()), Integer.parseInt(product_id.getText().toString()), Integer.parseInt(quantity.getText().toString()));
            if (out == 2) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                builder1.setTitle("ERROR");
                builder1.setMessage("Item ID should be unique");
                builder1.show();
            } else if (out == 3) {
                product_id_alert();
            } else {
                Intent i = new Intent(this, view_purchase.class);
                i.putExtra("shipment_id", shipment_id);
                startActivity(i);
            }
        }}

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Going back will erase current purchase and all items in it. Do you still wish to go back? ");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                        dbO.deleteShipment_and_related_items(shipment_id);
                        Intent i=new Intent(Add_Shipment_Item.this,MainActivity.class);
                        i.putExtra("from_purchase","purchase");
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
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}