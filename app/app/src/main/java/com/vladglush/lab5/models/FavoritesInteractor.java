package com.vladglush.lab5.models;

import com.vladglush.lab5.entity.UfcFighter;

import java.util.List;

public interface FavoritesInteractor {
    interface OnFinishedListener {
        void addData(List<UfcFighter> fighters);
    }

    void getFighters();
}
