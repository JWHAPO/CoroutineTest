package com.example.coroutineexample

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*
import java.lang.AssertionError
import java.lang.Runnable
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class MainActivity: AppCompatActivity(), CoroutineScope {

    private lateinit var mJob: Job
    override val coroutineContext: CoroutineContext
        get() = mJob + Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)

        mJob = Job()

        launch(handler) {
            val result = getFromCallback()
            Log.i("TAG", "result:$result")
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

    private val handler = CoroutineExceptionHandler{
        coroutineContext, throwable -> Log.e("Exception", ":$throwable")
    }

    private suspend fun getFromCallback() = suspendCoroutine<Int>{
        Handler().postDelayed({
            it.resume(15)

//            it.resumeWith(Result.success(15))
//
//            it.resumeWith(Result.failure(AssertionError()))
        }, 2000)
    }

    override fun onDestroy() {
        super.onDestroy()
        mJob.cancel()
    }
}