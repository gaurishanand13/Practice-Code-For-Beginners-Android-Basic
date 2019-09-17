package com.example.asynctask

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    var count = 0
    var tvField: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        countButton.setOnClickListener{
            count++
            textView.text = count.toString()
        }

        tvField = findViewById(R.id.textView)

        waitButton1.setOnClickListener {

            //it is a normal class, just to teach you
            WaitTask(tvField!!).execute()  //Making the object of the class "WaitTask" and executing its function
        }


    }



    //Note-> setting up of UI elements can be done only on the UI Thread(also called as main Thread) and they can't be done on the seperate thread.
    // Therefore perform only those functions on the seperate thread(Using Async Task) which do not involve changes in the xml file or any other UI changes.
    //It is because the thread on which the xml file has been set up can only make changes in it. Since MainThread sets the xml file through("setContentView(R.layout.activity_main)).
    //Therefore we can make any changes in the xml file only on the MainThread and not on the seperate Thread we make using using this class.
    //Therefore to write "DONE" in the textView after 5 seconds, we should first comee back to MainThread and make changes in the TextView. Otherwise the app will get crashed



    //A view is passed so that changes can be performed in the particular view we want in the xml file in the onPostExecute function which performs the task in
    // it on the Main Thread only. Here the last data type is of the data which we want to return to the onPostExecute function
    class WaitTask(val tc:TextView) : AsyncTask<Int,Float,String>(){


        //Therefore this function doInBackground contains the task which is to be performed on the seperate Thread. Since we can't make the changes in the TextView here, so
        //we have passed the result to the onPostExecute function() -> What this function does is that after doInBackground function performs its task on the seperate thread,
        //it passes its result to the onPostExecute function which performs the functions on the MainThread and not on the seperate Thread. But still we need to pass the view
        //in which we want to make changes as this class can't access even them with their id's which we can eaily do in kotlin

        override fun doInBackground(vararg p0: Int?):String{
            // It is way to handle the timers if we want to delay the work on the thread in which we are in. If we write this code on the MainThread. Then it
            // will perform this task on the mainThread too. But that will result in the blockage of your UI thread. Therefore we are doing here.
            val StartTime = System.currentTimeMillis()
            while (System.currentTimeMillis()< (StartTime + 5000))
            {
                //Nothing is to performed. This function will wait for 5 seconds and then after 5 seconds, it will return "DONE"
            }
            return "DONE"
        }

        override fun onProgressUpdate(vararg values: Float?) {
            // We are not doing here anything for now. But we can do something if we want to do.
            super.onProgressUpdate(*values)
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)

            //work that is to be performed on the mainThread
            tc.text = result
        }
    }

}

//Just barring the last of type "String" we have used other 2 as random, because we are no using them for now
//Information about the variables we pass using the making on the AsyncTask<int,Float,String>
//Here the first Data type "Int" is of the data which if we want to pass the data to the doInBackground function
//Second data type is of the data for the progress function, if we want something to pass it.
//3rd dataType as discussed is for the onPostExecute function which is returned from doInBackground function when all the steps of that function are completed


//check the explanation pic in the drawable folder.