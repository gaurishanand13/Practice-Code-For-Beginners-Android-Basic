package com.example.usingretrofit

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.view_for_each_item.view.*

class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)

class myAdapter(private val users:List<User>) : RecyclerView.Adapter<ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val li = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = li.inflate(R.layout.view_for_each_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount() = users.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = users[position]
        holder.itemView.linearLayout.textView.text = user.email
        holder.itemView.linearLayout.textView2.text = user.username
        holder.itemView.linearLayout.textView3.text = user.name
    }
}