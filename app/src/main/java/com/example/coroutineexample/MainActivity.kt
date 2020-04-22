package com.example.coroutineexample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*

class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)

        //코루틴 생성
        val job = GlobalScope.launch(Dispatchers.Default){
            repeat(10){
                delay(1000L)
                Log.i("TAG", "I'm Working")
            }
        }

        //3초 후 코루틴 종료
        runBlocking {
            delay(3000L)
            job.cancel()
            Log.i("TAG", "Coroutine is done")
        }

    }
}