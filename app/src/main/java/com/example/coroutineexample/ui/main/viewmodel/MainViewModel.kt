package com.example.coroutineexample.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.coroutineexample.data.repository.MainRepository
import com.example.coroutineexample.utils.Resource
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    fun getUsers() = liveData(Dispatchers.IO){
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getUsers()))
        }catch (exception: Exception){
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

}