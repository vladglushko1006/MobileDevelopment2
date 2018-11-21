package com.vladglush.lab5.presenters;

import com.vladglush.lab5.entity.UfcFighter;
import com.vladglush.lab5.models.FavoritesInteractor;
import com.vladglush.lab5.models.FavoritesInteractorImpl;
import com.vladglush.lab5.repositories.FavoritesRepository;
import com.vladglush.lab5.views.FavoritesView;

import java.util.List;

public class FavoritesPresenterImpl implements FavoritesPresenter, FavoritesInteractor.OnFinishedListener {
    private FavoritesView view;
    private FavoritesInteractor interactor;
    private FavoritesRepository repository;

    public FavoritesPresenterImpl(FavoritesView view, FavoritesRepository repository) {
        this.view = view;
        this.repository = repository;

        this.interactor = new FavoritesInteractorImpl(repository, this);
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
