package com.example.workers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class SignUp extends AppCompatActivity {

    private EditText edtEmailSignUp, edtUsernameSignUp, edtPasswordSignUp;
    private Spinner categorySelectorSpinner;
    private Button signUpBtn;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference DBRoot;
    private String userType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edtEmailSignUp = findViewById(R.id.edtEmailSignUp);
        edtUsernameSignUp = findViewById(R.id.edtUsernameSignUp);
        edtPasswordSignUp = findViewById(R.id.edtPasswordSignUp);
        categorySelectorSpinner = findViewById(R.id.categorySpinner);
        signUpBtn = findViewById(R.id.btnSignUp);

        firebaseAuth = FirebaseAuth.getInstance();
        DBRoot = FirebaseDatabase.getInstance().getReference();

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();

            }
        });
    }

    private void signup() {
        if (!edtEmailSignUp.getText().toString().isEmpty()) {
            if (!edtUsernameSignUp.getText().toString().isEmpty()) {
                if (!edtPasswordSignUp.getText().toString().isEmpty()) {
                    firebaseAuth.createUserWithEmailAndPassword(edtEmailSignUp.getText().toString(), edtPasswordSignUp.getText().toString())
                            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Create a hash map to put details to the database
                                        HashMap<String, String> userDetails = new HashMap<>();
                                        userDetails.put("userName", edtUsernameSignUp.getText().toString());
                                        userDetails.put("userType", String.valueOf(categorySelectorSpinner.getSelectedItem()));
                                        userDetails.put("createdDate", new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date()));
                                        userDetails.put("createdTime", new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date()));

                                        userType = categorySelectorSpinner.getSelectedItem().toString();

                                        // if the app user is public
                                        if (userType.equals("Public")) {
                                            // update the database at
                                            // https://workers-facbc.firebaseio.com/app_users_public/public_details/UID_public
                                            DBRoot.child("app_users_public")
                                                    .child(task.getResult().getUser().getUid())
                                                    .child("public_details")
                                                    .push().setValue(userDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        Toast.makeText(SignUp.this, "Registered as a app user", Toast.LENGTH_SHORT).show();
                                                        // move to user page
                                                    }
                                                }
                                            });
                                        }
                                        // of the app user is a worker
                                        else {
                                            // Add extra fields
                                            userDetails.put("work_state", "Free");
                                            userDetails.put("booking_state", "Free");

                                            // update the date to database
                                            // https://workers-facbc.firebaseio.com/app_users_workers/worker_details/UID_workers
                                            DBRoot.child("app_users_workers")
                                                    .child("worker_details")
                                                    .child(task.getResult().getUser().getUid())
                                                    .push().setValue(userDetails);

                                            // Add the worker to appropriate work group
                                            // https://workers-facbc.firebaseio.com/app_users_workers/worker_group/
                                            switch (userType) {
                                                case "Electrician":
                                                    DBRoot.child("app_users_workers")
                                                            .child("electrician")
                                                            .child(task.getResult().getUser().getUid()).setValue(edtUsernameSignUp.getText().toString());
                                                    Toast.makeText(SignUp.this, "You have been mapped as an " + userType.toLowerCase(),
                                                            Toast.LENGTH_SHORT).show();
                                                    break;
                                                case "Plumber":
                                                    DBRoot.child("app_users_workers")
                                                            .child("plumber")
                                                            .child(task.getResult().getUser().getUid()).setValue(edtUsernameSignUp.getText().toString());
                                                    Toast.makeText(SignUp.this, "You have been mapped as an " + userType.toLowerCase(),
                                                            Toast.LENGTH_SHORT).show();
                                                    break;
                                                case "Painter":
                                                    DBRoot.child("app_users_workers")
                                                            .child("painter")
                                                            .child(task.getResult().getUser().getUid()).setValue(edtUsernameSignUp.getText().toString());
                                                    Toast.makeText(SignUp.this, "You have been mapped as an " + userType.toLowerCase(),
                                                            Toast.LENGTH_SHORT).show();
                                                    break;
                                                case "Carpenter":
                                                    DBRoot.child("app_users_workers")
                                                            .child("carpenter")
                                                            .child(task.getResult().getUser().getUid()).setValue(edtUsernameSignUp.getText().toString());
                                                    Toast.makeText(SignUp.this, "You have been mapped as an " + userType.toLowerCase(),
                                                            Toast.LENGTH_SHORT).show();
                                                    break;
                                                case "Cook":
                                                    DBRoot.child("app_users_workers")
                                                            .child("cook")
                                                            .child(task.getResult().getUser().getUid()).setValue(edtUsernameSignUp.getText().toString());
                                                    Toast.makeText(SignUp.this, "You have been mapped as an " + userType.toLowerCase(),
                                                            Toast.LENGTH_SHORT).show();
                                                    break;
                                                case "Laundry":
                                                    DBRoot.child("app_users_workers")
                                                            .child("laundry")
                                                            .child(task.getResult().getUser().getUid()).setValue(edtUsernameSignUp.getText().toString());
                                                    Toast.makeText(SignUp.this, "You have been mapped as an " + userType.toLowerCase(),
                                                            Toast.LENGTH_SHORT).show();
                                                    break;
                                                case "Computer Service":
                                                    DBRoot.child("app_users_workers")
                                                            .child("computer_service")
                                                            .child(task.getResult().getUser().getUid()).setValue(edtUsernameSignUp.getText().toString());
                                                    Toast.makeText(SignUp.this, "You have been mapped as an " + userType.toLowerCase(),
                                                            Toast.LENGTH_SHORT).show();
                                                    break;

                                            }


                                        }

                                    } else {
                                        Toast.makeText(SignUp.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                } else {
                    Toast.makeText(SignUp.this, "Password required", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(SignUp.this, "User name required", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(SignUp.this, "E-mail id required", Toast.LENGTH_LONG).show();
        }

    }
//                Log.i("TAG", "onClick: "+ String.valueOf(categorySelectorSpinner.getSelectedItem()) );

}
