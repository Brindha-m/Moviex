
package com.example.moviex.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviex.R;
import com.example.moviex.adapters.interfaces.IUserAdapterCommunicator;
import com.example.moviex.retrofit.modelflight.PassengerDto;

import java.util.List;

public class FlightAdapter extends RecyclerView.Adapter<FlightAdapter.UserViewHolder> {
    private final List<PassengerDto> searchItemsList;
    //  DataItem searchItems;
    IUserAdapterCommunicator iUserAdapterCommunicator;

    private final int LOADER=1;
    private final int DISABLE=0;

    // private  List<AirlineItem> airlineItems;
    public FlightAdapter(List<PassengerDto> searchItemsList, IUserAdapterCommunicator iUserAdapterCommunicator)
    {
        this.searchItemsList = searchItemsList;
        this.iUserAdapterCommunicator = iUserAdapterCommunicator;
    }
    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        Log.d("SUBSTITLE","came here");
//// View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_userdata,parent,false);
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_flightmain,parent,false);
//        return new UserViewHolder(view);


        if (viewType == LOADER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loader, parent, false);
            return new UserViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_flightmain, parent, false);
            return new UserViewHolder(view);
        }
    }


    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        PassengerDto searchItems =searchItemsList.get(position);
        holder.passengerName.setText(searchItems.getName());
        holder.name.setText(searchItems.getFlightName());
        holder.trips.setText(searchItems.getId().replace(searchItems.getId().substring(0,15),"*****"));
        holder.country.setText(searchItems.getCountry());
        holder.established.setText(searchItems.getEstablished());
        Glide.with(holder.logo.getContext()).load(searchItems.getLogo()).placeholder(R.drawable.image).into(holder.logo);
        if(position==searchItemsList.size()-1)
        {
            iUserAdapterCommunicator.initApiFlightCall();
        }
//        holder.root.setOnClickListener(view -> {
//            iUserAdapterCommunicator.OnUserFlightClick(DataItem,position);
//        });
    }

    @Override
    public int getItemViewType(int position) {
        if (position == searchItemsList.size() )
            return LOADER;
        return DISABLE;
    }

    @Override
    public int getItemCount() {
        return searchItemsList.size();
    }
    public static class UserViewHolder extends RecyclerView.ViewHolder{
        private TextView passengerName;
        private TextView trips;
        private TextView country;
        private TextView established;
        private TextView name;
        private ImageView logo;
        private View root;
        public UserViewHolder(View view)
        {
            super(view);
            root = view;
            passengerName = view.findViewById(R.id.tv_flight_passengerName);
            logo = view.findViewById(R.id.iv_flight_logo);
            name = view.findViewById(R.id.tv_flight_name);
            trips = view.findViewById(R.id.tv_flight_trips);
            country = view.findViewById(R.id.tv_flight_country);
            established = view.findViewById((R.id.tv_flight_established));
        }
    }
}


