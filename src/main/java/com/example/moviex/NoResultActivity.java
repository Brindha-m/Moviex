package com.example.moviex;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class NoResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_resultfound);
        ImageView noResult = findViewById(R.id.iv_noresult);
        ImageButton imageButton = findViewById(R.id.error_home);
        imageButton.setOnClickListener(view -> {
            startActivity(new Intent(NoResultActivity.this, MainActivity.class));
        });

        ImageButton imageButton1 = findViewById((R.id.error_backward));
        imageButton1.setOnClickListener(view -> {
           onBackPressed();
        });

        Glide.with(this).asGif()
                .load("https://c.tenor.com/7zKZuIk31GEAAAAd/bird-dance.gif")
                .into(noResult);

    }
}