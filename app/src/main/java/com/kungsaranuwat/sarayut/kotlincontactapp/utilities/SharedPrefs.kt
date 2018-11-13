package com.kungsaranuwat.sarayut.kotlincontactapp.utilities

import android.content.Context
import com.android.volley.toolbox.Volley

class SharedPrefs(context: Context) {

    val PREFS_FILENAME = "PrefsKotlinContactApp"
    val prefs = context.getSharedPreferences(PREFS_FILENAME,0)

    val IS_LOGIN = "isLogin"
    val TOKEN = "token"

    var isLogin: Boolean
        get() = prefs.getBoolean(IS_LOGIN, false)
        set(value) = prefs.edit().putBoolean(IS_LOGIN, value).apply()

    var token: String
        get() = prefs.getString(TOKEN, "")
        set(value) = prefs.edit().putString(TOKEN, value).apply()

    val requestVolley = Volley.newRequestQueue(context)

}