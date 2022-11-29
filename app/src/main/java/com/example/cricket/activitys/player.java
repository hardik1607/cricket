package com.example.cricket.activitys;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.palette.graphics.Palette;

import android.annotation.SuppressLint;
import android.app.WallpaperManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cricket.R;
import com.example.cricket.config;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class player extends AppCompatActivity {

    ImageView imageView,prev,next,wallpaper,share,download;
    TextView textView;
    String[] playarname;
    int[] playarimage;
    int pos;
    LinearLayout linearLayout;
    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player);

        imageView=findViewById(R.id.player_image1);
        textView=findViewById(R.id.playar_name1);
        prev=findViewById(R.id.prev);
        next=findViewById(R.id.next);
        linearLayout=findViewById(R.id.linear);
        wallpaper=findViewById(R.id.set);
        share=findViewById(R.id.share);
        download=findViewById(R.id.download);


        pos=getIntent().getIntExtra("position",0);
        playarimage=getIntent().getIntArrayExtra("image");
        playarname=getIntent().getStringArrayExtra("name");

        imageView.setImageResource(playarimage[pos]);
        textView.setText(playarname[pos]);

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pos>0)
                {
                    pos--;
                    imageView.setImageResource(playarimage[pos]);
                    textView.setText(playarname[pos]);
                }

                Bitmap bitmap = BitmapFactory.decodeResource(getResources(),playarimage[pos]);

                Palette p = createPaletteSync(bitmap);
                Palette.Swatch vibrantSwatch = p.getLightVibrantSwatch();

                if(vibrantSwatch != null){
                    linearLayout.setBackgroundColor(vibrantSwatch.getRgb());
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if (pos<playarname.length-1)
               {
                   pos++;
                   imageView.setImageResource(playarimage[pos]);
                   textView.setText(playarname[pos]);
               }

                Bitmap bitmap = BitmapFactory.decodeResource(getResources(),playarimage[pos]);

                Palette p = createPaletteSync(bitmap);
                Palette.Swatch vibrantSwatch = p.getDarkVibrantSwatch();

                if(vibrantSwatch != null){
                    linearLayout.setBackgroundColor(vibrantSwatch.getRgb());
                }
            }
        });

        String[] arr={"Lock Screen","Home Screen"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("select");
        builder.setItems(arr, new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                WallpaperManager myWallpaperManager
                        = WallpaperManager.getInstance(getApplicationContext());
                try {
                    if (i==0)
                    {
                        myWallpaperManager.setResource(playarimage[pos],WallpaperManager.FLAG_LOCK);
                    }
                    if (i==1)
                    {
                        myWallpaperManager.setResource(playarimage[pos],WallpaperManager.FLAG_SYSTEM);
                    }

                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        wallpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.show();
            }
        });


        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap icon = getBitmapFromView(imageView);
                System.out.println("bitmap======>"+icon);
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                icon.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                int num=new Random().nextInt(2000);
                File f = new File(Environment.getExternalStorageDirectory() + File.separator + "/temporary_file"+num+".jpg");
                try {
                    f.createNewFile();
                    FileOutputStream fo = new FileOutputStream(f);
                    fo.write(bytes.toByteArray());
                    Toast.makeText(player.this,"file downloaded", Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap icon = getBitmapFromView(imageView);
                System.out.println("bitmap======>"+icon);
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("image/jpeg");
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                icon.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                int num=new Random().nextInt(2000);
                File f = new File(config.file.getAbsolutePath() + "/temporary_file"+num+".jpg");
                try {
                    f.createNewFile();
                    FileOutputStream fo = new FileOutputStream(f);
                    fo.write(bytes.toByteArray());
                    Toast.makeText(player.this,"file downloaded", Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    share.putExtra(Intent.EXTRA_STREAM, Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), f.getAbsolutePath(),"img","Identified image")));
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                startActivity(Intent.createChooser(share, "Share Image"));
            }
        });

    }
    public Palette createPaletteSync(Bitmap bitmap) {
        Palette p = Palette.from(bitmap).generate();
        return p;
    }
    public static Bitmap getBitmapFromView(View view) {
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable =view.getBackground();
        if (bgDrawable!=null)
            bgDrawable.draw(canvas);
        else
            canvas.drawColor(Color.WHITE);
        view.draw(canvas);
        return returnedBitmap;
    }
}