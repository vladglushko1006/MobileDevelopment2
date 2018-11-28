package com.vladglush.lab5.models;

import com.vladglush.lab5.entity.UfcFighter;
import com.vladglush.lab5.network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListInteractorImpl implements ListInteractor {
    @Override
    public void getFightersList(final OnFinishedListener onFinishedListener) {
        Call<List<UfcFighter>> call = RetrofitClient.getApiService().getUfcFighter();
        call.enqueue(new Callback<List<UfcFighter>>() {
            @Override
            public void onResponse(Call<List<UfcFighter>> call, Response<List<UfcFighter>> response) {
                onFinishedListener.onFinished(response.body());
            }

            @Override
            public void onFailure(Call<List<UfcFighter>> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });
    }
}
