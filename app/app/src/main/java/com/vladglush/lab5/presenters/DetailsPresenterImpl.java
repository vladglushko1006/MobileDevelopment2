package com.vladglush.lab5.presenters;

import com.vladglush.lab5.entity.UfcFighter;
import com.vladglush.lab5.models.DetailsInteractor;
import com.vladglush.lab5.models.DetailsInteractorImpl;
import com.vladglush.lab5.repositories.DetailsRepository;
import com.vladglush.lab5.views.DetailsView;

public class DetailsPresenterImpl implements DetailsPresenter, DetailsInteractor.OnFinishedListener {
    private DetailsView view;
    private DetailsInteractor interactor;

    private DetailsRepository repository;

    public DetailsPresenterImpl(DetailsView view, DetailsRepository repository) {
        this.view = view;
        this.repository = repository;

        this.interactor = new DetailsInteractorImpl(repository, this);
    }

    @Override
    public void toggleFavorite(UfcFighter fighter) {
        interactor.toggleFavorite(fighter);
    }

    @Override
    public void ifFavorite(UfcFighter fighter) {
        interactor.isFavorite(fighter);
    }

    @Override
    public void getFighter() {
        interactor.getFighter();
    }

    @Override
    public void favoriteResult(boolean isFavoriteNow) {
        view.isFavorite(isFavoriteNow);
    }

    @Override
    public void setFighter(UfcFighter fighter) {
        view.setFighter(fighter);
    }
}
