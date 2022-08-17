package com.example.moviex.retrofit.network;

import com.example.moviex.retrofit.model.NarutoFields;
import com.example.moviex.retrofit.modelflight.FlightResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IPostApi {
     @GET("?apikey=9cfa95f8")
     Call<NarutoFields> getSearchItem(@Query("s") String searchTerm);

//     @GET("?apikey=9cfa95f8&s=\"naruto\"")
//     Call<NarutoFields> getNarutoItem();

     @GET("?apikey=9cfa95f8&s=\"lion%20king\"")
     Call<NarutoFields> getLionKingItem();

     @GET("?apikey=9cfa95f8&s=\"frozen\"")
     Call<NarutoFields> getFrozenItem();

     @GET("?apikey=9cfa95f8&s=\"ben_10\"")
     Call<NarutoFields> getBenTenItem();

     @GET("?apikey=9cfa95f8&s=\"toy%20story\"")
     Call<NarutoFields> getToyStoryItem();


     @GET("v1/passenger?page=0&size=10")
     Call<FlightResponse> getTenFlightDetails();

     @GET("v1/passenger")
     Call<FlightResponse> getFlightResult(@Query("page") int page, @Query("size") int limit);


   //  @GET("posts/{id}")
    // Call<PostResponse1> getOnePosts(@Path("id") Integer id);

}
