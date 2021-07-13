package com.example.mystory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Profile extends AppCompatActivity {
TextView text1;
private TextView name;
private TextView number;
private TextView email;
private TextView casesr;
private TextView typeend;
private TextView  durationt;
private Button Subscription_renewal;
FirebaseFirestore db= FirebaseFirestore.getInstance();
String k;
String numbe;
String hhh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        text1=findViewById(R.id.problem);
        name=findViewById(R.id.namesr);
        number=findViewById(R.id.numbersr);
        email=findViewById(R.id.emailsr);
        casesr=findViewById(R.id.casesr);
        typeend=findViewById(R.id.typeendsr);
        durationt=findViewById(R.id.durationsr);
        Subscription_renewal=findViewById(R.id.Subscription_renewal);
         k= getIntent().getStringExtra("key");
         String o=getIntent().getStringExtra("o");
         switch (o){
             case "o":
                 Subscription_renewal.setVisibility(View.GONE);
                 db.collection("user").document(k).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                     @Override
                     public void onSuccess(DocumentSnapshot documentSnapshot) {
                         if (documentSnapshot.exists()){


                             String nm=documentSnapshot.get("name").toString();
                             name.setText(""+nm);
                             String em=documentSnapshot.get("email").toString();
                             email.setText(""+em);
                             String numbe=documentSnapshot.get("numbercard").toString();
                             number.setText(""+numbe);
                             String ca=documentSnapshot.get("case").toString();
                             casesr.setText(""+ca);
                             String typeen=documentSnapshot.get("typeend").toString();
                             typeend.setText(""+typeen);
                             String duration=documentSnapshot.get("duration").toString();
                             durationt.setText(""+duration);

                         }else {text1.setText("no fuond");
                             name.setVisibility(View.GONE);
                             email.setVisibility(View.GONE);
                             number.setVisibility(View.GONE);
                             casesr.setVisibility(View.GONE);
                             typeend.setVisibility(View.GONE);
                             durationt.setVisibility(View.GONE);
                             Subscription_renewal.setVisibility(View.GONE);
                             return;}
                     }
                 }).addOnFailureListener(new OnFailureListener() {
                     @Override
                     public void onFailure(@NonNull Exception e) {
                         text1.setText("" +e.getMessage());


                     }
                 });


                 break;
             case "m":
                 Subscription_renewal.setVisibility(View.GONE);
                 db.collection("user").document(k).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                     @Override
                     public void onSuccess(DocumentSnapshot documentSnapshot) {
                         if (documentSnapshot.exists()){


                             String nm=documentSnapshot.get("name").toString();
                             name.setText(""+nm);
                             String em=documentSnapshot.get("email").toString();
                             email.setText(""+em);
                             String numbe1=documentSnapshot.get("city").toString();
                             number.setText(""+numbe1);
                             casesr.setVisibility(View.GONE);
                             typeend.setVisibility(View.GONE);
                             durationt.setVisibility(View.GONE);

                         }else {text1.setText("no fuond");
                             name.setVisibility(View.GONE);
                             email.setVisibility(View.GONE);
                             number.setVisibility(View.GONE);
                             casesr.setVisibility(View.GONE);
                             typeend.setVisibility(View.GONE);
                             durationt.setVisibility(View.GONE);
                             Subscription_renewal.setVisibility(View.GONE);
                             return;}
                     }
                 }).addOnFailureListener(new OnFailureListener() {
                     @Override
                     public void onFailure(@NonNull Exception e) {
                         text1.setText("" +e.getMessage());


                     }
                 });






                 break;
             case "o1":
                 db.collection("user").document(k).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                     @Override
                     public void onSuccess(DocumentSnapshot documentSnapshot) {
                         if (documentSnapshot.exists()){


                             String nm=documentSnapshot.get("name").toString();
                             name.setText(""+nm);
                             String em=documentSnapshot.get("email").toString();
                             email.setText(""+em);
                              numbe=documentSnapshot.get("numbercard").toString();
                             number.setText(""+numbe);
                             String ca=documentSnapshot.get("case").toString();
                             casesr.setText(""+ca);
                             String typeen=documentSnapshot.get("typeend").toString();
                             typeend.setText(""+typeen);
                             String duration=documentSnapshot.get("duration").toString();
                             durationt.setText(""+duration);

                         }else {text1.setText("no fuond");
                             name.setVisibility(View.GONE);
                             email.setVisibility(View.GONE);
                             number.setVisibility(View.GONE);
                             casesr.setVisibility(View.GONE);
                             typeend.setVisibility(View.GONE);
                             durationt.setVisibility(View.GONE);
                             Subscription_renewal.setVisibility(View.GONE);
                             return;}
                     }
                 }).addOnFailureListener(new OnFailureListener() {
                     @Override
                     public void onFailure(@NonNull Exception e) {
                         text1.setText("" +e.getMessage());


                     }
                 });
                 break;

         }

        Subscription_renewal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updite updite=new updite();
                Bundle bundle= new Bundle();
                Toast.makeText(Profile.this, ""+numbe, Toast.LENGTH_SHORT).show();
                bundle.putString("numer",numbe);
                bundle.putString("num",k);
                updite.setArguments(bundle);

                updite.show(getSupportFragmentManager(),"ahmed");














            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}
