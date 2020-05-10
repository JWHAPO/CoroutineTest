package com.example.coroutineexample.ui.main.view

import android.os.Bundle
import android.preference.PreferenceManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.coroutineexample.R
import com.getkeepsafe.taptargetview.TapTarget
import com.getkeepsafe.taptargetview.TapTargetSequence
import com.getkeepsafe.taptargetview.TapTargetView
import com.google.android.material.bottomnavigation.BottomNavigationView

class StartActivity:AppCompatActivity() {

    private val fragment1 : Fragment = MainFragment()
    private val fragment2 : Fragment = Tab2Fragment()
    private val fm: FragmentManager = supportFragmentManager
    private var active: Fragment = fragment1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        fm.beginTransaction().add(R.id.fragment_container, fragment1, "1").hide(fragment1).commit()
        fm.beginTransaction().add(R.id.fragment_container, fragment2, "2").hide(fragment2).commit()

        fm.beginTransaction().hide(active).show(fragment1).commit()
        active = fragment1

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.page_1 -> {
                    fm.beginTransaction().hide(active).show(fragment1).commit()
                    active = fragment1
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.page_2 -> {
                    fm.beginTransaction().hide(active).show(fragment2).commit()
                    active = fragment2
                    return@setOnNavigationItemSelectedListener true
                }
            }
            return@setOnNavigationItemSelectedListener false
        }

        showTabPromptSequence()

    }

    private fun showTabPromptSequence(){
        TapTargetSequence(this)
            .targets(
                TapTarget.forView(findViewById(R.id.page_1), "이 탭1 을 클릭하세요"),
                TapTarget.forView(findViewById(R.id.page_2), "이 탭2를 클릭하세요.", "OK?")
                    .dimColor(android.R.color.holo_blue_dark)
            )
            .listener(object: TapTargetSequence.Listener{
                override fun onSequenceCanceled(lastTarget: TapTarget?) {
                }

                override fun onSequenceFinish() {
                }

                override fun onSequenceStep(lastTarget: TapTarget?, targetClicked: Boolean) {
                }
            }).start()
    }

    private fun showTabPrompt(){
        TapTargetView.showFor(this,
            TapTarget.forView(findViewById(R.id.page_1), "이것을 누르면 탭 1이 열립니다.")
                .outerCircleColor(R.color.design_default_color_primary)
                .targetRadius(60),
            object : TapTargetView.Listener(){
                override fun onTargetClick(view: TapTargetView) {
                    super.onTargetClick(view)
                    view.dismiss(true)
                }
            }
        )
    }
}