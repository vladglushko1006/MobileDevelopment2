package com.vladglush.lab5.presenters;

import com.vladglush.lab5.entity.UfcFighter;
import com.vladglush.lab5.models.ListInteractor;
import com.vladglush.lab5.models.ListInteractorImpl;
import com.vladglush.lab5.views.ListView;

import java.util.List;

public class ListPresenterImpl implements ListPresenter, ListInteractor.OnFinishedListener {
    private ListView view;
    private ListInteractor interactor;

    public ListPresenterImpl(ListView listView) {
        this.view = listView;
        this.interactor = new ListInteractorImpl();
    }

    @Override
    public void requestData() {
        interactor.getFightersList(this);
    }

    @Override
    public void onFinished(List<UfcFighter> fightersList) {
        if (view != null)
            view.updateList(fightersList);
    }

    @Override
    public void onFailure(Throwable t) {
        if (view != null)
            view.onResponseFailure(t);
    }
}
