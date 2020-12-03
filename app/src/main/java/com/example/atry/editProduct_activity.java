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
import android.widget.Toast;

public class editProduct_activity extends AppCompatActivity {

    EditText etProdID, etProdName, etProdBrand, etProdCategory, etProdListPrice, etProdQuant, etProdYear;
    DBHelper dbh;
    Button finAdd;
    int prodID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product_activity);


        Toolbar toolbar = findViewById(R.id.toolbar3); //makes your own toolbar, needed inorder to make your nav drawer functional
        //also remove the other actionbar using NoActionBar theme
        toolbar.setTitle("Edit Product");
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

        int prodID;
        String ProductName;
        String ProductBrand;
        String ProductCategory;
        double ProductListPrice;
        int ProductYear;
        int ProductQuantity;

        finAdd = findViewById(R.id.finish_editing_product);
        //etProdID = findViewById(R.id.editTextEditProductID);
        etProdName = findViewById(R.id.editTextEditProductName);
        etProdBrand = findViewById(R.id.editTextEditBrandName);
        etProdCategory = findViewById(R.id.editTextEditProductCategory);
        etProdListPrice = findViewById(R.id.editTextEditPrice);
        etProdQuant = findViewById(R.id.editTextEditQuantity);
        etProdYear = findViewById(R.id.editTextEditModelYear);

        dbh = new DBHelper(getApplicationContext());

        Intent i = getIntent();
        prodID = i.getIntExtra("product_id", 0);
        ProductName = i.getStringExtra("p_name");
        ProductBrand = i.getStringExtra("brand");
        ProductCategory = i.getStringExtra("category");
        ProductListPrice = i.getDoubleExtra("price",0.0);
        ProductYear = i.getIntExtra("model_year",0);
        ProductQuantity = i.getIntExtra("stock",0);

        etProdName.setText(ProductName);
        etProdBrand.setText(ProductBrand);
        etProdCategory.setText(ProductCategory);
        etProdListPrice.setText(Double.toString(ProductListPrice));
        etProdQuant.setText(Integer.toString(ProductQuantity));
        etProdYear.setText(Integer.toString(ProductYear));


        finAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String customer_id = customerID_tv.getText().toString();
                //int customer_id = Integer.parseInt(customerID_tv.getText().toString());
                String ProductName = etProdName.getText().toString();
                String ProductBrand = etProdBrand.getText().toString();
                String ProductCategory = etProdCategory.getText().toString();
                double ProductListPrice = Double.parseDouble(etProdListPrice.getText().toString());
                int ProductQuantity = Integer.parseInt(etProdQuant.getText().toString());
                int ProductYear = Integer.parseInt(etProdYear.getText().toString());


                if (!ProductName.isEmpty() && !ProductBrand.isEmpty() && !ProductCategory.isEmpty() && ProductListPrice!=0.0 && ProductQuantity != 0 && ProductYear != 0) {

                    if (dbh.updateProduct(prodID, ProductName, ProductBrand, ProductCategory, ProductYear, ProductListPrice, ProductQuantity)) {
                        Toast.makeText(getApplicationContext(), "Product updated", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        i.putExtra("message_from_editing_products","product");
                        startActivity(i);
                    } else {
                        Toast.makeText(getApplicationContext(), "Product not updated", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Product not updated", Toast.LENGTH_SHORT).show();
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
            }
        });

    }
}