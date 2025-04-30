package com.example.test1

import com.example.test1.models.GifsModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiCall {


    @GET("v1/gifs/trending/")
    suspend fun getGifs(
        @Query("api_key") apiKey: String
    ) : GifsModel


    @GET("v1/gifs/search/")
    suspend fun searchGifs(
        @Query("api_key") apiKey: String,
        @Query("q") searchText: String
    ) : GifsModel


}