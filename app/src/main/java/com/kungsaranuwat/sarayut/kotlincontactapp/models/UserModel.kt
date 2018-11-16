package com.kungsaranuwat.sarayut.kotlincontactapp.models

class UserModel (
    val id: String,
    val firstName: String,
    val lastName: String,
    val displayName: String,
    val email: String,
    val username: String,
    val profileImageURL: String,
    val roles: Array<String>
)