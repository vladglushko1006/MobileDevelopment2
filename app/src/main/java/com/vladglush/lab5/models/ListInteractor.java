package com.vladglush.lab5.models;

import com.vladglush.lab5.entity.UfcFighter;

import java.util.List;

public interface ListInteractor {
    interface OnFinishedListener {
        void onFinished(List<UfcFighter> fightersList);

        void onFailure(Throwable t);
    }

    void getFightersList(OnFinishedListener onFinishedListener);
}
