package kma.longhoang.beta.retrofit

import kma.longhoang.beta.model.UserModel
import kma.longhoang.beta.model.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface APIService {
    @POST("v1/accounts:signUp?key=AIzaSyB21VNaMyWLCGVCl7E1qsgmUM0eGKvwhJY")
    suspend fun postToSignUp(@Body user: UserModel): Response<UserResponse>

    @POST("v1/accounts:signInWithPassword?key=AIzaSyB21VNaMyWLCGVCl7E1qsgmUM0eGKvwhJY")
    suspend fun postToSignIn(@Body user: UserModel): Response<UserResponse>
}