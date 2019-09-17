package com.example.intentscb

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_second.*
import java.util.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        //Here the intent is the same variable which is passed

        //Receiving data through Intents from where the activity is passed.
        val data  = intent.getStringExtra("data")
        textView.text = data.toString()
        Toast.makeText(this,Arrays.toString(intent.getStringArrayExtra("myStuff")), Toast.LENGTH_SHORT).show()
    }
}
