package com.example.normallist

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.my_layout_for_each_row.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var x = arrayListOf<String>()
        for (y in 1..100)
        {
            x.add(y.toString())
        }
        //We pass 4 arguments to the arrayAdapter -> 1) Context 2) the layout in which you want every row to be present
        // 3) then pass the textView ki id where you want to display the text 4) array of things which you want to add
        val myAdapter = ArrayAdapter<String>(
            this,
            R.layout.my_layout_for_each_row, // Therefore first create a new layoutResource file in the layouts
            R.id.textView,
            x
        )
        listView.adapter = myAdapter
        listView.setOnItemClickListener { adapterView, view, i, l ->
            //Here view is the view of the item in xml which is clicked whereas i is the position of the the listView clicked in terms of index
            // and l is the id of each view clicked
            Toast.makeText(this,"Clicked on $i : ${view.textView.text}",Toast.LENGTH_SHORT).show()
        }


        //Therefore in the above code, We can see that we can only use this method to create a listView.
        // If we want to pass only the one textView to have the text. Therefore if you have multiple TextView text for each item. Check the procedure 3.
    }
}
