package com.haidv.pocketfb.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.haidv.pocketfb.R

class MainActivity : AppCompatActivity() {

    lateinit var controller: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        controller = findNavController(R.id.navHostFragment)
    }
}