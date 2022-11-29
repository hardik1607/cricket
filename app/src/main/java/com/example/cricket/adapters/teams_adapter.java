package com.example.cricket.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cricket.R;
import com.example.cricket.activitys.teams;
import com.example.cricket.config;
import com.google.android.material.slider.LabelFormatter;

public class teams_adapter extends BaseAdapter {

    teams teams;
    public teams_adapter(teams teams) {
        this.teams=teams;
    }

    @Override
    public int getCount() {
        return config.teams_name.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view= LayoutInflater.from(teams).inflate(R.layout.team_item,viewGroup,false);

        TextView textView=view.findViewById(R.id.teams_text);
        ImageView imageView=view.findViewById(R.id.teams_image);

        textView.setText(config.teams_name[i]);
        imageView.setImageResource(config.teams_img[i]);
        return view;
    }
}
