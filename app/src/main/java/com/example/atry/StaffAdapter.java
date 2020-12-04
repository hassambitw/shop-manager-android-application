package com.example.atry;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;

public class StaffAdapter extends RecyclerView.Adapter<com.example.atry.StaffAdapter.StaffViewHolder> {

    private ArrayList<Staff> sAdapterList;

    public class StaffViewHolder extends RecyclerView.ViewHolder{
        public TextView staff_id_tv;
        public TextView firstNameTV;
        public TextView lastNameTV;
        public TextView phoneTV;
        public TextView emailTV;
        Button editBtn;

        public StaffViewHolder(@NonNull View itemView) {
            super(itemView);

            staff_id_tv = itemView.findViewById(R.id.staffID);
            firstNameTV = itemView.findViewById(R.id.staffFname);
            lastNameTV = itemView.findViewById(R.id.staffLname);
            phoneTV = itemView.findViewById(R.id.staffphone);
            emailTV = itemView.findViewById(R.id.staffemail);
            editBtn = itemView.findViewById(R.id.editStaff);
        }
    }

    public StaffAdapter(ArrayList<Staff> sAdapterList) {
        this.sAdapterList = sAdapterList;
    }

    @NonNull
    @Override
    public com.example.atry.StaffAdapter.StaffViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.staff_item,parent,false);
        StaffViewHolder svh = new StaffViewHolder(v);
        return svh;
    }

    @Override
    public void onBindViewHolder(@NonNull StaffViewHolder holder, int position) {

        Staff curItem = sAdapterList.get(position);
        int staffID = curItem.getStaffID();
        String fname = curItem.getStaff_fname();
        String lname = curItem.getStaff_lname();
        String phone = curItem.getStaff_phone();
        String email = curItem.getStaff_email();

        holder.staff_id_tv.setText(Integer.toString(staffID));
        holder.firstNameTV.setText(fname);
        holder.lastNameTV.setText(lname);
        holder.phoneTV.setText(phone);
        holder.emailTV.setText(email);

        //CustomerAdapter.this.notifyDataSetChanged();

        holder.editBtn.setOnClickListener((v)->{
            Intent i = new Intent(v.getContext(), editStaff_activity.class);
            i.putExtra("staff_id",  Integer.parseInt(holder.staff_id_tv.getText().toString()));
            i.putExtra("first_name", holder.firstNameTV.getText().toString());
            i.putExtra("last_name", holder.lastNameTV.getText().toString());
            i.putExtra("phone", holder.phoneTV.getText().toString());
            i.putExtra("email", holder.emailTV.getText().toString());
            v.getContext().startActivity(i);
        });

        //Intent intent = ((Activity) this).getIntent();


    }

    public void setStaff(ArrayList<Staff> staff) {
        this.sAdapterList = staff;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return sAdapterList.size();
    }
}
