package com.example.coroutineexample.ui.main.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.coroutineexample.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class StartActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        bottomNavigationView.setOnNavigationItemSelectedListener {
            val selectedFragment: Fragment = when(it.itemId){
                R.id.page_1 -> MainFragment()
                R.id.page_2 -> Tab2Fragment()
                else -> MainFragment()
            }

            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, selectedFragment).commit()
            true
        }

    }
}