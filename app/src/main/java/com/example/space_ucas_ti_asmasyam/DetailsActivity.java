package com.example.space_ucas_ti_asmasyam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.space_ucas_ti_asmasyam.databinding.ActivityDetailsBinding;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class DetailsActivity extends AppCompatActivity {


    ActivityDetailsBinding binding ;

    int t1Hour, t1Minute ;

    Calendar calendar ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



//        binding.buttonCheck.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Log.d("add", "");
//
//                Bookable_class bookableClass = new Bookable_class("id" , "number" , "yes" ,"bookable_id" ,
//                        "parent_id" , "user_id" , "first_name" , "last_name" , "" , "" , "" ,
//                        "" , "" , "" ,"" , "" , " 6th Floor" , "Training Lab");
//
//                firestore.collection("Room").document("Training Lab2").set(roomClass);
//
//            }
//        });


        calendar = Calendar.getInstance();

        Intent intent = getIntent();
        String name  = intent.getStringExtra("name");
        String capacity  = intent.getStringExtra("capacity");
        String location  = intent.getStringExtra("location");
        String minimum_term  = intent.getStringExtra("minimum_term");
        String type  = intent.getStringExtra("type");


        binding.nameRoomId.setText(name);
        binding.location2Id.setText(location);


        binding.calenderId.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                getDate();

                Toast.makeText(DetailsActivity.this,  day + "/" + month + "/" + year, Toast.LENGTH_SHORT).show();


            }
        });


        binding.linearStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialogStartTime();
            }
        });

        binding.linearEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialogEndTime();
            }
        });


        binding.buttonCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext() , BookingConfirmationActivity.class));
            }
        });

    }

    private void openDialogStartTime() {

        TimePickerDialog timePickerDialog = new TimePickerDialog(
                DetailsActivity.this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {

                        //Initialize hour and minute :
                        t1Hour = hourOfDay ;
                        t1Minute = minute ;

                        //set hour and minute in calender :
                        calendar.set(0 , 0 , 0 , t1Hour , t1Minute);

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();

                        binding.textStartTime.setText(DateFormat.format("hh:mm aa" , calendar));
                        Toast.makeText(DetailsActivity.this, DateFormat.format("hh:mm aa" , calendar), Toast.LENGTH_SHORT).show();

                    }
                }, 12 , 0 , false
        );

        // timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.));

        timePickerDialog.updateTime(t1Hour , t1Minute);
        timePickerDialog.show();
    }

    private void openDialogEndTime() {

        TimePickerDialog timePickerDialog = new TimePickerDialog(
                DetailsActivity.this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {

                        //Initialize hour and minute :
                        t1Hour = hourOfDay ;
                        t1Minute = minute ;

                        //set hour and minute in calender :
                        calendar.set(0 , 0 , 0 , t1Hour , t1Minute);

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();

                        binding.textEndTime.setText(DateFormat.format("hh:mm aa" , calendar));
                        Toast.makeText(DetailsActivity.this, DateFormat.format("hh:mm aa" , calendar), Toast.LENGTH_SHORT).show();

                    }
                }, 12 , 0 , false
        );

        // timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.));

        timePickerDialog.updateTime(t1Hour , t1Minute);
        timePickerDialog.show();
    }



    public void getDate(){

        long date = binding.calenderId.getDate();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy" , Locale.getDefault());
        calendar.setTimeInMillis(date);

        String selected_date = simpleDateFormat.format(calendar.getTime());
        //Toast.makeText(this, selected_date, Toast.LENGTH_SHORT).show();

    }


}