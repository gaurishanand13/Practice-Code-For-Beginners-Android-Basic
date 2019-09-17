package com.example.locationsmart

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.LocationSettingsStatusCodes
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {


    //Note - if the location of the device is not on , then our app would crash. Therefore to check if the user's location is on/off and then make it on. We should do it so that our app doex't crash.
    private fun checkUserSettingsAndGetLocation(){

        val locationRequest = LocationRequest().apply {
            interval = 1000
            fastestInterval = 5000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }


        //therefore to get the current location settings of the user's device we would follow these steps
        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)
            .build()

        val client = LocationServices.getSettingsClient(this)

        val task = client.checkLocationSettings(builder).apply {
            addOnSuccessListener {
                //On success shows that location is already on. Therefore we don't need to show user the dialog to change its device settings for the location
                checkAndStartLocationUpdates()
            }
            addOnFailureListener{
                if(it is ResolvableApiException)
                {
                    //It means location settings are not satisfied, but this can be fixed by showing the user a dialog
                    try{
                        //Now show the dialog to the user by calling this activity and check for the result in onActivity result
                        it.startResolutionForResult(this@MainActivity,12)

                    }catch (e:Exception){
                        e.printStackTrace()
                    }
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 12){
            if (resultCode == Activity.RESULT_OK){
                checkAndStartLocationUpdates()
            }else{
                Toast.makeText(this,"Please enable Location to continue",Toast.LENGTH_LONG).show()
            }
        }

    }





    @SuppressLint("MissingPermission")
    fun getLocationsSmartly()
    {
        //Using this method, we don't need to use location listener and location manager. It is a libriary which is provided by google only.
        //"implementation 'com.google.android.gms:play-services-location:17.0.0'"
        val client = LocationServices.getFusedLocationProviderClient(this)
        client.lastLocation.apply {
            addOnFailureListener{
                //the code written here will be executed if the it fails to fetch the location of the user.
            }
            addOnSuccessListener {
                //Now since the location is fetched which can be seen in it as it is type of location object
                textView.text = """
                My Location ${it.latitude}, ${it.longitude}
                Accuracy ${it.accuracy}
                Provider ${it.provider}
            """.trimIndent()
            }
        }
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 121)
        {
            for(i in grantResults.indices){
                if(grantResults[i]!=PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this,"Please give ${permissions[i]}",Toast.LENGTH_LONG).show()
                    return
                }
            }
            //If it comes out of the "for" loop that means all the permission is granted.
            getLocationsSmartly()
        }
    }


    private fun  checkAndStartLocationUpdates(){
        val permission:Int = ContextCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)
        if(permission == PackageManager.PERMISSION_GRANTED)
        {
            //Now we can get location becuase permission to the location of apps is not granted
            getLocationsSmartly()
        }
        else
        {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,android.Manifest.permission.ACCESS_FINE_LOCATION)){
                //This function is just used to show some extra information about the permission. This fucntion works on the seperate Thread. So it does't block the UI thread
                Toast.makeText(this,"Telephone permission is needed to make a call", Toast.LENGTH_SHORT).show()
            }
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    android.Manifest.permission.ACCESS_COARSE_LOCATION,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ),121)
            //Now whether the user says yes or no, depending on it , it will pass the values to the onRequestPermission function
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            checkUserSettingsAndGetLocation()
        }
    }
}
