package com.vladglush.lab5;

import android.app.Application;

import com.vladglush.lab5.network.Api;
import com.vladglush.lab5.network.RetrofitClient;

public class UFCApplication extends Application {
    private Api api;

    public UFCApplication() {
        api = RetrofitClient.getRetrofitInstance().create(Api.class);
    }

    public Api getApi() {
        return api;
    }
}
