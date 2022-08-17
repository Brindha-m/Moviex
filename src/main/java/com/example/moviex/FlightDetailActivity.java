package com.example.moviex;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.moviex.adapters.interfaces.IUserAdapterCommunicator;
import com.example.moviex.retrofit.model.SearchItem;
import com.example.moviex.retrofit.modelflight.PassengerDto;

public class FlightDetailActivity extends AppCompatActivity implements IUserAdapterCommunicator {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_flight);
        ImageView logo = findViewById(R.id.iv_detailflight_logo);
        Glide.with(this).load(getIntent().getStringExtra("LOGO")).placeholder(R.drawable.image).into(logo);
        TextView FlightName = findViewById(R.id.tv_detailflight_name);
        FlightName.setText(getIntent().getStringExtra("FLIGHT"));
        TextView Name = findViewById(R.id.tv_detailflight_Name);
        Name.setText(getIntent().getStringExtra("NAME"));
        TextView trips = findViewById(R.id.tv_detailflight_trip);
        trips.setText(getIntent().getStringExtra("TRIPS"));
        TextView id = findViewById(R.id.tv_detailflight_id);
        id.setText("ID: "+getIntent().getStringExtra("ID"));
        TextView country = findViewById(R.id.tv_detailflight_country);
        country.setText(getIntent().getStringExtra("COUNTRY"));
        TextView slogan = findViewById(R.id.tv_detailflight_slogan);
        slogan.setText(getIntent().getStringExtra("SLOGAN"));
        TextView headQuaters = findViewById(R.id.tv_detailflight_headquarters);
        headQuaters.setText(getIntent().getStringExtra("HEADQUARTERS"));
        TextView website = findViewById(R.id.tv_detailflight_website);
        website.setText(getIntent().getStringExtra("WEBSITE"));
        TextView established = findViewById(R.id.tv_detailflight_established);
        established.setText(getIntent().getStringExtra("ESTABLISHED"));

    }

    @Override
    public void OnUserClick(SearchItem searchIndex, int position) {

    }

    @Override
    public void OnUserFlightClick(PassengerDto airlineItem, int position) {

    }

    @Override
    public void initApiFlightCall() {

    }
}