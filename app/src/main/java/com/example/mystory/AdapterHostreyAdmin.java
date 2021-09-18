package com.example.mystory;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterHostreyAdmin extends RecyclerView.Adapter<AdapterHostreyAdmin.ViewHolder> {
  ArrayList<ModelDataAdminHostrey>list;
  Context context;

  public AdapterHostreyAdmin(ArrayList<ModelDataAdminHostrey> list, Context context) {
    this.list = list;
    this.context = context;
  }

  @NonNull
    @Override
    public AdapterHostreyAdmin.ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
      View view = (LayoutInflater.from(parent.getContext())).inflate(R.layout.item_admin_hostrey,parent,false);
      return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  AdapterHostreyAdmin.ViewHolder holder, int position) {
      ModelDataAdminHostrey item=list.get(position);
      holder.name1.setText(item.getName());
      holder.time.setText(item.getTime());
      holder.Payment_type.setText(item.getPaymentType());
      holder.pice.setText(item.getPic());
      holder.ShowHawya.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          Intent Show=new Intent(context,Profile.class);
          Show.putExtra("key",item.KeyRequste);
          Show.putExtra("o","m");
          context.startActivity(Show);
        }
      });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }
  public class ViewHolder extends RecyclerView.ViewHolder{
    TextView name1;
    TextView time;
    TextView Payment_type;
    TextView pice;
    Button ShowHawya;


    ViewHolder(View view){
      super(view);
      name1=view.findViewById(R.id.nameAdminHostrey);
      time=view.findViewById(R.id.timeAdminHostrey);
      Payment_type=view.findViewById(R.id.PaymentTypeAdminHostrey);
      pice=view.findViewById(R.id.piceAdminHostrey);
      ShowHawya=view.findViewById(R.id.ShowHawya);




    }

  }


}
