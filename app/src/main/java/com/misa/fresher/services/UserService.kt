package com.misa.fresher.services

import com.misa.fresher.models.SignInRespone
import com.misa.fresher.models.SignUpRespone
import com.misa.fresher.models.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("/v1/accounts:signInWithPassword?key=AIzaSyB21VNaMyWLCGVCl7E1qsgmUM0eGKvwhJY")
    suspend fun signIn(@Body user: User):Response<SignInRespone>

    @POST("/v1/accounts:signUp?key=AIzaSyB21VNaMyWLCGVCl7E1qsgmUM0eGKvwhJY")
    suspend fun signUp(@Body user: User) : Response<SignUpRespone>
}