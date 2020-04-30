package com.example.coroutineexample.data.repository

import com.example.coroutineexample.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getUsers() = apiHelper.getUsers()

}