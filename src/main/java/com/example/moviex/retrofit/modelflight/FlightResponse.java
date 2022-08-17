package com.example.moviex.retrofit.modelflight;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FlightResponse {

	@SerializedName("totalPassengers")
	private int totalPassengers;

	@SerializedName("data")
	private List<DataItem> data;

	@SerializedName("totalPages")
	private int totalPages;

	public void setTotalPassengers(int totalPassengers){
		this.totalPassengers = totalPassengers;
	}

	public int getTotalPassengers(){
		return totalPassengers;
	}

	public void setData(List<DataItem> data){
		this.data = data;
	}

	public List<DataItem> getData(){
		return data;
	}

	public void setTotalPages(int totalPages){
		this.totalPages = totalPages;
	}

	public int getTotalPages(){
		return totalPages;
	}
}