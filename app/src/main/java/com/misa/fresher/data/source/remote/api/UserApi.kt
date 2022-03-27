package com.misa.fresher.data.source.remote.api

import com.misa.fresher.common.RetrofitSingleton
import com.misa.fresher.data.source.remote.request.SignInRequest
import com.misa.fresher.data.source.remote.response.SignInResponse
import com.misa.fresher.data.source.remote.response.SignUpResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Api để yêu cầu các thông tin liên quan đến user
 *
 * @author Nguyễn Công Chính
 * @since 3/22/2022
 *
 * @version 1
 * @updated 3/22/2022: Tạo class
 */
interface UserApi {

    @POST("v1/accounts:signInWithPassword?key=AIzaSyB21VNaMyWLCGVCl7E1qsgmUM0eGKvwhJY")
    suspend fun signIn(@Body request: SignInRequest): Response<SignInResponse>

    @POST("v1/accounts:signUp?key=AIzaSyB21VNaMyWLCGVCl7E1qsgmUM0eGKvwhJY")
    suspend fun signUp(@Body request: SignInRequest): Response<SignUpResponse>

    companion object {
        private val api by lazy { RetrofitSingleton.create(UserApi::class.java) }

        fun getInstance(): UserApi = api
    }
}