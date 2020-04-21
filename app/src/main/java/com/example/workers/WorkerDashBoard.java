package com.example.workers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class WorkerDashBoard extends AppCompatActivity {
    private Button logOutWorker;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_dash_board);

        logOutWorker = findViewById(R.id.btnLogOutWorker);
        firebaseAuth = FirebaseAuth.getInstance();


        logOutWorker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutWorker();
            }
        });
    }

    private void logoutWorker() {
        firebaseAuth.signOut();
        finish();
    }
}
