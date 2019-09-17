package com.example.listview2

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.view_for_each_item.view.*

//make the colour adapter class along with declaring the constructor in the function defintion
//Note while making our own customAdapter we always need to pass the context -> i.e where listView is present to our customAdapter class as argument.
//other arguments can be anything or any number which we want to set in the EachListView.

//Now as we have inherited the base class which the BaseAdapter.  We shold also implement the abstract methods of the BaseAdapter in this. Which we can easily add
class colourAdapter(val context: Context, val nums: ArrayList<Int>, val cols: Array<String>) : BaseAdapter(){
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        //This function will be executed while making each View of the listView

        //First we need to inherit or On the LayoutInflator class. This class has a function called as inflate which will convert the xml file to
        // a type of view
        val li = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        //Note -> Assume that we have initially 12 Views on our screen. Therefore it will make the 12 new View ans when we will log the p1(View).
        //We will see that it is null in these cases. But as we scroll, we will see that they become a type of linearLayout(which can be seen in logs)
        //Therefore what android does is that like if we scroll down to 13th value. 1st ItemView is of no use now. So it makes it go to the lastView. If we want to use it again.
        //Therefore instead of making a new itemView for each time scrolling up or down(as it wll take a lot of space of android app). try to use this convertedView
        //as your View Now.(Refer to the images)

        val itemView:View

        if(p1==null)
        {
            //Here we pass the the layout of the xml File which we want to convert into a view object as we want to create a new view
            itemView = li.inflate(R.layout.view_for_each_item,p2, false)
            //Here p2 is the parentView where the listView is there. Normally we don't need to change it.
        }
        else
        {
            itemView = p1
        }


        val id = nums[p0]  //Here id refers to the number in the list

        //Since we have only 15 colours . We want it to repeat after 15 if the list is greater than 15
        val colorName = cols[p0 % 15]

        //This Color.parse is a class function which will give us the colour code if we provide it the colour. It has only specfic colour Names.
        //We can press command and click on it to see them.
        val color = Color.parseColor(colorName)

        //Now sincee we have converted the xml File to a view. We can add The features to our each view
        itemView.linearLayout.setBackgroundColor(color)
        itemView.ColourTextView.text = colorName
        itemView.NumberTextView.text = id.toString()
        return itemView

    }

    override fun getItem(p0: Int): Any?{
         //To change body of created functions use File | Settings | File Templates.
        return null
    }

    override fun getItemId(p0: Int): Long {
        //To change body of created functions use File | Settings | File Templates.
        return 0;
    }

    override fun getCount(): Int {
         //To change body of created functions use File | Settings | File Templates.
        return nums.size
    }

}