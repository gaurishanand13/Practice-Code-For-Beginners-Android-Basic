package com.example.dynamicfragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.fragment_fruits.*
import kotlinx.android.synthetic.main.fragment_fruits.view.*


/*
// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
*/

/**
 * A simple [Fragment] subclass.
 *
 */

class FruitsFragment : Fragment() {

    val fruits = arrayOf("Apple","Orange","Banana","Pineapple","Grapes","Pomogrenade","Kiwi","Watermelon","Cherry")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragmentView = inflater.inflate(R.layout.fragment_fruits, container, false)

        val myAdapter = ArrayAdapter<String>(
            requireContext(),
            R.layout.my_view_for_each_each_item,
            R.id.textView,
            fruits
        )
        fragmentView.fruitFragmentListView.adapter = myAdapter
        return fragmentView
    }


}
