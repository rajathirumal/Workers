package com.example.workers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

public class SignUp extends AppCompatActivity {

    private Spinner categorySelectorSpinner;
    private Button signUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        categorySelectorSpinner = findViewById(R.id.categorySpinner);
        signUpBtn = findViewById(R.id.btnSignUp);


        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("TAG", "onClick: "+ String.valueOf(categorySelectorSpinner.getSelectedItem()) );
            }
        });
    }



}
