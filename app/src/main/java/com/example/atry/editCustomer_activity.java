package com.example.atry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class editCustomer_activity extends AppCompatActivity {

    Button cancelBtn;
    Button doneBtn;
    TextView customerID_tv;
    EditText fname_et, lname_et, phone_et, email_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_customer);


        //
//        Toolbar toolbar = findViewById(R.id.toolbar2); //makes your own toolbar, needed inorder to make your nav drawer functional
//        //also remove the other actionbar using NoActionBar theme
//        toolbar.setBackgroundColor(getResources().getColor(R.color.weirdbluevariant));
//        setSupportActionBar(toolbar);
//
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        Window window = this.getWindow();
//
//        // clear FLAG_TRANSLUCENT_STATUS flag:
//        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//
//        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//
//        // finally change the color
//        window.setStatusBarColor(ContextCompat.getColor(this, R.color.black));
        //ids
        customerID_tv = findViewById(R.id.customerid_edit);
        fname_et = findViewById(R.id.firstName_edit);
        lname_et = findViewById(R.id.lastName_edit);
        phone_et = findViewById(R.id.phone_edit);
        email_et = findViewById(R.id.email_edit);
        cancelBtn = findViewById(R.id.cancel);
        doneBtn = findViewById(R.id.done);

        //getting vals from adapter
        Intent i = getIntent();
        int custID = i.getIntExtra("customer_id", 0);
        String fname = i.getStringExtra("first_name");
        String lname = i.getStringExtra("last_name");
        String phone = i.getStringExtra("phone");
        String email = i.getStringExtra("email");


        //setting them to tvs
        customerID_tv.setText(Integer.toString(custID));
        fname_et.setText(fname);
        lname_et.setText(lname);
        phone_et.setText(phone);
        email_et.setText(email);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}