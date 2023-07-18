package com.example.space_ucas_ti_asmasyam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.space_ucas_ti_asmasyam.databinding.ActivityRoomBinding;
import com.example.space_ucas_ti_asmasyam.databinding.ActivityTrainingLabBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class TrainingLabActivity extends AppCompatActivity {

    ActivityTrainingLabBinding binding ;


    FirebaseFirestore firestore ;

    roomAdapter adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTrainingLabBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        firestore = FirebaseFirestore.getInstance();


        adapter = new  roomAdapter( new ArrayList<>(), getApplicationContext());
        binding.recyclerViewRoom.setAdapter(adapter);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(TrainingLabActivity.this, RecyclerView.VERTICAL,
                false);
        binding.recyclerViewRoom.setLayoutManager(lm);


        firestore.collection("Room").whereEqualTo("type", "Training Lab")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {


                            List<room_class> list = new ArrayList<>();

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("add", "onCreate: ");

                                room_class newsClass = document.toObject(room_class.class);

                                list.add(newsClass);
                                Toast.makeText(TrainingLabActivity.this, task.getResult().toString(), Toast.LENGTH_SHORT).show();

                            }
                            adapter.setData(list);

                        } else {
                            Toast.makeText(TrainingLabActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }
}