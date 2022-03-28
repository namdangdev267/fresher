package com.misa.fresher.data.model

data class UserRespone(
    val kind : String,
    val localId : String,
    val email : String,
    val displayName : String,
    val idToken : String,
    val registered : Boolean
)
