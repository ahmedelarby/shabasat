package com.example.mystory;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import android.text.style.UpdateLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
import com.google.firebase.firestore.SetOptions;

import org.joda.time.DateTimeUtils;
import org.joda.time.Period;
import org.joda.time.PeriodType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static android.content.Context.NOTIFICATION_SERVICE;
import static android.provider.Settings.System.DATE_FORMAT;
import static java.time.temporal.ChronoUnit.DAYS;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserPage extends Fragment {
 private final String CHANNEL_ID = "simple_notification";
 private final int NOTIFICATION_ID = 01;
private String gettime;
Context context;
ImageSlider image_slider;

     List<SlideModel> modelsuser=new ArrayList<>();
     FirebaseFirestore db=FirebaseFirestore.getInstance();
    CollectionReference collectionReference=db.collection("slider");
    FirebaseAuth auth = FirebaseAuth.getInstance();
    long ggg;
    String tot;
    String tot2;
    TextView update;
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
String e;
String s;
String name;
String number;
NotificationManagerCompat compat;
    AlertDialog dialog1;

    public UserPage() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_user_page, container, false);
       compat= NotificationManagerCompat.from(getContext());
        image_slider=view.findViewById(R.id.image_slider);
        nametext=view.findViewById(R.id.name);
        casetext=view.findViewById(R.id.case1);
        update=view.findViewById(R.id.update);
        nc=view.findViewById(R.id.nc);
        typeStarting=view.findViewById(R.id.typeStarting);
        typeend1=view.findViewById(R.id.typeend1);
        progressBar2=view.findViewById(R.id.progressBar2);
        progressBar2.setProgress(pro);
        signout=view.findViewById(R.id.sinout);
        numday=view.findViewById(R.id.numday);
        callme=view.findViewById(R.id.callme);
        button=view.findViewById(R.id.button);




        callme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                AlertDialog.Builder builder1 =new AlertDialog.Builder(getContext());
                View view1 = getLayoutInflater().inflate(R.layout.itemdiloge,null);
                final TextView textD=view1.findViewById(R.id.textD);
                textD.setText("ارسال رساله للدعم ");
                final EditText userInput = (EditText) view1.findViewById(R.id.numbercard);
               userInput.setHint("Enter your problem");
                final Button b =view1.findViewById(R.id.yes);
                b.setText("ابلاغ");

                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String g=userInput.getText().toString().trim();
                        if (g.isEmpty()){userInput.setError("is empty");return;}
                        else {

                            Map<String,Object> u=new HashMap<>();
                            u.put("المشكله",g);
                            u.put("name",name);
                            u.put("numbercard",number);

                            u.put("keysubscriber",auth.getUid());
                            db.collection("مشكله دعم فني من المشتركبن").document().set(u,SetOptions.merge());
                            Toast.makeText(getContext(), "تم الابلاغ بنجاح", Toast.LENGTH_SHORT).show();
                            dialog1.dismiss();

                        }

                    }
                });

                // create alert dialog
                builder1.setView(view1);
                dialog1 = builder1.create();
                dialog1.show();














































            }
        });


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.collection("user").document(auth.getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()){
                            casetext.setText(documentSnapshot.get("case").toString());
                            if (documentSnapshot.get("case").toString().equals("noActivie"))
                            {
                                casetext.setBackgroundColor(Color.parseColor("#FF1919"));
                            }else {
                                casetext.setBackgroundColor(Color.parseColor("#098C0B"));

                            }
                            typeStarting.setText("" + documentSnapshot.get("typestarting").toString());
                            nc.setText("" + documentSnapshot.get("numbercard").toString());
                            typeend1.setText("" + documentSnapshot.get("typeend").toString());
                            numday.setText("" + documentSnapshot.get("totalday").toString());
                            progressBar2.setProgress(Integer.parseInt(documentSnapshot.get("totalday").toString()));


                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull  Exception e) {

                    }
                });
            }
        });








        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent b=new Intent(getContext(),Home.class);
                startActivity(b);
                getActivity().finish();
            }
        });
        db.collection("user").document(auth.getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()){
                    nametext.setText("" + documentSnapshot.get("name").toString());
                    casetext.setText("" + documentSnapshot.get("case").toString());
                    if (documentSnapshot.get("case").toString().equals("noActivie"))
                    {
                        casetext.setBackgroundColor(Color.parseColor("#FF1919"));
                    }
                    if (documentSnapshot.get("case").toString().equals("Activie"))
                    {
                        casetext.setBackgroundColor(Color.parseColor("#098C0B"));
                        long timeInMillis = System.currentTimeMillis();
                        Calendar cal1 = Calendar.getInstance();
                        cal1.setTimeInMillis(timeInMillis);
                        SimpleDateFormat dateFormat = new SimpleDateFormat(
                                "yyyy/MM/dd", Locale.ENGLISH);
                        gettime = dateFormat.format(cal1.getTime());
                        Map<String, Object> user123 = new HashMap<>();
                        user123.put("typestarting", gettime);
                        //user.put("totalday2",dayDifference);
                        db.collection("user")
                                .document(auth.getUid())
                                .set(user123, SetOptions.merge());

                        createNotificationChannel();
                        NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(), CHANNEL_ID);
                        builder.setSmallIcon(R.drawable.logo);
                        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.logo));
                        builder.setContentTitle("حاله الحساب");
                        builder.setContentText(documentSnapshot.get("case").toString());
                        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

                        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getContext());
                        notificationManagerCompat.notify(NOTIFICATION_ID, builder.build());



                    }
                    typeStarting.setText("" + documentSnapshot.get("typestarting").toString());
                    nc.setText("" + documentSnapshot.get("numbercard").toString());
                    typeend1.setText("" + documentSnapshot.get("typeend").toString());
                    numday.setText("" + documentSnapshot.get("totalday").toString());
                    e=  documentSnapshot.get("typeend").toString();
                    s=documentSnapshot.get("typestarting").toString();
                    tot=documentSnapshot.get("totalday").toString();
                    if (tot.equals("3")){
                        createNotificationChannel();
                        NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(), CHANNEL_ID);
                        builder.setSmallIcon(R.drawable.logo);
                        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.logo));
                        builder.setContentTitle("حاله الاشتراك");
                        builder.setContentText("باقي علي انتهاء الاشتراك"+tot+"ايام");
                        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

                        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getContext());
                        notificationManagerCompat.notify(NOTIFICATION_ID, builder.build());




                    }
                    // tot2=documentSnapshot.get("totalday2").toString();
                   name= documentSnapshot.get("name").toString();
                   number= documentSnapshot.get("numbercard").toString();
                    progressBar2.setProgress(Integer.parseInt(documentSnapshot.get("totalday").toString()));
                    setTimeOptions(e,s);

                }else {
                    Toast.makeText(context, "لايوجد لديك بيانات", Toast.LENGTH_SHORT).show();









                }


            }
        });



        return view;














    }
    private void createNotificationChannel(){
      if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){

       CharSequence name = "Simple Notification";
        String description = "Include all the simple notification";
         int importance = NotificationManager.IMPORTANCE_DEFAULT;

           NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID,name,importance);
           notificationChannel.setDescription(description);

            NotificationManager notificationManager = (NotificationManager) getContext().getSystemService(NOTIFICATION_SERVICE);
             notificationManager.createNotificationChannel(notificationChannel);
               }
            }

























    private void setTimeOptions(String end, String start) {
        Log.i("end", "setTimeOptions: " + end);
        Log.i("satrt", "setTimeOptions: " + start);
        try {
            Date date1;
            Date date2;
            SimpleDateFormat dates = new SimpleDateFormat("yyyy/MM/dd");
            date1 = dates.parse(start);
            date2 = dates.parse(end);
            long difference = Math.abs(date1.getTime() - date2.getTime());
            long differenceDates = difference / (24 * 60 * 60 * 1000);
            String dayDifference = Long.toString(differenceDates);
            Map<String, Object> user = new HashMap<>();
            user.put("totalday", dayDifference);
            //user.put("totalday2",dayDifference);
            db.collection("user")
                    .document(auth.getUid())
                    .set(user, SetOptions.merge());
            numday.setText(""+tot);
            progressBar2.setProgress(Integer.parseInt(tot));
            progressBar2.setMax(40);

            Log.i("Ahmed", "setTimeOptions: " + dayDifference);
//            textView.setText("The difference between two dates is " + dayDifference + " days");
        } catch (Exception exception) {
            Toast.makeText(getContext(), "Unable to find difference", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public void onStart() {
        super.onStart();
        collectionReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                modelsuser.clear();
                for (QueryDocumentSnapshot datauser : queryDocumentSnapshots) {
                    modelsuser.add(new SlideModel(datauser.get("url").toString(), datauser.get("title").toString(), ScaleTypes.FIT));
                    image_slider.setImageList(modelsuser, ScaleTypes.FIT);

                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });


    }






















    }

