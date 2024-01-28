package jp.aqua.randomuser.network

import jp.aqua.randomuser.domain.randomuser.model.ApiResponse
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://randomuser.me/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface RandomUserApiService {
    @GET("api")
    suspend fun get(): ApiResponse
}

object RandomUserApi {
    val retrofitService: RandomUserApiService by lazy {
        retrofit.create(RandomUserApiService::class.java)
    }
}