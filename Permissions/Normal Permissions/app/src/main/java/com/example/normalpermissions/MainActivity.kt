package com.example.normalpermissions

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Note -> Even to check whether your phone is connected to internet or not by the app , For that too we
        // need to give the app permission in the manifest file. "<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>"
        //writing this line the app would ask the OS of the phone for this permission. Since this permission is not that dangerous and not harms
        //the privacy of the app user. Therefore the OS would grant this permission directly to the user without taking the conset of the user.
        button.setOnClickListener{
            // note get system services is used to access managers like Vonnectivity manager, Bluetooth Manager

            //Since here we want to see whether the app is connected to net or not. Therefore we will call the connectivity manager using systemService
            //connectivityManager variable here is the object of the class ConnectivityManager
            val connetivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            //Here the systemService returns the object of another class Therefore we need to typeCast the above object to of type ConnectivityManager


            //Now to get the information of currently active network. We will use the member function of the class of above object which is "getActiveNetworkInfo"
            //To get the information of current active networkState
            val networkInfo = connetivityManager.activeNetworkInfo
            val isConnected:Boolean = (networkInfo!= null) && networkInfo.isConnected()

            //Now setting the text in the textView. Whether the phone is connected to net or not.
            if(isConnected)
            {
                textView.text = "CONNECTED"
            }
            else{
                textView.text = "DISCONNECTED"
            }
        }
    }
}

// Therefor If the permissions that don't pose much risk to the user's privacy or the device's operation, the system(or OS- operating system) automatically grants
// those permissions to your app. Some of these normal Permissions are - ACCESS_NETWORK_STATE ,ACCESS_NOTIFICATION_POLICY ,ACCESS_WIFI_STATE
// ,BLUETOOTH,etc