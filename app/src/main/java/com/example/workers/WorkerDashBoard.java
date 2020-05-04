package com.example.workers;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class WorkerDashBoard extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_dash_board);
        firebaseAuth = FirebaseAuth.getInstance();
    }


    // settings icon clicked
    public void onSettingsClickedByWorker(View view) {
        startActivity(new Intent(WorkerDashBoard.this, Worker_setting.class));
    }

    // logout icon clicked
    public void onLogOutClickedByWorker(View view) {
        logout();
    }

    @Override
    public void onBackPressed() {
        logout();
    }

    //Logout function
    private void logout() {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(WorkerDashBoard.this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(WorkerDashBoard.this);
        }
        builder.setTitle("Delete Entry")
                .setMessage("Are you sure you want to log-out ?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        firebaseAuth.signOut();
                        finish();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
