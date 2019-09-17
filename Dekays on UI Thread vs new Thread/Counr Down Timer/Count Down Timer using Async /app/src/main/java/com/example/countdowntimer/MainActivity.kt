package com.example.countdowntimer

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

//Here we will learn about all the functions of the Async class
// onPreExecute -> it is function to be executed first on the UI Thread when the object of the class is executed.
// doInBackground -> it is the function which is executed on a seperate thread and it is executed after the function onPreExecute.
// progressUpdate -> this function is executed on the mainThread only. Like if we want to update some progress of the task we are
//                   doing on the seperate thread (in doInBackground Function). we can call this progress function in between which will update the
//                   progress on the UI thread and then go back to its seperate thread.
//onPostExecute -> this function also works on the mainThread.

//there all the functions except doInBackground works on the main Thread


class MainActivity : AppCompatActivity() {

    var textView:TextView? = null
    var editTextOne:EditText? = null
    var editTextTwo:EditText? = null

    //This is the variable of the object of the TimerClass because we just want to cancel the async class whenever we want.
    var myClass: TimerClass? = null

    class TimerClass(val textView: TextView,var editText1: EditText,var editText2: EditText): AsyncTask<Int,Int,String>(){

        override fun onPreExecute() {
            super.onPreExecute()
            //Here nothing to do for now so leave this function this now.
        }

        override fun doInBackground(vararg p0: Int?): String {
            //Here p0[0] will be the total Time
            for(i in 0..(p0[0]!!))
            {
                //for each value we have to wait for 1 second
                var startTime = System.currentTimeMillis()
                while (System.currentTimeMillis()<startTime+1000){
                    //This loop will run for 1 second
                }
                //Now we should update the textView by calling the function progress
                publishProgress((p0[0]!!)-i)
            }
            return "TIME UP"
        }

        override fun onProgressUpdate(vararg values: Int?) {
            super.onProgressUpdate(*values)

            val timeInMinutes = (values[0]!!-1)/60
            val seconds = (values[0]!!)%60
            if(seconds<10)
            {
                textView.text = timeInMinutes.toString() + ":0" + seconds.toString()
            }
            else
            {
                textView.text = timeInMinutes.toString() + ":" + seconds.toString()
            }
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            textView.text = result
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView1)
        editTextOne = findViewById(R.id.editText2)
        editTextTwo = findViewById(R.id.editText2)
        setButton.setOnClickListener{
            val totalTimeInMillis = (editText1.text.toString().toInt())*60 + editText2.text.toString().toInt()
            textView1.text = editText1.text.toString() + ":" + editText2.text.toString()
            myClass = TimerClass(textView!!,editTextOne!!,editTextTwo!!).execute(totalTimeInMillis) as (MainActivity.TimerClass)//this variable value is always passed to the doInBackground Function
        }
        pauseButton.setOnClickListener{
            //this statement will stop my async task where ever it was
            myClass?.cancel(true)

            //Here i have set the text as editable i.e first string is converted to an editable format to be able to insert in the editText
            editTextOne?.text = Editable.Factory.getInstance().newEditable("00")
            editTextTwo?.text = Editable.Factory.getInstance().newEditable("00")
        }
    }
}
