package com.vladglush.lab5.presenters;

import com.vladglush.lab5.entity.UfcFighter;

public interface DetailsPresenter {
    void toggleFavorite(UfcFighter fighter);
    void ifFavorite(UfcFighter fighter);

    void getFighter();
}
