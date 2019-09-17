package com.example.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
var todoList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val adapter = ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,todoList)
        listView.adapter = adapter

        button.setOnClickListener {
            todoList.add(editText.text.toString())
            adapter.notifyDataSetChanged()
        }

        listView.setOnItemClickListener{ adapterView: AdapterView<*>?,view: View?, position: Int, l: Long ->
            todoList.removeAt(position)
            adapter.notifyDataSetChanged()
        }
    }
    
    
}
