package com.vladglush.lab5.views;

import com.vladglush.lab5.entity.UfcFighter;

public interface DetailsView {
    void isFavorite(boolean isFavorite);

    void setFighter(UfcFighter fighter);
}
