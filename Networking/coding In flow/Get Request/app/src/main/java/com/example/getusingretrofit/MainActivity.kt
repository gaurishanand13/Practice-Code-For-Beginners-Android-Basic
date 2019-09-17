package com.example.getusingretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {

    var service : retroFitInterface? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(retroFitInterface::class.java)

        //To get all the post in the textView. We will do this.
        //getPost()


        //To get more closer by path
        //getCommentsBypostID()

        // To get all the comments in the textView by their postId. We will do this
        //getComments()



       // getPost_Of_A_particular_userId_in_Sorting_ID()



        //Now to get post of number of post of array of users we do like this
        var myList = ArrayList<Int>()
        myList.add(1)
        myList.add(2)
        val call1= service!!.getPostOfIDs(myList)


        //Now we will learn how to pass multiple queries using hashmaps . It will reduce our code length.
        val myQueries = HashMap<String,String>()
        myQueries.put("userId","1")
        myQueries.put("_sort","id")
        myQueries.put("_order","desc")
        val call2 = service!!.get_Post_By_Values(myQueries)



    }

    private fun getPost_Of_A_particular_userId_in_Sorting_ID() {

        val call = service!!.get_Post_Of_a_Particular_id_in_Asecding_order(1,"id","desc")
        //Now we would execute this call on the background thread.
        call.enqueue(object : Callback<ArrayList<post>>{
            override fun onFailure(call: Call<ArrayList<post>>, t: Throwable) {

                runOnUiThread {
                    textView.text = t.message   //The throwable message will be displayed on the textView.
                }

            }

            override fun onResponse(call: Call<ArrayList<post>>, response: Response<ArrayList<post>>) {
                //Respnse here too can be a failure too like error 404, error 505 etc.
                if(response.isSuccessful)
                {
                    runOnUiThread {
                        val postList = response.body()
                        for(post in postList!!)
                        {
                            val s = """
                                ID : ${post.id} 
                                USER_ID : ${post.userId}
                                TITLE : ${post.title}
                                TEXT : ${post.text}
                                
                            """.trimIndent()

                            textView.append(s)
                        }
                    }
                }
                else
                {
                    runOnUiThread {
                        textView.text = "code : ${response.code()}"
                        return@runOnUiThread
                    }
                }
            }

        })


    }


    fun getPost(){

        val call = service!!.getAllThePost()
        //Now we would execute this call on the background thread.
        call.enqueue(object : Callback<ArrayList<post>>{
            override fun onFailure(call: Call<ArrayList<post>>, t: Throwable) {

                runOnUiThread {
                    textView.text = t.message   //The throwable message will be displayed on the textView.
                }

            }

            override fun onResponse(call: Call<ArrayList<post>>, response: Response<ArrayList<post>>) {
                //Respnse here too can be a failure too like error 404, error 505 etc.
                if(response.isSuccessful)
                {
                    runOnUiThread {
                        val postList = response.body()
                        for(post in postList!!)
                        {
                            val s = """
                                ID : ${post.id} 
                                USER_ID : ${post.userId}
                                TITLE : ${post.title}
                                TEXT : ${post.text}
                                
                            """.trimIndent()

                            textView.append(s)
                        }
                    }
                }
                else
                {
                    runOnUiThread {
                        textView.text = "code : ${response.code()}"
                        return@runOnUiThread
                    }
                }
            }

        })
    }


    private fun getCommentsBypostID() {

        val call = service!!.getParticularIdComments(3)
        //Now we would execute this call on the background thread.
        call.enqueue(object : Callback<ArrayList<post>>{
            override fun onFailure(call: Call<ArrayList<post>>, t: Throwable) {

                runOnUiThread {
                    textView.text = t.message   //The throwable message will be displayed on the textView.
                }

            }

            override fun onResponse(call: Call<ArrayList<post>>, response: Response<ArrayList<post>>) {
                //Respnse here too can be a failure too like error 404, error 505 etc.
                if(response.isSuccessful)
                {
                    runOnUiThread {
                        val postList = response.body()
                        for(post in postList!!)
                        {
                            val s = """
                                ID : ${post.id} 
                                USER_ID : ${post.userId}
                                TITLE : ${post.title}
                                TEXT : ${post.text}
                                
                            """.trimIndent()

                            textView.append(s)
                        }
                    }
                }
                else
                {
                    runOnUiThread {
                        textView.text = "code : ${response.code()}"
                        return@runOnUiThread
                    }
                }
            }

        })
    }


    private fun getComments() {
        val call = service!!.getCommentsById(3)

        call.enqueue(object : Callback<ArrayList<comments>>{
            override fun onFailure(call: Call<ArrayList<comments>>, t: Throwable) {

                runOnUiThread {
                    textView.text = t.message   //The throwable message will be displayed on the textView.
                }

            }

            override fun onResponse(call: Call<ArrayList<comments>>, response: Response<ArrayList<comments>>) {
                //Respnse here too can be a failure too like error 404, error 505 etc.
                if(response.isSuccessful)
                {
                    runOnUiThread {
                        val commentsList = response.body()
                        for(comments in commentsList!!)
                        {
                            val s = """
                                ID : ${comments.id} 
                                POST_ID : ${comments.postId}
                                NAME : ${comments.name}
                                EMAIL : ${comments.email}
                                BODY : ${comments.body}
                                
                            """.trimIndent()

                            textView.append(s)
                        }
                    }
                }
                else
                {
                    runOnUiThread {
                        textView.text = "code : ${response.code()}"
                        return@runOnUiThread
                    }
                }
            }

        })
    }

}
