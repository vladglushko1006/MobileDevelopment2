package com.vladglush.lab5.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;
import com.vladglush.lab5.DetailsFragment;
import com.vladglush.lab5.MainActivity;
import com.vladglush.lab5.R;
import com.vladglush.lab5.model.UfcFighter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private List<UfcFighter> dataList;
    private Context context;

    public CustomAdapter(Context context,List<UfcFighter> dataList){
        this.context = context;
        this.dataList = dataList;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.coverImage)
        ImageView coverImage;

        @BindView(R.id.title)
        TextView txtTitle;

        @BindView(R.id.parentLayout)
        RelativeLayout parentLayout;

        CustomViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.custom_row, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, final int position) {
        holder.txtTitle.setText(dataList.get(position).getLast_name());
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(dataList.get(position).getThumbnail())
                .resize(150, 150)
                .into(holder.coverImage);

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailsFragment fragment = new DetailsFragment();
                Bundle bundle = new Bundle();
                bundle.putString("details", new Gson().toJson(dataList.get(position)));
                fragment.setArguments(bundle);
                ((MainActivity) v.getContext()).setFragment(fragment);
            }
        });
    }

    @Override
    public int getItemCount() {
        return Math.min(dataList.size(),10);
    }

    public void clear() {
        dataList.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<UfcFighter> list) {
        dataList.addAll(list);
        notifyDataSetChanged();
    }

    public void updatehList(List<UfcFighter> fighters) {
        this.dataList.clear();
        this.dataList.addAll(fighters);
        notifyDataSetChanged();
    }
}
