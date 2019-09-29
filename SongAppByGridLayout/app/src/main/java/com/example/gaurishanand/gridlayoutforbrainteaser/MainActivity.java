package com.example.gaurishanand.gridlayoutforbrainteaser;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

2    public void clickFunction(View view)
    {
        Button button=(Button)view;
        MediaPlayer mediaPlayer=MediaPlayer.create(this,getResources().getIdentifier(button.getTag().toString(), "raw", getPackageName()));
        mediaPlayer.start();
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
