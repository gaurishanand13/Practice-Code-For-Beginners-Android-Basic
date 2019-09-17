package com.example.httprequesttoapisrecevingjsonobject

//The url of the api i have provided has the data of many Github accounts with the name "Harshit".
//Therefore we will show each account on the listView by extracting the data from the website

//(though the above mentioned comments can be seen by going to the API), but i am still mentioning it.

//Here the login variable indicates the login id
//id is of type int which is unique for a particular user on gitHub
// html_url is the link to profile of that user
//score is the score of the user on the github
//avatar_url is the url to the profile picture (image) // So from here we can download the profile image of the user

data class GithubUser(val login:String,val id:Int,val html_url:String,val score:Double,val avatar_url:String)