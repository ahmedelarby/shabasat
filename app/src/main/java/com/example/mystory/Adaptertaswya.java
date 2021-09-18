package com.example.mystory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Adaptertaswya extends RecyclerView.Adapter<Adaptertaswya.ViewHolder> {
    ArrayList<modeldatataswya>arrayList;
    Context context;
    String cash="balance";
    String gettime;
    FirebaseFirestore dbb=FirebaseFirestore.getInstance();
    FirebaseAuth auth=FirebaseAuth.getInstance();

    public Adaptertaswya(ArrayList<modeldatataswya> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull

    @Override
    public Adaptertaswya.ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = (LayoutInflater.from(parent.getContext())).inflate(R.layout.itemtaswya,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  Adaptertaswya.ViewHolder holder, int position) {
        modeldatataswya item=arrayList.get(position);
        holder.name.setText("اسم الموزع :"+"\t"+item.getName());
        holder.Requeste.setText(item.getRequestname());
        holder.B.setText("رصيده الحالي :"+"\t"+item.getBalance());
        long timeInMillis = System.currentTimeMillis();
        Calendar cal1 = Calendar.getInstance();
        cal1.setTimeInMillis(timeInMillis);
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy/MM/dd hh:mm a", Locale.ENGLISH);
        gettime = dateFormat.format(cal1.getTime());
        holder.addrased.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.addrased.setVisibility(View.GONE);
                holder.choserased.setVisibility(View.VISIBLE);
                holder.valuo.setVisibility(View.VISIBLE);
                holder.addraseeed.setVisibility(View.VISIBLE);
            }
        });
        holder.addraseeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           String ras= holder.valuo.getText().toString();
           if (ras.isEmpty()){holder.valuo.setError("Empty");return;}
           if (cash.isEmpty()){
               Toast.makeText(context, "اختار نوع الرصيد مدفوع ام لا ", Toast.LENGTH_SHORT).show();
                return;
           }
           else {
               Map<String,Object> cash1=new HashMap<>();
               cash1.put(cash,ras);
               dbb.collection("user").document(item.getKey()).set(cash1, SetOptions.merge()).addOnCompleteListener(new OnCompleteListener<Void>() {
                   @Override
                   public void onComplete(@NonNull  Task<Void> task) {
                       if (task.isSuccessful()){
                           dbb.collection("talab"+""+auth.getUid()).document(item.getKey()).delete();
                           Map<String,Object> Hostrey=new HashMap<>();
                           Hostrey.put("time",gettime);
                           Hostrey.put("KeyRequste",item.getKey());
                           Hostrey.put("name",item.getName());
                           Hostrey.put("PaymentType",cash);
                           Hostrey.put("pic",ras);
                           dbb.collection("Payment history"+" "+auth.getUid()).document().set(Hostrey);
                           Toast.makeText(context, "isSuccessful", Toast.LENGTH_SHORT).show();
                           holder.addrased.setVisibility(View.GONE);
                           holder.choserased.setVisibility(View.GONE);
                           holder.valuo.setVisibility(View.GONE);
                           holder.addraseeed.setVisibility(View.GONE);
                       }
                   }
               }).addOnFailureListener(new OnFailureListener() {
                   @Override
                   public void onFailure(@NonNull  Exception e) {
                       Toast.makeText(context, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                   }
               });

           }
            }
        });

    holder.choserased.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            PopupMenu popupMenu =new PopupMenu(view.getContext(), holder.choserased);
            popupMenu.getMenuInflater().inflate(R.menu.item_choserased,popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    int id = item.getItemId();
                    switch (id){
                        case R.id.assasey:
                            cash="balance";
                            String c1="رصيد مدفوع";
                            holder.choserased.setText(c1);
                            break;
                        case R.id.unp:
                            cash="unpaidbalance";
                            String c2="رصيد غير مدفوع";
                            holder.choserased.setText(c2);
                    break;
                    }



                    return true;
                }
            });
            popupMenu.show();






        }
    });


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView Requeste;
        TextView B;
         Button addrased;
        EditText valuo;
        Button choserased;
        Button addraseeed;

        ViewHolder(View view){
            super(view);
            name=view.findViewById(R.id.nametaswya);
            Requeste=view.findViewById(R.id.talbe);
            B=view.findViewById(R.id.blc);
            addrased=view.findViewById(R.id.Addrased);
            choserased=view.findViewById(R.id.choserased);
            valuo=view.findViewById(R.id.rasedadd);
            addraseeed=view.findViewById(R.id.addraseed);



        }

    }


}
