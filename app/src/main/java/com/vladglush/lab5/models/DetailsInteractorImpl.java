package com.vladglush.lab5.models;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.vladglush.lab5.ApplicationEx;
import com.vladglush.lab5.entity.UfcFighter;

public class DetailsInteractorImpl implements DetailsInteractor {
    private Context context;
    private DetailsInteractor.OnFinishedListener onFinishedListener;

    private SharedPreferences sharedPreferences;

    public DetailsInteractorImpl(Context context, DetailsInteractor.OnFinishedListener onFinishedListener) {
        this.context = context;
        this.onFinishedListener = onFinishedListener;

        sharedPreferences = context.getSharedPreferences("favorites", Context.MODE_PRIVATE);
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
        UfcFighter fighter = ApplicationEx.getInstance().getCurrentFighter();
        onFinishedListener.setFighter(fighter);
    }
}
