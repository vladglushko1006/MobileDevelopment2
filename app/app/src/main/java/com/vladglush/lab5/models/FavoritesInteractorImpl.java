package com.vladglush.lab5.models;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.vladglush.lab5.entity.UfcFighter;
import com.vladglush.lab5.repositories.FavoritesRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FavoritesInteractorImpl implements FavoritesInteractor {
    private FavoritesRepository repository;
    private FavoritesInteractor.OnFinishedListener onFinishedListener;

    public  FavoritesInteractorImpl(FavoritesRepository repository, FavoritesInteractor.OnFinishedListener onFinishedListener) {
        this.repository = repository;
        this.onFinishedListener = onFinishedListener;
    }

    @Override
    public void getFighters() {
        SharedPreferences sharedPreferences = repository.getContext().getSharedPreferences("favorites", Context.MODE_PRIVATE);

        List<UfcFighter> fighters = new ArrayList<>();
        Map<String, ?> map = sharedPreferences.getAll();

        for (Map.Entry<String, ?> entry : map.entrySet()) {
            UfcFighter fighter = new Gson().fromJson(entry.getValue().toString(), UfcFighter.class);
            fighters.add(fighter);
        }

        onFinishedListener.addData(fighters);
    }
}
