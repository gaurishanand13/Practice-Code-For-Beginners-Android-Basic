package com.example.listview2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    // In this project we will make each listItem backGround to have a random Colour From this and display in the textView the name of the colour and in another
    //TextView display the position of the TextView.
    val colors = arrayOf("red", "green", "blue", "cyan", "magenta", "yellow", "black", "white", "gray", "maroon", "purple", "fuchsia", "navy", "olive", "teal")
    val numList = ArrayList<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for (i in 1..30) {
            numList.add(i)
        }

        //Since we want the each list Item to have 2 unique parameter. Therefore we have to create our own
        // customAdapter class which inherits the baseAdapter and help us to easily make it.
        val colorAdapter = colourAdapter (this, numList, colors) //These are arguments passed in the constructor to make the object of the class colourAdapter
        //Now we will set the adapter as our made colorAdapter
        listView.adapter = colorAdapter
    }
}
