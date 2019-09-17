package com.example.getusingretrofit

import android.provider.ContactsContract
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap


data class post(
    val userId:Int,

    val id:Int,

    val title:String,

    @SerializedName("body")
    val text: String
)

//Generally we use serialise name by chance if we want to give the variable our name and that name does't match with the key
// on the api. It is also used if the key name of the api doesn't follow the syntax to declare variable in kotlin

data class comments(
    val postId : Int,
    val id : Int,
    val name : String,
    val email : String,
    val body : String
)


public interface retroFitInterface{



    @GET("posts")
    fun getAllThePost (): Call<ArrayList<post>>    //Here in this call method we will mention the thing which we want to return from the http api



    //https://jsonplaceholder.typicode.com/post/3/comments  -> for the down function endpoint looks like this.
    @GET("post/{id}/comments")    //Here the name value written in the path statement should be same as the name written in the @GET() statement
    fun getParticularIdComments(@Path("id") idOfThePost :Int) : Call<ArrayList<post>>



    //Now we will see about the query parameter
    //https://jsonplaceholder.typicode.com/comments?postId=3   Therefore in 3rd endpoints look like this. This is the query parameter
    @GET("comments")      //here the value in the query should be same as
    fun getCommentsById(@Query("postId") postId: Int) : Call<ArrayList<comments>>

    //Therefore we can see 2 of  above the methods are almost same. In 2nd method we have the postId and with that we are finding the comments of the user which has that particular id
    //In 3rd one we have all the comments of all the users there we are seaching comments for a particular post id


    //Here is the way how can we use multiple parameters in a single function to get particular
    @GET("posts") // -> It looks like this - https://jsonplaceholder.typicode.com/posts?userId=1&sort=id&_order=desc
    fun get_Post_Of_a_Particular_id_in_Asecding_order(@Query("userId") userId: Int,@Query("_sort") sort : String,@Query("_order") order : String) : Call<ArrayList<post>>



    //Now like above we have seen how can we get the post of a particular id. But what if we want the data of post of not just id. But a multiple of them. We can do this in this way -
    @GET("posts")
    fun getPostOfIDs(@Query("userId") users : ArrayList<Int>) : Call<ArrayList<post>>  //Therefore here we can multiple user's id whose data of the post we want to get



    //Now like we don't want to specify all these values again and again for all the values because then it makes our task very lengthy as we have to make a new function for each new endpoint or query
    @GET("posts")
    fun get_Post_By_Values(@QueryMap qeryParameterName : HashMap<String,String>) : Call<ArrayList<post>>
}