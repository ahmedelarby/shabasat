package com.example.mystory;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Showmowazea extends AppCompatActivity {
    RecyclerView recyclerView2;
    ArrayList<datadis> idates2 = new ArrayList<>();
    Adapterrectoo adapter2;
    LinearLayoutManager linearLayoutManager2;



    FirebaseFirestore db=FirebaseFirestore.getInstance();
    CollectionReference collectionReference;
ImageView bac;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showmowazea);
        bac=findViewById(R.id.imageViewr);
        String ph=getIntent().getStringExtra("photo");
        String na=getIntent().getStringExtra("name");
        Toast.makeText(this, ""+na, Toast.LENGTH_SHORT).show();
        collectionReference=db.collection(na);

        Picasso.get().load(ph).into(bac);
        recyclerView2=findViewById(R.id.rec2);







        adapter2= new Adapterrectoo(idates2,Showmowazea.this);
        linearLayoutManager2 = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView2.setLayoutManager(linearLayoutManager2);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setAdapter(adapter2);
        Animation a1= AnimationUtils.loadAnimation(this,R.anim.right);

        recyclerView2.startAnimation(a1);





    }

    @Override
    protected void onStart() {
        super.onStart();

        collectionReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                if (e != null) {
                    return;
                }
                idates2.clear();
                for (QueryDocumentSnapshot querySnapshot : queryDocumentSnapshots){
                    datadis dater2 = querySnapshot.toObject(datadis.class);

                    idates2.add(dater2);
                }
                adapter2.notifyDataSetChanged();


            }
        });




    }
}
