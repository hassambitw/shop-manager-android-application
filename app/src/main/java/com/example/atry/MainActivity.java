package com.example.atry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        drawer = findViewById(R.id.drawer_layout); //menu
        Toolbar toolbar = findViewById(R.id.toolbar); //makes your own toolbar, needed inorder to make your nav drawer functional
        //also remove the other actionbar using NoActionBar theme
        toolbar.setBackgroundColor(getResources().getColor(R.color.weirdbluevariant));
        setSupportActionBar(toolbar);

        NavigationView nav_view = findViewById(R.id.nav_view);
        nav_view.setNavigationItemSelectedListener(this); //for now i am implemmenting thelistner to this class but in future(or asap)
        // we can try changing it to its own class or something once we have a better grasp of this


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle); //this is for the toggle, it allows the toggle animation
        toggle.syncState();


        //trying to put a comment to see if this wiorks - hassan
        //adding another comment to show hassam how to commit
        //test again
        // when theres nothing in the saved state ie when the activity restarts or resumes, it will get the deashboard fragment
        if (savedInstanceState ==null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new DashboardFragment()).commit();
            nav_view.setCheckedItem(R.id.nav_dashb1);
        }

        //note to self, all fragment classes must be a public class inorder for it to function
    }

    //when you want to go back, you dont want to close activity only close nav bar when navbar open
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.END);
        }else{
            super.onBackPressed();
        }}


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.nav_dashb1:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new DashboardFragment()).commit();
                break;
            case R.id.nav_dashb2:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new DashboardFragment()).commit();
                break;
            case R.id.nav_product:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ProductFragment()).commit();
                break;
            case R.id.nav_other:
                Toast.makeText(this,"Other",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_purchase:
                Toast.makeText(this,"Purchases",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_report:
                Toast.makeText(this,"Reports",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_sale:
                Toast.makeText(this,"Sale",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_setting:
                Toast.makeText(this,"Settings",Toast.LENGTH_SHORT).show();
                break;
            case R.id.share:
                Toast.makeText(this,"Test ?",Toast.LENGTH_SHORT).show();
                break;
            case R.id.send:
                Toast.makeText(this,"Settings ?",Toast.LENGTH_SHORT).show();
                break;


        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}