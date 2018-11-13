package com.kungsaranuwat.sarayut.kotlincontactapp.controllers

import android.app.Application
import android.content.Intent
import com.kungsaranuwat.sarayut.kotlincontactapp.utilities.SharedPrefs

class App: Application() {

    companion object {
        lateinit var prefs: SharedPrefs
    }

    override fun onCreate() {
        prefs = SharedPrefs(applicationContext)
        println("On create")
        var intent = Intent(this, LoginActivity::class.java)
        if(prefs.isLogin){
            intent = Intent(this, ContactListActivity::class.java)
        }
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        super.onCreate()
    }
}