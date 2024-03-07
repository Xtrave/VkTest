package com.nikkazakov.vktest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment1 = Fragment1()
        supportFragmentManager
            .beginTransaction()
            .add(R.id.activity_main_frame, fragment1)
            .commit()
    }
}