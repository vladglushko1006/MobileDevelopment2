package com.vladglush.lab5;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.vladglush.lab5.adapter.CustomAdapter;
import com.vladglush.lab5.model.UfcFighter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoritesFragment extends Fragment {

    private SharedPreferences sharedPrefs;
    private List<UfcFighter> fighterList;

    private CustomAdapter adapter;

    @BindView(R.id.favoritesRecyclerView)
    RecyclerView favoritesList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        ButterKnife.bind(this, view);

        fighterList = new ArrayList<>();
        getFavorites();

        adapter = new CustomAdapter(this.getContext(), fighterList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        favoritesList.setLayoutManager(layoutManager);
        favoritesList.setAdapter(adapter);

        return view;
    }

    private void getFavorites() {
        sharedPrefs = getActivity().getSharedPreferences("favorites", Context.MODE_PRIVATE);
        Map<String, ?> map = sharedPrefs.getAll();

        for (Map.Entry<String, ?> entry : map.entrySet()) {
            UfcFighter fighter = new Gson().fromJson(entry.getValue().toString(), UfcFighter.class);
            fighterList.add(fighter);
        }
    }

}
