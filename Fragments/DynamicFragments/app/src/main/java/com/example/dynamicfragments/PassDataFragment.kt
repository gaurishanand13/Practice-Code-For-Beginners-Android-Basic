package com.example.dynamicfragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_pass_data.*
import kotlinx.android.synthetic.main.fragment_pass_data.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class PassDataFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_pass_data, container, false)
        val bundle = arguments; //This function will give us the object of type bundle which we passed from the activity
        //now we can take all the data from this bundle
        val x = bundle?.getString("gaurish") //like this we can get data from the main Activity to the fragment we want.
        view.PassDatatextView.text = x.toString()
        return view
    }
}
