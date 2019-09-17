package com.example.usingretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(),CoroutineScope {

    val supervisor = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() =Dispatchers.Main + supervisor


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            //since we need to launch or call/access the suspend function. Therefore we will do it in a coroutine only
            launch {
                val users = getUserListUsingRetrifit()
                //Now we would set up this list of users in the recyclerView
                recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                recyclerView.adapter = myAdapter(users!!)
            }
        }
    }

    suspend fun getUserListUsingRetrifit(): List<User>?{

        //Now we would link our RetrofitClient and the interface to get the response
        val userApi = RetrofitClient.userApi
        val response = userApi.getUsers()
        return if(response.isSuccessful){
            response.body()
        }else{
            emptyList<User>()
        }
    }
}
