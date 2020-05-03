package com.example.coroutineexample.ui.main.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coroutineexample.data.api.ApiHelper
import com.example.coroutineexample.data.api.RetrofitBuilder
import com.example.coroutineexample.ui.base.ViewModelFactory
import com.example.coroutineexample.ui.main.adapter.MainAdapter
import com.example.coroutineexample.ui.main.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private fun setupViewModel(){
        viewModel = ViewModelProviders.of(this, ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))).get(MainViewModel::class.java)
    }

    private fun setupUI(){
        recyclerView.layoutManager = LinearLayoutManager(this)

    }
}