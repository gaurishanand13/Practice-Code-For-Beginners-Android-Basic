package com.example.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*

/**
 *  To use room librirary. we need to add these to our manifest :
 *  implementation "androidx.room:room-runtime:2.1.0"
 *  kapt "androidx.room:room-compiler:2.1.0"
 *  apply plugin: 'kotlin-kapt'
 */



class MainActivity : AppCompatActivity() {

    //Here the appDatabase is the dataType of the db. We have made it lazy i.e these lines will be executed only when this variable is used for the first time. Only them this variable will be declared
    val db : appDataBase by lazy {
        Room.databaseBuilder(this, appDataBase::class.java, "todo.db")
            .allowMainThreadQueries()  //Here since we are not doing so large work. Therefore we can perform these queries on the main thread. But if we won't write this then it would perform all the queries on another thread
            .fallbackToDestructiveMigration()  //Here this fallbackToDestructiveMigration is used when like we want to change the version of the table like adding a new row. Then we want to delete the previous data of the table and add another data.
            .build()
    }

    var list = arrayListOf<Todo>()
    var fillForm : MutableLiveData<Boolean> = MutableLiveData()
    var mediatorLiveData : MediatorLiveData<Boolean> = MediatorLiveData()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = TaskAdapter(list)
        /**
         * Now we are setting an observer on this Live data
         */
        db.todoDao().getAllTheTodosLive().observe(this, Observer {
            adapter.updateTasks(it as ArrayList<Todo>)
        })

        adapter.listItemClickListener = object : ListItemClickListener{
            override fun listItemClicked(todo: Todo, position: Int) {
                db.todoDao().deleteParticularTodo(todo)
            }
        }
        listView.adapter = adapter

        addBtn.setOnClickListener {
            db.todoDao().insertRow(
                Todo(
                    task = editText.text.toString(),
                    done = false
                )
            )
        }

    }
}
