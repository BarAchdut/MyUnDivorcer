package com.example.myundivorcer.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.myundivorcer.InterfaceFragmentTitle
import com.example.myundivorcer.R
import com.example.myundivorcer.dataClasses.User
import com.example.myundivorcer.dbHelpers.UsersDatabaseHelper
import com.google.android.material.bottomnavigation.BottomNavigationView

open class BaseActivity : AppCompatActivity(), InterfaceFragmentTitle {
    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var navController: NavController
    private lateinit var dbHelper: UsersDatabaseHelper

    var username: String? = null
    var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base) // Make sure this layout contains NavHostFragment

        dbHelper = UsersDatabaseHelper(this)
        user = dbHelper.getLocallyStoredUser()
        username = user?.username

        // Setup Navigation Controller
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        // Setup Bottom Navigation with NavController
        bottomNavigation = findViewById(R.id.bottomNavigation)
        bottomNavigation.setupWithNavController(navController)

        setUpUpperNavBar()
    }

    internal fun setUpUpperNavBar() {
        val inflater = LayoutInflater.from(this)
        val customUpperNavBar = inflater.inflate(R.layout.upper_nav_bar, null)

        supportActionBar?.setDisplayShowCustomEnabled(true)
        supportActionBar?.customView = customUpperNavBar
    }

    override fun updateTitle(title: String) {
//        val fragmentTitle: TextView = findViewById(R.id.title)
//        fragmentTitle.text = title
    }

    fun onProfileButtonClick(view: View) {
        navController.navigate(R.id.profileFragment)
    }

    fun onBackButtonClick(view: View) {
        if (!navController.popBackStack()) {
            finish()
        }
    }

    fun updateNavigationBarToWishlists() {
        navController.navigate(R.id.wishlistsFragment)
    }
}