package com.example.moviex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moviex.adapters.BenTenRecyclerAdapter;
import com.example.moviex.adapters.LionKingRecyclerAdapter;
import com.example.moviex.adapters.UserRecyclerAdapter;
import com.example.moviex.adapters.interfaces.IUserAdapterCommunicator;
import com.example.moviex.retrofit.model.NarutoFields;
import com.example.moviex.retrofit.model.SearchItem;
import com.example.moviex.retrofit.modelflight.PassengerDto;
import com.example.moviex.retrofit.network.IPostApi;
import com.example.moviex.retrofit.networkHandler.RetroFitBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements IUserAdapterCommunicator {
    List<SearchItem> searchItemArrayList = new ArrayList<>();
    //public static String searchString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initApiDora();
        initApiPotter();
        initApiBenTen();
        initApiToyStory();
        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(),Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        ImageButton imageButton =  findViewById(R.id.main_search_parameter);
        EditText editText = findViewById(R.id.search_parameter);


        findViewById(R.id.home_clear).setOnClickListener(view -> {
            editText.setText("");
        });
        imageButton.setOnClickListener(view -> {

            editor.putString("SEARCH",editText.getText().toString());
            editor.apply();
            startActivity(new Intent(MainActivity.this,SearchActivity.class));
            //(new Intent(MainActivity.this,SearchActivity.class));
        });

        findViewById(R.id.navigate_flight).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,FlightSplashActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);

        });




    }
    public void initApiDora() {
        Retrofit retrofit = RetroFitBuilder.getInstance();
        IPostApi iPostApi = retrofit.create(IPostApi.class);
        Call<NarutoFields> responses = iPostApi.getLionKingItem();
        responses.enqueue(new Callback<NarutoFields>() {
            @Override
            public void onResponse(Call<NarutoFields> call, Response<NarutoFields> response) {
                // for(NarutoFields narutoFields:response.body()) {

                Log.d("SAMPLE_TEST",String.valueOf(response.body()));
                NarutoFields narutoFields = response.body();
                Log.d("SAMPLE_TEST", String.valueOf(narutoFields.getResponse()));
                //searchItemArrayList.add(narutoFields.getSearch().get(0));
                searchItemArrayList = narutoFields.getSearch();
                Log.d("SAMPLE_TEST", String.valueOf(searchItemArrayList.size()));
                RecyclerView recyclerView = findViewById(R.id.home_naruto);
                LionKingRecyclerAdapter userViewAdapter = new LionKingRecyclerAdapter(searchItemArrayList,MainActivity.this);
                //recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false));
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL));
                recyclerView.setAdapter(userViewAdapter);
            }


            @Override
            public void onFailure(Call<NarutoFields> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("SAMPLE_LIST", t.getMessage());
            }
        });
    }
    public void initApiPotter() {
        Retrofit retrofit = RetroFitBuilder.getInstance();
        IPostApi iPostApi = retrofit.create(IPostApi.class);
        Call<NarutoFields> responses = iPostApi.getFrozenItem();
        responses.enqueue(new Callback<NarutoFields>() {
            @Override
            public void onResponse(Call<NarutoFields> call, Response<NarutoFields> response) {
                // for(NarutoFields narutoFields:response.body()) {

                Log.d("SAMPLE_TEST",String.valueOf(response.body()));
                NarutoFields narutoFields = response.body();
                Log.d("SAMPLE_TEST", String.valueOf(narutoFields.getResponse()));
                //searchItemArrayList.add(narutoFields.getSearch().get(0));
                searchItemArrayList = narutoFields.getSearch();
                Log.d("SAMPLE_TEST", String.valueOf(searchItemArrayList.size()));
                RecyclerView recyclerView = findViewById(R.id.home_potter);
                UserRecyclerAdapter userViewAdapter = new UserRecyclerAdapter(searchItemArrayList,MainActivity.this);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false));
                //recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                recyclerView.setAdapter(userViewAdapter);
            }


            @Override
            public void onFailure(Call<NarutoFields> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("SAMPLE_LIST", t.getMessage());
            }
        });
    }
    public void initApiBenTen() {
        Retrofit retrofit = RetroFitBuilder.getInstance();
        IPostApi iPostApi = retrofit.create(IPostApi.class);
        Call<NarutoFields> responses = iPostApi.getBenTenItem();
        responses.enqueue(new Callback<NarutoFields>() {
            @Override
            public void onResponse(Call<NarutoFields> call, Response<NarutoFields> response) {
                // for(NarutoFields narutoFields:response.body()) {

                Log.d("SAMPLE_TEST",String.valueOf(response.body()));
                NarutoFields narutoFields = response.body();
                Log.d("SAMPLE_TEST", String.valueOf(narutoFields.getResponse()));
                //searchItemArrayList.add(narutoFields.getSearch().get(0));
                searchItemArrayList = narutoFields.getSearch();
                Log.d("SAMPLE_TEST", String.valueOf(searchItemArrayList.size()));
                RecyclerView recyclerView = findViewById(R.id.home_ben_10);
                BenTenRecyclerAdapter userViewAdapter = new BenTenRecyclerAdapter(searchItemArrayList,MainActivity.this);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false));
                //recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                recyclerView.setAdapter(userViewAdapter);
            }


            @Override
            public void onFailure(Call<NarutoFields> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("SAMPLE_LIST", t.getMessage());
            }
        });
    }
    public void initApiToyStory() {
        Retrofit retrofit = RetroFitBuilder.getInstance();
        IPostApi iPostApi = retrofit.create(IPostApi.class);
        Call<NarutoFields> responses = iPostApi.getToyStoryItem();
        responses.enqueue(new Callback<NarutoFields>() {
            @Override
            public void onResponse(Call<NarutoFields> call, Response<NarutoFields> response) {
                // for(NarutoFields narutoFields:response.body()) {

                Log.d("SAMPLE_TEST",String.valueOf(response.body()));
                NarutoFields narutoFields = response.body();
                Log.d("SAMPLE_TEST", String.valueOf(narutoFields.getResponse()));
                //searchItemArrayList.add(narutoFields.getSearch().get(0));
                searchItemArrayList = narutoFields.getSearch();
                Log.d("SAMPLE_TEST", String.valueOf(searchItemArrayList.size()));
                RecyclerView recyclerView = findViewById(R.id.home_king_kong);
                UserRecyclerAdapter userViewAdapter = new UserRecyclerAdapter(searchItemArrayList,MainActivity.this);
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL));
                recyclerView.setAdapter(userViewAdapter);
            }


            @Override
            public void onFailure(Call<NarutoFields> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("SAMPLE_LIST", t.getMessage());
            }
        });
    }
    public void initApiSearch() {
        Retrofit retrofit = RetroFitBuilder.getInstance();
        IPostApi iPostApi = retrofit.create(IPostApi.class);
        Call<NarutoFields> responses = iPostApi.getToyStoryItem();
        responses.enqueue(new Callback<NarutoFields>() {
            @Override
            public void onResponse(Call<NarutoFields> call, Response<NarutoFields> response) {
                // for(NarutoFields narutoFields:response.body()) {

                Log.d("SAMPLE_TEST",String.valueOf(response.body()));
                NarutoFields narutoFields = response.body();
                Log.d("SAMPLE_TEST", String.valueOf(narutoFields.getResponse()));
                //searchItemArrayList.add(narutoFields.getSearch().get(0));
                searchItemArrayList = narutoFields.getSearch();
                Log.d("SAMPLE_TEST", String.valueOf(searchItemArrayList.size()));
                RecyclerView recyclerView = findViewById(R.id.home_king_kong);
                UserRecyclerAdapter userViewAdapter = new UserRecyclerAdapter(searchItemArrayList,MainActivity.this);
                //recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false));
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.HORIZONTAL));
                recyclerView.setAdapter(userViewAdapter);
            }


            @Override
            public void onFailure(Call<NarutoFields> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("SAMPLE_LIST", t.getMessage());
            }
        });
    }


    @Override
    public void OnUserClick(SearchItem searchIndex, int position) {
        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Title",searchIndex.getTitle());
        editor.putString("Type",searchIndex.getType());
        editor.putString("Year",searchIndex.getYear());
        editor.putString("Rating",searchIndex.getImdbID());
        editor.putString("Poster",searchIndex.getPoster());
        editor.apply();
        startActivity(new Intent(MainActivity.this,DetailActivity.class));
    }


    @Override
    public void OnUserFlightClick(PassengerDto airlineItem, int position) {

    }

    @Override
    public void initApiFlightCall() {

    }

}