package com.example.commerce.presentation.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.commerce.R
import com.example.commerce.common.util.gone
import com.example.commerce.common.util.visible
import com.example.commerce.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setup()
    }

    private fun setup() {
        val navHost =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHost.navController

        with(binding) {
            bottomNavigationView.setupWithNavController(navController)
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            with(binding.bottomNavigationView) {
                when (destination.id) {
                    R.id.signInFragment -> gone()
                    R.id.singnInPasswordFragment -> gone()
                    else -> {
                        visible()
                    }
                }
            }
        }
    }
}