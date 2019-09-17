package com.example.recyclerview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //First we will generate the arrayList of courses throught this Function
        val coursesArrayList = genNRandomCourses(200)

        recyclerView.layoutManager = LinearLayoutManager(this)
        //recyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        recyclerView.adapter = courseAdapter(coursesArrayList,this)

       // recyclerView.addOnItemTouchListener()
    }
}
