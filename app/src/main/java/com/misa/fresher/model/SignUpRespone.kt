package com.misa.fresher.model

data class SignUpRespone(
    val kind : String,
    val idToken: String,
    val email:String,
    val refreshToken:String,
    val expiresIn :String,
    val localId : String
)
