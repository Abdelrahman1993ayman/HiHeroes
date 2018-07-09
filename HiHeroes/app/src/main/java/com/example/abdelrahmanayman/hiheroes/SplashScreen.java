package com.example.abdelrahmanayman.hiheroes;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    private static  int SPLASH_TIME = 2000 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent myIntent = new Intent(SplashScreen.this , MainActivity.class);
                startActivity(myIntent);
                finish();
            }
        } , SPLASH_TIME);

    }
    }
