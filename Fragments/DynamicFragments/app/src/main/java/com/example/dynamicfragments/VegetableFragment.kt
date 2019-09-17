package com.example.dynamicfragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.fragment_vegetable.view.*

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
class VegetableFragment : Fragment() {

    val vegetables = arrayOf("Potato","Onion","Tomato","Lettuce","Carrot","Capsicum","Gourd","Pumpkin","Radish")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val fragmentView = inflater.inflate(R.layout.fragment_vegetable, container, false)


        //if we want simple layout without aby extra text. Then we can even use the standard adapter of android studio.
        //val myAdapter = ArrayAdapter<String>(requireContext(),android.R.layout.simple_list_item_1,vegetables)

        val myAdapter = ArrayAdapter<String>(
            requireContext(), // It by default gets us the context
            R.layout.my_view_for_each_each_item,
            R.id.textView,
            vegetables
        )
        fragmentView.VegetableFragmentListView.adapter = myAdapter
        return fragmentView
    }


}
