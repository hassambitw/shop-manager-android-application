package com.example.atry;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StaffFragment extends Fragment {


    private RecyclerView staffRecyclerView;
    private RecyclerView.Adapter staffAdapter;
    private RecyclerView.LayoutManager staffLayoutManager;
    Button addBtn;
    DBHelper dbh;
    ArrayList<Staff> staffList = new ArrayList<>();
    int staff_id;
    String staff_fname;
    String staff_lname;
    String staff_email;
    String staff_phone;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        dbh = new DBHelper(getContext());


        addToArrayList();

        View v = inflater.inflate(R.layout.fragment_staff, container, false);

        addBtn = v.findViewById(R.id.addStaff);

        addBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent (getActivity(), AddStaff_Activity.class);
                startActivity(i);
            }
        });

        buildRecyclerView(v);
        return v;
    }



    public void buildRecyclerView(View v) {
        staffRecyclerView = v.findViewById(R.id.staff_recycler_view);
        staffRecyclerView.setHasFixedSize(true);
        staffLayoutManager = new LinearLayoutManager(getActivity());
        staffAdapter = new StaffAdapter(staffList);
        staffRecyclerView.setLayoutManager(staffLayoutManager);
        staffRecyclerView.setAdapter(staffAdapter);
        staffAdapter.notifyDataSetChanged();
    }

    public void addToArrayList() {
        //retrieving customers
        Cursor c = dbh.getStaff();

        //adding to arrayList
        while (c.moveToNext()) {
            int i = staffList.indexOf(c);
            staff_id = c.getInt(c.getColumnIndex("staff_id"));
            staff_fname = c.getString(c.getColumnIndex("first_name"));
            staff_lname = c.getString(c.getColumnIndex("last_name"));
            staff_email = c.getString(c.getColumnIndex("email"));
            staff_phone = c.getString(c.getColumnIndex("phone"));
            staffList.add(new Staff(staff_id, staff_fname, staff_lname, staff_email, staff_phone));
        }
    }

}
