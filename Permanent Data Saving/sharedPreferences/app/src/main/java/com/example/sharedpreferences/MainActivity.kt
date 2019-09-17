package com.example.sharedpreferences

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //This variable will keep track of how many times this app opens. It will be shown in the TextView
    var appOpenCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Here we are setting the sharedPreferences which will save the data in the phone permanently. Here we have set the mode PRIVATE
        // i.e no other app on the phone will be able to access this data other than this app.
        val prefs = getPreferences(Context.MODE_PRIVATE)

        //Here 0 is the byDefault value if it doesn't find any value by the string name "App_Open_Key" . Here it will happen when the app opens up for the first Time. So it should be zero.
        appOpenCount = prefs.getInt("App_Open_Key", 0) //We will first get the before count and save it in the memory and increase it by one
        appOpenCount++

        CountTextView.text = appOpenCount.toString()
        prefs.edit().putInt("App_Open_Key",appOpenCount).apply()

        
        button.setOnClickListener{
            var data = editText.text.toString()
            //Therefore to save any type of data in permanent memory through sharedPreferences. We need to provide the key of type string for that value
            //it is similar to hashmaps
            prefs.edit().putString("data",data).apply()
            Toast.makeText(this,"DATA SAVED SUCCESSFULLY",Toast.LENGTH_SHORT).show()
        }
        button2.setOnClickListener{
            var data = prefs.getString("data", "") //Here the "" string is the default value
            DataTextView.text = data
            Toast.makeText(this,"hh",Toast.LENGTH_SHORT).show()
        }
    }
}
