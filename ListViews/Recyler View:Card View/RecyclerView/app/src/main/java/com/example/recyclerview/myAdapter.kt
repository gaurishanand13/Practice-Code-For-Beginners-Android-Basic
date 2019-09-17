package com.example.recyclerview

import android.app.Application
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.item_view.view.*

class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

// 1)Here since we want to pass the arrayList of type courses which has data we want to set on our each View.
// 2)Note since this adapter is used for recycler View. Therefore this class should inherit RecyclerView,class
// 3) When we will inherit this class. We would see that while making parent Class it requires an Object of type View Holder
// which extends the class RecyclerView.holder. Therefore make that class too. So that errors canbe removed
// 4) Now we should override the abstract method of the class RecyclerView.Adapter

//Note -  here we have passed the context just to show the toast messages as to show a toast message, we need the context of the mainActivity.
//otherwise if we don't even pass this context in the constructor then also. It will work.
class courseAdapter(val courseList: ArrayList<Course>,val context: Context):RecyclerView.Adapter<CourseViewHolder>(){

    //Note -> Unlike the BaseADapter class. This recylerView class is very smart as It has divided the program in 2 main functions.
    // onCreateViewHolder -> it will basically be called only when to create those 12 Views which will appear on the screen first.(As discussed Before)
    // Then if we want to set the data in eachView. It will go to the onBindViewHolder Function,Each time we scroll.
    // Therefore no extraView is created here as it will not come to this function again to make a new View.
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CourseViewHolder {

        //The same thing is working here we are converting the xml file to a view. So that we can use it.
        val li = p0.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView = li.inflate(R.layout.item_view, p0, false)

        //Note -> Since the return Type of this function is the ViewHolder which has a member of type "View". Therefore we wrap our view in this ViewHolder
        //and pass it. It is  just the syntax. At last We will be using the view only

        var courseViewHolder = CourseViewHolder(itemView) // -> we can see its syntax. It accepts a type of view in its constructor
        return courseViewHolder
    }

    override fun getItemCount(): Int {
        //This function return the count of totalNumber of Views to be inserted in our recyclerView.
        return courseList.size
    }

    override fun onBindViewHolder(p0: CourseViewHolder, p1: Int) {
        // since onCreateViewHolderFunction will be called only here like 12 times. But this onBindViewHolder will be called for eachView
        // When we scroll up and down to set the data. Therefore eachTime we scroll down or above ,newView won't be created. It will be created only once the app starts.

        //Here p1 is the position in the list
        //Whereas the CourseViewHolder is the object which made above which has the view. It may or may not have data before depending on we have scrolled or not
        p0.itemView.linearLayout.centreName.text = courseList[p1].center
        p0.itemView.linearLayout.teacherName.text = courseList[p1].teacher
        p0.itemView.linearLayout.courseName.text = courseList[p1].name
        p0.itemView.linearLayout.lectures.text = courseList[p1].lectures.toString()

        p0.itemView.setOnClickListener {
            //either do it by this way. We can pass some information in the constructor like the context to the activity if we want to go to the other activity
            // or the array of some text, if we want to go to some other activity.
            //If you don't want to set up onClickListener here, Then use interface for it
            Toast.makeText(context,courseList[p1].center,Toast.LENGTH_SHORT).show() //Here the context is of the activity
        }
    }


}