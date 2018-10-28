package com.kungsaranuwat.sarayut.kotlincontactapp.controllers

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import com.kungsaranuwat.sarayut.kotlincontactapp.R
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        registerToolbar.setNavigationIcon(R.drawable.ic_action_back)
        registerToolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        loginConstraintLayout.setOnClickListener {
            hideKeyboard()
        }
    }

    private fun hideKeyboard() {
        val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }
}
