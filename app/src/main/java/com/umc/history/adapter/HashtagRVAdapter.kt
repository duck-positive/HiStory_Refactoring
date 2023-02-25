package com.umc.history.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.umc.history.databinding.ItemHashtagBinding


class HashtagRVAdapter(private val hashtagList : ArrayList<String>) : RecyclerView.Adapter<HashtagRVAdapter.ViewHolder>(){

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemHashtagBinding
            .inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder : ViewHolder, position : Int){
        holder.bind(hashtagList[position])
        holder.binding.itemHashtagTv.setOnClickListener {
            removeHashtag(position)
        }
    //holder.bind(hashtagList[position])
    }

    override fun getItemCount(): Int = hashtagList.size
    inner class ViewHolder(val binding : ItemHashtagBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(hashtag : String){
            binding.itemHashtagTv.text = hashtag
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    fun removeHashtag(position: Int){
        hashtagList.removeAt(position)
        notifyDataSetChanged()
    }

}
