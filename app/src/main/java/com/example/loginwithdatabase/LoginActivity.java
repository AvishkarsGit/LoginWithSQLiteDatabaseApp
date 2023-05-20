package com.example.loginwithdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {


    EditText username,password;

    Button login;
    String uname,pass;

    TextView signupTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.e_uname);
        password = findViewById(R.id.e_pass);

        login = findViewById(R.id.btnLogin);

        signupTv = findViewById(R.id.signupClick);


        DbHelper helper =new DbHelper(getApplicationContext());

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uname = username.getText().toString();
                pass = password.getText().toString();
                if (TextUtils.isEmpty(uname) || TextUtils.isEmpty(pass)){
                    Toast.makeText(LoginActivity.this, "Enter All fields..", Toast.LENGTH_SHORT).show();
                }else {
                    boolean result = helper.signIn(uname,pass);
                    if (result==true){
                        Toast.makeText(LoginActivity.this, "Login Succeed..", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "Invalid credentials.!!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        signupTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
    }
}