package com.misa.fresher.data.source.user.remote.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory


object UserClient {
    val instance: UserApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://identitytoolkit.googleapis.com")
            .client(OkHttpClient())
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
            .create(UserApi::class.java)
    }
}