package com.example.mystory;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;

public class Hostrey extends AppCompatActivity {
    RecyclerView rec4;
    ArrayList<datahostrey> idates = new ArrayList<>();
    Adapter_rec_hostery adapter;
    LinearLayoutManager linearLayoutManager;
    FirebaseAuth auth =FirebaseAuth.getInstance();

    CollectionReference db= FirebaseFirestore.getInstance().collection(auth.getUid());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hostrey);
        rec4=findViewById(R.id.rechostery);
       // Collections.reverse(idates);
        adapter = new Adapter_rec_hostery(idates,this);
        linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rec4.setLayoutManager(linearLayoutManager);
        rec4.setHasFixedSize(true);
        rec4.setAdapter(adapter);


    }

    @Override
    protected void onStart() {
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
                    datahostrey dater1 = querySnapshot.toObject(datahostrey.class);
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