package com.misa.fresher.data.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Api {
    val apiClient: ApiClient = Retrofit.Builder()
        .baseUrl("https://identitytoolkit.googleapis.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiClient::class.java)
}