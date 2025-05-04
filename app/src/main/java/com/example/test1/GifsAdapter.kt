package com.example.test1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.test1.databinding.GifsItemBinding
import com.example.test1.models.Data

class GifsAdapter : RecyclerView.Adapter<GifsAdapter.ViewHolder>() {

    var gifsList: List<Data> = mutableListOf()

    fun updateData(gifsList: List<Data>){
        this.gifsList = gifsList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = GifsItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {

        val list = gifsList[position].images.original.url

        Glide.with(holder.itemView.context)
            .load(list)
            .into(holder.binding.gifs);


    }

    override fun getItemCount(): Int {
        return gifsList.size
    }

    class ViewHolder(val binding: GifsItemBinding): RecyclerView.ViewHolder(binding.root) {

    }
}