package com.example.gaurishanand.rotatoryandanimatoryanimations;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView=(ImageView)findViewById(R.id.CatimageView);
       imageView.setX(-1000);
       imageView.animate().translationXBy(1000).rotation(720).setDuration(3000);
    }
}
