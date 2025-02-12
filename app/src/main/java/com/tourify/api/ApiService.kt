package com.tourify.api

import com.tourify.ui.home.Destination
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("destinations/trending")
    fun getTrendingDestinations(): Call<List<Destination>>

    @GET("destinations/most-liked")
    fun getMostLikedDestinations(): Call<List<Destination>>

    @GET("destination/{id}")
    fun getDestinationById(@Path("id") id: Int): Call<Destination>
}