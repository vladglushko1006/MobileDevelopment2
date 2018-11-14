package com.vladglush.lab5;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.vladglush.lab5.model.UfcFighter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsFragment extends Fragment {

    private Bundle bundle;
    private UfcFighter fighter;

    private boolean isFullScreen;
    private boolean isFavorite;

    private final int normalSize = 300;
    private final int bigSize = 1000;

    private SharedPreferences sharedPrefs;

    @BindView(R.id.fullNameTextView)
    TextView fullNameTextView;

    @BindView(R.id.thumbnailImage)
    ImageView thumbnailImage;

    @BindView(R.id.classTextView)
    TextView classTextView;

    @BindView(R.id.winsTextView)
    TextView winsTextView;

    @BindView(R.id.lossesTextView)
    TextView lossesTextView;

    @BindView(R.id.drawsTextView)
    TextView drawsTextView;

    @BindView(R.id.addRemoveFavorite)
    Button addRemoveFavorite;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        ButterKnife.bind(this, view);

        bundle = this.getArguments();
        fighter = new Gson().fromJson(bundle.getString("details"), UfcFighter.class);

        sharedPrefs = getActivity().getSharedPreferences("favorites", Context.MODE_PRIVATE);
        isFavorite = sharedPrefs.contains(getFullName());

        setContent();

        thumbnailImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleImageClick();
            }
        });

        addRemoveFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isFavorite) {
                    sharedPrefs.edit().putString(getFullName(), new Gson().toJson(fighter)).apply();
                }
                else {
                    sharedPrefs.edit().remove(getFullName()).apply();
                }

                isFavorite = !isFavorite;
                updateButtonCaption();
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

    private void updateButtonCaption() {
        if (isFavorite) {
            addRemoveFavorite.setText("Remove from favorite");
        }
        else {
            addRemoveFavorite.setText("Add to favorite");
        }
    }

    private void setContent() {
        fullNameTextView.setText(getFullName());
        Picasso.get().load(fighter.getThumbnail()).into(thumbnailImage);
        classTextView.setText("Weight class: " + fighter.getWeight_class());
        winsTextView.setText("Wins: " + fighter.getWins());
        lossesTextView.setText("Losses: " + fighter.getLosses());
        drawsTextView.setText("Draws: " + fighter.getDraws());

        updateButtonCaption();
    }
}
