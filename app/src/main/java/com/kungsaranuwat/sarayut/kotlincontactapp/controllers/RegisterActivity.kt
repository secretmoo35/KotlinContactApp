package com.kungsaranuwat.sarayut.kotlincontactapp.controllers

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.kungsaranuwat.sarayut.kotlincontactapp.R
import com.kungsaranuwat.sarayut.kotlincontactapp.models.RegisterModel
import com.kungsaranuwat.sarayut.kotlincontactapp.services.AuthenService
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)



        registerToolbar.setNavigationIcon(R.drawable.ic_action_back)
        registerToolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        registerConstraintLayout.setOnClickListener {
            hideKeyboard()
        }

        loading(false)
    }

    private fun hideKeyboard() {
        val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }

    private fun loading(isLoading : Boolean){
        if(isLoading) {
            progressBar.visibility = View.VISIBLE
            registerButton.isEnabled = false
            usernameInput.isEnabled = false
            passwordInput.isEnabled = false
            firstNameInput.isEnabled = false
            lastNameInput.isEnabled = false
            emailInput.isEnabled = false
        }else {
            progressBar.visibility = View.INVISIBLE
            registerButton.isEnabled = true
            usernameInput.isEnabled = true
            passwordInput.isEnabled = true
            firstNameInput.isEnabled = true
            lastNameInput.isEnabled = true
            emailInput.isEnabled = true
        }
    }

    fun onRegister(view: View) {

        hideKeyboard()

        val username = usernameInput?.text.toString()
        val password = passwordInput?.text.toString()
        val firstName = firstNameInput?.text.toString()
        val lastName = lastNameInput?.text.toString()
        val email = emailInput?.text.toString()

        if(username == "" || password == "" || firstName == "" || lastName == "" || email == "") {
            return Toast.makeText(this, "Please fill in all information.", Toast.LENGTH_SHORT).show()
        }

        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            return Toast.makeText(this, "Email is invalid.", Toast.LENGTH_SHORT).show()
        }

        loading(true)

        val registerData = RegisterModel(firstName,lastName,email,username,password)

        AuthenService.registerUser(this, registerData) {complete ->

            loading(false)

            if(complete) {
                val intent = Intent(this, ContactListActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }


    }
}
