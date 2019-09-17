package com.example.httprequesttoapisrecevingjsonobject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.view_for_each_item.view.*

class GithubUserViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView)


//since this RecyclerView.Adapter class takes an object of a class which extends the class "RecyclerView.ViewHolder"
class myAdapter(val githubUsersData:ArrayList<GithubUser>):RecyclerView.Adapter<GithubUserViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubUserViewHolder {
        val li = parent.context.applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView = li.inflate(R.layout.view_for_each_item,parent,false)
        val githubUserViewHolder = GithubUserViewHolder(itemView)
        return githubUserViewHolder
    }

    override fun getItemCount() = githubUsersData.size

    override fun onBindViewHolder(holder: GithubUserViewHolder, position: Int) {
        holder.itemView.linearLayout.loginTextView.text = githubUsersData[position].login
        holder.itemView.linearLayout.scoreTextView.text = githubUsersData[position].score.toString()

        //First add This is an external libriary in build.gradle which helps us to download the images from the net if we are given an url.
        //this libriary works on on another thread only internally. in into we set the image view where we want to set the image
        Picasso.get().load(githubUsersData[position].avatar_url).into(holder.itemView.imageView)
    }
}