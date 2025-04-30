package com.example.test1

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test1.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        getAllGifs()

        binding.searchgifs.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

                val search = s.toString().trim()

                    if (search.isEmpty()){
                        getAllGifs()
                    }else{
                        searchGifs(search)

                    }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })


        Log.d("all gifs", "onResponse: ${url}")


    }

    private fun searchGifs(searchText: String) {
        lifecycleScope.launch {
            val api =
                RetrofitInstance.apiCall.searchGifs("DHyJbfhiXuL6zkBeDEP63J4BgmTEvNn7", searchText)

            binding.recyclerView.adapter = GifsAdapter(api.data)


        }
    }

    private fun getAllGifs() {
        lifecycleScope.launch {
            val api = RetrofitInstance.apiCall.getGifs("DHyJbfhiXuL6zkBeDEP63J4BgmTEvNn7")

            binding.recyclerView.adapter = GifsAdapter(api.data)


        }
    }
}