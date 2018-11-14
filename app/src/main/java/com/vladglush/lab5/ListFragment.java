package com.vladglush.lab5;

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

import com.vladglush.lab5.adapter.CustomAdapter;
import com.vladglush.lab5.model.UfcFighter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListFragment extends Fragment {
    @BindView(R.id.customRecyclerView)
    protected RecyclerView recyclerView;
    @BindView(R.id.no_data)
    protected TextView noDataText;
    @BindView(R.id.swipeContainer)
    protected SwipeRefreshLayout swipeContainer;

    @BindView(R.id.showFavorites)
    Button showFavoritesButton;

    private CustomAdapter adapter;
    private ProgressDialog progDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        ButterKnife.bind(this, view);
        progDialog = new ProgressDialog(this.getContext());
        progDialog.setMessage(getString(R.string.loading));
        progDialog.show();

        adapter = new CustomAdapter(this.getContext(), new ArrayList<UfcFighter>());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                updateFightersList();
            }
        });

        showFavoritesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) v.getContext()).setFragment(new FavoritesFragment());
            }
        });

        updateFightersList();

        return view;
    }

    private void updateFightersList() {
        Call<List<UfcFighter>> call = ((UFCApplication)getActivity().getApplication()).getApi().getUfcFighter();
        call.enqueue(new Callback<List<UfcFighter>>() {

            @Override
            public void onResponse(Call<List<UfcFighter>> call, Response<List<UfcFighter>> response) {
                progDialog.dismiss();
                swipeContainer.setRefreshing(false);

                adapter.updatehList(response.body());

                dataAvailable();
            }

            @Override
            public void onFailure(Call<List<UfcFighter>> call, Throwable t) {
                progDialog.dismiss();
                swipeContainer.setRefreshing(false);
                Toast.makeText(ListFragment.this.getContext(), getString(R.string.on_failure),
                        Toast.LENGTH_SHORT).show();

                noData();
            }
        });
    }

    private void dataAvailable() {
        noDataText.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    private void noData() {
        noDataText.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }
}
