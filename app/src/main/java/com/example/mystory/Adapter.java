package com.example.mystory;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{
    List<data> model_data;
    Context context;
    public Adapter(List<data> model_data, Context context) {
        this.model_data = model_data;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = (LayoutInflater.from(parent.getContext())).inflate(R.layout.item_rec_distr,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  ViewHolder holder, int position) {
        data item = model_data.get(position);
        holder.nameitem.setText(item.getName());
        holder.num.setText(item.getNum());
        Picasso.get().load(item.getPhoto()).into(holder.photo);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.pro.setVisibility(View.VISIBLE);
                String i=item.getPhoto();
                String n=item.getName();
                Intent go= new Intent(context,Showmowazea.class);
                go.putExtra("photo",i);
                go.putExtra("name",n);
                context.startActivity(go);
                holder.pro.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public int getItemCount() {
        return model_data.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView nameitem;
        TextView num;
        ImageView photo;
        ProgressBar pro;

        ViewHolder(View view){
            super(view);
            nameitem = view.findViewById(R.id.cityname);
            num = view.findViewById(R.id.phone);
            photo = view.findViewById(R.id.photo);
            pro=view.findViewById(R.id.pro);


        }

    }


}