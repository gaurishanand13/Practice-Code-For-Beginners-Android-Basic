package com.example.usingretrofit

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


//Note - > though both for better android layout projects the interface and the object should be in a different class. It looks more systematic.
//But for my convinience so that i can Understand, I have done in the single file.


//First we will create the interface where abstract functions will be present.  Now here the abstract function UserApi
//will extend the Response class. Note - many response class will come as option but you should extend the
// Response class of the retrofit libriary only
interface UserApi{
    //here we will mention the Endpoint of the API(or the the base url) or site from where we have to fetch the data
    @GET("/users")
    suspend fun getUsers(): Response<List<User>>
}

//Therefore as we can see for each different endPoint , we will have to create a different interface until our base url is same




//Now we create a RetrofitClient which would implement this interface
//Here in this retrofit client only we mention the baseUrl of whose endpoint we want to find the data.
object RetrofitClient{

    //Here the name of the variable can be anything. Here we are here just mentioning that the interface it should implement to get the data.
    val userApi = retrofit().create(UserApi::class.java)

    private fun retrofit() = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}