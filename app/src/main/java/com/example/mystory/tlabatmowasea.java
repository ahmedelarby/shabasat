package com.example.mystory;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;


/**
 * A simple {@link Fragment} subclass.
 */
public class tlabatmowasea extends Fragment {
RecyclerView rec3;
    ArrayList<datarec3> idates = new ArrayList<>();
    Adapter_rec3 adapter;
    LinearLayoutManager linearLayoutManager;
    FirebaseAuth auth =FirebaseAuth.getInstance();

    CollectionReference db=FirebaseFirestore.getInstance().collection(auth.getUid());
    public tlabatmowasea() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_tlabatmowasea, container, false);
        rec3=view.findViewById(R.id.rec3);
         Collections.reverse(idates);
        adapter = new Adapter_rec3(idates,getContext());
        linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rec3.setLayoutManager(linearLayoutManager);
        rec3.setHasFixedSize(true);
        rec3.setAdapter(adapter);
























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
                    datarec3 dater1 = querySnapshot.toObject(datarec3.class);
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
