package com.example.mystory;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.style.UpdateLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserPage extends Fragment {
Context context;
ImageSlider image_slider;
     List<SlideModel> modelsuser=new ArrayList<>();
     FirebaseFirestore db=FirebaseFirestore.getInstance();
    CollectionReference collectionReference=db.collection("slider");
    FirebaseAuth auth = FirebaseAuth.getInstance();

    TextView nametext;
    TextView casetext;
    TextView nc;
    TextView typeStarting;
    TextView typeend1;
    ProgressBar progressBar2;
    TextView numday;
    Button callme,button;
    int pro=360;
    Button signout;
    public UserPage() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_user_page, container, false);
        image_slider=view.findViewById(R.id.image_slider);
        nametext=view.findViewById(R.id.name);
        casetext=view.findViewById(R.id.case1);
        nc=view.findViewById(R.id.nc);
        typeStarting=view.findViewById(R.id.typeStarting);
        typeend1=view.findViewById(R.id.typeend1);
        progressBar2=view.findViewById(R.id.progressBar2);
        progressBar2.setProgress(pro);
        signout=view.findViewById(R.id.sinout);
        numday=view.findViewById(R.id.numday);
        callme=view.findViewById(R.id.callme);
        button=view.findViewById(R.id.button);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent b=new Intent(getContext(),Home.class);
                startActivity(b);
            }
        });
        db.collection("user").document(auth.getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot==null){
                    Toast.makeText(context, "لايوجد لديك بيانات", Toast.LENGTH_SHORT).show();
                    return;
                }else {

                    nametext.setText("" + documentSnapshot.get("name").toString());
                    casetext.setText("" + documentSnapshot.get("case").toString());
                    typeStarting.setText("" + documentSnapshot.get("typestarting").toString());
                    nc.setText("" + documentSnapshot.get("numbercard").toString());
                    typeend1.setText("" + documentSnapshot.get("typeend").toString());
                    numday.setText("" + documentSnapshot.get("totalday").toString());
                    progressBar2.setProgress(Integer.parseInt(documentSnapshot.get("totalday").toString()));



                }


            }
        });












            return view;














    }

    @Override
    public void onStart() {
        super.onStart();
        collectionReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                modelsuser.clear();
                for (QueryDocumentSnapshot datauser:queryDocumentSnapshots){
                    modelsuser.add(new SlideModel(datauser.get("url").toString(),datauser.get("title").toString(),ScaleTypes.FIT));
                    image_slider.setImageList(modelsuser,ScaleTypes.FIT);

                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }
}
