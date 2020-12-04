package com.example.atry;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Add_Sale extends AppCompatActivity {
    EditText order_id;
    EditText customer_id;
    EditText staff_id;
    EditText order_date;
    DBHelper dbO;
    Date currDate;
    Date enteredDate;
    final Calendar myCalendar = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dbO=new DBHelper(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__sale);
        order_id=(EditText)findViewById(R.id.order_id_tv);
        customer_id=(EditText)findViewById(R.id.customer_id_tv);
        staff_id=(EditText)findViewById(R.id.staff_id_tv);
        order_date=(EditText)findViewById(R.id.order_date_tv);



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

        order_date.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(Add_Sale.this, date, myCalendar
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


        order_date.setText(sdf.format(myCalendar.getTime()));
    }

    public void onClick_Cancel(View v){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Are you sure you want to cancel order?");
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
    public void onClick_Add(View v) {

        if (stringEmpty(order_id)||stringEmpty(customer_id)||stringEmpty(order_date)||stringEmpty(staff_id)) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setTitle("ERROR");
            builder1.setMessage("All fields must be filled");
            builder1.show();
        }
        else if(enteredDate.after(currDate)){
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setTitle("ERROR");
            builder1.setMessage("Invalid date selected");
            builder1.show();
        }

        else {
            int out = dbO.insertOrder(Integer.parseInt(order_id.getText().toString()), Integer.parseInt(customer_id.getText().toString()), order_date.getText().toString(), Integer.parseInt(staff_id.getText().toString()));
            if(out==2){
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                builder1.setTitle("ERROR");
                builder1.setMessage("ID should be unique");
                builder1.show();
            }else if(out==3){
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                builder1.setTitle("ERROR");
                builder1.setMessage("Customer does not exist. Would you like to re-enter the customer ID or add a new customer?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Add Customer",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                dialog.cancel();
                                Intent i = new Intent(Add_Sale.this, AddCustomer_Activity.class);
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
                Intent i = new Intent(this, Add_item.class);
                i.putExtra("order_id", Integer.parseInt(order_id.getText().toString()));
                startActivity(i);
            }
        }
    }



}