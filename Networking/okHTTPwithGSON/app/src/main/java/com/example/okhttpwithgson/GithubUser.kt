package com.example.okhttpwithgson


//We can also see that for each JSON object , we can actually miss some keys, it is not necessary that we should use all the items in the API website
data class ApiResult(val items:Array<GithubUser>)

data class GithubUser(val login:String,val id:Int,val html_url:String,val score:Double,val avatar_url:String)

//though the above classes should be in seperate kotlin files to make our code better. But here for my understanding,i have copied all the things in one folder.