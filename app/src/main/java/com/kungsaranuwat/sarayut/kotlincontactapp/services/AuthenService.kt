package com.kungsaranuwat.sarayut.kotlincontactapp.services

import android.content.Context
import android.util.Log
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import com.kungsaranuwat.sarayut.kotlincontactapp.utilities.URL_REGISTER
import com.kungsaranuwat.sarayut.kotlincontactapp.models.RegisterModel
import org.json.JSONObject
import android.widget.Toast
import com.android.volley.toolbox.JsonObjectRequest
import com.kungsaranuwat.sarayut.kotlincontactapp.controllers.App
import com.kungsaranuwat.sarayut.kotlincontactapp.models.LoginModel
import com.kungsaranuwat.sarayut.kotlincontactapp.utilities.URL_LOGIN


object AuthenService {

    fun registerUser(context: Context, register: RegisterModel, complete: (Boolean) -> Unit) {

        val jsonBody = JSONObject()
        jsonBody.put("firstName", register.firstName)
        jsonBody.put("lastName", register.lastname)
        jsonBody.put("email", register.email)
        jsonBody.put("username", register.username)
        jsonBody.put("password", register.password)

        val requestBody = jsonBody.toString()

        val request = object : JsonObjectRequest(Method.POST, URL_REGISTER, null,Response.Listener { response ->
            App.prefs.token = response.getString("token")
            App.prefs.userProfile = response.getString("data")
            App.prefs.isLogin = true
            println(response)
            complete(true)
        }, Response.ErrorListener {error ->
            val networkResponse = error.networkResponse
            if (networkResponse?.data != null) {
                val jsonError = String(networkResponse.data,Charsets.UTF_8)
                val data = JSONObject(jsonError)
                val message = data.getString("message")
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                Log.d("Error","Fail to register: $jsonError")
            }
            complete(false)
        }) {

            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }

            override fun getBody(): ByteArray {
                return requestBody.toByteArray()
            }
        }

        App.prefs.requestVolley.add(request)

    }

    fun loginUser(context: Context, login:LoginModel, complete: (Boolean) -> Unit) {

        var jsonBody = JSONObject()
        jsonBody.put("username", login.username)
        jsonBody.put("password", login.password)

        var requestBody = jsonBody.toString()

        val request = object : JsonObjectRequest(Method.POST, URL_LOGIN, null,Response.Listener { response ->
            App.prefs.token = response.getString("token")
            App.prefs.userProfile = response.getString("data")
            App.prefs.isLogin = true
            complete(true)
        }, Response.ErrorListener {error ->
            val networkResponse = error.networkResponse
            if (networkResponse?.data != null) {
                val jsonError = String(networkResponse.data,Charsets.UTF_8)
                val data = JSONObject(jsonError)
                val message = data.getString("message")
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                Log.d("Error","Fail to login: $jsonError")
            }
            complete(false)
        }) {

            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }

            override fun getBody(): ByteArray {
                return requestBody.toByteArray()
            }
        }

        App.prefs.requestVolley.add(request)
    }

}

