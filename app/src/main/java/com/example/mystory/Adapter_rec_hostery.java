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
        holder.time.setText("وقت ارسال الطلب"+"\t"+item.getTime());
        holder.numbercard.setText("رقم كارت الاشتراك"+"\t"+item.getNumbercard());
        holder.rased_qabel.setText("الرصيد قبل الخصم "+"\t"+item.getRsedafter());
        holder.rasedcut.setText("الرصيد المخصوم"+"\t"+item.getCutraseed());
        holder.rasedbad.setText("الرصيد بعد الخصم "+"\t"+item.getAvalibol());
        holder.rased_type.setText("نوع الدفع"+"\t"+item.getTypepay());
        holder.moda.setText("مده الاشتراك"+"\t"+item.getDuration());




    }

    @Override
    public int getItemCount() {
        return listes.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView time;

        TextView numbercard;

        TextView moda;

        TextView rased_qabel;

        TextView rasedcut;

        TextView rasedbad;

        TextView rased_type;

        ViewHolder(View view){
            super(view);
            time=view.findViewById(R.id.time);
            numbercard=view.findViewById(R.id.numcardsbscrib);
            moda=view.findViewById(R.id.modetelashterak);
            rased_qabel=view.findViewById(R.id.raseadqbel);
            rasedbad=view.findViewById(R.id.rasedbaad);
            rasedcut=view.findViewById(R.id.rasedcut);
            rased_type=view.findViewById(R.id.rasedtype);



        }

    }
























}
