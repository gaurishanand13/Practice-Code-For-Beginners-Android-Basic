package com.example.navigation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //Now we would add our meny which we have made.
        val inflater = menuInflater
        inflater.inflate(R.menu.main,menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.button1 ->{
                return true
            }
            R.id.home ->{
                //by defualt the id of the back button is home. Therefore we can also do something else if we want to do like here we are finishing this activity
                finish()
            }
            else -> return false
        }
        return false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Note first to add our action bar, we should first remove the default toolbar

        //to add the our action bar and change the name of the activity in the action bar
        setSupportActionBar(toolBar)
        supportActionBar?.title = "Hello World"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        button.setOnClickListener {
            startActivity(Intent(this,Main2Activity::class.java))
        }
    }
}
