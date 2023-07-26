package com.example.space_ucas_ti_asmasyam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.space_ucas_ti_asmasyam.databinding.ActivityBookingConfirmationBinding;

public class BookingConfirmationActivity extends AppCompatActivity {

    ActivityBookingConfirmationBinding binding ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBookingConfirmationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



    }
}