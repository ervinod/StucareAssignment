package com.stucareassignment.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiRequestData {
    @GET("api/shibes?count=50&urls=true")
    Call<List<String>> getDogs();
}
