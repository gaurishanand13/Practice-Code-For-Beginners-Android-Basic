package com.example.gaurishanand.timetableapps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public void generate(int Number)
    {
        ListView listView=(ListView)findViewById(R.id.myListView);
        ArrayList<String> arrayList=new ArrayList<String>();
        for(int i=1;i<=10;i++)
        {
            arrayList.add(Integer.toString(Number*i));
        }
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       SeekBar seekBar=(SeekBar)findViewById(R.id.seekBar);
       seekBar.setMax(20);
       seekBar.setProgress(5);
        ListView listView=(ListView)findViewById(R.id.myListView);
        ArrayList<String> arrayList=new ArrayList<String>();
        for(int i=1;i<=10;i++)
        {
            arrayList.add(Integer.toString(5*i));
        }
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);
       seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
           @Override
           public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
               if(progress<1)
               {
                   seekBar.setProgress(1);
                   generate(1);
               }
               else
               {
                   generate(progress);
               }
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
