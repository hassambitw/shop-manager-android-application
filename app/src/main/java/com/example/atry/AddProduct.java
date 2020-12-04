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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddProduct extends AppCompatActivity {


    Button finAdd;
    EditText etProdID, etProdName, etProdBrand, etProdCategory, etProdListPrice, etProdQuant, etProdYear;
    DBHelper dbh;
    int prodID;
    String ProductName;
    String ProductBrand;
    String ProductCategory;
    double ProductListPrice;
    int ProductYear;
    int ProductQuantity;

    private RecyclerView.Adapter productAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);


        Toolbar toolbar = findViewById(R.id.toolbar2); //makes your own toolbar, needed inorder to make your nav drawer functional
        //also remove the other actionbar using NoActionBar theme
        toolbar.setTitle("Add Product");
        toolbar.setBackgroundColor(getResources().getColor(R.color.weirdbluevariant));
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Window window = this.getWindow();

// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.black));

//
//        Spinner spinner = (Spinner) findViewById(R.id.spinner_category);
//        spinner.setOnItemClickListener(this);
//
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//                R.array.product_category_array, android.R.layout.simple_spinner_item);
//
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        spinner.setAdapter(adapter);

        //when done , implement toast informing user about the product id created


        finAdd = findViewById(R.id.finish_adding_product);
        etProdID = findViewById(R.id.editTextProductID);
        etProdName = findViewById(R.id.editTextProductName);
        etProdBrand = findViewById(R.id.editTextBrandName);
        etProdCategory = findViewById(R.id.editTextProductCategory);
        etProdListPrice = findViewById(R.id.editTextPrice);
        etProdQuant = findViewById(R.id.editTextQuantity);
        etProdYear = findViewById(R.id.editTextModelYear);

        dbh = new DBHelper(getApplicationContext());

        finAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    prodID = Integer.parseInt(etProdID.getText().toString());
                } catch (NumberFormatException nfe) {
                    Toast.makeText(getApplicationContext(), "Product not added", Toast.LENGTH_SHORT).show();
                }
                ProductName = etProdName.getText().toString();
                ProductBrand = etProdBrand.getText().toString();
                ProductCategory = etProdCategory.getText().toString();
                ProductListPrice = Double.parseDouble(etProdListPrice.getText().toString());
                ProductQuantity = Integer.parseInt(etProdQuant.getText().toString());
                ProductYear = Integer.parseInt(etProdYear.getText().toString());
                if (!etProdID.getText().toString().isEmpty() && !ProductName.isEmpty() && !ProductBrand.isEmpty() && !ProductCategory.isEmpty() && ProductListPrice!=0.0 && ProductQuantity != 0 && ProductYear != 0) {
                    if (dbh.insertProduct(prodID, ProductName, ProductBrand, ProductCategory, ProductYear, ProductListPrice, ProductQuantity)) {
                        Toast.makeText(getApplicationContext(), "Product added", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        i.putExtra("from_product","product");
                        startActivity(i);
//                        etProdCategory.setText("");
//                        etProdYear.setText("");
//                        etProdQuant.setText("");
//                        etProdListPrice.setText("");
//                        etProdBrand.setText("");
//                        etProdName.setText("");
                        //etProdBrand.setText("");

                    } else {
                        Toast.makeText(getApplicationContext(), "Product not added", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Product not added", Toast.LENGTH_SHORT).show();
                    if (etProdID.getText().toString().length() == 0)
                        etProdID.setError("Enter product ID.");
                    else if (etProdName.length() == 0)
                        etProdName.setError("Enter product name.");
                    else if (etProdBrand.length() == 0)
                        etProdBrand.setError("Enter product brand.");
                    else if (etProdListPrice.length() == 0) etProdListPrice.setError("Enter product list price.");
                    else if (etProdQuant.length() == 0)
                        etProdQuant.setError("Enter product stock.");
                    else if (etProdYear.length() == 0)
                        etProdYear.setError("Enter product year.");
                    else if (etProdCategory.length() == 0)
                        etProdCategory.setError("Enter product category.");
                }
//                    dbh.insertCustomer(customer_id, customer_fname, customer_lname, customer_email, customer_phone);

            }
        });




    }


}