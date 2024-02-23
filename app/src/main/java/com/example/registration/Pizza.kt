package com.example.registration
import java.io.Serializable

data class Pizza(
    val title:String,
    val price:String,
    val imageRes:Int,
    val description:String
) : Serializable

