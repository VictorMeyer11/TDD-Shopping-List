package com.androiddevs.tddshoppinglist.data.remote

import com.androiddevs.tddshoppinglist.BuildConfig
import com.androiddevs.tddshoppinglist.data.remote.responses.ImageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PixaBayApi {
    @GET("/api/")
    suspend fun searchForImage(
        @Query("q") searchQuery: String,
        @Query("key") apiKey: String = BuildConfig.API_KEY
    ): Response<ImageResponse>
}