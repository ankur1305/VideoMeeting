package com.example.videomeeting.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.videomeeting.R;

public class SplashScreen extends AppCompatActivity {

    public static int SPLASH_TIMER = 3500;

    //Variables
    ImageView appIcon;
    TextView poweredByLine, appTitle;

    //Animation Variables
    Animation sideAnim, bottomAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //Hooks
        appIcon = findViewById(R.id.app_icon);
        appTitle = findViewById(R.id.app_title);
        poweredByLine = findViewById(R.id.powered_by_line);

        //Hooks Animations
        sideAnim = AnimationUtils.loadAnimation(this, R.anim.side_anim);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_anim);

        //Set Animations
        appIcon.setAnimation(sideAnim);
        appTitle.setAnimation(bottomAnim);
        poweredByLine.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), SignInActivity.class));
                finish();
            }
        }, SPLASH_TIMER);
    }
}