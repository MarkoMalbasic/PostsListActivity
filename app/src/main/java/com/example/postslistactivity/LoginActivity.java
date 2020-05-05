package com.example.postslistactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
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

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText edtUserEmail, edtUserPassword;
    private TextView txtPassword, txtSignupHere;
    private Button btnLogin;

    private FirebaseAuth mAuth;
    private Toolbar myToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        /*ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(Html.fromHtml("<font color='#101C2C'>"+"Avli By Tashas"+"</font>"));*/


        edtUserEmail = findViewById(R.id.edtUserEmail);
        edtUserPassword = findViewById(R.id.edtUserPassword);
        txtPassword = findViewById(R.id.txtPassword);
        txtSignupHere = findViewById(R.id.txtSignupHere);
        btnLogin = findViewById(R.id.btnLogin);

        mAuth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                logIn();
            }
        });

        txtSignupHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });

        if(mAuth.getCurrentUser() != null){

            transitionToMainActivity();
        }
    }


    private void logIn(){

        if(edtUserPassword.getText().toString().equals("") ||
                edtUserPassword.getText().toString().equals("")){

            Toast.makeText(this, "Email and Password are Required!", Toast.LENGTH_SHORT).show();

        }else {
            mAuth.signInWithEmailAndPassword(edtUserEmail.getText().toString(),
                    edtUserPassword.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {

                                Toast.makeText(LoginActivity.this, "Logging in is successful!",
                                        Toast.LENGTH_SHORT).show();
                                transitionToMainActivity();

                            } else {

                                Toast.makeText(LoginActivity.this, "Logging in is failed!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }


    private void transitionToMainActivity(){

        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
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
