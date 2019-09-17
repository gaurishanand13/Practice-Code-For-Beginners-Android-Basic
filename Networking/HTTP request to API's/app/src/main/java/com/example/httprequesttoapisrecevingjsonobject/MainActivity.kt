package com.example.httprequesttoapisrecevingjsonobject

import android.content.Context
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.*
import kotlin.collections.ArrayList

//Note -> we already extracted HTTP data from the normal sites like google.com etc. Now we would extract data from the API (websites which provide
// data in the JSON format to an HTTP request). Since we can easily extract data from them by parsing them into an json Object.
//We can easily see that in the json, corresponding to each value there is corresponding key which is used to get data from them

//Here we will first extract data from an API site and then use that data.

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView:RecyclerView = findViewById(R.id.recyclerView)

        button.setOnClickListener{
            netWorkText(recyclerView,this).execute("https://api.github.com/search/users?q=harshit")
        }
    }


    //I have added this word inner so that we can use all the members and functions declared above their scope can be extened in this async class.
    // Like here i have passed it in the function prototype but this could be easily access by their id's if we write this inner
    inner class netWorkText( val recyclerView: RecyclerView,var context: Context) : AsyncTask<String, Void, String>(){
        override fun doInBackground(vararg p0: String?): String {
            val url = p0[0]
            try {
                val url = URL(url)
                val httpURLConnection = url.openConnection() as HttpURLConnection
                val inputStream = httpURLConnection.inputStream
                val scanner = Scanner(inputStream)
                scanner.useDelimiter("\\A")
                if(scanner.hasNext()!=null)
                {
                    val s = scanner.next()
                    return s
                }
            }catch (e : MalformedURLException){
                Toast.makeText(context,"ENTER THE CORRECT URL", Toast.LENGTH_SHORT).show()
            }catch (e: IOException) {
                Toast.makeText(context,e.printStackTrace().toString(), Toast.LENGTH_SHORT).show()
            }
            return "FAILED TO LOAD"
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)

            //We would recieve the json data in String format and now we would convert it into the array list of type github users
            val allTheUsers =  ArrayList<GithubUser>() //first making the object

            try {
                //First we will convert the data we recieve to a type of JSON object.
                val mainJSONObject : JSONObject = JSONObject(result)



                //Now we can easily see that in the above JSON object, there are 2 JSON object, whereas 3rd one is an array of some other JSONObjects.
                //we will get it using this statement
                val itemArrays: JSONArray = mainJSONObject.getJSONArray("items")

                //Now since the the above array has many Json Objects whose data we want to put in the adapter of our class. Therefore first extract data
                // from each oject of this array

                Log.i("TAG",itemArrays.length().toString())
                for(i in 0..8)
                {
                    //Now get access to particular JSON object.
                    val jsonObject = itemArrays.getJSONObject(i)
                    val id = jsonObject.getInt("id") //green green in all is the key of each data which is mentioned in the API's
                    val login = jsonObject.getString("login")
                    val avatar = jsonObject.getString("avatar_url")
                    val score = jsonObject.getDouble("score")
                    val html = jsonObject.getString("html_url")
                    val user:GithubUser = GithubUser(login,id,html,score,avatar)
                    allTheUsers.add(user)
                }

                recyclerView.layoutManager = LinearLayoutManager(context)
                recyclerView.adapter = myAdapter(allTheUsers)

            } catch (e : JSONException){
                Log.i("TAG",e.localizedMessage)
                Toast.makeText(context,"PLease make sure the string is in the form of JSON format",Toast.LENGTH_LONG).show()
            }
        }
    }
}
