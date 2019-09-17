package com.example.bottomnavigation

import android.os.Bundle
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                supportFragmentManager.beginTransaction().replace(R.id.container,homeFragment()).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {

                //Here we will learn about the snackBar
                                                                                                //this text will be displayed on the right
                Snackbar.make(container,"Snakbar displayed",Snackbar.LENGTH_LONG).setAction("UNDO VALA TEXT") {
                    //we can do anyhting here now like if by chance we click a photo in our gallery, if we delete the image by chance then a snackbar appears at the bottom
                    //in which we want to undo the change. Not only undo we can display anything or do any other task if we want when that text/button is clicked.
                    Toast.makeText(this,"IMAGE IS RESTORED",Toast.LENGTH_LONG).show()
                }.show()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //since here we have chosen the bottom navigation drawer activity in the start. Therefore the libriary is added by default. But if you add this bottom navigation drawer, you need to add libriary to add this.
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}


//wasabeef
//anco -> used for that content