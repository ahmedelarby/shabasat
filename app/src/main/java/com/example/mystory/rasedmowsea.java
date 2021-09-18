package com.example.mystory;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;


public class rasedmowsea extends Fragment {
RecyclerView rec4;
    ArrayList<ModelDataAdminHostrey> idates = new ArrayList<>();
    AdapterHostreyAdmin adapter;
    LinearLayoutManager linearLayoutManager;
    FirebaseAuth auth =FirebaseAuth.getInstance();

    CollectionReference db= FirebaseFirestore.getInstance().collection("Payment history"+" "+auth.getUid());
    public rasedmowsea() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_rasedmowsea, container, false);
        rec4=view.findViewById(R.id.rec4);
        Collections.reverse(idates);
        adapter = new AdapterHostreyAdmin(idates,getContext());
        linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rec4.setLayoutManager(linearLayoutManager);
        rec4.setHasFixedSize(true);
        rec4.setAdapter(adapter);










        return view;


    }

    @Override
    public void onStart() {
        super.onStart();
        db.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value,
                                @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    return;
                }
                idates.clear();

                for (QueryDocumentSnapshot querySnapshot : value){
                    ModelDataAdminHostrey dater1 = querySnapshot.toObject(ModelDataAdminHostrey.class);
//                    if(dater1.equals(null)){
//                        Toast.makeText(getContext(), "not found", Toast.LENGTH_SHORT).show();
//                          return;
//                    }else
                    idates.add(dater1);
                }
                adapter.notifyDataSetChanged();



            }
        });
    }
}
