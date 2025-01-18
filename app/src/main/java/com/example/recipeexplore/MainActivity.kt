package com.example.recipeexplore

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import androidx.navigation.ui.setupWithNavController
import com.example.recipeexplore.databinding.ActivityMainBinding
import com.example.recipeexplore.ui.favorite.FavoriteFragment
import com.example.recipeexplore.ui.random.RandomFragment
import com.example.recipeexplore.ui.recipe.RecipeFragment
import com.example.recipeexplore.ui.search.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import io.github.inflationx.viewpump.ViewPumpContextWrapper

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var backPressedTime: Long = 0 // زمان آخرین فشار دکمه‌ی بک

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        findViewById<BottomNavigationView>(R.id.mainBottomNav).selectedItemId = R.id.recipeFragment
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHost) as NavHostFragment
        val navController = navHostFragment.navController
        binding.mainBottomNav.setupWithNavController(navController)

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val currentDestination = navController.currentDestination?.id

                when (currentDestination) {
                    R.id.recipeFragment -> {
                        // نمایش پیام تأیید خروج
                        if (backPressedTime + 2000 > System.currentTimeMillis()) {
                            finish()
                        } else {
                            Toast.makeText(
                                this@MainActivity,
                                "برای خروج دوباره کلیک کنید",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        backPressedTime = System.currentTimeMillis()
                    }

                    R.id.searchFragment, R.id.randomFragment, R.id.favoriteFragment -> {
                        // اگر در یکی از این صفحات هستیم، به صفحه recipeFragment برگردیم
                        binding.mainBottomNav.selectedItemId = R.id.recipeFragment
                        navController.navigate(R.id.recipeFragment)
                    }

                    else -> {
                        // اگر در صفحات دیگر بودیم، به صفحه‌ی قبلی بازگردیم
                        navController.popBackStack()
                    }
                }
            }
        })
    }


    private fun visibilityBottomBar(isVisible: Boolean) {
        binding.apply {
            mainBottomAppbar.isVisible = isVisible
            fabMain.isVisible = isVisible
        }
    }

   /* override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase))
    }*/
}


// add loading progress bar in login fragment
// use regex for email in login fragment
//update all project to flow
//work optional for fab butotn