package com.example.mamaduck;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class splash_screen extends AppCompatActivity {

    private final int duration = 3000; //3000 miliseconds (3 sec) of delay to go to the login_activity activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {    //oncreate method, is automaticlly generated
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        getSupportActionBar().hide();//hide action bar

        new Handler().postDelayed(new Runnable() { //pasar al activity de initial_config con un intent
            @Override
            public void run() { //intent used for going to the next activity (login_activity)
                Intent intent = new Intent(splash_screen.this, login_activity.class);
                startActivity(intent);
                finish();
            };
        },duration);
    }
}