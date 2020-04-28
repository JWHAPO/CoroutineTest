package com.example.coroutineexample

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class MainActivity: AppCompatActivity() {

    private lateinit var tvUserName: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)

        tvUserName = findViewById(R.id.tvUserName)

        GlobalScope.launch(Dispatchers.Main) {
            val userOne = async(Dispatchers.IO) { fetchFirstUser() }
            val userTwo = async(Dispatchers.IO) { fetchSecondUser() }
            showUsers(userOne.await(), userTwo.await()) // back on UI thread
        }
    }
//    suspend fun fetchAndShowUser() {
//        val user = fetchUser()
//        showUser(user)
//    }

    suspend fun fetchFirstUser() : String{
        delay(1000L)
        return "KJW1"
    }

    suspend fun fetchSecondUser() : String{
        delay(1000L)
        return "KJW2"
    }

    fun showUsers(user1: String, user2: String){
        tvUserName.text = "User1 : $user1 , User2: $user2"
    }
}