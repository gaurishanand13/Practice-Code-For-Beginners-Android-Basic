package com.example.androidactionbar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    // STEP1 - First we need to add a directory in the res folder by the name "menu" (right click on res-> new -> directory -> and name it as "menu") -> this name is specific
    // STEP2 - Now right click on the menu -> new -> menu resource file and give the name as "main". (Here the name is main because we are creating for the mainActivity).
    // STEP3 - Now to first make the layout for the action Bar. We should download images from the image assets in the drawable folder. While chosing from the image Assets set the action type for action bar and select
    // STEP4 - Now to include this menu option in our xml layout use onCreateOptionsMenu after creating that xml file properly(go and read it first)
    // STEP 5 - Now learn how to bring the backMenu in the action Bar. For this we will go the nextActivity and add it, so that we can come back to the mainActivity. So check the case of Download.
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main , menu)
        //we have first converted this xml file to an Object and ask the program to add it.
        return super.onCreateOptionsMenu(menu) //If the app is unable to add that options menu. it will return false here.
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //Here we will use switch case to handle these cases
        when(item.itemId){
            R.id.download ->{
                //Now we will learn about back option
                startActivity(Intent(this,DownloadActivity::class.java))
            }
            R.id.copy ->{

            }
            R.id.refresh ->{

            }
            R.id.add ->{

            }
            R.id.email ->{

            }
        }
        return super.onOptionsItemSelected(item)
    }
}
