package com.example.space_ucas_ti_asmasyam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.space_ucas_ti_asmasyam.databinding.ActivityBookingConfirmationBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class BookingConfirmationActivity extends AppCompatActivity {

    ActivityBookingConfirmationBinding binding;
    FirebaseFirestore firestore;
    Bookable_class bookableClass1;
    room_class roomClass;

    ArrayList list;

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
        String duration = intent.getStringExtra("duration");
        String name = intent.getStringExtra("name");

        binding.dateText.setText(date);
        binding.startTime.setText(startTime);
        binding.endTime.setText(endTime);
        binding.roomNameId.setText(roomNameId);
        binding.duration.setText(duration);


//        int duration = Integer.parseInt(endTime) - Integer.parseInt(startTime);
//        binding.duration.setText(String.valueOf(duration));


        firestore.collection("Room").whereEqualTo("name", name)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                roomClass = document.toObject(room_class.class);
                                roomClass.setDocumentId(document.getId());
                                Log.d("documentId", "onComplete: " + document.getId());
                            }

                        } else {
                            Log.d("TAG", "onComplete: " + task.getException().getMessage());
                        }

                    }
                });

        binding.buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("add", "");

                String firstName = binding.editFirstName.getText().toString();
                String lastName = binding.editLastName.getText().toString();
                String email = binding.editEmailAddress.getText().toString();
                String phone = binding.editPhoneNumber.getText().toString();
                String duration = binding.duration.getText().toString();
                String date = binding.dateText.getText().toString();

                String notes = binding.editNotes.getText().toString();

                Bookable_class bookableClass = new Bookable_class(roomClass.getDocumentId(), firstName, lastName, email, phone, startTime,
                        endTime, date, duration, "", "", "", people, "", ""
                        , "booked up", "", "", "", "");

                firestore.collection("Booking").document().set(bookableClass);
//                firestore.collection("Booking").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//
//                            list = new ArrayList<>();
//
//
//                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                Bookable_class bookableClass1 = document.toObject(Bookable_class.class);
//                                if (bookableClass1.getRoom_id() .equals(roomClass.getDocumentId()) )
//                                    list.add(bookableClass1);
//                            }
//                            list.add(bookableClass);
//                            Map<String,Object> map = new HashMap<>();
//
//                            for(int i=0; i<list.size();i++){
//                                map.put(String.valueOf(i),list.get(i));
//                            }
//
//                        } else {
//                            Log.d("TAG", "onComplete: " + task.getException().getMessage());
//                        }
//                    }
//                });


//                    firestore.collection("Booking").whereEqualTo(roomNameId , roomClass.getDocumentId())
//                            .get()
//                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                                @Override
//                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
//
//                                    if (task.isSuccessful()) {
//
//                                        for (QueryDocumentSnapshot document : task.getResult()) {
//                                            bookableClass1 = document.toObject(Bookable_class.class);
//                                            bookableClass1.setDocumentId(document.getId());
//                                            Log.d("documentId", "onComplete: " + document.getId());
//                                        }
//
//                                    } else {
//                                        Log.d("error booking", "onComplete: " + task.getException().getMessage());
//                                    }
//                                }
//                            });
//
//
//
//
//                Status_class status_class = new Status_class(bookableClass1.getDocumentId() , "booked up" , notes);
//
//                firestore.collection("Status").document("status")
//                        .collection("myStatus").document(bookableClass1.getDocumentId()).set(status_class);


                Toast.makeText(BookingConfirmationActivity.this, "Confirm and book", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));


            }
        });


    }
}