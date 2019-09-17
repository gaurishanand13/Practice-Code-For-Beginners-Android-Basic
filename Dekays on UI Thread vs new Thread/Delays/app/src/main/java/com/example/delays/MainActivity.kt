package com.example.delays

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlinx.android.synthetic.main.activity_main.*

// Seperate Threads are generally used to increase the perfomance of our app

class MainActivity : AppCompatActivity() {

    var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        countButton.setOnClickListener{
            count++
            textView.text = count.toString()
        }
        waitButton.setOnClickListener{
            //Now what we want to when the user pressed the wait button. After 5 seconds the text View should display the text "Done". But in those 5 seonds too if we click on count
            // button the number should go on increasing and display i.e the UI thread should not get Blocked even if the timer is going on. But if we use this timer on this Main
            //Thread only. When the timer is going on , No other statements would be executed as it will first execute the current statement. Therefore in that case, if we click on
            //button to add then, the statements in that would get stacked and would be executed only after the current Timer statement is completed.


            //Therefore we should use this timer on the seperate thread i.e perform this function on the seperate thread. So that the task which are to performed on the
            // main Thread doesn't stop like here adding 1 and display

            //Therefore to handle the timer on another thread  we use the keyword "Handler" which is the class , which has the functions to delay some work on the seperate Thread
            Handler().postDelayed({
                textView.text = "DONE"
            },5000)
        }
    }

    //handle is used to only delay some task after 5 seconds. But what if we want to perform some task on the seperate thread in those 5 seconds too. Then in that case
    // we make a class which extends the class "AsyncTask".
}
