package com.vladglush.lab5.models;

import com.vladglush.lab5.entity.UfcFighter;

public interface DetailsInteractor {
    interface OnFinishedListener {
        void favoriteResult(boolean isFavoriteNow);
        void setFighter(UfcFighter fighter);
    }

    void toggleFavorite(UfcFighter fighter);
    void isFavorite(UfcFighter fighter);

    void getFighter();
}
