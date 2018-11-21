package com.vladglush.lab5.views;

import com.vladglush.lab5.entity.UfcFighter;

import java.util.List;

public interface ListView {
    void updateList(List<UfcFighter> fighters);
    void onResponseFailure(Throwable t);
}
