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

public class AddSupplier extends AppCompatActivity {

    Button cancelBtn;
    Button doneBtn;
    EditText supplierID_et, supplier_name_et, supplier_email_et, supplier_phone_et;
    DBHelper dbh;
    int supplier_id;
    String supplier_name;
    String supplier_email;
    String supplier_phone;
    private RecyclerView.Adapter supplierAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_supplier);



        Toolbar toolbar = findViewById(R.id.toolbar4); //makes your own toolbar, needed inorder to make your nav drawer functional
        //also remove the other actionbar using NoActionBar theme
        toolbar.setBackgroundColor(getResources().getColor(R.color.weirdbluevariant));
        toolbar.setTitle("Add Supplier");
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
        supplierID_et = findViewById(R.id.supplier_idtv);
        supplier_name_et = findViewById(R.id.supplier_name_tv);
        supplier_email_et = findViewById(R.id.supplier_email_tv);
        supplier_phone_et = findViewById(R.id.supplier_phone_tv);

        dbh = new DBHelper(getApplicationContext());

        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    supplier_id = Integer.parseInt(supplierID_et.getText().toString());
                } catch (NumberFormatException nfe) {
                    Toast.makeText(getApplicationContext(), "Data not added", Toast.LENGTH_SHORT).show();
                }
                supplier_name = supplier_name_et.getText().toString();
                supplier_email = supplier_email_et.getText().toString();
                supplier_phone = supplier_phone_et.getText().toString();
                if (!supplierID_et.getText().toString().isEmpty() && !supplier_name.isEmpty() && !supplier_email.isEmpty() && !supplier_phone.isEmpty()) {
                   if (!idStartWithZero(supplierID_et)) {
                       if (dbh.insertSupplier(supplier_id, supplier_name, supplier_phone, supplier_email)) {
                           Toast.makeText(getApplicationContext(), "Data added", Toast.LENGTH_SHORT).show();
                           Intent i = new Intent(getApplicationContext(), MainActivity.class);
                           i.putExtra("from_supplier", "suppliers");
                           startActivity(i);
                       } else {
                           Toast.makeText(getApplicationContext(), "Data not added", Toast.LENGTH_SHORT).show();
                       }
                   } else {
                       Toast.makeText(getApplicationContext(), "IDs cannot start with 0", Toast.LENGTH_SHORT).show();
                   }
                } else {
                    Toast.makeText(getApplicationContext(), "Data not added", Toast.LENGTH_SHORT).show();
                    if (supplierID_et.getText().toString().length() == 0)
                        supplierID_et.setError("Enter Supplier ID.");
                    else if (supplier_name.length() == 0)
                        supplier_name_et.setError("Enter first name.");
                    else if (supplier_email.length() == 0) supplier_email_et.setError("Enter email.");
                    else if (supplier_phone.length() == 0)
                        supplier_phone_et.setError("Enter phone number.");
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
    public boolean idStartWithZero(EditText et){
        if (et.getText().toString().trim().startsWith("0")){
            return true;
        }else{
            return false;
        }
    }
}