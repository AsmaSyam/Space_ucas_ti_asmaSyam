package com.example.space_ucas_ti_asmasyam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.space_ucas_ti_asmasyam.databinding.ActivityRegisterBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding binding ;

    FirebaseAuth firebaseAuth;
    FirebaseFirestore firestore;
    FirebaseUser currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        binding.btnRegister.setOnClickListener(view -> {

            binding.progressBar.setVisibility(View.VISIBLE);
            binding.btnRegister.setEnabled(false);

            if (!(binding.editTextTextPassword.getText().toString().isEmpty() ||
                    binding.emailEdt.getText().toString().isEmpty())) {

                String email = binding.emailEdt.getText().toString();
                String password = binding.editTextTextPassword.getText().toString();

                binding.progressBar.setVisibility(View.VISIBLE);
                binding.btnRegister.setEnabled(false);

                firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {

                        binding.progressBar.setVisibility(View.GONE);
                        binding.btnRegister.setEnabled(true);

                        Log.d("Register", task.getResult().toString());
                        Toast.makeText(RegisterActivity.this, "Register is successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext() , MainActivity.class));

//                        String uid = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
//                        Log.e("TAG", "onClick: " + uid);


                    } else {
                        binding.progressBar.setVisibility(View.GONE);
                        binding.btnRegister.setEnabled(true);
                        Log.d("TAG", Objects.requireNonNull(task.getException()).getMessage());
                        Toast.makeText(RegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            } else {
                Toast.makeText(RegisterActivity.this, "Enter all the data", Toast.LENGTH_SHORT).show();
                binding.progressBar.setVisibility(View.GONE);
                binding.btnRegister.setEnabled(true);
            }

        });

    }
}