package com.misa.fresher.model

data class UserRespone(
    val kind : String,
    val localId : String,
    val email : String,
    val displayName : String,
    val idToken : String,
    val registered : Boolean
)
