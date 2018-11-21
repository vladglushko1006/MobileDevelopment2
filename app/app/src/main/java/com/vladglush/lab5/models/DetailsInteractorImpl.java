package com.vladglush.lab5.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.gson.Gson;
import com.vladglush.lab5.entity.UfcFighter;
import com.vladglush.lab5.repositories.DetailsRepository;

public class DetailsInteractorImpl implements DetailsInteractor {
    private DetailsRepository repository;
    private DetailsInteractor.OnFinishedListener onFinishedListener;

    private SharedPreferences sharedPreferences;

    public DetailsInteractorImpl(DetailsRepository repository, DetailsInteractor.OnFinishedListener onFinishedListener) {
        this.repository = repository;
        this.onFinishedListener = onFinishedListener;

        sharedPreferences = repository.getContext().getSharedPreferences("favorites", Context.MODE_PRIVATE);
    }

    private String getFullName(UfcFighter fighter) {
        return fighter.getFirst_name() + " " + fighter.getLast_name();
    }

    @Override
    public void toggleFavorite(UfcFighter fighter) {
        if (!sharedPreferences.contains(getFullName(fighter)))
            sharedPreferences.edit().putString(getFullName(fighter), new Gson().toJson(fighter)).apply();
        else
            sharedPreferences.edit().remove(getFullName(fighter)).apply();
    }

    @Override
    public void isFavorite(UfcFighter fighter) {
        onFinishedListener.favoriteResult(sharedPreferences.contains(getFullName(fighter)));
    }

    @Override
    public void getFighter() {
        Bundle bundle = repository.getFragment().getArguments();

        if (bundle != null) {
            UfcFighter fighter = new Gson().fromJson(bundle.getString("details"), UfcFighter.class);
            onFinishedListener.setFighter(fighter);
        }
    }
}
