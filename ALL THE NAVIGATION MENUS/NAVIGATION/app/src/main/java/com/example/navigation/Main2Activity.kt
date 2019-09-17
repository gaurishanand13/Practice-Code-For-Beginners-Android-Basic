package com.example.navigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        //Here we will add the back button in this activity which will direct us back to its parent activity. But for that we will need to set the parent activity of this activity in the manifest file
        setSupportActionBar(toolBar2)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
