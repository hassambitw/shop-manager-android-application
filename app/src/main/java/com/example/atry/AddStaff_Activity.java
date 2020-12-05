package com.example.atry;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class AddStaff_Activity extends AppCompatActivity {

    Button cancelBtn;
    Button doneBtn;
    EditText staffID_et, staff_fname_et, staff_lname_et, staff_email_et, staff_phone_et;
    DBHelper dbh;
    int staff_id;
    String staff_fname;
    String staff_lname;
    String staff_email;
    String staff_phone;
    private RecyclerView.Adapter staffAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_staff);



        Toolbar toolbar = findViewById(R.id.toolbar4); //makes your own toolbar, needed inorder to make your nav drawer functional
        //also remove the other actionbar using NoActionBar theme
        toolbar.setBackgroundColor(getResources().getColor(R.color.weirdbluevariant));
        toolbar.setTitle("Add Staff");
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
        staffID_et = findViewById(R.id.staff_idtv);
        staff_fname_et = findViewById(R.id.firstNametv);
        staff_lname_et = findViewById(R.id.lastNametv);
        staff_email_et = findViewById(R.id.emailtv);
        staff_phone_et = findViewById(R.id.phonetv);

        dbh = new DBHelper(getApplicationContext());

        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    staff_id = Integer.parseInt(staffID_et.getText().toString());
                } catch (NumberFormatException nfe) {
                    Toast.makeText(getApplicationContext(), "Data not added", Toast.LENGTH_SHORT).show();
                }
                staff_fname = staff_fname_et.getText().toString();
                staff_lname = staff_lname_et.getText().toString();
                staff_email = staff_email_et.getText().toString();
                staff_phone = staff_phone_et.getText().toString();
                if (!staffID_et.getText().toString().isEmpty() && !staff_fname.isEmpty() && !staff_lname.isEmpty() && !staff_email.isEmpty() && !staff_phone.isEmpty()) {
                    if (!idStartWithZero(staffID_et)) {
                        if (dbh.insertStaff(staff_id, staff_fname, staff_lname, staff_phone, staff_email)) {
                            Toast.makeText(getApplicationContext(), "Data added", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getApplicationContext(), MainActivity.class);
                            i.putExtra("from_staff", "staff");
                            startActivity(i);
//                        custID_et.setText("");
//                        cust_fname_et.setText("");
//                        cust_lname_et.setText("");
//                        cust_email_et.setText("");
//                        cust_phone_et.setText("");
                        } else {
                            Toast.makeText(getApplicationContext(), "Data not added", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "IDs cannot start with 0", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Data not added", Toast.LENGTH_SHORT).show();
                    if (staffID_et.getText().toString().length() == 0)
                        staffID_et.setError("Enter Staff ID.");
                    else if (staff_fname.length() == 0)
                        staff_fname_et.setError("Enter first name.");
                    else if (staff_lname.length() == 0)
                        staff_lname_et.setError("Enter last name.");
                    else if (staff_email.length() == 0) staff_email_et.setError("Enter email.");
                    else if (staff_phone.length() == 0)
                        staff_phone_et.setError("Enter phone number.");
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
