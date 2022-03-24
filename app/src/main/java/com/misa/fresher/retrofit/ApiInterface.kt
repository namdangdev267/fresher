package com.misa.fresher.retrofit

import com.misa.fresher.model.User
import com.misa.fresher.model.UserRespone
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface ApiInterface {
    @POST("/v1/accounts:signInWithPassword?key=AIzaSyB21VNaMyWLCGVCl7E1qsgmUM0eGKvwhJY")
   suspend fun signIn(@Body user:User):Response<UserRespone>
   @POST("/v1/accounts:signUp?key=AIzaSyB21VNaMyWLCGVCl7E1qsgmUM0eGKvwhJY")
   suspend fun signUp(@Body user: User):Response<UserRespone>
}