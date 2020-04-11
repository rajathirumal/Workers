package com.example.workers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }





    public void onRegisterClick(View v){
        Intent moveToSignUp = new Intent(Login.this,SignUp.class);
        startActivity(moveToSignUp);
    }
}
