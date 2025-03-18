package com.example.myundivorcer.activities

import android.os.Bundle
import com.example.myundivorcer.R

class HomeActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        this.setUpUpperNavBar()
//        this.setBottomNavBar()
    }
}
