package com.example.room

import androidx.room.Database
import androidx.room.RoomDatabase


//Therefore here we can inlude as many entities(table) in this array and make as many abstract functions we want
//which will have the abstract functions and their return type will be as Dao.
// Therefore No. of abstract functions = No. of entities inluded generally

@Database(entities = arrayOf(Todo::class), version = 1)
abstract class appDataBase : RoomDatabase(){

    abstract fun todoDao() : TodoDao

}

//Like here we have created only 1 table only. But like if we have created multiple table, then we should declare
// all our dao in this abstract class and we will create just the object of this abstract class and access all the todos
