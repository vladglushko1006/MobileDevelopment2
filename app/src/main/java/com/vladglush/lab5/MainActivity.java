package com.vladglush.lab5;

import android.app.ProgressDialog;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.vladglush.lab5.adapter.CustomAdapter;
import com.vladglush.lab5.model.UfcFighter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.customRecyclerView)
    protected RecyclerView recyclerView;
    @BindView(R.id.no_data)
    protected TextView noDataText;
    @BindView(R.id.swipeContainer)
    protected SwipeRefreshLayout swipeContainer;

    private CustomAdapter adapter;
    private ProgressDialog progDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        progDialog = new ProgressDialog(MainActivity.this);
        progDialog.setMessage(getString(R.string.loading));
        progDialog.show();

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                updateFightersList(false);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateFightersList(false);
    }

    private void updateFightersList(final boolean isLoading) {
        Call<List<UfcFighter>> call = ((UFCApplication)getApplication()).getApi().getUfcFighter();
        call.enqueue(new Callback<List<UfcFighter>>() {

            @Override
            public void onResponse(Call<List<UfcFighter>> call, Response<List<UfcFighter>> response) {
                progDialog.dismiss();
                swipeContainer.setRefreshing(false);
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<UfcFighter>> call, Throwable t) {
                progDialog.dismiss();
                swipeContainer.setRefreshing(false);
                Toast.makeText(MainActivity.this, getString(R.string.on_failure),
                        Toast.LENGTH_SHORT).show();
                noData();
            }
        });
    }

    private void generateDataList(List<UfcFighter> ufcFighterList) {
        adapter = new CustomAdapter(this, ufcFighterList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        noDataText.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    private void noData() {
        noDataText.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }
}
