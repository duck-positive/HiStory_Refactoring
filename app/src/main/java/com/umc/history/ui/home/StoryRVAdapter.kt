package com.umc.history.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.umc.history.Story
import com.umc.history.databinding.ItemStoryBinding


class StoryListAdapter : ListAdapter<Story, StoryListAdapter.StoryViewHolder>(StoryComparator()) {
    private lateinit var mItemClickListener: ItemClickListener

    interface ItemClickListener {
        fun onItemClick(story : Story)
    }

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        mItemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        val binding : ItemStoryBinding = ItemStoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
        holder.itemView.setOnClickListener {
            mItemClickListener.onItemClick(current)
        }
    }

    //뷰홀더
    inner class StoryViewHolder(val binding: ItemStoryBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(story : Story){
            binding.itemStoryTitleTv.text = story.title
            binding.itemStoryCommentTv.text = story.commentNumber.toString()
            binding.itemStoryLikeTv.text = story.likeNumber.toString()
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