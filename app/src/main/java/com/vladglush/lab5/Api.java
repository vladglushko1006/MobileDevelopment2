package com.vladglush.lab5;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    String Base_URL = "http://ufc-data-api.ufc.com";

    @GET("/api/mpcs1/us/fighters")
    Call<List<UfcFighter>> getUfcFighter();

}

