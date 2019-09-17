package com.example.locations

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

//There are basic 2 method to find locations
//locationManager is used to manage and provide locations
//locationListener is used when we want to store the location changed


class MainActivity : AppCompatActivity(), LocationListener {

    var locationManager:LocationManager? = null
    var myLat :Double?= 0.0
    var myLong :Double?= 0.0

    override fun onLocationChanged(p0: Location?) {
        //this function is called when the location is changed

        //Note- we can either get location either with wifi location(Netwok provider provide us the location) or through GPS(gps provider provides the location using mobile)
        //Note - > it.provider will show us the provider(i.e  either gps or Network provider) through which we are getting the location.
        p0?.let {
            textView.text = """
                My Location ${it.latitude}, ${it.longitude}
                Accuracy ${it.accuracy}
                Provider ${it.provider}
            """.trimIndent()
        }
        myLat = p0?.latitude
        myLong = p0?.longitude

    }

    override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {
        //this function is called when the provider which is providing us the interent changes. There are basically 3 types of providers
        // 1)Gps provider - It is the location provider when we are connected to the mobile data
        // 2)Network provider - It is also the location provider like when we are connected to the wifi
        // 3)Passive provider - It is the location provider which gets the location from any other app if it is fetching the location and takes data from that app
    }

    override fun onProviderEnabled(p0: String?) {
        //this function is executed when the provider providing us the internet to fetch location of the user is enabled
    }

    override fun onProviderDisabled(p0: String?) {
        //this function is executed when the provider providing us the internet to fetch location of the user is disabled
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
            startAndUpdateLocations()
        }
    }

    private fun  checkAndStartLocationUpdates(){
        val permission:Int = ContextCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)
        if(permission == PackageManager.PERMISSION_GRANTED)
        {
            //Now we can get location becuase permission to the location of apps is not granted
            startAndUpdateLocations()
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
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                ),121)
            //Now whether the user says yes or no, depending on it , it will pass the values to the onRequestPermission function
        }
    }








    @SuppressLint("MissingPermission")
    private fun startAndUpdateLocations() {
        locationManager  = getSystemService(Context.LOCATION_SERVICE) as LocationManager


        //First we will get the provider which is providing us the location or is on and will get us the location. Therefore we should first find that
        //as we need to provide it to the locationManager
        var enabledProvider =
            when {
                locationManager!!.isProviderEnabled(LocationManager.NETWORK_PROVIDER) -> LocationManager.NETWORK_PROVIDER
                locationManager!!.isProviderEnabled(LocationManager.GPS_PROVIDER) -> LocationManager.GPS_PROVIDER
                else -> "nill"
            }

        //to get location, location Manager needs 2 things, one is the provider which is getting the location,
        // other is the minTime after which we want to check the location and the 3rd one is the minDistance for
        // which location change listner should be executed. 4th one is the context of the activity where it want to get the location
        locationManager?.requestLocationUpdates(
            enabledProvider,
            1000,
            0f,
            this
        )
        locationManager?.requestLocationUpdates(
            enabledProvider,
            1000,
            0f,
            this
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            checkAndStartLocationUpdates()
        }

        button2.setOnClickListener {
            val intent = Intent(this,MapsActivity::class.java)
            intent.putExtra("myLat",myLat)
            intent.putExtra("myLong",myLong)
            startActivity(intent)
        }
    }

    override fun onDestroy() {

        //Since location Manager won't be deleted on its own. Therefore it is always a good practice to use
        locationManager?.removeUpdates(this)
        super.onDestroy()
    }
}
