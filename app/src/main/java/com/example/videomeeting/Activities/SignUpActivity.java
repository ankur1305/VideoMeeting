package com.example.videomeeting.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.videomeeting.R;
import com.example.videomeeting.Utilities.Constants;
import com.example.videomeeting.Utilities.PreferenceManager;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity {

    //Variables of fields
    private EditText inputFirstName, inputLastName, inputEmail, inputPassword, inputConfirmPassword;
    private MaterialButton buttonSignUp;
    private ProgressBar signUpProgressBar;

    //Preference Manager variable
    PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        preferenceManager = new PreferenceManager(getApplicationContext());

        findViewById(R.id.imageBack).setOnClickListener(v -> onBackPressed());
        findViewById(R.id.textSignIn).setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), SignInActivity.class)));

        //Hooks
        inputFirstName = findViewById(R.id.inputFirstName);
        inputLastName = findViewById(R.id.inputLastName);
        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        inputConfirmPassword = findViewById(R.id.inputConfirmPassword);
        buttonSignUp = findViewById(R.id.buttonSignUp);
        signUpProgressBar = findViewById(R.id.signUpProgressBar);

        buttonSignUp.setOnClickListener(v -> {
            if(inputFirstName.getText().toString().trim().isEmpty()){
                Toast.makeText(SignUpActivity.this, "Please Enter Your First Name", Toast.LENGTH_SHORT).show();
            }else if(inputLastName.getText().toString().trim().isEmpty()){
                Toast.makeText(SignUpActivity.this, "Please Enter Your Last Name", Toast.LENGTH_SHORT).show();
            }else if(inputEmail.getText().toString().trim().isEmpty()){
                Toast.makeText(SignUpActivity.this, "Please Enter Your Email Address", Toast.LENGTH_SHORT).show();
            }else if(!Patterns.EMAIL_ADDRESS.matcher(inputEmail.getText().toString()).matches()){
                Toast.makeText(SignUpActivity.this, "Please Enter Valid Email Address", Toast.LENGTH_SHORT).show();
            }else if(inputPassword.getText().toString().trim().isEmpty()){
                Toast.makeText(SignUpActivity.this, "Please Enter Your Password", Toast.LENGTH_SHORT).show();
            }else if(inputConfirmPassword.getText().toString().trim().isEmpty()){
                Toast.makeText(SignUpActivity.this, "Please Enter Your Confirm Password", Toast.LENGTH_SHORT).show();
            }else if(!inputPassword.getText().toString().equals(inputConfirmPassword.getText().toString())){
                Toast.makeText(SignUpActivity.this, "Passwords Do Not Match", Toast.LENGTH_SHORT).show();
            }else{
                signUp();
            }
        });
    }

    private void signUp() {
        buttonSignUp.setVisibility(View.GONE);
        signUpProgressBar.setVisibility(View.VISIBLE);

        FirebaseFirestore database = FirebaseFirestore.getInstance();
        HashMap<String, Object> user = new HashMap<>();
        user.put(Constants.KEY_FIRST_NAME, inputFirstName.getText().toString());
        user.put(Constants.KEY_LAST_NAME, inputLastName.getText().toString());
        user.put(Constants.KEY_EMAIL, inputEmail.getText().toString());
        user.put(Constants.KEY_PASSWORD, inputPassword.getText().toString());

        database.collection(Constants.KEY_COLLECTION_USERS)
                .add(user)
                .addOnSuccessListener(documentReference -> {
                    preferenceManager.putBoolean(Constants.KEY_IS_SIGNED_IN, true);
                    preferenceManager.putString(Constants.KEY_FIRST_NAME, inputFirstName.getText().toString());
                    preferenceManager.putString(Constants.KEY_LAST_NAME, inputLastName.getText().toString());
                    preferenceManager.putString(Constants.KEY_EMAIL, inputEmail.getText().toString());
                    preferenceManager.putString(Constants.KEY_PASSWORD, inputPassword.getText().toString());
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    Toast.makeText(SignUpActivity.this, "Your Account Have Been Created Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                })
                .addOnFailureListener(e -> {
                    signUpProgressBar.setVisibility(View.GONE);
                    buttonSignUp.setVisibility(View.VISIBLE);
                    Toast.makeText(SignUpActivity.this, "Error : "+e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }
}