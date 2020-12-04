package com.example.atry;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

public class editStaff_activity extends AppCompatActivity {

    Button cancelBtn;
    Button doneBtn;
    TextView staffID_tv;
    EditText fname_et, lname_et, phone_et, email_et;
    DBHelper dbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_staff);


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
        staffID_tv = findViewById(R.id.staffid_edit);
        fname_et = findViewById(R.id.firstName_edit);
        lname_et = findViewById(R.id.lastName_edit);
        phone_et = findViewById(R.id.phone_edit);
        email_et = findViewById(R.id.email_edit);
        cancelBtn = findViewById(R.id.cancel);
        doneBtn = findViewById(R.id.done);

        dbh = new DBHelper(getApplicationContext());

        //getting vals from adapter
        Intent i = getIntent();
        int staffID = i.getIntExtra("staff_id", 0);
        String fname = i.getStringExtra("first_name");
        String lname = i.getStringExtra("last_name");
        String phone = i.getStringExtra("phone");
        String email = i.getStringExtra("email");


        //setting them to tvs
        staffID_tv.setText(Integer.toString(staffID));
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
                int staff_id = Integer.parseInt(staffID_tv.getText().toString());
                String staff_fname = fname_et.getText().toString();
                String staff_lname = lname_et.getText().toString();
                String staff_email = email_et.getText().toString();
                String staff_phone = phone_et.getText().toString();


                if (!staff_fname.isEmpty() && !staff_lname.isEmpty() && !staff_email.isEmpty() && !staff_phone.isEmpty()) {

                    if (dbh.updateStaff(staff_id, staff_fname, staff_lname, staff_phone, staff_email)) {
                        Toast.makeText(getApplicationContext(), "Data updated", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        i.putExtra("from_edit_staff","staff");
                        startActivity(i);
                    } else {
                        Toast.makeText(getApplicationContext(), "Data not updated", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Data not updated", Toast.LENGTH_SHORT).show();
                    if (staff_fname.length() == 0)
                        fname_et.setError("Enter first name.");
                    else if (staff_lname.length() == 0)
                        lname_et.setError("Enter last name.");
                    else if (staff_email.length() == 0) email_et.setError("Enter email.");
                    else if (staff_phone.length() == 0)
                        phone_et.setError("Enter phone number.");
                }
            }
        });
    }
}
