
package com.example.mystory;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
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

public class Adapter_rec3 extends RecyclerView.Adapter<Adapter_rec3.ViewHolder> {
    ArrayList<datarec3> list;
    Context context;
    FirebaseAuth auth= FirebaseAuth.getInstance();
    FirebaseFirestore db=FirebaseFirestore.getInstance();

    public Adapter_rec3(ArrayList<datarec3> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = (LayoutInflater.from(parent.getContext())).inflate(R.layout.itemrec3,parent,false);
        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_rec3.ViewHolder holder, int position) {

        datarec3 item = list.get(position);
        holder.t.setText(item.getCaseActivie());
        holder.m.setText(item.getDuration());
        holder.names.setText(item.getNamesbscirabe());
        holder.named.setText(item.getNamedistributor());
        holder.gettime.setText(item.getTimesend());
        holder.numbercard.setText(item.getNum());
       String k1 = item.getKeyofregistration();
       String k2 = item.getKeysubscriber();
       String num=item.getNum();
        String getcase=item.getCaseActivie();
        if (getcase.equals("تمت الموافقه")) {
            holder.ok1.setVisibility(View.GONE);
            holder.cancel.setVisibility(View.GONE);
            holder.t.setTextColor(Color.parseColor("#0A0404"));
            holder.m.setTextColor(Color.parseColor("#0A0404"));
            //break;
        }


        holder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (k1.isEmpty()||k2.isEmpty()||num.isEmpty()){
                    Toast.makeText(context, "not fuond", Toast.LENGTH_SHORT).show();
                        return;
                }else {
                    PopupMenu popupMenu =new PopupMenu(context,holder.more);
                    popupMenu.getMenuInflater().inflate(R.menu.itemmanu2inrec3,popupMenu.getMenu());
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            int id = item.getItemId();
                            switch (id){
                                case R.id.showmowsea:
                                    Intent show=new Intent(context,Profile.class);
                                    show.putExtra("key",k1);
                                    show.putExtra("o","m");
                                    context.startActivity(show);


                                    break;
                                case  R.id.showmoshtarek:
                                    Intent show2=new Intent(context,Profile.class);
                                    show2.putExtra("key",k2);
                                    show2.putExtra("o","o");
                                    context.startActivity(show2);
                                    break;

                            }



                            return true;
                        }
                    });
                    popupMenu.show();



                }

            }
        });

        long timeInMillis = System.currentTimeMillis();
        Calendar cal1 = Calendar.getInstance();
        cal1.setTimeInMillis(timeInMillis);
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy/MM/dd", Locale.ENGLISH);
        String gettime = dateFormat.format(cal1.getTime());
        holder.ok1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (k1.isEmpty()||k2.isEmpty()||num.isEmpty()){
                    Toast.makeText(context, "not fuond", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                        Map<String,Object> u1=new HashMap<>();
                        u1.put("typestarting",gettime);
                        u1.put("case","Activie");

                    db.collection("user").document(k2).set(u1,SetOptions.merge()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Map<String,Object> u2=new HashMap<>();
                                u2.put("caseActivie","تمت الموافقه");
                                db.collection(auth.getUid()).document(num).set(u2,SetOptions.merge());
                                holder.ok1.setVisibility(View.GONE);
                                holder.cancel.setVisibility(View.GONE);
                                Toast.makeText(context, "is complete", Toast.LENGTH_SHORT).show();

                            }

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(context, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        holder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (k1.isEmpty()||k2.isEmpty()){
                    Toast.makeText(context, "not fuond", Toast.LENGTH_SHORT).show();
                    return;
                }else {

                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ConstraintLayout lyout;
        TextView t;
        TextView m;
        TextView names;
        TextView named;
        TextView gettime;
        TextView numbercard;
        Button ok1;
        Button cancel;
        Button more;
        TextView kam;
        TextView nnn;
        TextView nnns;
        TextView tareh;
        TextView namca;
        ViewHolder(View view){
            super(view);
            lyout=view.findViewById(R.id.c11);
            kam=view.findViewById(R.id.kam);
            nnn=view.findViewById(R.id.nnn);
            nnns=view.findViewById(R.id.nnns);
            tareh=view.findViewById(R.id.tareh);
            namca=view.findViewById(R.id.namca);
            names=view.findViewById(R.id.namemash);
            named=view.findViewById(R.id.namemaws);
            gettime=view.findViewById(R.id.type);
            numbercard=view.findViewById(R.id.num11);

           t=view.findViewById(R.id.t);
           m=view.findViewById(R.id.k);
           ok1=view.findViewById(R.id.okaytalb);
           cancel=view.findViewById(R.id.rafdtalb);
           more=view.findViewById(R.id.showm);




        }

    }
}
