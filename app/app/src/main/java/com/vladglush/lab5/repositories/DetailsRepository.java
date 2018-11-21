package com.vladglush.lab5.repositories;

import android.content.Context;
import android.support.v4.app.Fragment;

public class DetailsRepository {
    private Context context;
    private Fragment fragment;

    public DetailsRepository(Context context, Fragment fragment) {
        this.context = context;
        this.fragment = fragment;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }
}
