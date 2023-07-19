package com.example.space_ucas_ti_asmasyam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
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

    Calendar calendar ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        calendar = Calendar.getInstance();

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
                openDialog();
            }
        });



    }

    private void openDialog() {

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hours, int minutes) {

                binding.textStartTime.setText(String.valueOf(hours)+":"+String.valueOf(minutes));
            }
        }, 15, 00, true);
    }

    public void getDate(){

        long date = binding.calenderId.getDate();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy" , Locale.getDefault());
        calendar.setTimeInMillis(date);

        String selected_date = simpleDateFormat.format(calendar.getTime());
        //Toast.makeText(this, selected_date, Toast.LENGTH_SHORT).show();

    }


}