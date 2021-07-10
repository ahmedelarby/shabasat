package com.example.mystory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ThemeUtils;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {
 FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    ImageSlider image_slider;
EditText number;
EditText password;
Button login;
AlertDialog dialog1;
ProgressBar progressBar1;
Button usern1;
FirebaseAuth auth = FirebaseAuth.getInstance();
FirebaseFirestore db = FirebaseFirestore.getInstance();
CollectionReference collectionReference=db.collection("slider");
    final List<SlideModel> models=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        progressBar1=findViewById(R.id.progressBar);
        number=findViewById(R.id.number);
        password=findViewById(R.id.password);
        login=findViewById(R.id.login);
        usern1=findViewById(R.id.usern);
        // this privet Slider
        image_slider=(ImageSlider) findViewById(R.id.logo);

//this is login firebase Starting
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String num=number.getText().toString().trim();
                String pass=password.getText().toString().trim();
                if (num.isEmpty()){number.setError("is Empty");return;}
                if (pass.isEmpty()){password.setError("is Empty");return;}
                if (pass.length()<8){password.setError("the number password not 8 char");return;}
                else {
                    login.setEnabled(false);
                   progressBar1.setVisibility(View.VISIBLE);
                    auth.signInWithEmailAndPassword(num,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(Home.this, "is Successful", Toast.LENGTH_SHORT).show();
                                db.collection("user").document(auth.getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                    @Override
                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                                        if (documentSnapshot.exists()){
                                        String kkk=  documentSnapshot.get("adjective").toString();
                                        progressBar1.setVisibility(View.GONE);
                                        Intent f= new Intent(Home.this,pagehomemenu.class);
                                        f.putExtra("aaa",kkk);
                                        startActivity(f);
                                            finish();}

                                        else {
                                            Toast.makeText(Home.this, "انتا داخل كزائر لانه غير مصرح لك بالدخول", Toast.LENGTH_SHORT).show();
                                            progressBar1.setVisibility(View.GONE);

                                            Intent u1=new Intent(Home.this,pagehomemenu.class);
                                            u1.putExtra("aaa","userzaer");
                                            startActivity(u1);
                                            finish();
                                        }

                                    }
                                });


//
//
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Home.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar1.setVisibility(View.GONE);
                            login.setEnabled(true);
                        }
                    });

                }
            }
        });
        usern1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar1.setVisibility(View.GONE);

                Intent u=new Intent(Home.this,pagehomemenu.class);
                u.putExtra("aaa","userzaer");
                startActivity(u);
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (user!=null) {
            AlertDialog.Builder builder1 =new AlertDialog.Builder(Home.this);
            View view = getLayoutInflater().inflate(R.layout.itemdiloge_home,null);
            ProgressBar pro = view.findViewById(R.id.prohome);
            pro.setVisibility(View.VISIBLE);
            builder1.setView(view);
            dialog1 = builder1.create();
            dialog1.show();
            db.collection("user").document(auth.getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    if (documentSnapshot.exists()) {
                        String kkk = documentSnapshot.get("adjective").toString();
                        String e = documentSnapshot.get("email").toString();
                        String p = documentSnapshot.get("pass").toString();

                        number.setText("" + e);
                        password.setText("" + p);

                        dialog1.dismiss();

                        Intent f = new Intent(Home.this, pagehomemenu.class);
                        f.putExtra("aaa", kkk);
                        startActivity(f);
                        finish();
                    } else {

                    }

                }
            });

        }















            FirebaseDatabase.getInstance().getReference().child("slider").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                models.clear();
                for (DataSnapshot data:snapshot.getChildren()){


                        models.add(new SlideModel(data.child("url").getValue().toString(), data.child("title").getValue().toString(), ScaleTypes.FIT));
                        image_slider.setImageList(models, ScaleTypes.FIT);
                     }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //this End Slider
































    }
}
