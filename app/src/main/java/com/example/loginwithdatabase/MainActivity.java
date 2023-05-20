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

public class MainActivity extends AppCompatActivity {

    EditText username,password,re_password;
    TextView loginTv;
    Button register;



    String uname,pass,repass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.e_uname);
        password = findViewById(R.id.e_pass);
        re_password = findViewById(R.id.e_repass);
        loginTv = findViewById(R.id.loginClick);

        register = findViewById(R.id.btnRegister);
        DbHelper helper =new DbHelper(getApplicationContext());


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uname = username.getText().toString();
                pass = password.getText().toString();
                repass = re_password.getText().toString();
                if (TextUtils.isEmpty(uname) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(repass)){
                    Toast.makeText(MainActivity.this, "Enter All fields..", Toast.LENGTH_SHORT).show();
                }else {
                    if (pass.equals(repass)){

                        boolean check = helper.checkUser(uname);
                        if (check == false){
                            boolean result = helper.signup(uname,pass);
                            if (result == true){
                                Toast.makeText(MainActivity.this, "Registration Success..", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                            }else {
                                Toast.makeText(MainActivity.this, "Registration Failed..", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Toast.makeText(MainActivity.this, "User already exists..", Toast.LENGTH_SHORT).show();
                        }


                    }
                    else {
                        Toast.makeText(MainActivity.this, "Password does not match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        loginTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });



    }
}