package com.example.mystory;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;


public class distributor extends Fragment {
Button add_sbscirabe1;
Button updeit_sbscirabe1;
Button Singout;
FirebaseFirestore db=FirebaseFirestore.getInstance();
TextView namepage;
TextView citypage;
TextView emilpage;
TextView res;
TextView resdi2;
ProgressBar loding;
FirebaseAuth auth=FirebaseAuth.getInstance();
Button report;
String k;
Button talab;
    AlertDialog dialog1;
    AlertDialog dialog2;
    AlertDialog dialog3;
    public distributor() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_distributor, container, false);
        add_sbscirabe1=view.findViewById(R.id.add_sbscirabe);
        report=view.findViewById(R.id.report);
        updeit_sbscirabe1=view.findViewById(R.id.updeit_sbscirabe);
        namepage=view.findViewById(R.id.namedi);
        citypage=view.findViewById(R.id.citydi);
        emilpage=view.findViewById(R.id.emaildi);
        res=view.findViewById(R.id.resdi);
        resdi2=view.findViewById(R.id.resdi2);
        talab=view.findViewById(R.id.talab);
        loding=view.findViewById(R.id.prodi);
        Singout=view.findViewById(R.id.Singout);
        Singout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent g=new Intent(getContext(),Home.class);
                startActivity(g);
            }
        });
        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu =new PopupMenu(getContext(),report);
                popupMenu.getMenuInflater().inflate(R.menu.item_menu,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int id = item.getItemId();
                        switch (id){
                            case R.id.repcard:

                                AlertDialog.Builder builder1 =new AlertDialog.Builder(getContext());
                                View view = getLayoutInflater().inflate(R.layout.itemdiloge,null);

                                final EditText userInput = (EditText) view.findViewById(R.id.numbercard);
                                final Button b =view.findViewById(R.id.yes);
                                b.setText("ابلاغ");

                                b.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        String g=userInput.getText().toString().trim();
                                        if (g.isEmpty()){userInput.setError("is empty");return;}
                                        else {
                                            Map<String,Object> u=new HashMap<>();
                                            u.put("lostcard",g);
                                            u.put("keydistributor",auth.getUid());
                                            db.collection("الكروت المفقوده").document().set(u,SetOptions.merge());
                                            Toast.makeText(getContext(), "تم الابلاغ بنجاح", Toast.LENGTH_SHORT).show();
                                            dialog1.dismiss();
                                        }

                                    }
                                });

                                // create alert dialog
                                builder1.setView(view);
                                dialog1 = builder1.create();
                                dialog1.show();


                                break;
                            case  R.id.repfan:
                                AlertDialog.Builder builder3 =new AlertDialog.Builder(getContext());
                                View view3 = getLayoutInflater().inflate(R.layout.itemdiloge,null);
                                final TextView textD=view3.findViewById(R.id.textD);
                                textD.setText("ابلاغ دعم فني ");
                                final EditText userInput3 = (EditText) view3.findViewById(R.id.numbercard);
                                userInput3.setWidth(350);
                                userInput3.setHeight(260);
                                userInput3.setHint("ادخل المشكله");
                                final Button b3 =view3.findViewById(R.id.yes);
                                b3.setText("ابلاغ");

                                b3.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        String g=userInput3.getText().toString().trim();
                                        if (g.isEmpty()){userInput3.setError("is empty");return;}
                                        else {
                                            Map<String,Object> u=new HashMap<>();
                                            u.put("المشكله",g);
                                            u.put("keydistributor",auth.getUid());
                                            db.collection("مشكله دعم فني").document().set(u,SetOptions.merge());
                                            Toast.makeText(getContext(), "تم الابلاغ بنجاح", Toast.LENGTH_SHORT).show();
                                            dialog3.dismiss();
                                        }

                                    }
                                });

                                // create alert dialog
                                builder3.setView(view3);
                                dialog3 = builder3.create();
                                dialog3.show();
































                                break;

                        }



                        return true;
                    }
                });
                popupMenu.show();
























            }
        });

        talab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                builder.setMessage("هل تريد ارسال طلب تسويه ").setTitle("طلب التسويه ");
                builder.setNegativeButton("موافق", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();









            }
        });

        add_sbscirabe1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent add=new Intent(getContext(),addSbscirabePage.class);
                startActivity(add);
            }
        });

        updeit_sbscirabe1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder1 =new AlertDialog.Builder(getContext());
                View view1 = getLayoutInflater().inflate(R.layout.itemdiloge,null);


                final EditText userInput = (EditText) view1.findViewById(R.id.numbercard);
                  final Button b =view1.findViewById(R.id.yes);

                           b.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View view) {
                         String g=userInput.getText().toString().trim();
                           if (g.isEmpty()){userInput.setError("is empty");return;}
                           else {
                               db.collection(k).document(g).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                   @Override
                                   public void onSuccess(DocumentSnapshot documentSnapshot) {
                                        if (documentSnapshot.exists()){
                                            String getkey=documentSnapshot.get("keysubscriber").toString();

                                            dialog2.dismiss();
                                            Intent profile = new Intent(getContext(), Profile.class);
                                            profile.putExtra("key", getkey);
                                            profile.putExtra("o","o1");
                                            startActivity(profile);






                                        }else {
                                            Toast.makeText(getContext(), "nofunod", Toast.LENGTH_SHORT).show();
                                        return;
                                        }








                                   }
                               });
                           }

                       }
                   });






                builder1.setView(view1);
                dialog2 = builder1.create();
                dialog2.show();















            }
        });
















        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        db.collection("user").document(auth.getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()){
                    loding.setVisibility(View.VISIBLE);
                    k=documentSnapshot.get("keyadmin").toString();
                    namepage.setText(""+documentSnapshot.get("name").toString());
                    citypage.setText(""+documentSnapshot.get("city").toString());
                    emilpage.setText(""+documentSnapshot.get("email").toString());
                    res.setText(""+documentSnapshot.get("balance").toString());
                    resdi2.setText(""+documentSnapshot.get("unpaidbalance").toString());
                    loding.setVisibility(View.GONE);
                }



            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                loding.setVisibility(View.VISIBLE);
                namepage.setText(""+e.getMessage());
                citypage.setVisibility(View.GONE);
                emilpage.setVisibility(View.GONE);
                res.setVisibility(View.GONE);
                loding.setVisibility(View.GONE);

            }
        });
    }
}
