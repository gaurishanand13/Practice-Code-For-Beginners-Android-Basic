package com.example.okhttpwithgson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            try {
                makeNetworkCall("https://api.github.com/search/users?q=harshit")
            }catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

     fun makeNetworkCall(url:String) {

        try {
            //first set up the OkHTTP client
            val okHttpClient = OkHttpClient()
            val request = Request.Builder().url(url).build()
            // Now we would make a request to the API or some other site(with the help of the OkHTTpClient)

            //Note this call request is automatically done on another thread automatically. Therefore we don't need to write this code
            //on the async task. Here the object creted is the object of an abstract class. Therefore we have implemented its abstract functions
            okHttpClient.newCall(request).enqueue(object : Callback{
                override fun onFailure(call: Call, e: IOException) {
                    //This function will be called only if it leads to the faliure of data from the web
                }

                override fun onResponse(call: Call, response: Response) {
                    //This function will be called only after all the data has been taken from the API. It can be seen as onPOSTExecute function
                    // the data which it gets is stored in this response
                    val result : String = response.body()!!.string()
                    Log.i("TAG",result)
                    val allTheGithubUser : Array<GithubUser> = parseJSON(result)

                    //since now we have got the all the user's list. we should set up this on the recyclerView. But since we can't thread on this background thread.
                    // we should first get access to the MAIN thread.
                    runOnUiThread {
                        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                        recyclerView.adapter = myAdapter(allTheGithubUser)
                    }
                }

            })
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    private fun parseJSON(result: String): Array<GithubUser> {
        Log.i("TAG","inParse")
        // Now instead of parsing the JSON data by that long way we will use the JSON libriary(which is the libriary provided by google only)
        val gson : Gson = Gson()

        //first argument we pass here is the string of the JSON we would want to convert
        //2nd argument is the class with which you want the gson to match. It would automatically search the members of the class and match it with the key
        //mentioned on the API website.
        val apiResult:ApiResult = gson.fromJson(result,ApiResult::class.java)
        Log.i("TAG",apiResult.items[0].html_url)
        return apiResult.items
    }
}
