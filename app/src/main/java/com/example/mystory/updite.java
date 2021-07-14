package com.example.mystory;


import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class updite extends BottomSheetDialogFragment {
    DatePickerDialog.OnDateSetListener setListener;
FirebaseAuth auth=FirebaseAuth.getInstance();
FirebaseFirestore db=FirebaseFirestore.getInstance();
EditText dui;
Button te;
String dttype;
Button ok;


  private String getkey1;
   private String getkey2;
   private String getbalance;
  private String getpice;
   private Double bb;
   private Double pp1;
    private String getkkk;
    String numget;
    private String getunpaidbalance2;
    private Double ub2;
    private Double as;
    private String asasey;


    public updite() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_updite, container, false);
        getkkk = getArguments().getString("num");
        numget=getArguments().getString("numer");
        Toast.makeText(getContext(), ""+numget, Toast.LENGTH_SHORT).show();

        dui=view.findViewById(R.id.dui);
    te=view.findViewById(R.id.te);
    ok=view.findViewById(R.id.ok);
        RadioGroup radioGroup = (RadioGroup) getView().findViewById(R.id.ri);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected

                switch(checkedId) {
                    case R.id.rasedasasee:
                        as=bb;
                        asasey="balance";
                        Toast.makeText(getContext(), ""+asasey+as, Toast.LENGTH_SHORT).show();
                        // switch to fragment 1
                        break;
                    case R.id.rasedahtyatee:
                        as=ub2;
                        asasey="unpaidbalance";
                        Toast.makeText(getContext(), ""+asasey+as, Toast.LENGTH_SHORT).show();
                        // Fragment 2
                        break;
                }
            }
        });






















        Calendar cal= Calendar.getInstance();
        final int year=cal.get(Calendar.YEAR);
        final int month=cal.get(Calendar.MONTH);
        final int day=cal.get(Calendar.DAY_OF_MONTH);



    te.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            DatePickerDialog dialog= new DatePickerDialog(getContext()
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
                dttype=date;
                te.setText(date);


            }
        };
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String vv= dui.getText().toString().trim();
                String tt=dttype;
                if (vv.isEmpty()){dui.setError("is Empty");return;}
                if (tt==null){
                    Toast.makeText(getContext(), "enter End type", Toast.LENGTH_SHORT).show();
                return;
                }
                Double du=Double.parseDouble(vv);
                Double to= pp1*du-as;
                Double h=Math.abs(to);
                String sendto= String.valueOf(h);


                if (to>=as){

                    Toast.makeText(getContext(), "الرصيد لايسمح", Toast.LENGTH_SHORT).show();
                    return;
                }

                else {
                    Map<String, Object> user1 = new HashMap<>();
                    user1.put("typeend",tt);
                    user1.put("duration",vv);

                    db.collection("user").document(getkkk)
                            .set(user1, SetOptions.merge());



                    Map<String,Object> user4=new HashMap<>();
                    user4.put(asasey,sendto);
                    db.collection("user").document(getkey1).set(user4,SetOptions.merge());




                    Map<String, Object> user3 = new HashMap<>();
                    user3.put("keyofregistration",getkey1);

                    user3.put("keysubscriber",getkkk);
                    user3.put("duration",vv);
                    user3.put("caseActivie","طلب اشتراك");
                    user3.put("num",numget);
                    db.collection(getkey2).document(numget)
                            .set(user3,SetOptions.merge());
                        

                }

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
                    getkey1 = documentSnapshot.get("keyofregistration").toString();
                    getkey2 = documentSnapshot.get("keyadmin").toString();
                    getbalance = documentSnapshot.get("balance").toString();
                    getpice = documentSnapshot.get("pice").toString();
                    getunpaidbalance2=documentSnapshot.get("unpaidbalance").toString();


                    bb=Double.parseDouble(getbalance);
                    pp1=Double.parseDouble(getpice);
                    ub2=Double.parseDouble(getunpaidbalance2);


                    Toast.makeText(getContext(), "الرصيد" +bb, Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(), "erorr", Toast.LENGTH_SHORT).show();
                    ok.setEnabled(false);

                    return;
                }

            }
        });






















    }
}
