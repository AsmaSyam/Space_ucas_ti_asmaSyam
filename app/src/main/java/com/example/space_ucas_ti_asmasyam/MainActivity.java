package com.example.space_ucas_ti_asmasyam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.space_ucas_ti_asmasyam.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding ;
    FirebaseFirestore firestore ;

    roomAdapter adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());




        String[] filter = getResources().getStringArray(R.array.Select_space_type);
        ArrayAdapter adapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item , filter);
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);

        binding.filterSpinner.setAdapter(adapter);


        binding.filterId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

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
    }
}