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

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder> {

    private ArrayList<Customer> cAdapterList;

    public class CustomerViewHolder extends RecyclerView.ViewHolder{
        public TextView customer_id_tv;
        public TextView firstNameTV;
        public TextView lastNameTV;
        public TextView phoneTV;
        public TextView emailTV;
        Button transactionBtn;
        Button editBtn;

        public CustomerViewHolder(@NonNull View itemView) {
            super(itemView);

            customer_id_tv = itemView.findViewById(R.id.customerID);
            firstNameTV = itemView.findViewById(R.id.firstName);
            lastNameTV = itemView.findViewById(R.id.lastName);
            phoneTV = itemView.findViewById(R.id.phone);
            emailTV = itemView.findViewById(R.id.email);
            transactionBtn = itemView.findViewById(R.id.viewTransaction);
            editBtn = itemView.findViewById(R.id.editCustomer);
        }
    }

    public CustomerAdapter(ArrayList<Customer> cAdapterList) {
        this.cAdapterList = cAdapterList;
    }

    @NonNull
    @Override
    public CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_item,parent,false);
        CustomerViewHolder pvh = new CustomerViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerViewHolder holder, int position) {

        Customer curItem = cAdapterList.get(position);
        int custID = curItem.getCustomerId();
        String fname = curItem.getFirstName();
        String lname = curItem.getLastName();
        String phone = curItem.getPhone();
        String email = curItem.getEmail();

        holder.customer_id_tv.setText(Integer.toString(custID));
        holder.firstNameTV.setText(fname);
        holder.lastNameTV.setText(lname);
        holder.phoneTV.setText(phone);
        holder.emailTV.setText(email);

        //CustomerAdapter.this.notifyDataSetChanged();

        holder.transactionBtn.setOnClickListener((v)->{
            Intent i = new Intent(v.getContext(), viewTransaction_activity.class);
//            i.putExtra("order_id",Integer.parseInt(holder.mTextView1.getText().toString()));
//            i.putExtra("customer_id",Integer.parseInt(holder.mTextView2.getText().toString()));
//            i.putExtra("order_date",holder.mTextView3.getText().toString());
//            i.putExtra("staff_id",Integer.parseInt(holder.mTextView4.getText().toString()));
            v.getContext().startActivity(i);
        });

        holder.editBtn.setOnClickListener((v)->{
            Intent i = new Intent(v.getContext(), editCustomer_activity.class);
            i.putExtra("customer_id",  Integer.parseInt(holder.customer_id_tv.getText().toString()));
            i.putExtra("first_name", holder.firstNameTV.getText().toString());
            i.putExtra("last_name", holder.lastNameTV.getText().toString());
            i.putExtra("phone", holder.phoneTV.getText().toString());
            i.putExtra("email", holder.emailTV.getText().toString());
            v.getContext().startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return cAdapterList.size();
    }
}
