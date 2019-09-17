package com.example.gettingthefromthegoogle

import android.content.Context
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException
import java.lang.AssertionError
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.*

class MainActivity : AppCompatActivity() {

    var textView:TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener{
            //Now we want to fetch the HTTP data from the internet. For that first we need to provide the permission to acces the
            //internet in the manifest file(which is a normal permission, Therefore don't need to ask it at the run Time).

            //Note we don't perform any network operation on the mainThread as it slows donw our app and can even crash the app if
            //it takes a lot of time to fetch the data

            textView = findViewById(R.id.textView)
            netWorkText(textView!!,this).execute("https://www.google.com/","https://codingblocks.com/")
        }
    }

    //Here since we want to pass the input url to the doInBackground function(url as string). Therefore here Param is of type String.
    //Since we don't want to update any progress, therfore 2nd type is of void.
    //Since we want to update the HTTP data on the TextView which can be the done only UI thread, Therefore we pass the result(of type string) to the onPostExecute function

    class netWorkText(var textView:TextView,var context: Context) : AsyncTask<String,Void,String>(){
        override fun doInBackground(vararg p0: String?): String {
            // The 2 paramaters were passed as i wanted to show you we can as many data of type string as its a string url.

            val url1 = p0[0]
            val url2 = p0[1]
            //for now we will fetch the data of only url1 and display it in the textView.

            try {
                //first we will make an object of type url. While making the url object it may happen that the user entered an incorrect url. Therefore always surround it in a try /catch
                val url = URL(url1)

                //Now we will connect with this URL through internet Connection
                val httpURLConnection = url.openConnection() as HttpURLConnection
                val inputStream = httpURLConnection.inputStream //This statement gets us the input Stream for that url
                val scanner = Scanner(inputStream)

                //we should have usually read bit by bit of the data. But since we know my phone has enough storage. Therefore we will read it one go
                scanner.useDelimiter("\\A") //this statemnt is asking the scanner to read the data in one go.

                if(scanner.hasNext()!=null)
                {
                    val s = scanner.next() //Generally this statement is used to read next byte of data if we are reading the data bit by bit. Since we are reading in the one go,
                    // Therefore writing it only once, we will get the whole data, Therefore we don't need to make it in a loop.
                    return s
                }
            }catch (e : MalformedURLException){
                Toast.makeText(context,"ENTER THE CORRECT URL",Toast.LENGTH_SHORT).show()
            }catch (e: IOException) {
                Toast.makeText(context,e.printStackTrace().toString(),Toast.LENGTH_SHORT).show()
            }
            return "FAILED TO LOAD"
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            textView.text = result //since it may happen that the entire data doesn't come on one view . That is why we have used scroll View.
        }
    }
}
