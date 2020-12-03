package com.example.atry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

public class Add_Sale extends AppCompatActivity {
    EditText order_id;
    EditText customer_id;
    EditText staff_id;
    EditText order_date;
    DBHelper dbO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {



        dbO=new DBHelper(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__sale);

        Toolbar toolbar = findViewById(R.id.toolbar5); //makes your own toolbar, needed inorder to make your nav drawer functional
        //also remove the other actionbar using NoActionBar theme
        toolbar.setTitle("Add Sale");
        toolbar.setBackgroundColor(getResources().getColor(R.color.weirdbluevariant));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Window window = this.getWindow();
        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.black));


        order_id=(EditText)findViewById(R.id.order_id_tv);
        customer_id=(EditText)findViewById(R.id.customer_id_tv);
        staff_id=(EditText)findViewById(R.id.staff_id_tv);
        order_date=(EditText)findViewById(R.id.order_date_tv);

    }
    public void onClick_Cancel(View v){
      finish();
    }
    public void onClick_Add(View v){
        Intent i=new Intent(this,Add_item.class);

        dbO.insertOrder(Integer.parseInt(order_id.getText().toString()),Integer.parseInt(customer_id.getText().toString()),order_date.getText().toString(),Integer.parseInt(staff_id.getText().toString()));
        i.putExtra("order_id",Integer.parseInt(order_id.getText().toString()));
        startActivity(i);
    }
}