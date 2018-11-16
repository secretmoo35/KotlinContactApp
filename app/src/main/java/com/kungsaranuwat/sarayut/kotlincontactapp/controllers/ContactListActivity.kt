package com.kungsaranuwat.sarayut.kotlincontactapp.controllers

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.kungsaranuwat.sarayut.kotlincontactapp.R
import com.kungsaranuwat.sarayut.kotlincontactapp.adapters.ContactRecycleAdapter
import com.kungsaranuwat.sarayut.kotlincontactapp.services.ContactService
import kotlinx.android.synthetic.main.activity_contact_list.*
import org.json.JSONObject

class ContactListActivity : AppCompatActivity() {

    lateinit var adapter : ContactRecycleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_list)
//        val displayName = JSONObject(App.prefs.userProfile).getString("displayName")
//        println("Welcome: $displayName")
        loadData()
    }

    private fun loadData(){
        loading(true)

        ContactService.getContactList(this,1000) { complete ->
            if(complete) {
                adapter = ContactRecycleAdapter(this, ContactService.contacts) { contact ->

                }
                contactView.adapter = adapter

                val layoutManager = LinearLayoutManager(this)
                contactView.layoutManager = layoutManager
            }

            loading(false)

        }
    }

    private fun loading(isLoading : Boolean){
        if(isLoading) {
            progressBar.visibility = View.VISIBLE
        }else {
            progressBar.visibility = View.INVISIBLE
        }
    }
}
