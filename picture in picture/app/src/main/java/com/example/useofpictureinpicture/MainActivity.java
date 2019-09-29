package com.example.useofpictureinpicture;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public void clickFunction(View view)
    {
        //this will take it to the pip mode
        enterPictureInPictureMode();
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode, Configuration newConfig) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode, newConfig);
        TextView textView = (TextView)findViewById(R.id.textView);
        Button button = (Button)findViewById(R.id.pipButton);
        if(isInPictureInPictureMode)
        {
            //if it is in pip mode
            button.setVisibility(View.INVISIBLE);
            //ealier when we were going in pip mode
            //most of the space was taken bar so we should hide it when going in pip mode
            //action bar is the top rectange where the name of the app comes
            getSupportActionBar().hide();
            textView.setText("$10,043.53");
        }
        else
        {
            //if it its out of PiP mode
            getSupportActionBar().show();
            button.setVisibility(View.VISIBLE);
            textView.setText("Hello World");
        }
    }
}
