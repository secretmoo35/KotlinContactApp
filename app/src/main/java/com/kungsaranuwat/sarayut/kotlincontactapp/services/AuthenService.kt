package com.kungsaranuwat.sarayut.kotlincontactapp.services

import android.content.Context
import android.util.Log
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.kungsaranuwat.sarayut.kotlincontactapp.Utilities.URL_REGISTER
import com.kungsaranuwat.sarayut.kotlincontactapp.models.RegisterModel
import org.json.JSONObject

object AuthenService {

    fun registerUser(context: Context, register: RegisterModel, complete: (Boolean) -> Unit) {

        val jsonBody = JSONObject()
        jsonBody.put("firstName", register.firstName)
        jsonBody.put("lastName", register.lastname)
        jsonBody.put("email", register.email)
        jsonBody.put("username", register.username)
        jsonBody.put("password", register.password)

        val requestBody = jsonBody.toString()

        val registerRequest = object : StringRequest(Method.POST, URL_REGISTER, Response.Listener { response ->
            println(response)
            complete(true)
        }, Response.ErrorListener { error ->
            Log.d("Error","Fail to register: ${error}")
            complete(false)
        }) {
            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }

            override fun getBody(): ByteArray {
                return requestBody.toByteArray()
            }
        }

        Volley.newRequestQueue(context).add(registerRequest)

    }

}