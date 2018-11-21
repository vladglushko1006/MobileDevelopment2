package com.vladglush.lab5.repositories;

import android.content.Context;

public class FavoritesRepository {
    Context context;

    public FavoritesRepository(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
