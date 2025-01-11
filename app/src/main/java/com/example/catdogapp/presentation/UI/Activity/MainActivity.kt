package com.example.catdogapp.presentation.UI.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.catdogapp.R
import com.example.catdogapp.databinding.ActivityMainBinding
import com.example.catdogapp.presentation.UI.Fragment.CatFragment
import com.example.catdogapp.presentation.UI.Fragment.DogFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController


        val bottomNavigationView = binding.bottomNav
        bottomNavigationView.setupWithNavController(navController)
        bottomNavigationView.setItemIconTintList(null)


        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.nav_translate, R.id.nav_cat, R.id.nav_dog)
        )

        val isCatPressed = intent.getBooleanExtra("isCatPressed", false)
        val selection = intent.getStringExtra("selection")

        if (isCatPressed && selection == "sound") {
            bottomNavigationView.selectedItemId = R.id.nav_cat
            navController.navigate(R.id.nav_cat)
        } else if (!isCatPressed && selection == "sound") {
            bottomNavigationView.selectedItemId = R.id.nav_dog
            navController.navigate(R.id.nav_dog)
        }
    }


}