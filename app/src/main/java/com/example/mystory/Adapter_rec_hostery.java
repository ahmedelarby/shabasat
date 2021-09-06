package com.example.mystory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter_rec_hostery extends RecyclerView.Adapter<Adapter_rec_hostery.ViewHolder> {

    List<datahostrey> listes;
    Context context;
    public Adapter_rec_hostery(List<datahostrey> listes, Context context) {
        this.listes = listes;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = (LayoutInflater.from(parent.getContext())).inflate(R.layout.item_rec_hostery,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_rec_hostery.ViewHolder holder, int position) {
        datahostrey item = listes.get(position);



    }

    @Override
    public int getItemCount() {
        return listes.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView time_text;
        TextView time;
        TextView numbercard_text;
        TextView numbercard;
        TextView moda_text;
        TextView moda;
        TextView rasedqabl_text;
        TextView rased_qabel;
        TextView rasedcut_text;
        TextView rasedcut;
        TextView rasedbad_text;
        TextView rasedbad;
        TextView rased_type_text;
        TextView rased_type;

        ViewHolder(View view){
            super(view);
            time_text=view.findViewById(R.id.time_text);
            time=view.findViewById(R.id.time);
            time_text=view.findViewById(R.id.time_text);
            time_text=view.findViewById(R.id.time_text);
            time_text=view.findViewById(R.id.time_text);
            time_text=view.findViewById(R.id.time_text);
            time_text=view.findViewById(R.id.time_text);
            time_text=view.findViewById(R.id.time_text);
            time_text=view.findViewById(R.id.time_text);
            time_text=view.findViewById(R.id.time_text);
            time_text=view.findViewById(R.id.time_text);
            time_text=view.findViewById(R.id.time_text);
            time_text=view.findViewById(R.id.time_text);
            time_text=view.findViewById(R.id.time_text);
            time_text=view.findViewById(R.id.time_text);
            time_text=view.findViewById(R.id.time_text);


        }

    }
























}
