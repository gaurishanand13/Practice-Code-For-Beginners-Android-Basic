package com.example.usingretrofit

data class User(
    val id:Int,
    val name:String,
    val username:String,
    val email:String,
    val address: Address
)
data class Address(
    val city:String,
    val street:String
)