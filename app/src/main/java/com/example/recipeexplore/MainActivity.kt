package com.example.recipeexplore

import android.content.Context
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.recipeexplore.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import io.github.inflationx.viewpump.ViewPumpContextWrapper

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navHost: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navHost = supportFragmentManager.findFragmentById(R.id.navHost) as NavHostFragment

        binding.mainBottomNav.background = null
        binding.mainBottomNav.setupWithNavController(navHost.navController)

        navHost.navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.splashFragment -> visibilityBottomBar(false)
                R.id.registerFragment -> visibilityBottomBar(false)
                else -> visibilityBottomBar(true)
            }
        }
    }

    private fun visibilityBottomBar(isVisibility: Boolean) {
        binding.apply {
            if (isVisibility) {
                mainBottomAppbar.isVisible = true
                fabMain.isVisible = true
            } else {
                mainBottomAppbar.isVisible = false
                fabMain.isVisible = false
            }
        }
    }

    override fun onNavigateUp(): Boolean {
        return navHost.navController.navigateUp() || super.onNavigateUp()
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase))

    }
}


// add loading progress bar in login fragment
// use regex for email in login fragment
//update all project to flow
//work optional for fab button
//do not use serialize name in models