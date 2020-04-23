package com.example.coroutineexample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivity: AppCompatActivity(), CoroutineScope {

    private lateinit var mJob: Job
    override val coroutineContext: CoroutineContext
        get() = mJob + Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)

        mJob = Job()

        launch {
            Log.i("TAG", Thread.currentThread().name)
            val deferred = async(Dispatchers.Default){
                10 + 10
            }
            Log.i("TAG", deferred.await().toString())
        }

//        //코루틴 생성
//        val job = GlobalScope.launch(Dispatchers.Default){
//            repeat(10){
//                delay(1000L)
//                Log.i("TAG", "I'm Working")
//            }
//        }

        //3초 후 코루틴 종료
//        runBlocking {
//            delay(3000L)
//            job.cancel()
//            Log.i("TAG", "Coroutine is done")
//        }

    }

    override fun onDestroy() {
        super.onDestroy()
        mJob.cancel()
    }
}