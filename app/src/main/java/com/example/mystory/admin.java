package com.example.mystory;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class admin extends Fragment {
Button sinout;
FirebaseAuth auth=FirebaseAuth.getInstance();
FirebaseFirestore db=FirebaseFirestore.getInstance();
TextView nameadmin;
TextView cityadmin;
TextView emailadmin;
TextView piceadmin;
ProgressBar pro3;
Button tm;
Button tr;
Button ts;
    public admin() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_admin, container, false);
        sinout= view.findViewById(R.id.sinout);
        nameadmin=view.findViewById(R.id.nameadmin);
        cityadmin=view.findViewById(R.id.cityadmin);
        emailadmin=view.findViewById(R.id.emailadmin);
        piceadmin=view.findViewById(R.id.piceadmin);
        pro3=view.findViewById(R.id.pro3);

        tm=view.findViewById(R.id.tlabat);
        tr=view.findViewById(R.id.hostreyadmin);
        ts=view.findViewById(R.id.talbtaswya);
        tm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tm=new Intent(getContext(),choseAdmin.class);
                tm.putExtra("tm","tm");
                startActivity(tm);
            }
        });
        tr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tm=new Intent(getContext(),choseAdmin.class);
                tm.putExtra("tm","rased");
                startActivity(tm);
            }
        });
        ts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ts=new Intent(getContext(),choseAdmin.class);
                ts.putExtra("tm","taswya");
                startActivity(ts);
            }
        });
        sinout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseAuth.getInstance().signOut();
                Intent out = new Intent(getContext(), Home.class);
                startActivity(out);
               getActivity().finish();
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
                if (documentSnapshot.exists()) {
                    pro3.setVisibility(View.VISIBLE);
                     nameadmin.setText(""+documentSnapshot.get("name").toString());
                     cityadmin.setText(""+documentSnapshot.get("city").toString());
                    emailadmin.setText(""+documentSnapshot.get("email").toString());
                    piceadmin.setText(""+documentSnapshot.get("pice").toString());
                pro3.setVisibility(View.GONE);}
                else
                    {
                        Toast.makeText(getContext(), "not found ", Toast.LENGTH_SHORT).show();
                    }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                cityadmin.setVisibility(View.GONE);
                emailadmin.setVisibility(View.GONE);
                piceadmin.setVisibility(View.GONE);
                nameadmin.setText("not found");
            }
        });





















    }
}
