package com.kungsaranuwat.sarayut.kotlincontactapp.controllers

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import com.kungsaranuwat.sarayut.kotlincontactapp.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginConstraintLayout.setOnClickListener {
            hideKeyboard()
        }

        registerText.setOnClickListener {
            openRegister()
        }
    }

    private fun hideKeyboard() {
        val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }

    private fun openRegister(){
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }
}
