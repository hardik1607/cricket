package com.example.cricket.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.cricket.R;
import com.example.cricket.adapters.teamplayer_adapter;
import com.example.cricket.config;

public class team_player extends AppCompatActivity {

    ListView listView;
    String[] team;
    int pos;
    int[] image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_player);

        listView=findViewById(R.id.teamsplayar_list);
        pos=getIntent().getIntExtra("position",0);

        if (pos==0)
        {
            team= config.rcb_name;
            image=config.rcb_img;
        }
        if (pos==1)
        {
            team= config.mi_name;
            image=config.mi_img;
        }
        if (pos==2)
        {
            team= config.dc_name;
            image=config.dc_img;
        }
        if (pos==3)
        {
            team= config.csk_name;
            image=config.csk_img;
        }
        if (pos==4)
        {
            team= config.kkr_name;
            image=config.kkr_img;
        }
        if (pos==5)
        {
            team= config.rr_name;
            image=config.rr_img;
        }
        if (pos==6)
        {
            team= config.srs_name;
            image=config.srs_img;
        }

        teamplayer_adapter adater = new teamplayer_adapter(this,team,image);
        listView.setAdapter(adater);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent= new Intent(team_player.this,player.class);
                intent.putExtra("position",i);
                intent.putExtra("name",team);
                intent.putExtra("image",image);
                startActivity(intent);
            }
        });
    }
}