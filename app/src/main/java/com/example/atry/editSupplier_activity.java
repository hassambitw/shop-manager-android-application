package com.example.atry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class editSupplier_activity extends AppCompatActivity {

    Button cancelBtn;
    Button doneBtn;
    TextView supplierID_tv;
    EditText name_et, phone_et, email_et;
    DBHelper dbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_supplier);


        Toolbar toolbar = findViewById(R.id.toolbar9); //makes your own toolbar, needed inorder to make your nav drawer functional
        //also remove the other actionbar using NoActionBar theme
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

        //ids
        supplierID_tv = findViewById(R.id.supplierid_edit);
        name_et = findViewById(R.id.name_edit);
        phone_et = findViewById(R.id.phone_edit);
        email_et = findViewById(R.id.email_edit);
        cancelBtn = findViewById(R.id.cancel);
        doneBtn = findViewById(R.id.done);

        dbh = new DBHelper(getApplicationContext());

        //getting vals from adapter
        Intent i = getIntent();
        int custID = i.getIntExtra("supplier_id", 0);
        String name = i.getStringExtra("supplier_name");
        String phone = i.getStringExtra("supplier_num");
        String email = i.getStringExtra("supplier_email");
        //do u see this

        //setting them to tvs
        supplierID_tv.setText(Integer.toString(custID));
        name_et.setText(name);
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
                int supplier_id = Integer.parseInt(supplierID_tv.getText().toString());
                String supplier_name = name_et.getText().toString();
                String supplier_email = email_et.getText().toString();
                String supplier_phone = phone_et.getText().toString();


                if (!supplier_name.isEmpty() && !supplier_email.isEmpty() && !supplier_phone.isEmpty()) {

                    if (dbh.updateSupplier(supplier_id, supplier_name, supplier_phone, supplier_email)) {
                        Toast.makeText(getApplicationContext(), "Data updated", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        i.putExtra("from_edit_supplier","supplier");
                        startActivity(i);
                    } else {
                        Toast.makeText(getApplicationContext(), "Data not updated", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Data not updated", Toast.LENGTH_SHORT).show();
                    if (supplier_name.length() == 0)
                        name_et.setError("Enter name.");
                    else if (supplier_email.length() == 0) email_et.setError("Enter email.");
                    else if (supplier_phone.length() == 0)
                        phone_et.setError("Enter phone number.");
                }
            }
        });
    }
}