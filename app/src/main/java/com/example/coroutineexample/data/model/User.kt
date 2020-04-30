package com.example.coroutineexample.data.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("avatar")
    val image: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val userName: String
)