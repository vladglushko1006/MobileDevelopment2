package com.vladglush.lab5;

import android.app.Application;

import com.vladglush.lab5.entity.UfcFighter;

public class ApplicationEx extends Application {
    private static final ApplicationEx instance = new ApplicationEx();

    private UfcFighter currentFighter;

    public static ApplicationEx getInstance() {
        return instance;
    }

    public UfcFighter getCurrentFighter() {
        return currentFighter;
    }

    public void setCurrentFighter(UfcFighter currentFighter) {
        this.currentFighter = currentFighter;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
