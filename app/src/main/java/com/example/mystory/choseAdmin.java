package com.example.mystory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class choseAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chose_admin);
        String chose=getIntent().getStringExtra("tm");
        switch (chose){
            case "tm":
                tlabatmowasea tt=new tlabatmowasea();
                FragmentManager fm1=getSupportFragmentManager();
                FragmentTransaction ft1=fm1.beginTransaction();
                ft1.add(R.id.frem123,tt);
                ft1.commit();

                break;
            case "rased":
                rasedmowsea tt2=new rasedmowsea();
                FragmentManager fm2=getSupportFragmentManager();
                FragmentTransaction ft2=fm2.beginTransaction();
                ft2.add(R.id.frem123,tt2);
                ft2.commit();


                break;
            case "ts":


        }






    }
}
