package com.vladglush.lab5.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.vladglush.lab5.MainActivity;
import com.vladglush.lab5.R;
import com.vladglush.lab5.adapters.RecyclerViewAdapter;
import com.vladglush.lab5.entity.UfcFighter;
import com.vladglush.lab5.presenters.ListPresenter;
import com.vladglush.lab5.presenters.ListPresenterImpl;
import com.vladglush.lab5.views.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListFragment extends Fragment implements ListView {
    @BindView(R.id.customRecyclerView)
    protected RecyclerView recyclerView;
    @BindView(R.id.no_data)
    protected TextView noDataText;
    @BindView(R.id.swipeContainer)
    protected SwipeRefreshLayout swipeContainer;

    @BindView(R.id.showFavorites)
    protected Button showFavoritesButton;

    private RecyclerViewAdapter adapter;
    private ProgressDialog progDialog;

    private ListPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        ButterKnife.bind(this, view);

        presenter = new ListPresenterImpl(this);

        progDialog = new ProgressDialog(this.getContext());
        progDialog.setMessage(getString(R.string.loading));
        progDialog.show();

        adapter = new RecyclerViewAdapter(this.getContext(), new ArrayList<UfcFighter>());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.requestData();
            }
        });

        showFavoritesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) v.getContext()).setFragment(new FavoritesFragment(), true);
            }
        });

        presenter.requestData();

        return view;
    }

    private void updateRefreshingUI(boolean isSuccess) {
        progDialog.dismiss();
        swipeContainer.setRefreshing(false);

        noDataText.setVisibility(isSuccess ? View.GONE : View.VISIBLE);
        recyclerView.setVisibility(isSuccess ? View.VISIBLE : View.GONE);
    }

    @Override
    public void updateList(List<UfcFighter> fighters) {
        adapter.updateList(fighters);
        updateRefreshingUI(true);
    }

    @Override
    public void onResponseFailure(Throwable t) {
        Toast.makeText(ListFragment.this.getContext(), getString(R.string.on_failure),
                Toast.LENGTH_SHORT).show();

        updateRefreshingUI(false);
    }
}
