package com.example.test1

import com.example.test1.models.GifsModel

class GifsRepository {

    val apiCall = RetrofitInstance.apiCall

    suspend fun getGifs(apiKey: String) : GifsModel{
        return apiCall.getGifs(apiKey)
    }

    suspend fun searchGifs(apiKey: String,searchText: String) : GifsModel{
        return apiCall.searchGifs(apiKey,searchText)
    }

}