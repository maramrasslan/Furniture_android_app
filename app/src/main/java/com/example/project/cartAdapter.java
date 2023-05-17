package com.example.project;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class cartAdapter extends RecyclerView.Adapter<cartAdapter.MyViewHolder> {

    private Context context;
    private ArrayList images;
    private ArrayList  product, quantity, price;

    public cartAdapter(Context context, ArrayList product, ArrayList quantity, ArrayList price) {
        this.context = context;
//        this.images = images;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.cartitem,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
       // holder.image.setImageBitmap(images.get(position));
        holder.product.setText(String.valueOf(product.get(position)));
        holder.price.setText(String.valueOf(price.get(position)));
        holder.quantity.setText(String.valueOf(quantity.get(position)));


    }

    @Override
    public int getItemCount() {
        return product.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView product, quantity, price;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imgbox);
            product = itemView.findViewById(R.id.pname);
            price = itemView.findViewById(R.id.pprice);
            quantity = itemView.findViewById(R.id.pquantity);
        }
    }
}
