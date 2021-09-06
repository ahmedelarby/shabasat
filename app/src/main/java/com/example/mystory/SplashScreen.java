package com.example.mystory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

public class SplashScreen extends AppCompatActivity {
ProgressBar proo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);

        proo=findViewById(R.id.proo);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                proo.setVisibility(View.VISIBLE);
                Intent home = new Intent(SplashScreen.this,Home.class);
                startActivity(home);
                finish();
            }
        }, 4000);
    }
}
