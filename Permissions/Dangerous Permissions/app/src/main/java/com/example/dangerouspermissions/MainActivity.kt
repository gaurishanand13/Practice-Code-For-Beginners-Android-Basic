package com.example.dangerouspermissions

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {

    //Dangerous permission are need to be asked at the runTime. It is because they are generally needed to access more private
    // data of the user. Some of the dangerous Permissions are -> like
    //1) accessing camera
    //2) accessing contacts(like read,write,or to update contacts).
    //3) accessing call logs.
    //4) accessing microphone of the phone by the app to record the audio.
    //5) accesing the sms service of your phone i.e to accesss receieved messages or send messages.
    // 6) accessing external storage like gallery or some other file.
    // 7) like making a call directly by the app. -> we have already seen intents in (ACTION_DIAL) in which if we type a number
    // the dialer opens up with the same number. But it does't dial the number. IF we want to dial the number straight away from the app
    // then for that too we need to ask for permission




    //Here we will try to make a call directly from here (not just opening the number in the dialer through the intents). Therefore we need to ask the permission to the user
    //at the runTime to access the Phone and dialer
    //Since we need to make a call, Therefore instead of using the ACTION_DIAL, we will use the action : ACTION_CALL.
    //Now for this(i.e to make a call) too we need to pass the permission statement in the manifestFile :" <uses-permission android:name="android.permission.CALL_PHONE"/>"
    //Writing only the above statement won't work. As when this time the app would ask the OS for the permission, it won't grant you directly as it comes
    //under the category of dangerous statements. Therefore dangerous permissions can't be granted directly just looking at the manifest File.

    //Therefore if we try to open the view to dial without taking the user's permission,The app would crash.
    //Therefore Here button1 will crash but button2,button3 would make the call.

    //Button2 and button3 would work in the same manner in the app. But button 3 is a more appropriate way to handle permissions and it reduces the code length if a lot of permissions are to be granted.



    fun makeCall()
    {
        var uri:Uri = Uri.parse("tel:"+ editText.text.toString())
        val intent:Intent = Intent(Intent.ACTION_CALL,uri)
        startActivity(intent)
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        button1.setOnClickListener {
          makeCall() //the app will crash when this button is clicked.
        }


        button2.setOnClickListener{
            //Here we will first check if permission is granted or not.
            //Note -> all the dangerous permission are granted in this way only
            // Here the first paramater passed in the checkSelfPermission is the context of the activity in which we are in and the 2nd parameter is the permission which we are checking if it is granted or not
            val permission:Int = ContextCompat.checkSelfPermission(this,android.Manifest.permission.CALL_PHONE)

            //Here the value of the permission can be either 0 or -1. It will be 0 if the permission is granted, otherwise -1



            //Note -> In the settings it may happen that before opening the app, the user would grant permission to make calls by the app. Therefore in that case the value of
            //permission variable will be 0.
            if(permission == PackageManager.PERMISSION_GRANTED)
            {
                //we can make a call Now.
                makeCall()
            }
            else
            {
                //Now since the permission to make calls is not allowed in the settings. We should first ask the permission to do so.
                //Here to seek the permission, first parameter is the context of the activity in which we are currently in, 2nd parameter should be the permission which we are asking for
                ActivityCompat.requestPermissions(this,arrayOf(android.Manifest.permission.CALL_PHONE),121)

                //Now it may happen that the user may grant the permission or may not grant the permission. Therefore we should again check if the app is granted permission in the settings to make the call.
                if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED)
                {
                    makeCall()
                }
                //Therefore if the permission would not be granted here too. Then nothing would happen. Therefore if the user clicks the button again, it would again ask the user to grant permission to make call.

                //But once the permission is granted, it would be automatically saved in the settings, and the nextTime, it won't ask for this permission.
            }
        }



        button3.setOnClickListener {
            val permission:Int = ContextCompat.checkSelfPermission(this,android.Manifest.permission.CALL_PHONE)
            if(permission == PackageManager.PERMISSION_GRANTED)
            {
                makeCall()
            }
            else
            {
                if(ActivityCompat.shouldShowRequestPermissionRationale(this,android.Manifest.permission.CALL_PHONE)){
                    //This function is just used to show some extra information about the permission. This fucntion works on the seperate Thread. So it does't block the UI thread
                    Toast.makeText(this,"Telephone permission is needed to make a call",Toast.LENGTH_SHORT).show()
                }
                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CALL_PHONE),121)
                //Now whether the user says yes or no, depending on it , it will pass the values to the onRequestPermission function
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode == 121)
        {
            //if result is cancelled. then the grantResult array is empty
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                makeCall()
            }
            else
            {
                //means permission not granted
            }
        }
    }
}
