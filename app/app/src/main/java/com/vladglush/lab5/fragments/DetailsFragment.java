package com.vladglush.lab5.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vladglush.lab5.R;
import com.vladglush.lab5.entity.UfcFighter;
import com.vladglush.lab5.presenters.DetailsPresenter;
import com.vladglush.lab5.presenters.DetailsPresenterImpl;
import com.vladglush.lab5.repositories.DetailsRepository;
import com.vladglush.lab5.views.DetailsView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsFragment extends Fragment implements DetailsView {


    private UfcFighter fighter;

    private boolean isFullScreen;

    private static final int normalSize = 300;
    private static final int bigSize = 1000;

    private DetailsPresenter presenter;
    private DetailsRepository repository;

    @BindView(R.id.fullNameTextView)
    protected TextView fullNameTextView;

    @BindView(R.id.thumbnailImage)
    protected ImageView thumbnailImage;

    @BindView(R.id.classTextView)
    protected TextView classTextView;

    @BindView(R.id.winsTextView)
    protected TextView winsTextView;

    @BindView(R.id.lossesTextView)
    protected TextView lossesTextView;

    @BindView(R.id.drawsTextView)
    protected TextView drawsTextView;

    @BindView(R.id.addRemoveFavorite)
    protected Button addRemoveFavorite;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        ButterKnife.bind(this, view);

        repository = new DetailsRepository(getActivity(), this);
        presenter = new DetailsPresenterImpl(this, repository);

        presenter.getFighter();
        setContent();

        presenter.ifFavorite(fighter);

        thumbnailImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleImageClick();
            }
        });

        addRemoveFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.toggleFavorite(fighter);
                presenter.ifFavorite(fighter);
            }
        });

        isFullScreen = false;

        return view;
    }

    private void handleImageClick() {
        if (isFullScreen) {
            thumbnailImage.setLayoutParams(new LinearLayout.LayoutParams(normalSize, normalSize));
        }
        else {
            thumbnailImage.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, bigSize));
        }

        isFullScreen = !isFullScreen;
    }

    private String getFullName() {
        return fighter.getFirst_name() + " " + fighter.getLast_name();
    }

    private void setContent() {
        fullNameTextView.setText(getFullName());
        Picasso.get().load(fighter.getThumbnail()).into(thumbnailImage);
        classTextView.setText(getString(R.string.Weight_class, fighter.getWeight_class()));
        winsTextView.setText(getString(R.string.wins, fighter.getWins()));
        lossesTextView.setText(getString(R.string.losses, fighter.getLosses()));
        drawsTextView.setText(getString(R.string.draws, fighter.getDraws()));
    }

    @Override
    public void isFavorite(boolean isFavorite) {
        addRemoveFavorite.setText(isFavorite ? "Remove from favorite" : "Add to favorite");
    }

    @Override
    public void setFighter(UfcFighter fighter) {
        this.fighter = fighter;
    }
}
