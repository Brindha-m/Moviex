package com.example.moviex;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviex.adapters.FlightAdapter;
import com.example.moviex.adapters.interfaces.IUserAdapterCommunicator;
import com.example.moviex.retrofit.model.SearchItem;
import com.example.moviex.retrofit.modelflight.AirlineItem;
import com.example.moviex.retrofit.modelflight.DataItem;
import com.example.moviex.retrofit.modelflight.FlightResponse;
import com.example.moviex.retrofit.modelflight.PassengerDto;
import com.example.moviex.retrofit.network.IPostApi;
import com.example.moviex.retrofit.networkHandler.RetroFitBuilderFlight;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FlightMainActivity extends AppCompatActivity implements IUserAdapterCommunicator {
    List<PassengerDto> searchItemArrayItem = new ArrayList<>();
    List<DataItem> Items = new ArrayList<>();
    List<AirlineItem> airlineItem =new ArrayList<>();

    FlightAdapter userViewAdapter;
    int page=1,limit=20;
    RecyclerView recyclerView;
    ProgressBar progressBar;

    CardView cardview;
    //public static String searchString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_main);
        findViewById(R.id.movie_redirect).setOnClickListener(view -> {
            Intent intent = new Intent(FlightMainActivity.this,SplashActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });


        recyclerView = findViewById(R.id.rc_flight_main_page);
        progressBar = findViewById(R.id.rendering_progress_bar);
//        cardview = findViewById(R.id.card);

        initApiHomeSearch(page,limit);

    }
    public void initApiHomeSearch(int page,int limit) {

        Retrofit retrofit = RetroFitBuilderFlight.getInstance();
        IPostApi iPostApi = retrofit.create(IPostApi.class);
        Call<FlightResponse> responses = iPostApi.getFlightResult(page,limit);
        //Call<FlightResponse> responses = iPostApi.getTenFlightDetails();
        responses.enqueue(new Callback<FlightResponse>() {
            @Override
            public void onResponse(Call<FlightResponse> call, Response<FlightResponse> response) {

                // for(NarutoFields narutoFields:response.body()) {
              //  boolean noList = false;
                // allFlight = (List<FlightResponse>) response.body();
                Items= response.body().getData();
                Log.d("SAMPLE_TEST", response.body().toString());


                    for(int index=0;index<Items.size();index++)
                    {
                        PassengerDto passengerDto=new PassengerDto();
                        airlineItem = Items.get(index).getAirline();
                        for(int airlineIndex=0;airlineIndex<airlineItem.size();airlineIndex++)
                        {

                            passengerDto.setFlightName(airlineItem.get(airlineIndex).getName());
                            passengerDto.setCountry(airlineItem.get(airlineIndex).getCountry());
                            passengerDto.setLogo(airlineItem.get(airlineIndex).getLogo());
                            passengerDto.setEstablished(airlineItem.get(airlineIndex).getEstablished());
                        }
                        passengerDto.setName(Items.get(index).getName());
                        passengerDto.setTrips(Items.get(index).getTrips());
                        passengerDto.setId(Items.get(index).getId());
                        searchItemArrayItem.add(passengerDto);
                    }
                    RecyclerView recyclerView = findViewById(R.id.rc_flight_main_page);
                    userViewAdapter = new FlightAdapter(searchItemArrayItem, FlightMainActivity.this);
                    recyclerView.setLayoutManager(new LinearLayoutManager(FlightMainActivity.this, LinearLayoutManager.VERTICAL, false));

                    recyclerView.scrollToPosition(searchItemArrayItem.size()- limit);
                    recyclerView.setAdapter(userViewAdapter);



            }


            @Override
            public void onFailure(Call<FlightResponse> call, Throwable t) {
                Toast.makeText(FlightMainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("SAMPLE_LIST", t.getMessage());
            }


        });
    }


    @Override
    public void OnUserClick(SearchItem searchIndex, int position) {

    }

    @Override
    public void OnUserFlightClick(PassengerDto airlineItem, int position) {
        Intent intent = new Intent(FlightMainActivity.this,FlightDetailActivity.class);
        intent.putExtra("LOGO",airlineItem.getLogo());
        intent.putExtra("FLIGHT",airlineItem.getFlightName());
        intent.putExtra("NAME",airlineItem.getName());
        intent.putExtra("TRIPS",String.valueOf(airlineItem.getTrips()));
        intent.putExtra("ID",airlineItem.getId());
        intent.putExtra("COUNTRY",airlineItem.getCountry());
        intent.putExtra("SLOGAN",airlineItem.getSlogan());
        intent.putExtra("HEADQUARTERS",airlineItem.getHeadQuaters());
        intent.putExtra("WEBSITE",""+airlineItem.getWebsite());
        intent.putExtra("ESTABLISHED",airlineItem.getEstablished());
        startActivity(intent);
    }

    @Override
    public void initApiFlightCall() {
        page++;
        initApiHomeSearch(page,limit);
    }
}