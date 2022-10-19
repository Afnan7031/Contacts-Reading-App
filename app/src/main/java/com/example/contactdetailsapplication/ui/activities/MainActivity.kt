package com.example.contactdetailsapplication.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.contactdetailsapplication.R
import com.example.contactdetailsapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initNavController()
    }

    private fun initNavController() {
        navController = (supportFragmentManager.findFragmentById(binding.navHostContainer.id) as NavHostFragment).navController
        setSupportActionBar(binding.toolbarMain)
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.homeFragment))
        binding.toolbarMain.setupWithNavController(navController, appBarConfiguration)
    }
}