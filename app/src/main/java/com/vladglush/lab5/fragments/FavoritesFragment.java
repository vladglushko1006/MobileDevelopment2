package com.vladglush.lab5.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vladglush.lab5.R;
import com.vladglush.lab5.adapters.RecyclerViewAdapter;
import com.vladglush.lab5.entity.UfcFighter;
import com.vladglush.lab5.presenters.FavoritesPresenter;
import com.vladglush.lab5.presenters.FavoritesPresenterImpl;
import com.vladglush.lab5.views.FavoritesView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoritesFragment extends Fragment implements FavoritesView {

    private Context context;
    private FavoritesPresenter presenter;

    private RecyclerViewAdapter adapter;

    @BindView(R.id.favoritesRecyclerView)
    protected RecyclerView favoritesList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        ButterKnife.bind(this, view);

        presenter = new FavoritesPresenterImpl(this, getActivity());

        presenter.requestData();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        favoritesList.setLayoutManager(layoutManager);

        return view;
    }

    @Override
    public void setData(List<UfcFighter> fighters) {
        adapter = new RecyclerViewAdapter(this.getContext(), fighters);
        favoritesList.setAdapter(adapter);
    }
}
