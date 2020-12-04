package com.example.atry;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private ArrayList<Product> prodList;
    public class ProductViewHolder extends RecyclerView.ViewHolder{
        public TextView mTextView1;
        public TextView mTextView2;
        public TextView mTextView3;
        public TextView mTextView4;
        public TextView mTextView5;
        public TextView mTextView6;
        public TextView mTextView7;
        Button editProd, deleteProd;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);


            mTextView1 = itemView.findViewById(R.id.category);
            mTextView2 = itemView.findViewById(R.id.brandName);
            mTextView3 = itemView.findViewById(R.id.productName);
            mTextView4 = itemView.findViewById(R.id.listprice);
            mTextView5 = itemView.findViewById(R.id.quantity);
            mTextView6 = itemView.findViewById(R.id.year);
            mTextView7 = itemView.findViewById(R.id.prod_id);
            editProd = itemView.findViewById(R.id.editProd);
            deleteProd = itemView.findViewById(R.id.deleteProd);

        }
    }

    public ProductAdapter (ArrayList<Product> prodList) {
        this.prodList = prodList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item,parent,false);
        ProductViewHolder pvh = new ProductViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

        Product curItem = prodList.get(position);

        holder.mTextView1.setText(curItem.getCategory());
        holder.mTextView2.setText(curItem.getBrandName());
        holder.mTextView3.setText(curItem.getProductName());
        holder.mTextView4.setText(Double.toString(curItem.getPrice()));
        holder.mTextView5.setText(Integer.toString(curItem.getQuantity()));
        holder.mTextView6.setText(Integer.toString(curItem.getModalYear()));
        holder.mTextView7.setText(Integer.toString(curItem.getProductId()));

        holder.editProd.setOnClickListener((v)->{
            Intent i = new Intent(v.getContext(), editProduct_activity.class);
            i.putExtra("product_id",  Integer.parseInt(holder.mTextView7.getText().toString()));
            i.putExtra("p_name", holder.mTextView3.getText().toString());
            i.putExtra("brand", holder.mTextView2.getText().toString());
            i.putExtra("category", holder.mTextView1.getText().toString());
            i.putExtra("price", Double.parseDouble(holder.mTextView4.getText().toString()));
            i.putExtra("model_year", Integer.parseInt(holder.mTextView6.getText().toString()));
            i.putExtra("stock", Integer.parseInt(holder.mTextView5.getText().toString()));
            v.getContext().startActivity(i);
        });

        holder.deleteProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int itemid = Integer.parseInt(holder.mTextView7.getText().toString());
                prodList.remove(prodList.get(position));
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, prodList.size());
                notifyDataSetChanged();
                

                DBHelper dbh = new DBHelper(holder.deleteProd.getContext());
                dbh.deleteProduct(itemid);
            }
        });

    }

    @Override
    public int getItemCount() {
        return prodList.size();
    }

    public void deleteProduct(int position){
        //DBHelper dbh = new DBHelper(getApplicationContext());
    }
}
