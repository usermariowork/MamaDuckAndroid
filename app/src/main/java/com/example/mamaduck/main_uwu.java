package com.example.mamaduck;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;


public class main_uwu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {    //generated automatically
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_uwu);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);     //we set the action bar so we can put our custom settings
        getSupportActionBar().setCustomView(R.layout.center_action_bar_text);       //we enter our file where we just center the text on the action bar, it is in layout folder

    }
}