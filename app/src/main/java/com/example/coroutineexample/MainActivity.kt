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

class MainActivity: AppCompatActivity(), CoroutineScope,LifecycleObserver {

    private lateinit var tvUserName: TextView

    private lateinit var mJob: Job

    override val coroutineContext: CoroutineContext
        get() = mJob + Dispatchers.Main

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun destroy() = coroutineContext.cancelChildren()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)

        tvUserName = findViewById(R.id.tvUserName)

        mJob = Job()

        launch(handler) {
            val result = getFromCallback()
            Log.i("TAG", "result:$result")

        }

        runBlocking {
            foo2().collect { value -> println(value) }
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

//            it.resumeWith(Result.failure(AssertionError()))
        }, 2000)
    }

    override fun onDestroy() {
        super.onDestroy()
        mJob.cancel()
    }

    fun foo2(): Flow<Int> = flow {
        for(i in 1..3){
            delay(1000L)
            emit(i)
        }
    }

}