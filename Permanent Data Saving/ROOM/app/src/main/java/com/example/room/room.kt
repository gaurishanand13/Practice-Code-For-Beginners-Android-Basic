package com.example.room

import androidx.lifecycle.LiveData
import androidx.room.*

//If i have not mentioned the table name, then it would have automatically created the table with table name "TODO"(class name)
@Entity(tableName = "TodosTable")
data class Todo(
    @PrimaryKey(autoGenerate = true)
    var id : Long? = null,

    @ColumnInfo(name = "task")
    var task : String? = null,

    var done : Boolean = false
)
//Juts writing the "entity" above, It will create the table in the database with Table name "Todo"
//and the name of the columns in this table is id,task,done. Therefore just writing the above data class with the keyword
//entity. it will create this table. To access this table we will create an interface and use annotation of Dao with it.



@Dao //Data access object -> just writing this we will be able to access this database and make changes in it.
interface TodoDao{

    /**
     * First we will see abou the insert query
     */
    @Insert
    fun insertRow(todo : Todo)

    @Insert
    fun insterMultipleRows(todo : ArrayList<Todo>)


    /**
     * Now we will see about the search query
     */
    @Query("Select * FROM TodosTable")
    fun getAllTheTodos() : List<Todo>

    @Query("Select * FROM TodosTable") //This query is exact same as above. The only difference lies is its return type. Now since its return type is of type Live Data.
    fun getAllTheTodosLive() : LiveData<List<Todo>>// Therfore whenever the data it is returning changes. The observer on this data will get executed. In short if we will make any changes in the table and since it return all the todoes. Thefore any change in the Todo. The observer on this live data will come into play

    /**
     * <---------------------------------------------------------------------------------------------------------------------------->
     */
    @Query("Select * FROM TodosTable WHERE done = :data")  //Therefore to access the data from the function parameter in the sql queries we need to use : to access it
    fun getAllTodosWhere_Task_is_Done(data : Boolean) : List<Todo>


    /**
     * Delete query
     */
    @Query("Delete From TodosTable")
    fun deleteAllTodos()

    @Delete
    fun deleteParticularTodo(todo: Todo)
}

//Though we have linked our Dao with our data class.
// But we need to create an abstract class officially which will have access to all our dao's