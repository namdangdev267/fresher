package vn.misa.freshertraining

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * - Mục đích Class: Build lớp Retrofit
 *
 * @created_by KhanhNQ on 24-Feb-2022.
 */

object Api {
    // Sử dụng API public Dog.ceo
    val apiClient: ApiClient = Retrofit.Builder()
        .baseUrl("https://dog.ceo")
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiClient::class.java)
}
