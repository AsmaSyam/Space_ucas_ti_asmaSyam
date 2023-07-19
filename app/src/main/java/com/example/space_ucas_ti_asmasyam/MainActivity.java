package com.example.space_ucas_ti_asmasyam;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.space_ucas_ti_asmasyam.databinding.ActivityMainBinding;

import com.google.firebase.firestore.FirebaseFirestore;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ActivityMainBinding binding ;
    FirebaseFirestore firestore ;

    String type ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        firestore = FirebaseFirestore.getInstance();




//        binding.buttonFindSpace.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Log.d("add", "");
//
//                room_class roomClass = new room_class("Trainning Room" , "" , "24" ,"" ,
//                        "" , "" , "" , "10.00" , "" , "" , "" ,
//                        "" , "" , "" ,"" , "" , " 6th Floor" , "Training Lab");
//
//                firestore.collection("Room").document("Training Lab2").set(roomClass);
//
//            }
//        });


        String[] filter = getResources().getStringArray(R.array.Select_space_type);
        ArrayAdapter adapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item , filter);
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);

        binding.filterSpinner.setAdapter(adapter);

//        binding.filterSpinner.setOnItemClickListener((AdapterView.OnItemClickListener) this);

        binding.datePikerId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar now = Calendar.getInstance();

                com.wdullaer.materialdatetimepicker.date.DatePickerDialog dpd = com.wdullaer.materialdatetimepicker.date.DatePickerDialog.newInstance(
                        new com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {

                                binding.textSelect.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                              /*  int year1 = Calendar.YEAR;
                                if (year <= year1){
                                    binding.dateOfBirth.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
                                }else if (year > year1){
                                    Toast.makeText(ProfileActivity.this, "Can not choose it", Toast.LENGTH_SHORT).show();
                                }*/
                                // int day = Calendar.DAY_OF_MONTH;
                                // int month = Calendar.MONTH;


                            }
                        },
                        now.get(Calendar.YEAR), // Initial year selection
                        now.get(Calendar.MONTH), // Initial month selection
                        now.get(Calendar.DAY_OF_MONTH) // Inital day selection

                );
                dpd.show(getSupportFragmentManager(), "Datepickerdialog");
            }
        });



        binding.buttonFindSpace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                if (type.equals("Meeting Room")){
//                    startActivity(new Intent(MainActivity.this , MeetingRoomActivity.class));
//                }

                startActivity(new Intent(MainActivity.this , MeetingRoomActivity.class));

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

         type = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(this, type, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}