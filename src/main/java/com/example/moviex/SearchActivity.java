package com.example.moviex;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;
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

public class SearchActivity extends AppCompatActivity implements IUserAdapterCommunicator {
    List<SearchItem> searchItemArrayList = new ArrayList<SearchItem>();
    boolean isToggle;
    RecyclerView recyclerView;
    UserRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        recyclerView = findViewById(R.id.search_page_result);


        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(),Context.MODE_PRIVATE);
        String term = sharedPreferences.getString("SEARCH","Default");
        initApiSearch(term);
        findViewById(R.id.toggle_button).setOnClickListener(view -> {
            if(isToggle) {
                isToggle = false;
               // recyclerView = findViewById(R.id.search_page_result);
               adapter = new UserRecyclerAdapter(searchItemArrayList,SearchActivity.this);
               recyclerView.setLayoutManager(new LinearLayoutManager(SearchActivity.this, LinearLayoutManager.VERTICAL, false));
                recyclerView.setAdapter(adapter);
               // initApiSearch(term);

            }
            else {
                isToggle = true;
                //recyclerView = findViewById(R.id.search_page_result);
                 adapter = new UserRecyclerAdapter(searchItemArrayList,SearchActivity.this);

                    recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL));
                recyclerView.setAdapter(adapter);
            }
        });
        EditText editText = findViewById(R.id.search_again);

        findViewById(R.id.search_clear).setOnClickListener(view -> {
            editText.setText("");
        });

        findViewById(R.id.search_back).setOnClickListener(view -> {

            if(editText.getText().toString().length()>0){
                editText.setText("");
            }
            else {
               onBackPressed();
            }

        });
        findViewById(R.id.search_home).setOnClickListener(view -> {
            Intent mainIntent = new Intent(SearchActivity.this,MainActivity.class);
            mainIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(mainIntent);


        });
        ImageButton searchAgain = findViewById(R.id.search_parameter);
        searchAgain.setOnClickListener(view -> {

            initApiSearch(editText.getText().toString());
        });

    }
    public void initApiSearch(String term) {

        Retrofit retrofit = RetroFitBuilder.getInstance();
        IPostApi iPostApi = retrofit.create(IPostApi.class);

        Call<NarutoFields> responses = iPostApi.getSearchItem(term);
        responses.enqueue(new Callback<NarutoFields>() {
            @Override
            public void onResponse(Call<NarutoFields> call, Response<NarutoFields> response) {
                // for(NarutoFields narutoFields:response.body()) {
                boolean noList=false;
              //  Log.d("SAMPLE_TEST",String.valueOf(response.body()));
                NarutoFields narutoFields = response.body();
            //    Log.d("SAMPLE_TEST", String.valueOf(narutoFields.getResponse()));
                //searchItemArrayList.add(narutoFields.getSearch().get(0));
                Log.e("STRING_Wire",narutoFields.getResponse());
                if(narutoFields.getResponse().equals("False"))
                {
                    noList=true;
                    startActivity(new Intent(SearchActivity.this,NoResultActivity.class));
                    finish();
                }
                if(!noList) {
                    searchItemArrayList = narutoFields.getSearch();
//                Log.d("SAMPLE_TEST", String.valueOf(searchItemArrayList.size()));
                    RecyclerView recyclerView = findViewById(R.id.search_page_result);
                    UserRecyclerAdapter userViewAdapter = new UserRecyclerAdapter(searchItemArrayList, SearchActivity.this);

                    recyclerView.setLayoutManager(new LinearLayoutManager(SearchActivity.this, LinearLayoutManager.VERTICAL, false));

                    //   else {
                    //     recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL));

                    recyclerView.setAdapter(userViewAdapter);
                }
            }


            @Override
            public void onFailure(Call<NarutoFields> call, Throwable t) {
                Toast.makeText(SearchActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("SAMPLE_LIST", t.getMessage());
            }
        });
    }

    @Override
    public void OnUserClick(SearchItem searchIndex, int position) {
        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(),Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Title",searchIndex.getTitle());
        editor.putString("Type",searchIndex.getType());
        editor.putString("Year",searchIndex.getYear());
        editor.putString("Rating",searchIndex.getImdbID());
        editor.putString("Poster",searchIndex.getPoster());
        editor.apply();
        startActivity(new Intent(SearchActivity.this,DetailActivity.class));
    }

    @Override
    public void OnUserFlightClick(PassengerDto airlineItem, int position) {

    }

    @Override
    public void initApiFlightCall() {

    }
}