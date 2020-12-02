package com.example.atry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class editCustomer_activity extends AppCompatActivity {

    Button cancelBtn;
    Button doneBtn;

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

        cancelBtn = findViewById(R.id.cancel);
        doneBtn = findViewById(R.id.done);

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