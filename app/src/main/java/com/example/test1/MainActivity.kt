package com.example.test1

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test1.databinding.ActivityMainBinding
import com.example.test1.models.GifsModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var gifsViewModel: GifsViewModel
    private lateinit var gifsAdapter: GifsAdapter

    private var url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        gifsViewModel = ViewModelProvider(this)[GifsViewModel::class.java]

         gifsAdapter = GifsAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = gifsAdapter


        gifsViewModel.getGifs("DHyJbfhiXuL6zkBeDEP63J4BgmTEvNn7")
        observeAllGifs()

        binding.searchgifs.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

                val search = s.toString().trim()

                    if (search.isEmpty()){

                        observeAllGifs()
                    }else{

                        gifsViewModel.searchGifs("DHyJbfhiXuL6zkBeDEP63J4BgmTEvNn7",search)

                        observeSearchGifs()

                    }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })


        Log.d("all gifs", "onResponse: ${url}")


    }

    private fun observeSearchGifs() {
        gifsViewModel.getAllSearchGifs.observe(this@MainActivity, Observer<GifsModel> {
            gifsAdapter.updateData(it.data)
        })
    }

    private fun observeAllGifs() {
        gifsViewModel.getAllGifs.observe(this@MainActivity, Observer<GifsModel> { gifsModel ->
            gifsAdapter.updateData(gifsModel.data)

        })
    }


}