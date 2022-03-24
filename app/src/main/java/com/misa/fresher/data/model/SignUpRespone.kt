package com.misa.fresher.data.model

data class SignUpRespone(
    val kind : String,
    val idToken: String,
    val email:String,
    val refreshToken:String,
    val expiresIn :String,
    val localId : String
)
