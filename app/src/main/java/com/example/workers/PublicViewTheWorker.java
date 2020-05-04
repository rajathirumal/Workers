package com.example.workers;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Layout;
import android.util.Log;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class PublicViewTheWorker extends AppCompatActivity {

    private DatabaseReference DBRoot;
    private LinearLayout layoutForPushingCards;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public_view_the_worker);

        DBRoot = FirebaseDatabase.getInstance().getReference();

        layoutForPushingCards = findViewById(R.id.linearLayoutThatHoldsAllCards);

        Intent intent = getIntent();
        String interestedWorker = intent.getStringExtra("workerType");
        Log.i("TAG", "PublicViewThwWorker " + interestedWorker);

        loadCardsOf(interestedWorker);

    }

    private void loadCardsOf(String interestedWorker) {
        Log.i("TAG", "PublicViewThwWorker : We are interested in " + interestedWorker);


        // Load all the interested type worker
        DBRoot.child("app_users_workers")
                .child(interestedWorker)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot d : dataSnapshot.getChildren()) {
                            Log.i("TAG", "onDataChange: added" + (String) d.getValue() + "  " + " " + d.getKey());
                            showWorkerCardsWith(d.getKey());
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }

    private void showWorkerCardsWith(final String UID) {
        Log.i("TAG", "showWorkerCardsWith: " + UID);
        DBRoot.child("app_users_workers")
                .child("worker_details")
                .child(UID)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            String workerName, bookingState, workState, stars, type, createDate, createTime;
                            workerName = (String) snapshot.child("user_name").getValue();
                            bookingState = (String) snapshot.child("booking_state").getValue();
                            workState = (String) snapshot.child("work_state").getValue();
                            stars = (String) snapshot.child("stars").getValue();
                            type = (String) snapshot.child("userType").getValue();
                            createDate = (String) snapshot.child("createdDate").getValue();
                            createTime = (String) snapshot.child("createdTime").getValue();
                            //String UID, String name, String bookingState, String work_state, String stars, String type, String createDate, String createTime
                            Log.i("TAG", "onDataChange: " + workerName);
                            createCardFor(new Worker(UID, workerName, bookingState,workState,stars,type,createDate,createTime));
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }

    private void createCardFor(Worker workerObj) {

        // TODO: Function pending :=> Try to uses getters to set text to the cards.
        CardView card = new CardView(getApplicationContext());

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        card.setLayoutParams(params);

        card.setRadius(9);
        card.setContentPadding(15, 15, 15, 15);
        card.setCardBackgroundColor(Color.parseColor("#FFC6D6C3"));
        card.setMaxCardElevation(15);
        card.setCardElevation(9);
        TextView tv = new TextView(getApplicationContext());
        tv.setLayoutParams(params);
        tv.setText("workers details ");
        tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 30);
        tv.setTextColor(Color.RED);

        // Put the TextView in CardView
        card.addView(tv);

        layoutForPushingCards.addView(card);
    }
}

