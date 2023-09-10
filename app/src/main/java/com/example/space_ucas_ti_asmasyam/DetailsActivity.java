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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class DetailsActivity extends AppCompatActivity {


    ActivityDetailsBinding binding ;


    int t1Hour, t1Minute ;
    Calendar calendar ;
    String date ;
    String startTime;
    String endTime;
    Date getTime ;
    Date getEndTime ;
    String duration ;
    String booking_date ;
    Date booking_start_time ;
    String roomId;
    Date booking_end_time  ;
    String booking_start_time_list ;
    String booking_end_time_list ;
    String roomNameId ;
    String roomNameId2 ;
    String people ;
    FirebaseFirestore firestore ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        calendar = Calendar.getInstance();
        firestore = FirebaseFirestore.getInstance();


        Intent intent = getIntent();
        roomNameId2 = intent.getStringExtra("roomId");
        String name = intent.getStringExtra("name");
        String capacity = intent.getStringExtra("capacity");
        String location = intent.getStringExtra("location");
        String minimum_term = intent.getStringExtra("minimum_term");
        String type = intent.getStringExtra("type");


        binding.nameRoomId.setText(name);
        binding.location2Id.setText(location);

        roomNameId = binding.nameRoomId.getText().toString();
        people = binding.textPeople.getText().toString();

        // disable dates before today
        Calendar today = Calendar.getInstance();
        long now = today.getTimeInMillis();
        binding.calenderId.setMinDate(now);

        binding.calenderId.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                getDate();

//                Toast.makeText(DetailsActivity.this, day + "/" + month + "/" + year, Toast.LENGTH_SHORT).show();
                date = day + "/" + month + "/" + year;

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

        //  بدي أحط في هاي الواجهة شرط على document
        firestore.collection("Booking").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            ArrayList<Bookable_class> list = new ArrayList<>();


                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Bookable_class bookableClass1 = document.toObject(Bookable_class.class);

//                                    booking_date = bookableClass1.getDate();
//                                    booking_start_time_list = bookableClass1.getStart_time();
//                                    booking_end_time_list = bookableClass1.getEnd_time();
//                                    roomId = bookableClass1.getRoom_id();

                                list.add(bookableClass1);

                                for (int i=0; i<list.size(); i++) {
                                    System.out.println(list.get(i));
                                    booking_date = list.get(i).getDate();
                                    booking_start_time_list = list.get(i).getStart_time();
                                    booking_end_time_list = list.get(i).getEnd_time();
                                    roomId = list.get(i).getRoom_id();
                                    Log.d("sizeList", "onComplete: " + list.size());
                                    Log.d("getDate", "onComplete: " + list.get(i).getDate());
                                    Log.d("getStart_time", "onComplete: " + list.get(i).getStart_time());

                                }

                                String pattern = "HH:mm";
                                SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                                try {
                                    booking_start_time = sdf.parse(booking_start_time_list);
                                } catch (ParseException e) {
                                    throw new RuntimeException(e);
                                }

                                try {
                                    booking_end_time = sdf.parse(booking_end_time_list);
                                } catch (ParseException e) {
                                    throw new RuntimeException(e);
                                }
                            }

                        } else {
                            Log.d("TAG", "onComplete: " + task.getException().getMessage());
                        }

                    }

                });


        binding.buttonCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (date != null && startTime != null && endTime != null) {

                    // هنا في اليوم نفسه بمشي صح بأنه ما بيختار نفس الوقت
                    //ولكن لما اختار يوم تاني وبنفس الوقت بقلي محجوز
                    // .فالي بدي اعمله اني اعدل على جزئية الفحص الخاصة بالوقت
                    Log.d("BookingTestingStep2", "onClick: "+roomNameId2 + ":"+ roomId);
                    if (date.equals(booking_date) && getTime.equals(booking_start_time) && getEndTime.equals(booking_end_time) && roomNameId2.equals(roomId)) {
                        Toast.makeText(DetailsActivity.this, "The room is booked up in this time", Toast.LENGTH_SHORT).show();
                        Log.d("BookingTesting", "onClick: 1");
                    }
                    else if ( (! date.equals(booking_date)) && roomNameId2.equals(roomId)) {

                        Intent intent1 = new Intent(getApplicationContext(), BookingConfirmationActivity.class);
                        intent1.putExtra("date", date);
                        intent1.putExtra("startTime", startTime);
                        intent1.putExtra("endTime", endTime);
                        intent1.putExtra("roomNameId", roomNameId);
                        intent1.putExtra("people", people);
                        intent1.putExtra("duration", duration);
                        intent1.putExtra("name", name);
                        startActivity(intent1);

                    }// hello asma
                    else if (date.equals(booking_date) && booking_start_time.after(getTime)
                            && booking_end_time.before(getEndTime) && roomNameId2.equals(roomId)) {
                        Toast.makeText(DetailsActivity.this, "The room is booked up in this time", Toast.LENGTH_SHORT).show();
                        Log.d("BookingTesting", "onClick: 2");

                    }
                    else if (date.equals(booking_date) && booking_start_time.after(getTime)
                            && booking_end_time.equals(getEndTime) && roomNameId2.equals(roomId)) {
                        Toast.makeText(DetailsActivity.this, "The room is booked up in this time", Toast.LENGTH_SHORT).show();
                        Log.d("BookingTesting", "onClick: 3");

                    }
                    else if (date.equals(booking_date) && booking_start_time.before(getTime)
                            && booking_end_time.after(getEndTime) && roomNameId2.equals(roomId)) {
                        Toast.makeText(DetailsActivity.this, "The room is booked up in this time", Toast.LENGTH_SHORT).show();
                        Log.d("BookingTesting", "onClick: 4");

                    }
                    else if (date.equals(booking_date) && booking_start_time.after(getTime)
                            && booking_end_time.after(getEndTime) && getEndTime.after(booking_start_time) && roomNameId2.equals(roomId)) {
                        Toast.makeText(DetailsActivity.this, "The room is booked up in this time", Toast.LENGTH_SHORT).show();
                        Log.d("BookingTesting", "onClick: 5");

                    }
                    else if (date.equals(booking_date) && booking_start_time.before(getTime)
                            && booking_end_time.before(getEndTime) && getTime.before(booking_end_time) && roomNameId2.equals(roomId)) {
                        Toast.makeText(DetailsActivity.this, "The room is booked up in this time", Toast.LENGTH_SHORT).show();
                        Log.d("BookingTesting", "onClick: 6");

                    }
                    else if (date.equals(booking_date) && booking_start_time.before(getTime)
                            && booking_end_time.equals(getEndTime) && roomNameId2.equals(roomId)) {
                        Toast.makeText(DetailsActivity.this, "The room is booked up in this time", Toast.LENGTH_SHORT).show();
                        Log.d("BookingTesting", "onClick: 7");

                    }
                    else if (date.equals(booking_date) && booking_start_time.equals(getTime)
                            && booking_end_time.after(getEndTime) && roomNameId2.equals(roomId)) {
                        Toast.makeText(DetailsActivity.this, "The room is booked up in this time", Toast.LENGTH_SHORT).show();
                        Log.d("BookingTesting", "onClick: 8");

                    }
                    else if (date.equals(booking_date) && booking_start_time.equals(getTime)
                            && booking_end_time.before(getEndTime) && roomNameId2.equals(roomId)) {
                        Toast.makeText(DetailsActivity.this, "The room is booked up in this time", Toast.LENGTH_SHORT).show();
                        Log.d("BookingTesting", "onClick: 9");

                    }
                    else if (date.equals(booking_date) && getTime != booking_start_time && getEndTime != booking_end_time
                            && roomNameId2.equals(roomId)) {

                        Intent intent1 = new Intent(getApplicationContext(), BookingConfirmationActivity.class);
                        intent1.putExtra("date", date);
                        intent1.putExtra("startTime", startTime);
                        intent1.putExtra("endTime", endTime);
                        intent1.putExtra("roomNameId", roomNameId);
                        intent1.putExtra("people", people);
                        intent1.putExtra("duration", duration);
                        intent1.putExtra("name", name);
                        startActivity(intent1);
                    } else {

                        Intent intent1 = new Intent(getApplicationContext(), BookingConfirmationActivity.class);
                        intent1.putExtra("date", date);
                        intent1.putExtra("startTime", startTime);
                        intent1.putExtra("endTime", endTime);
                        intent1.putExtra("roomNameId", roomNameId);
                        intent1.putExtra("people", people);
                        intent1.putExtra("duration", duration);
                        intent1.putExtra("name", name);
                        startActivity(intent1);

                    }
                } else {
                    Toast.makeText(DetailsActivity.this, "select the date and time you want", Toast.LENGTH_SHORT).show();
                }


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
//                        Toast.makeText(DetailsActivity.this, DateFormat.format("hh:mm aa" , calendar), Toast.LENGTH_SHORT).show();
                          startTime = (String) DateFormat.format("hh:mm" , calendar);

                        String pattern = "hh:mm";
                        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                        try {
                            getTime = sdf.parse(DateFormat.format("hh:mm aa" , calendar).toString());
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }

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
//                        Toast.makeText(DetailsActivity.this, DateFormat.format("hh:mm aa" , calendar), Toast.LENGTH_SHORT).show();
                        endTime = (String) DateFormat.format("hh:mm aa" , calendar);

                        String pattern = "HH:mm";
                        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                        try {
                            getEndTime = sdf.parse(DateFormat.format("hh:mm aa" , calendar).toString());
                            duration = String.valueOf(getEndTime.getTime() - getTime.getTime());
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }

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