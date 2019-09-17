package com.example.intentfilters

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        browseButton.setOnClickListener{
            val intent:Intent = Intent()
            //setting up of the action which the intent should perform. Here we are asking to show us the ACTION View
            intent.action = Intent.ACTION_VIEW
            //Now we should pass the data to the intent. Depending on the type of data we pass to the intents. It will search for the apps where it can execute them
            //generally ACTION_VIEW is used to open the urls in browser or if written email id it will open the app to send mail to that id

            //Data passed to the intents should be of the type Uri. Therefore first create an object or type Uri
            val uri  = Uri.parse(editText.text.toString())
            intent.data = uri

            //Now before starting the activity. We should use try and catch because what if the text enter by the user. There is no browser or app to open it or is unusable data
            try{
                startActivity(intent)
            }catch (e: ActivityNotFoundException)
            {
                Toast.makeText(this,"No app found to open this url or email ID",Toast.LENGTH_SHORT).show()
            }
        }
        Call.setOnClickListener{
            val intent:Intent = Intent()
            //setting up of the action which the intent should perform. Here we are asking to show us the ACTION View
            intent.action = Intent.ACTION_DIAL
            //Now we should pass the data to the intent. Depending on the type of data we pass to the intents. It will search for the apps where it can execute them
            // like if we write "tel:9582024452" in the edit text -> It will give the options of all dialers and when we open the dialer. It will open the keypad with the number 9582024452, so that we can dial it.

            //Data passed to the intents should be of the type Uri. Therefore first create an object or type Uri
            val uri  = Uri.parse("tel:"+editText.text.toString())
            intent.data = uri

            //Now before starting the activity. We should use try and catch because what if the text enter by the user. There is no  app to open it or is unusable data(like if it enter characters instead of numbers)
            try{
                startActivity(intent)
            }catch (e: ActivityNotFoundException)
            {
                Toast.makeText(this,"No app found to open this url or email ID",Toast.LENGTH_SHORT).show()
            }
        }
    }
}

//Here we also discussed about intent Filters which is to be written in the manifest file. Intent filters are used to tell the os, which type of actions this app can handle.
//Like if a user in another app wants to open the browser and we set our app as browsable then it will also come as options to the user when the user tries to open the app.
