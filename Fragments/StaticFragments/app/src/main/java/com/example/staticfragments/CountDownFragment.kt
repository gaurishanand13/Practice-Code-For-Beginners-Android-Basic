package com.example.staticfragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_count_down.view.*
import kotlinx.android.synthetic.main.fragment_count_down.view.textView
import kotlinx.android.synthetic.main.fragment_count_up.view.*

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
class CountDownFragment : Fragment() {

    //we can see that return type of this function is of type View which will be of fragments.
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var count = 0


        // Inflating the layout for this fragment i.e converting this countUpFragment xml file to a type of view
        val fragmentView : View = inflater.inflate(R.layout.fragment_count_down,container,false)

        fragmentView.Subtractbutton.setOnClickListener{
            count--
            fragmentView.textView.text = count.toString()
        }
        return fragmentView
    }


}
