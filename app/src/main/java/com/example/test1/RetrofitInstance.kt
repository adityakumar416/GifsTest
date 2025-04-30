package com.example.test1

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.giphy.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiCall by lazy {
        retrofit.create(ApiCall::class.java)
    }


}