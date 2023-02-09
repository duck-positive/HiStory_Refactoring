package com.umc.history.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.umc.history.OneStory
import com.umc.history.Story
import com.umc.history.databinding.ItemStoryBinding


class StoryListAdapter() : ListAdapter<Story, StoryListAdapter.StoryViewHolder>(StoryComparator()){

    lateinit var context:Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        return StoryViewHolder()
    }

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }



    //뷰홀더
    inner class StoryViewHolder(val binding: ItemStoryBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(story : Story){
            binding.itemStoryTitleTv.text=story.title
            binding.itemStoryLikeTv.text= story.likeNumber.toString()
            binding.itemStoryCommentTv.text=story.commentNumber.toString()
//            Glide.with(context)
//                .load(if(story.images.isNullOrEmpty()){
//                    "https://history-app-story-image.s3.ap-northeast-2.amazonaws.com/static/35dd9731-2e90-41ba-a47b-79c36e9c3435history_logo.png"
//                } else {
//                    story.images[0].imageUrl
//                })
//                .into(binding.itemStoryCoverImgIv)

        }
        fun create(parent : ViewGroup) {

        }
    }
    class StoryComparator : DiffUtil.ItemCallback<Story>() {
        override fun areContentsTheSame(oldItem: Story, newItem: Story): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areItemsTheSame(oldItem: Story, newItem: Story): Boolean {
            return oldItem === newItem
        }
    }

}