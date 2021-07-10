package com.example.mystory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.transition.FragmentTransitionSupport;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class pagehomemenu extends AppCompatActivity {
//public  int counter;
//Button ol;
//TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagehomemenu);
        String w=getIntent().getStringExtra("aaa");
        switch (w){
            case "subscriber":
                UserPage Subscriber=new UserPage();
                FragmentManager fm=getSupportFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                ft.add(R.id.freem,Subscriber);
                ft.commit();
                break;
            case "distributor":
                distributor distributor1=new distributor();
                FragmentManager fm2=getSupportFragmentManager();
                FragmentTransaction ft2=fm2.beginTransaction();
                ft2.add(R.id.freem,distributor1);
                ft2.commit();
                Toast.makeText(this, "admin2", Toast.LENGTH_SHORT).show();
                break;
            case "admin":
                admin admin1=new admin();
                FragmentManager fmadmin=getSupportFragmentManager();
                FragmentTransaction ftadmin=fmadmin.beginTransaction();
                ftadmin.add(R.id.freem,admin1);
                ftadmin.commit();
                break;
            case "userzaer":
                userzaer user=new userzaer();
                FragmentManager fmuser=getSupportFragmentManager();
                FragmentTransaction ftuser=fmuser.beginTransaction();
                ftuser.add(R.id.freem,user);
                ftuser.commit();

        }


       //        ol=findViewById(R.id.button);
//        t=findViewById(R.id.numday);
//        ol.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                new CountDownTimer(,){
////                    @Override
////                    public void onTick(long l) {
////                        t.setText(String.valueOf(counter));
////                        counter++;
////                    }
////
////                    @Override
////                    public void onFinish() {
////                        Toast.makeText(pagehomemenu.this, "finish", Toast.LENGTH_SHORT).show();
////                    }
////                }.start();
//           }
//        });



    }
}
