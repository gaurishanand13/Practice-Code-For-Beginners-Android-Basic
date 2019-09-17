package com.example.recievinglocationupdates

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception


//In this we will learn how can we continuously track location, so that it can deliever more relevant information to the user when the user is walking or driving
class MainActivity : AppCompatActivity() {


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 121)
        {
            for(i in grantResults.indices){
                if(grantResults[i]!= PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this,"Please give ${permissions[i]}", Toast.LENGTH_LONG).show()
                    return
                }
            }
            startActivity(Intent(this,MapsActivity::class.java))
        }
    }


    private fun  checkAndStartLocationUpdates(){
        val permission:Int = ContextCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)
        if(permission == PackageManager.PERMISSION_GRANTED)
        {
            //Now since we have all the required permissions. Therefore we are now allowed to access the location of the user
            startActivity(Intent(this,MapsActivity::class.java))
        }
        else
        {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,android.Manifest.permission.ACCESS_FINE_LOCATION)){
                Toast.makeText(this,"Telephone permission is needed to make a call", Toast.LENGTH_SHORT).show()
            }
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    android.Manifest.permission.ACCESS_COARSE_LOCATION,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ),121)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            checkAndStartLocationUpdates()
        }
    }
}

