package com.example.atry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddCustomer_Activity extends AppCompatActivity {

    Button cancelBtn;
    Button doneBtn;
    EditText custID_et, cust_fname_et, cust_lname_et, cust_email_et, cust_phone_et;
    DBHelper dbh;
    int customer_id;
    String customer_fname;
    String customer_lname;
    String customer_email;
    String customer_phone;
    private RecyclerView.Adapter customerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);



        Toolbar toolbar = findViewById(R.id.toolbar4); //makes your own toolbar, needed inorder to make your nav drawer functional
        //also remove the other actionbar using NoActionBar theme
        toolbar.setBackgroundColor(getResources().getColor(R.color.weirdbluevariant));
        toolbar.setTitle("Add Customer");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Window window = this.getWindow();

        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.black));
        cancelBtn = findViewById(R.id.cancelbtn);
        doneBtn = findViewById(R.id.donebtn);
        custID_et = findViewById(R.id.customer_idtv);
        cust_fname_et = findViewById(R.id.firstNametv);
        cust_lname_et = findViewById(R.id.lastNametv);
        cust_email_et = findViewById(R.id.emailtv);
        cust_phone_et = findViewById(R.id.phonetv);

        dbh = new DBHelper(getApplicationContext());

        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    customer_id = Integer.parseInt(custID_et.getText().toString());
                } catch (NumberFormatException nfe) {
                    Toast.makeText(getApplicationContext(), "Data not added", Toast.LENGTH_SHORT).show();
                }
                customer_fname = cust_fname_et.getText().toString();
                customer_lname = cust_lname_et.getText().toString();
                customer_email = cust_email_et.getText().toString();
                customer_phone = cust_phone_et.getText().toString();
                if (!custID_et.getText().toString().isEmpty() && !customer_fname.isEmpty() && !customer_lname.isEmpty() && !customer_email.isEmpty() && !customer_phone.isEmpty()) {

                    if (dbh.insertCustomer(customer_id, customer_fname, customer_lname, customer_phone, customer_email)) {
                        Toast.makeText(getApplicationContext(), "Data added", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        i.putExtra("from_customers","customers");
                        startActivity(i);
                    } else {
                        Toast.makeText(getApplicationContext(), "Data not added", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Data not added", Toast.LENGTH_SHORT).show();
                    if (custID_et.getText().toString().length() == 0)
                        custID_et.setError("Enter Customer ID.");
                    else if (customer_fname.length() == 0)
                        cust_fname_et.setError("Enter first name.");
                    else if (customer_lname.length() == 0)
                        cust_lname_et.setError("Enter last name.");
                    else if (customer_email.length() == 0) cust_email_et.setError("Enter email.");
                    else if (customer_phone.length() == 0)
                        cust_phone_et.setError("Enter phone number.");
                }
//    }

            }
        });


        cancelBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }
}