package com.example.test1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test1.models.GifsModel
import kotlinx.coroutines.launch

class GifsViewModel : ViewModel() {

    val gifsRepository = GifsRepository()

    val setAllGifs = MutableLiveData<GifsModel>()
    val getAllGifs: LiveData<GifsModel> = setAllGifs

    val setSearchGifs = MutableLiveData<GifsModel>()
    val getAllSearchGifs: LiveData<GifsModel> = setSearchGifs

    fun getGifs(apiKey: String){
        viewModelScope.launch {
            val gifs = gifsRepository.getGifs(apiKey)
            setAllGifs.postValue(gifs)
        }
    }

    fun searchGifs(apiKey: String,searchText: String){
        viewModelScope.launch {
            val searchGifs = gifsRepository.searchGifs(apiKey,searchText)
            setSearchGifs.postValue(searchGifs)
        }
    }

}