package com.kungsaranuwat.sarayut.kotlincontactapp.models

class ContactModel (
    val name: ContactNameModel,
    val gender: String,
    val email: String,
    val picture: ContactPictureModel
)

class ContactNameModel (
    val title: String,
    val first: String,
    val last: String
)

class  ContactPictureModel (
    val large: String,
    val medium: String,
    val thumbnail: String
)