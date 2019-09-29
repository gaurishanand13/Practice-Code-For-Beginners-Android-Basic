package com.example.gaurishanand.fadinganimations;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    boolean x=true;
    public void clickFunction(View view)
    {
        ImageView imageView1=(ImageView)findViewById(R.id.Cat1imageView);
        ImageView imageView2=(ImageView)findViewById(R.id.Cat2imageView);
        if(x)
        {
            x=false;
            imageView1.animate().alpha(0).setDuration(2000);
            imageView2.animate().alpha(1).setDuration(2000);
        }
        else
        {
            x=true;
            imageView1.animate().alpha(1).setDuration(2000);
            imageView2.animate().alpha(0).setDuration(2000);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
