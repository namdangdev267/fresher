package com.misa.fresher.loginactivity

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

public interface UserInterfaceService {
    @POST("/v1/accounts:signInWithPassword?key=AIzaSyB21VNaMyWLCGVCl7E1qsgmUM0eGKvwhJY")
    suspend fun userLogin(@Body user: User): Response<LoginRespone>

    @POST("/v1/accounts:signUp?key=AIzaSyB21VNaMyWLCGVCl7E1qsgmUM0eGKvwhJY")
    suspend fun signUp(@Body user: User): Response<SignUpRespone>

}