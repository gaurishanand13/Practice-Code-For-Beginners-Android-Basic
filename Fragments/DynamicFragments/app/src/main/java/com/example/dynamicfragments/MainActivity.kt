package com.example.dynamicfragments

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

//we have done static Fragments. Now lets do dynamicFragments -> i.e to add them dynamically when the app is running.
//We can add or detatch a fragment from the app's activity when the app is running.
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //In Order to access the fragments we need the fragmentManager
        val fragmentManager = supportFragmentManager //-> It gets us the fragment manager to manage Fragments


        //We can see easily that there is no fragment object in the activity_main.xml. But this piece of code create and adds that particular fragment.

        //Note-> If we want to detach or attach any type of fragment to the layout. We need to begin Transaction
        fruitFragmentButton.setOnClickListener{
            fragmentManager.beginTransaction()
                .replace(R.id.FragmentContainer,FruitsFragment()) //Here the first parameter is the container from which you want to replace or add the Fragment and the second parameter is the fragment which we want to add it
                .commit()
        }
        vegetableFragmentButton.setOnClickListener{
            fragmentManager.beginTransaction()
                .replace(R.id.FragmentContainer,VegetableFragment())
                .commit()
        }

        //To pass some information from the activity to the the fragment, we pass it with the help of bundles.
        //we pass an object of type bundle to this fragment

        //Bundle is nothing but like the array of {key,value} pairs (can be seen as hashmaps which has many key,value pairs)
        val bundle :Bundle =  Bundle()
        bundle.putString("gaurish","anand")
        bundle.putString("divyanshu","malik")
        bundle.putString("ishank","anand")
        val frag = PassDataFragment()
        frag.arguments = bundle
        button3.setOnClickListener{
            //here instead of replacing with the new fragment object, we will mention the variable of the fragment we created above
            fragmentManager.beginTransaction().replace(R.id.FragmentContainer,frag).commit()
        }
    }
}
