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


public class Taswya extends Fragment {
RecyclerView recy;
    ArrayList<modeldatataswya> idates = new ArrayList<>();
    Adaptertaswya adapter;
    LinearLayoutManager linearLayoutManager;
    FirebaseAuth auth =FirebaseAuth.getInstance();

    CollectionReference db= FirebaseFirestore.getInstance().collection("talab"+" "+auth.getUid());

    public Taswya() {
        // Required empty public constructor
    }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_taswya, container, false);
        recy=view.findViewById(R.id.recy);
        Collections.reverse(idates);
        adapter = new Adaptertaswya(idates,getContext());
        linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recy.setLayoutManager(linearLayoutManager);
        recy.setHasFixedSize(true);
        recy.setAdapter(adapter);









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
                    modeldatataswya dater1 = querySnapshot.toObject(modeldatataswya.class);
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