package com.example.myundivorcer.utils

import com.example.myundivorcer.dataClasses.User

interface LoginCallback {
    fun onLoginResult(user: User?)
}