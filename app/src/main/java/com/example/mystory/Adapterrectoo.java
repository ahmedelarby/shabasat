package com.example.mystory;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapterrectoo extends RecyclerView.Adapter<Adapterrectoo.ViewHolder>{
    List<datadis> model_data2;
    Context context;
    public Adapterrectoo(List<datadis> model_data, Context context) {
        this.model_data2 = model_data;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = (LayoutInflater.from(parent.getContext())).inflate(R.layout.itemrec2,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  ViewHolder holder, int position) {
        datadis item = model_data2.get(position);
        holder.nameitemtoo.setText(item.getName());
        holder.num.setText(item.getNumber());
        Picasso.get().load(item.getImage()).into(holder.photo2);


    }

    @Override
    public int getItemCount() {
        return model_data2.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nameitemtoo;
        TextView num;
        ImageView photo2;

        ViewHolder(View view){
            super(view);
            nameitemtoo = view.findViewById(R.id.nameitemtoo);
            num = view.findViewById(R.id.numitemtoo);
            photo2 = view.findViewById(R.id.imagerectoo);


        }

    }


}