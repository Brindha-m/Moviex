package com.example.moviex.retrofit.networkHandler;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroFitBuilder {
    private static Retrofit instance;
    private RetroFitBuilder()
    {}

    public static Retrofit getInstance()
    {
        if(instance==null){
            synchronized (RetroFitBuilder.class){
                if(instance==null)
                {
                    instance =new Retrofit.Builder().baseUrl("https://www.omdbapi.com/").addConverterFactory(GsonConverterFactory.create()).client((new OkHttpClient())).build();
                }
            }
        }
        return instance;
    }


}
