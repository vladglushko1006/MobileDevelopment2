package com.vladglush.lab5.presenters;

import android.content.Context;

import com.vladglush.lab5.entity.UfcFighter;
import com.vladglush.lab5.models.FavoritesInteractor;
import com.vladglush.lab5.models.FavoritesInteractorImpl;
import com.vladglush.lab5.views.FavoritesView;

import java.util.List;

public class FavoritesPresenterImpl implements FavoritesPresenter, FavoritesInteractor.OnFinishedListener {
    private FavoritesView view;
    private FavoritesInteractor interactor;
    private Context context;

    public FavoritesPresenterImpl(FavoritesView view, Context context) {
        this.view = view;
        this.context = context;

        this.interactor = new FavoritesInteractorImpl(context, this);
    }

    @Override
    public void requestData() {
        interactor.getFighters();
    }

    @Override
    public void addData(List<UfcFighter> fighters) {
        view.setData(fighters);
    }
}
