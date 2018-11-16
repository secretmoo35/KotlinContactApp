package com.kungsaranuwat.sarayut.kotlincontactapp.services

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.Response.Listener
import com.android.volley.toolbox.JsonObjectRequest
import com.kungsaranuwat.sarayut.kotlincontactapp.controllers.App
import com.kungsaranuwat.sarayut.kotlincontactapp.models.ContactModel
import com.kungsaranuwat.sarayut.kotlincontactapp.models.ContactNameModel
import com.kungsaranuwat.sarayut.kotlincontactapp.models.ContactPictureModel
import com.kungsaranuwat.sarayut.kotlincontactapp.utilities.URL_CONTACT_LIST
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

object ContactService {

    val contacts = ArrayList<ContactModel>()

    fun getContactList(context: Context, limit: Number, complete: (Boolean) -> Unit) {

        val jsonBody = JSONObject()
        jsonBody.put("limit", limit)

        val requestBody = jsonBody.toString()

        val request = object : JsonObjectRequest(Method.POST, URL_CONTACT_LIST, null, Listener { response ->
            try {
                val datas = JSONArray(response.getString("datas"))
                for (x in 0 until datas.length() ) {
                    var contact = datas.getJSONObject(x)
                    val name = JSONObject(contact.getString("name"))
                    val picture = JSONObject(contact.getString("picture"))
                    val contactName = ContactNameModel(name.getString("title"),name.getString("first"),name.getString("last"))
                    val contactPicture = ContactPictureModel(picture.getString("large"),picture.getString("medium"),picture.getString("thumbnail"))
                    val gender = contact.getString("gender")
                    val email = contact.getString("email")

                    val contactModel = ContactModel(contactName,gender,email,contactPicture)
                    this.contacts.add(contactModel)
                }
                complete(true)
            } catch (e: JSONException){
                Log.d("JSON", "EXC: ${e.localizedMessage}")
                complete(false)
            }
        }, Response.ErrorListener { error ->
            val networkResponse = error.networkResponse
            if (networkResponse?.data != null) {
                val jsonError = String(networkResponse.data,Charsets.UTF_8)
                val data = JSONObject(jsonError)
                val message = data.getString("message")
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                Log.d("Error","Fail to : $jsonError")
            }
            complete(false)
        }) {

            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }

            override fun getBody(): ByteArray {
                return requestBody.toByteArray()
            }

            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers.put("Authorization", "Bearer ${App.prefs.token}")
                return headers
            }
        }

        App.prefs.requestVolley.add(request)

    }

}