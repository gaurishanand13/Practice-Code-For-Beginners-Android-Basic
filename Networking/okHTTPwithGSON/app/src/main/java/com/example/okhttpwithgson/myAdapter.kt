package com.example.okhttpwithgson

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.view_for_each_item.view.*

class GithubUserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


//since this RecyclerView.Adapter class takes an object of a class which extends the class "RecyclerView.ViewHolder"
class myAdapter(val githubUsersData:Array<GithubUser>): RecyclerView.Adapter<GithubUserViewHolder>(){
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
        Picasso.get().load(githubUsersData[position].avatar_url).into(holder.itemView.imageView)
    }
}