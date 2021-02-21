package com.example.mamaduck;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class login_activity extends AppCompatActivity {

    FirebaseAuth mfirebaseAutH;
    FirebaseAuth.AuthStateListener mAuthListener;

    public static final int REQ_CODE = 123;     //is just a code that we will need

    List<AuthUI.IdpConfig> provider = Arrays.asList( //using a List of objects we can login by
            //new AuthUI.IdpConfig.FacebookBuilder().build(), //using Facebook
            new AuthUI.IdpConfig.GoogleBuilder().build(),   //using a google acc
            new AuthUI.IdpConfig.EmailBuilder().build()     //using an email acc
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);     //we set the action bar so we can put our custom settings
        getSupportActionBar().setCustomView(R.layout.center_action_bar_text);       //we enter our file where we just center the text on the action bar, it is in layout folder

        mfirebaseAutH = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if(user != null){   //successful login
                    Toast.makeText(login_activity.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                }else{  //unsuccessful login
                    startActivityForResult(AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(provider)
                            .setIsSmartLockEnabled(false)
                            .build(), REQ_CODE
                    );
                }

            }
        };

    }

    @Override
    protected void onResume(){ //just to check if the user have loged in so he/she can resume the session
        super.onResume();
        mfirebaseAutH.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onPause() { //same as resume but the user is not using the app, its on pause
        super.onPause();
        mfirebaseAutH.removeAuthStateListener(mAuthListener);
    }

    public void signoutButton(View view) {
        AuthUI.getInstance().signOut(this).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(login_activity.this, "Sesión cerrada", Toast.LENGTH_SHORT).show();
            }
        });
    }
}