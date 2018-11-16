package com.kungsaranuwat.sarayut.kotlincontactapp.utilities

import android.content.Context
import com.android.volley.toolbox.Volley
import com.kungsaranuwat.sarayut.kotlincontactapp.models.UserModel

class SharedPrefs(context: Context) {

    val PREFS_FILENAME = "PrefsKotlinContactApp"
    val prefs = context.getSharedPreferences(PREFS_FILENAME,0)

    val IS_LOGIN = "isLogin"
    val TOKEN = "token"
    val USER_PROFILE = "user"

    var isLogin: Boolean
        get() = prefs.getBoolean(IS_LOGIN, false)
        set(value) = prefs.edit().putBoolean(IS_LOGIN, value).apply()

    var token: String
        get() = prefs.getString(TOKEN, "")
        set(value) = prefs.edit().putString(TOKEN, value).apply()

    var userProfile: String
        get() = prefs.getString(USER_PROFILE, "")
        set(value) = prefs.edit().putString(USER_PROFILE, value).apply()

    val requestVolley = Volley.newRequestQueue(context)

}