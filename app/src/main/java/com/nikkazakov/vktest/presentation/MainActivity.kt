package com.nikkazakov.vktest.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nikkazakov.vktest.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val productsFragment = ProductsFragment()
        supportFragmentManager
            .beginTransaction()
            .add(R.id.activity_main_frame, productsFragment)
            .commit()
    }
}