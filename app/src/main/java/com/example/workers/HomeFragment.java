package com.example.workers;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    private CardView electricianCard, plumberCard, painterCard, carpenterCard, cookCard, laundryCard, tailorCard, computerServiceCard;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home_fragment, container, false);


        electricianCard = v.findViewById(R.id.electricianCard);
        plumberCard = v.findViewById(R.id.plumberCard);
        painterCard = v.findViewById(R.id.painterCard);
        carpenterCard = v.findViewById(R.id.carpenterCard);
        cookCard = v.findViewById(R.id.cookCard);
        laundryCard = v.findViewById(R.id.laundryCard);
        tailorCard = v.findViewById(R.id.tailorCard);
        computerServiceCard = v.findViewById(R.id.computerServiceCard);


        electricianCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("TAG", "electricianCard");
                showAll("electrician");
            }
        });
        plumberCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("TAG", "plumberCard");
                showAll("plumber");
            }
        });
        painterCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("TAG", "painterCard");
                showAll("painter");
            }
        });
        carpenterCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("TAG", "carpenterCard");
                showAll("carpenter");

            }
        });
        cookCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("TAG", "cookCard");
                showAll("cook");
            }
        });
        laundryCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("TAG", "laundryCard");
                showAll("laundry");
            }
        });
        tailorCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("TAG", "tailorCard");
                showAll("tailor");
            }
        });
        computerServiceCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("TAG", "computerServiceCard");
                showAll("computerService");
            }

        });

        return v;
    }

    private void showAll(String showWorkerOfType) {
        Intent intent = new Intent(getContext(), PublicViewTheWorker.class);
        intent.putExtra("workerType",showWorkerOfType);
        startActivity(intent);
    }


}
