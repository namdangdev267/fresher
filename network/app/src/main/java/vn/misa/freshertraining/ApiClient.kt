package vn.misa.freshertraining

import retrofit2.Response
import retrofit2.http.GET

/**
 * - Mục đích Class: Interface để lớp Retrofit sử dụng, chứa các đầu API
 *
 * @created_by KhanhNQ on 24-Feb-2022.
 */

interface ApiClient {

    @GET("/api/breeds/image/random")
    suspend fun getRandomDogImage(): Response<DogImageModel>
}
