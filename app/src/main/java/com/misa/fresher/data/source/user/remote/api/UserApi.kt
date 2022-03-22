package com.misa.fresher.data.source.user.remote.api

import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UserApi {
    @FormUrlEncoded
    @POST("/v1/accounts:signInWithPassword?key=AIzaSyB21VNaMyWLCGVCl7E1qsgmUM0eGKvwhJY")
    suspend fun signIn(
        @Field("email") email: String,
        @Field("password") password: String,
    ): Response<String>

    @FormUrlEncoded
    @POST("/v1/accounts:signUp?key=AIzaSyB21VNaMyWLCGVCl7E1qsgmUM0eGKvwhJY")
    suspend fun signUp(
        @Field("email") email: String,
        @Field("password") password: String,
    ): Response<String>
}