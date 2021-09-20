package com.hardbobby.cryptocurrencyapp.presentation.main.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.hardbobby.cryptocurrencyapp.R
import com.hardbobby.cryptocurrencyapp.databinding.ActivityMainBinding
import com.hardbobby.cryptocurrencyapp.presentation.main.viewmodel.MainHomeViewModel
import com.hardbobby.cryptocurrencyapp.presentation.utils.setGone
import com.hardbobby.cryptocurrencyapp.presentation.utils.setVisible
import com.hardbobby.cryptocurrencyapp.presentation.utils.showSuccessSnackbar
import com.hardbobby.cryptocurrencyapp.presentation.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import observeEvent


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityMainBinding::inflate)
    private val viewModel: MainHomeViewModel by viewModels()
    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupView()
        setUpObserver()
    }

    private fun setUpObserver() {
        viewModel.snackbarMessage().observeEvent(this) { message ->
            binding.root.showSuccessSnackbar(message)
        }
    }

    private fun setupView() {
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        NavigationUI.setupWithNavController(
            binding.bottomNavigationMenu,
            navHostFragment.navController
        )

        val appBarConfiguration = AppBarConfiguration(
            topLevelDestinationIds = setOf(
                R.id.watchListFragment,
                R.id.dataFeedFragment,
                R.id.loginFragment,
                R.id.accountFragment
            )
        )

        setupActionBarWithNavController(navHostFragment.navController, appBarConfiguration)
    }

    fun showBottomNavigation() {
        binding.bottomNavigationMenu.setVisible()
    }

    fun hideBottomNavigation() {
        binding.bottomNavigationMenu.setGone()
    }

    fun logout() {
        viewModel.onUserSignOut()
        navHostFragment.findNavController().navigate(R.id.loginFragment, null)
    }

    override fun onBackPressed() {
        val navigationController = navHostFragment.findNavController()
        val currentDestId = navigationController.currentDestination?.id
        if (currentDestId == R.id.watchListFragment || currentDestId == R.id.loginFragment || currentDestId == R.id.accountFragment) {
            finish()
        } else {
            super.onBackPressed()
        }
    }
}