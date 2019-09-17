package com.example.intentscb

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener{
            val intent = Intent(this,SecondActivity::class.java)
            //passing a string to another Activity Through Intents.
            intent.putExtra("data",editText.text.toString())
            //Passin an array to another activity through Intents
            val x = arrayOf("ja ","be ","ja ","gaand ","mra")
            intent.putExtra("myStuff",x)
            startActivity(intent)
        }
        button2.setOnClickListener{
            finish()
        }
        Log.i("main","onCreate")
    }
    override fun onStart() {
        super.onStart()
        Log.i("main","onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("main","Resume")
    }

    override fun onPause() {
        Log.i("main","onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.i("main","onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.i("main","onDestroy")
        super.onDestroy()
    }
}
