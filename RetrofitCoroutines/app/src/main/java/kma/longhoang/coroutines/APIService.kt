package kma.longhoang.coroutines

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST

interface APIService {
    @GET("v3/stats/worldometer/country")
    fun getData(): Call<List<Country>>
}