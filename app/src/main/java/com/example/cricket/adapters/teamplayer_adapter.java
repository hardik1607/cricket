package com.example.cricket.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cricket.R;
import com.example.cricket.activitys.team_player;

public class teamplayer_adapter extends BaseAdapter {

    team_player team_player;
    String[] team;
    int[] image;
    public teamplayer_adapter(team_player team_player,String[] team,int[] image) {
        this.team_player=team_player;
        this.team=team;
        this.image=image;
    }

    @Override
    public int getCount() {
        return team.length;
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
        view= LayoutInflater.from(team_player).inflate(R.layout.teamplayer_item,viewGroup,false);

        TextView textView=view.findViewById(R.id.teamsplayar_text);
        ImageView imageView=view.findViewById(R.id.teamsplayar_image);

        textView.setText(team[i]);
        imageView.setImageResource(image[i]);
        return view;
    }
}
