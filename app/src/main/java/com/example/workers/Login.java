package com.example.workers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class Login extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference DBRoot;
    private String currentUserType;
    private Button btnLogin;
    private EditText edtEmailLogin, edtPasswordLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        DBRoot = FirebaseDatabase.getInstance().getReference();

        edtEmailLogin = findViewById(R.id.edtEmailLogin);
        edtPasswordLogin = findViewById(R.id.edtPasswordLogin);

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {
        mAuth.signInWithEmailAndPassword(edtEmailLogin.getText().toString(), edtPasswordLogin.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                final FirebaseUser user = authResult.getUser();
                Log.i("TAG", user.getUid());
                DBRoot.child("all_users").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot d : dataSnapshot.getChildren()) {
                            if (d.getKey().equals(user.getUid())) {
                                currentUserType = Objects.requireNonNull(d.getValue()).toString();
                                Log.i("TAG", currentUserType);
                                if (currentUserType.equals("Public")) {
                                    // Move to public page.
                                    Log.i("TAG", "i am a Public");
                                } else {
                                    // Move to worker dash bord.
                                    Log.i("TAG", "im going to worker dash board");
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        final FirebaseUser currentUser = mAuth.getCurrentUser();


        if (currentUser != null) {
            DBRoot.child("all_users").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot d : dataSnapshot.getChildren()) {
                        if (d.getKey().equals(currentUser.getUid())) {
                            currentUserType = Objects.requireNonNull(d.getValue()).toString();
                            Log.i("TAG", currentUserType);
                            if (currentUserType.equals("Public")) {
                                // Move to public page.
                                Log.i("TAG", "i am a Public");
                            } else {
                                // Move to worker dash bord.
                                Log.i("TAG", "im going to worker dash board");
                            }
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        }
    }


    public void onRegisterClick(View v) {
        Intent moveToSignUp = new Intent(Login.this, SignUp.class);
        startActivity(moveToSignUp);
    }
}
