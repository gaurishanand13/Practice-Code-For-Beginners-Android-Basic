package com.example.communication_between_fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import kotlinx.android.synthetic.main.fragmentb.*
import kotlinx.android.synthetic.main.fragment_.*

class MainActivity : AppCompatActivity() , FragmentB.FragmentBListner,FragmentA.FragmentAListner{
    override fun onInputBSent(v: String) {
        fragmentAEditText.text = Editable.Factory.getInstance().newEditable(v)
    }

    override fun onInputASent(v: String) {
        fragmentBEditText.text = Editable.Factory.getInstance().newEditable(v)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentAContainer,FragmentA())
            .replace(R.id.fragmentBContainer,FragmentB())
            .commit()

    }
}
