package com.example.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    CountDownTimer start;
    boolean x=false;
    public void resetTimer()
    {
        x=false;
        SeekBar seekBar=(SeekBar)findViewById(R.id.seekBar);
        seekBar.setEnabled(true);
        seekBar.setProgress(30);
        Button button=(Button)findViewById(R.id.button);
        button.setText("SET");
        TextView textView=(TextView)findViewById(R.id.textView);
        textView.setText("0:30");
        start.cancel();

    }
    public void updateTime(int totalSeconds)
    {
        TextView textView=(TextView)findViewById(R.id.textView);
        int minutes=totalSeconds/60;
        int seconds=totalSeconds%60;
        if(seconds<10)
        {
            textView.setText(Integer.toString(minutes)+":0"+Integer.toString(seconds));
        }
        else
        {
            textView.setText(Integer.toString(minutes)+":"+Integer.toString(seconds));
        }
    }
    public void clickFunction(View view)
    {
        if(x==true)
        {
           resetTimer();
        }
        else if(x==false)
        {
            x=true;
            SeekBar seekBar=(SeekBar)findViewById(R.id.seekBar);
            seekBar.setEnabled(false);
            Button button=(Button)findViewById(R.id.button);
            button.setText("STOP");
             start = new CountDownTimer(seekBar.getProgress()*1000, 1000) {
                public void onTick(long l)
                {
                    updateTime((int)l/1000);
                }
                public void onFinish()
                {
                    MediaPlayer mediaPlayer=MediaPlayer.create(getApplicationContext(),R.raw.ishvalalove);
                    mediaPlayer.start();
                    resetTimer();
                }
            }.start();
        }
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SeekBar seekBar=(SeekBar)findViewById(R.id.seekBar);
        seekBar.setMax(600);
        seekBar.setProgress(30);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
               updateTime(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
