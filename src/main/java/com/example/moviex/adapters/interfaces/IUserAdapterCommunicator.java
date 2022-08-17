package com.example.moviex.adapters.interfaces;


import com.example.moviex.retrofit.model.SearchItem;
import com.example.moviex.retrofit.modelflight.PassengerDto;

public interface IUserAdapterCommunicator {
    void OnUserClick(SearchItem searchIndex, int position);
    void initApiFlightCall();
    void OnUserFlightClick(PassengerDto passengerDto, int position);


}
