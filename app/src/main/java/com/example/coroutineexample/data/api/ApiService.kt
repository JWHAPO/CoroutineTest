package com.example.coroutineexample.data.api

import com.example.coroutineexample.data.model.User
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers(): List<User>
}