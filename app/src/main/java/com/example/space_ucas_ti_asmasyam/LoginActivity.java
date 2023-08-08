package com.example.space_ucas_ti_asmasyam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.space_ucas_ti_asmasyam.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding ;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        Log.e("TAG", "onCreate: dfdfdfdf");
        if (firebaseUser != null) {
            Log.e("TAG", "onCreate: " + firebaseUser.getUid());
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            Log.e("TAG", "onCreate: user not found");
        }

        binding.registerTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext() , RegisterActivity.class));
            }
        });


        binding.LoginBtn.setOnClickListener(view -> {
            binding. progressBar.setVisibility(View.VISIBLE);
            binding.LoginBtn.setEnabled(false);
            if(!(binding.emailEdt.getText().toString().isEmpty()
                    || binding.editTextTextPassword.getText().toString().isEmpty())) {
                String password = binding.editTextTextPassword.getText().toString();
                String email = binding.emailEdt.getText().toString();
                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            binding. progressBar.setVisibility(View.GONE);
                            binding.LoginBtn.setEnabled(true);
                            Log.d("TAG", task.getResult().toString());
                            Toast.makeText(LoginActivity.this, "Login is successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                        } else {
                            binding. progressBar.setVisibility(View.GONE);
                            binding.LoginBtn.setEnabled(true);
                            Log.d("TAG", task.getException().getMessage());
                            Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }else {
                Toast.makeText(this, "Enter all the data", Toast.LENGTH_SHORT).show();
                binding. progressBar.setVisibility(View.GONE);
                binding.LoginBtn.setEnabled(true);
            }
        });

    }
}