package com.misa.fresher.common

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Lớp static chứa instance của retrofit
 *
 * @author Nguyễn Công Chính
 * @since 3/21/2022
 *
 * @version 1
 * @updated 3/21/2022: Tạo class
 */
object RetrofitSingleton {

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getInstance(): Retrofit = retrofit

    /**
     * Hàm khởi tạo service api sử dụng retrofit
     *
     * @author Nguyễn Công Chính
     * @since 3/21/2022
     *
     * @version 1
     * @updated 3/21/2022: Tạo function
     */
    fun <T> create(service: Class<T>): T {
        return retrofit.create(service)
    }
}