package com.example.space_ucas_ti_asmasyam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.space_ucas_ti_asmasyam.databinding.ActivityRoomBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class MeetingRoomActivity extends AppCompatActivity implements Listener{

    ActivityRoomBinding binding ;

    FirebaseFirestore firestore ;
    roomAdapter adapter ;

    room_class newsClass ;
    FirebaseStorage firebaseStorage ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        firestore = FirebaseFirestore.getInstance();

        firebaseStorage = FirebaseStorage.getInstance();

        binding.progressBar.setVisibility(View.VISIBLE);




        adapter = new  roomAdapter( new ArrayList<>(), getApplicationContext() , this);
        binding.recyclerViewRoom.setAdapter(adapter);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(MeetingRoomActivity.this, RecyclerView.VERTICAL,
                false);
        binding.recyclerViewRoom.setLayoutManager(lm);



        firestore.collection("Room").whereEqualTo("type", "Meeting Room")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {


                            List<room_class> list = new ArrayList<>();

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("add", "onCreate: ");

                                 newsClass = document.toObject(room_class.class);

                                list.add(newsClass);
                                Toast.makeText(MeetingRoomActivity.this, task.getResult().toString(), Toast.LENGTH_SHORT).show();
                                binding.progressBar.setVisibility(View.GONE);

                            }
                            adapter.setData(list);

                        } else {
                            Toast.makeText(MeetingRoomActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });



    }

    @Override
    public void IsClickDetails(int position, room_class roomClass) {

        newsClass = roomClass ;

        String name = roomClass.getName();
        String capacity = roomClass.getCapacity();
        String location = roomClass.getLocation();
        String minimum_term = roomClass.getMinimum_term();
        String type = roomClass.getType();

        Intent intent = new Intent(MeetingRoomActivity.this , DetailsActivity.class);
        intent.putExtra("name" , name);
        intent.putExtra("capacity" , capacity);
        intent.putExtra("location" , location);
        intent.putExtra("minimum_term" , minimum_term);
        intent.putExtra("type" , type);
        startActivity(intent);

    }
}