package com.example.recievinglocationupdates

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    var client : FusedLocationProviderClient? = null
    var locationRequest: LocationRequest? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 12){
            if (resultCode == Activity.RESULT_OK){
                updateLocationsOnMap()
            }else{
                Toast.makeText(this,"Please enable Location to continue", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun checkUserSettingsAndGetLocation(){

        locationRequest = LocationRequest().apply {
            interval = 1000
            fastestInterval = 5000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }


        //first checking if location service is on/off
        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest!!)
            .build()

        val client = LocationServices.getSettingsClient(this)

        val task = client.checkLocationSettings(builder).apply {
            addOnSuccessListener {
                //Now we will check if location permission is given to the app or not
                updateLocationsOnMap()
            }
            addOnFailureListener{
                if(it is ResolvableApiException)
                {
                    try{
                        it.startResolutionForResult(this@MapsActivity,12)

                    }catch (e: Exception){
                        e.printStackTrace()
                    }
                }
            }
        }
    }


    @SuppressLint("MissingPermission")
    fun updateLocationsOnMap()
    {
        client!!.lastLocation.apply {
            addOnFailureListener{
            }
            addOnSuccessListener {
               mMap.clear()
                mMap.addMarker(MarkerOptions().position(LatLng(it.latitude,it.longitude)).title("YOU ARE HERE"))
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(it.latitude,it.longitude),18f))
            }
        }
    }




    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        // Add a marker in Sydney and move the camera

        client = LocationServices.getFusedLocationProviderClient(this)
        checkUserSettingsAndGetLocation()

        client!!.requestLocationUpdates(locationRequest,object : LocationCallback(){
            override fun onLocationResult(p0: LocationResult?) {
                super.onLocationResult(p0)
                for(location in p0!!.locations)
                {
                    mMap.clear()
                    mMap.addMarker(MarkerOptions().position(LatLng(location.latitude,location.longitude)).title("YOU ARE HERE"))
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(location.latitude,location.longitude),18f))
                }
            }
        },null)

    }
}
