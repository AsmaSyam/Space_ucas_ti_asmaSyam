package com.example.space_ucas_ti_asmasyam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.space_ucas_ti_asmasyam.databinding.ActivityBookingConfirmationBinding;
import com.google.firebase.firestore.FirebaseFirestore;

public class BookingConfirmationActivity extends AppCompatActivity {

    ActivityBookingConfirmationBinding binding ;
    FirebaseFirestore firestore ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBookingConfirmationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        firestore = FirebaseFirestore.getInstance();

        Intent intent = getIntent();
        String date = intent.getStringExtra("date");
        String startTime = intent.getStringExtra("startTime");
        String endTime = intent.getStringExtra("endTime");
        String roomNameId = intent.getStringExtra("roomNameId");

        binding.dateText.setText(date);
        binding.startTime.setText(startTime);
        binding.endTime.setText(endTime);
        binding.roomNameId.setText(roomNameId);
        binding.buttonCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("add", "");

                Bookable_class bookableClass = new Bookable_class("1" , "34989382"  , "asma" , "syam" , "asma@gmail.com" , "39284793" , "1:00" ,
                        "2:00" , "1" , "" ,"" , "" , "5" , "" , ""
                        , "" , "" , "" , "" ,""  );

                firestore.collection("Booking").document("booking1").set(bookableClass);

            }
        });


    }
}