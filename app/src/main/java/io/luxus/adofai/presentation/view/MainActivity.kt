package io.luxus.adofai.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import io.luxus.adofai.R

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navInflater = navHostFragment.navController.navInflater

        val navGraph = navInflater.inflate(R.navigation.navigation_graph)
        val navController = navHostFragment.navController

        // can change start fragment at future.

        navController.graph = navGraph

    }

}