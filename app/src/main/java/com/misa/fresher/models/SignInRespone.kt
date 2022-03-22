package com.misa.fresher.models

data class SignInRespone (
    val kind : String,
    val localId : String,
    val email : String,
    val displayName : String,
    val idToken : String,
    val registered : Boolean
)