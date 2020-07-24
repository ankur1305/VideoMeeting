package com.example.videomeeting.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.videomeeting.R;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        findViewById(R.id.textSignUp).setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), SignUpActivity.class)));
    }
}