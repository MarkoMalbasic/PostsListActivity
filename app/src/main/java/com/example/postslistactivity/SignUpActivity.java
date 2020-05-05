package com.example.postslistactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    private TextInputEditText edtEmail, edtUsername, edtPassword;
    private Button btnSignup;
    private TextView txtLoginHere;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        /*ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(Html.fromHtml("<font color='#101C2C'>"+"Avli By Tashas"+"</font>"));*/


        edtEmail = findViewById(R.id.edtEmail);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnSignup = findViewById(R.id.btnSignup);
        txtLoginHere = findViewById(R.id.txtLoginHere);

        mAuth = FirebaseAuth.getInstance();

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                signUp();
            }
        });

        txtLoginHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        if(mAuth.getCurrentUser() != null){

            transitionToMainActivity();
        }

    }


    private void signUp(){
        
        if(edtEmail.getText().toString().equals("") || 
                edtUsername.getText().toString().equals("") || 
                edtPassword.getText().toString().equals("")){

            Toast.makeText(this, "Email, Username and Password are Required!", Toast.LENGTH_SHORT).show();

        }else {
            mAuth.createUserWithEmailAndPassword(edtEmail.getText().toString(), edtPassword.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {

                                Toast.makeText(SignUpActivity.this, "Signing up successful.",
                                        Toast.LENGTH_SHORT).show();

                                // method to get username stored in database
                                FirebaseDatabase.getInstance().getReference()
                                        .child("users").child(task.getResult().getUser().getUid())
                                        .child("username")
                                        .setValue(edtUsername.getText().toString());

                                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                        .setDisplayName(edtUsername.getText().toString())
                                        .build();

                                FirebaseAuth.getInstance().getCurrentUser().updateProfile(profileUpdates)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {

                                                if (task.isSuccessful()) {

                                                    Toast.makeText(SignUpActivity.this, "User " +
                                                            edtUsername.getText().toString() + " updated",
                                                            Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });

                                transitionToMainActivity();
                            } else {

                                Toast.makeText(SignUpActivity.this, "Signing up failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }


    private void transitionToMainActivity(){

        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
        startActivity(intent);
    }


    public void rootLayoutTapped(View view){

        try{

            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }catch (Exception e){

            e.printStackTrace();
        }
    }
}
