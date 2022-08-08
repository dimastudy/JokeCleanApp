package com.justadroiddev.wordscleanarchapp.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.justadroiddev.wordscleanarchapp.R
import com.justadroiddev.wordscleanarchapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }
        val navController: NavController = Navigation.findNavController(this, R.id.activity_nav_host)
        val bottomNavView : BottomNavigationView = binding.bottomNavigation
        NavigationUI.setupWithNavController(bottomNavView, navController)
    }
}