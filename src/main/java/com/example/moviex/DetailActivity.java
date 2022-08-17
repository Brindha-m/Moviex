package com.example.moviex;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.moviex.retrofit.model.SearchItem;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    List<SearchItem> searchItemArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);
        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);

        //tvd - text view detail
        TextView title = findViewById(R.id.tvd_title);
        TextView type = findViewById(R.id.tvd_type);
        TextView date = findViewById(R.id.tvd_year);
        TextView review = findViewById(R.id.tvd_imdbId);
        ImageView poster = findViewById(R.id.ivd_poster);

        title.setText(sharedPreferences.getString("Title", "default"));
        type.setText(sharedPreferences.getString("Type", "default"));
        date.setText(sharedPreferences.getString("Year", "default"));
        review.setText(sharedPreferences.getString("Rating", "default"));
        Glide.with(this).load(sharedPreferences.getString("Poster", "default")).placeholder(R.drawable.splash).into(poster);
        findViewById(R.id.detail_home).setOnClickListener(view -> {
            Intent mainIntent = new Intent(DetailActivity.this, MainActivity.class);
            mainIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(mainIntent);


        });
        findViewById(R.id.detail_back).setOnClickListener(view -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("SEARCH", "hello");
            onBackPressed();
        });
        findViewById(R.id.detail_search_parameter).setOnClickListener(view -> {
            EditText editText = findViewById(R.id.detail_search);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("SEARCH", editText.getText().toString());
            editor.apply();
            startActivity(new Intent(DetailActivity.this, SearchActivity.class));
            //startActivity(new Intent(DetailActivity.this,SearchActivity.class));
        });
    }

}


