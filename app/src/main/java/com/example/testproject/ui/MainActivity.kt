package com.example.testproject.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.testproject.R
import com.example.testproject.base.KotlinBaseActivity
import com.example.testproject.databinding.ActivityMainBinding
import com.example.testproject.ui.home.HomeViewModel
import com.example.testproject.ui.home.serverReq.HomeServerRequests

class MainActivity : KotlinBaseActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var navGraph: NavGraph

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpNavigation()


    }

    fun setUpNavigation() {
        val navHostFragment = (supportFragmentManager.findFragmentById(R.id.my_nav_host_fragment) as NavHostFragment)
        navController = navHostFragment.navController
        navGraph = navController.navInflater.inflate(R.navigation.my_nav)
        //setStartDestination()
        setupWithNavController(binding.bottomNavigationView, navController)
        setupActionBarWithNavController(navController)

        // badge
//        val badge = binding.bottomNavigationView.getOrCreateBadge(R.id.nav_cart)
//        badge.backgroundColor = ContextCompat.getColor(this, R.color.purple_200)
//        badge.badgeTextColor = ContextCompat.getColor(this, R.color.white)
    }
}