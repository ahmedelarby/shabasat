package com.example.mystory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class addSbscirabePage extends AppCompatActivity  {
EditText name;
EditText email;
EditText pass;
EditText numbercard;
Button dayofmonthofyears;
Button add;
String datatype;
Button cancel;
EditText numphone;
EditText addriss;
DatePickerDialog.OnDateSetListener setListener;
FirebaseAuth auth =FirebaseAuth.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
   private String gettime;
   private String getkey;
EditText durationy;
   private String getkey2;
  private   String getbalance;
   private String getpice;
   private String getunpaidbalance;
   private Double ub;
   private Double bb;
   private Double as;
   private Double pp;
   private String asasey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sbscirabe_page);
        cancel=findViewById(R.id.cancel);


        name=findViewById(R.id.editname);
        numphone=findViewById(R.id.editnumphone);
        addriss=findViewById(R.id.editaddriss);
        email=findViewById(R.id.editemail);
        pass=findViewById(R.id.editpass);
        numbercard=findViewById(R.id.editnumcard);
        dayofmonthofyears=findViewById(R.id.dayofmonthofyear);
        add=findViewById(R.id.SignUp);
        durationy=findViewById(R.id.duration);
        long timeInMillis = System.currentTimeMillis();
        Calendar cal1 = Calendar.getInstance();
        cal1.setTimeInMillis(timeInMillis);
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy/MM/dd hh:mm a", Locale.ENGLISH);
        gettime = dateFormat.format(cal1.getTime());


//        Double b=Double.parseDouble(getbalance);
//        Double pi=Double.parseDouble(getpice);









        Calendar cal= Calendar.getInstance();
        final int year=cal.get(Calendar.YEAR);
        final int month=cal.get(Calendar.MONTH);
        final int day=cal.get(Calendar.DAY_OF_MONTH);
        dayofmonthofyears.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            DatePickerDialog dialog= new DatePickerDialog(addSbscirabePage.this
                  ,R.style.Theme_AppCompat_Light_Dialog_MinWidth,setListener,year,month,day);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                    dialog.show();

            }
        });
    setListener= new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

            i1 =i1+1;
            String date=i+"/"+i1+"/"+i2;
            datatype=date;
            dayofmonthofyears.setText(date);


        }
    };


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });







       add.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
            String n= name.getText().toString().trim();
              String e= email.getText().toString().trim();
               String p=pass.getText().toString().trim();
              String phone= numphone.getText().toString().trim();
               String ads=addriss.getText().toString().trim();
             String nc=  numbercard.getText().toString().trim();
             String d=durationy.getText().toString().trim();
             String type=datatype;
             if (n.isEmpty()){name.setError("is Empty");return;}
             if (e.isEmpty()){email.setError("is Empty");return;}
             if (phone.isEmpty()){numphone.setError("is Empty");return;}
             if (ads.isEmpty()){addriss.setError("is Empty");return;}
             if (p.isEmpty()){pass.setError("isEmpty");return;}
             if (nc.isEmpty()){numbercard.setError("is Empty");return;}
             if (d.isEmpty()){durationy.setError("is Empty");return;}
             if (type==null){
                 Toast.makeText(addSbscirabePage.this, "is Empty type", Toast.LENGTH_SHORT).show();return;}
               Double du=Double.parseDouble(d);
               Double to= pp*du-as;
               Double h=Math.abs(to);
               String sendto= String.valueOf(h);
                //
//               Double to2=pp*du-ub;
//               Double h2=Math.abs(to2);
//               String sendto2=String.valueOf(h2);

             if (to>=as){

                 Toast.makeText(addSbscirabePage.this, "الرصيد لايسمح", Toast.LENGTH_SHORT).show();
                    return;
             }
             auth.createUserWithEmailAndPassword(e,p).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                 @Override
                 public void onComplete(@NonNull Task<AuthResult> task) {
                     if (task.isSuccessful()){

                         Map<String, Object> user = new HashMap<>();
                         user.put("keyofregistration",getkey);
                         user.put("keysubscriber",auth.getCurrentUser().getUid());
                         user.put("name",n);
                         user.put("email", e);
                         user.put("pass",p);
                         user.put("phone",phone);
                         user.put("addriss",ads);
                         user.put("duration",d);
                         user.put("numbercard",nc);
                         user.put("dateofregistration",gettime);
                         user.put("adjective", "subscriber");
                         user.put("case", "noActivie");
                         user.put("totalday2","0");
                         user.put("totalday", "0");
                         user.put("typeend", type);
                         user.put("typestarting", "0");

                         db.collection("user").document(auth.getCurrentUser().getUid())
                                 .set(user, SetOptions.merge());
//                            // privite mowsea



                         Map<String, Object> user3 = new HashMap<>();
                         user3.put("keyofregistration",getkey);
                         user3.put("caseActivie","طلب اشتراك");

                         user3.put("keysubscriber",auth.getCurrentUser().getUid());
                         user3.put("duration",d);
                         user3.put("num",nc);
                         db.collection(getkey2).document(nc)
                                 .set(user3,SetOptions.merge());

                             Map<String,Object> user4=new HashMap<>();
                             user4.put(asasey,sendto);
                             db.collection("user").document(getkey).set(user4,SetOptions.merge());




//                         Map<String,Object> user5=new HashMap<>();
//
//                         user5.put("unpaidbalance",sendto2);
//                         db.collection("user").document(getkey).set(user5,SetOptions.merge());
                         Toast.makeText(addSbscirabePage.this, "is complete", Toast.LENGTH_SHORT).show();
                           // FirebaseAuth.getInstance().signOut();
                         Intent back= new Intent(addSbscirabePage.this,Home.class);
                         startActivity(back);
                         finish();























                     }






                 }
             }).addOnFailureListener(new OnFailureListener() {
                 @Override
                 public void onFailure(@NonNull Exception e) {

                 }
             });









           }
       });


    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rasedassasey:
                if (checked)
                    as=bb;
                    asasey="balance";
                Toast.makeText(this, ""+asasey+as, Toast.LENGTH_SHORT).show();
                    // Pirates are the best
                    break;
            case R.id.rasedahtyatey:
                if (checked)
                    as=ub;
                    asasey="unpaidbalance";
                Toast.makeText(this, ""+asasey+as, Toast.LENGTH_SHORT).show();

                // Ninjas rule
                    break;
        }
    }





    @Override
    protected void onStart() {
        super.onStart();
        db.collection("user").document(auth.getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    getkey = documentSnapshot.get("keyofregistration").toString();
                    getkey2 = documentSnapshot.get("keyadmin").toString();
                    getbalance = documentSnapshot.get("balance").toString();
                    getpice = documentSnapshot.get("pice").toString();
                    getunpaidbalance=documentSnapshot.get("unpaidbalance").toString();
                     bb=Double.parseDouble(getbalance);
                     pp=Double.parseDouble(getpice);
                     ub=Double.parseDouble(getunpaidbalance);

                    Toast.makeText(addSbscirabePage.this, "الرصيد" +bb, Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(addSbscirabePage.this, "erorr", Toast.LENGTH_SHORT).show();
                    add.setEnabled(false);

                    return;
                }

            }
        });
    }
}
