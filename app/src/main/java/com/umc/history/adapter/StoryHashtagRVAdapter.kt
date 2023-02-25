package com.umc.history.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.umc.history.R
import com.umc.history.databinding.ItemHashtagBinding


class StoryHashtagRVAdapter(private val hashtagList : ArrayList<String>) : RecyclerView.Adapter<StoryHashtagRVAdapter.ViewHolder>(){

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemHashtagBinding
            .inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder : ViewHolder, position : Int){
        holder.bind(hashtagList[position])
    }

    override fun getItemCount(): Int = hashtagList.size
    inner class ViewHolder(val binding : ItemHashtagBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(hashtag : String){
            binding.itemHashtagTv.text = hashtag
            binding.itemHashtagIv.setImageResource(R.drawable.button_hashtag_detail)
            binding.itemHashtagTv.setTextColor(Color.parseColor("#27316A"))
        }

    }


}
