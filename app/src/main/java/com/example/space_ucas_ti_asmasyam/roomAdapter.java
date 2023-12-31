package com.example.space_ucas_ti_asmasyam;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.GnssAntennaInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.space_ucas_ti_asmasyam.databinding.ItemSpaceBinding;

import java.util.List;

public class roomAdapter extends RecyclerView.Adapter<roomAdapter.RoomViewHolder> {

    List<room_class> list ;
    Context context ;

    Listener listener ;



    public roomAdapter(List<room_class> list  , Context context , Listener listener) {
        this.list = list;
        this.context = context ;
        this.listener = listener ;
    }


    public void setData(List<room_class> list){
        this.list = list ;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSpaceBinding binding = ItemSpaceBinding.inflate(LayoutInflater.from(parent.getContext()) , parent , false);

        return new RoomViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, int position) {

        int pos = position ;

        String name = list.get(pos).getName();
        holder.name.setText(name);

        String location = list.get(pos).getLocation();
        holder.location.setText(location);

        String type = list.get(pos).getType();
        holder.type.setText(type);

        String hourly = list.get(pos).getUnit_cost();
        holder.hourly.setText(hourly);

        String people = list.get(pos).getCapacity();
        holder.people.setText(people);

        String image = list.get(pos).getImage_bath();
        Glide.with(context)
                .load(image)
                .into(holder.imageView);


        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                listener.IsClickDetails(pos , list.get(pos));
            }
        });


    }

    @Override
    public int getItemCount() {
        if(list == null)
            return  0 ;
        return list.size();
    }

    public class RoomViewHolder extends RecyclerView.ViewHolder{

        TextView name ;
        TextView location ;
        TextView type ;
        TextView hourly ;
        TextView people ;
        TextView area ;

        ImageView imageView ;

        public RoomViewHolder(@NonNull ItemSpaceBinding binding) {
            super(binding.getRoot());

            name = binding.roomName ;
            location = binding.textLocation ;
            type = binding.textType ;
            hourly = binding.textHourly ;
            people = binding.textPeople ;
            area = binding.textArea ;
            imageView = binding.imageSpace;
        }
    }

}
