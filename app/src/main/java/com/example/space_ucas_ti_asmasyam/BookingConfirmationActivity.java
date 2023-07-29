package com.example.space_ucas_ti_asmasyam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

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
        String people = intent.getStringExtra("people");

        binding.dateText.setText(date);
        binding.startTime.setText(startTime);
        binding.endTime.setText(endTime);
        binding.roomNameId.setText(roomNameId);


//        int duration = Integer.parseInt(endTime) - Integer.parseInt(startTime);
//        binding.duration.setText(String.valueOf(duration));

        binding.buttonCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("add", "");

                String firstName = binding.firstText.getText().toString();
                String lastName = binding.lastText.getText().toString();
                String email = binding.editEmailAddress.getText().toString();
                String phone = binding.editPhoneNumber.getText().toString();
                String duration = binding.duration.getText().toString();

                Bookable_class bookableClass = new Bookable_class(firstName , lastName , email , phone , startTime ,
                        endTime , duration , "" ,"" , "" , people , "" , ""
                        , "booked up" , "" , "" , "" ,""  );

                firestore.collection("Booking").document("booking1").set(bookableClass);

                Toast.makeText(BookingConfirmationActivity.this, "Confirm and book", Toast.LENGTH_SHORT).show();

            }
        });


    }
}