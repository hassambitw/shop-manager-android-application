package com.example.atry;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddPurchase extends AppCompatActivity {
    EditText shipment_id;
    EditText shipment_quote;
    EditText supplier_id;
    EditText shipment_date;
    DBHelper dbO;
    Date currDate;
    Date enteredDate;
    final Calendar myCalendar = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_purchase);


        Toolbar toolbar = findViewById(R.id.toolbar8); //makes your own toolbar, needed inorder to make your nav drawer functional
        //also remove the other actionbar using NoActionBar theme
        toolbar.setTitle("Add Purchase");
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

        dbO=new DBHelper(this);


        shipment_id=(EditText)findViewById(R.id.shipment_id_tv);
        supplier_id=(EditText)findViewById(R.id.supplier_id_tv);
        shipment_quote=(EditText)findViewById(R.id.shipment_quote_tv);
        shipment_date=(EditText)findViewById(R.id.shipment_date_tv);
        shipment_quote.setText("5");

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                try {
                    updateLabel();
                }catch (Exception e){

                }
            }

        };

        shipment_date.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(AddPurchase.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });




    }
    private void updateLabel() throws ParseException {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        String date= sdf.format(myCalendar.getTime());
        enteredDate=sdf.parse(date);
        currDate=new Date();


        shipment_date.setText(sdf.format(myCalendar.getTime()));
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Going back will cancel your purchase. Do you wish to continue?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                        finish();
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

    public void onClick_Cancel(View v){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Are you sure you want to cancel purchase?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                        finish();
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
    public void onClick_Add(View v) {

        if (stringEmpty(shipment_id)||stringEmpty(supplier_id)||stringEmpty(shipment_date)||stringEmpty(shipment_quote)) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setTitle("ERROR");
            builder1.setMessage("All fields must be filled");
            builder1.show();
        }else if(idStartWithZero(shipment_id)||idStartWithZero(supplier_id)){
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setTitle("ERROR");
            builder1.setMessage("IDs cannot start with zero");
            builder1.show();
        }

        else if(enteredDate.after(currDate)){
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setTitle("ERROR");
            builder1.setMessage("Invalid date selected");
            builder1.show();
        }
        else {
            int out = dbO.insertShipment(Integer.parseInt(shipment_id.getText().toString()), Integer.parseInt(supplier_id.getText().toString()),  Integer.parseInt(shipment_quote.getText().toString()),shipment_date.getText().toString());
            if(out==2){
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                builder1.setTitle("ERROR");
                builder1.setMessage("ID should be unique");
                builder1.show();
            }else if(out==3){
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                builder1.setTitle("ERROR");
                builder1.setMessage("Supplier does not exist. Would you like to re-enter the supplier ID or add a new supplier?");
                builder1.setCancelable(true);
                builder1.setPositiveButton(
                        "Add Customer",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                dialog.cancel();
                                Intent i = new Intent(AddPurchase.this, AddSupplier.class);
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
            }/*else if(out==4){
                // go to add staff
            }*/
            else if (out == 1) {
                Intent i = new Intent(this, Add_Shipment_Item.class);
                i.putExtra("shipment_id", Integer.parseInt(shipment_id.getText().toString()));
                startActivity(i);
            }
        }
    }



}