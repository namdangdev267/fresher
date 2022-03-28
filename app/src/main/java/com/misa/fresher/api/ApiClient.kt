package com.misa.fresher.api

import com.misa.fresher.models.User
import com.misa.fresher.models.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiClient {
    @POST("/v1/accounts:signInWithPassword?key=AIzaSyB21VNaMyWLCGVCl7E1qsgmUM0eGKvwhJY")
    suspend fun signIn(@Body user : User) : Response<UserResponse>

    @POST("/v1/accounts:signUp?key=AIzaSyB21VNaMyWLCGVCl7E1qsgmUM0eGKvwhJY")
    suspend fun signUp(@Body user : User) : Response<UserResponse>
}