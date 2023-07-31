package com.example.space_ucas_ti_asmasyam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.space_ucas_ti_asmasyam.databinding.ActivityChooseLoginOrRegBinding;

public class ChooseLoginOrRegActivity extends AppCompatActivity {

    ActivityChooseLoginOrRegBinding binding ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChooseLoginOrRegBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext() , LoginActivity.class));
            }
        });

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext() , RegisterActivity.class));
            }
        });
    }
}