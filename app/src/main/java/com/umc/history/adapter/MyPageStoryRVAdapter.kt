package com.umc.history.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.umc.history.data.Story
import com.umc.history.databinding.ItemPreviewStoryBinding
import com.umc.history.ui.home.StoryListAdapter

import retrofit2.http.Body

class MyPageStoryRVAdapter() : ListAdapter<Story, MyPageStoryRVAdapter.ViewHolder>(StoryListAdapter.StoryComparator()){

    lateinit var context:Context


    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        context = recyclerView.context
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemPreviewStoryBinding = ItemPreviewStoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }
    //뷰홀더

    inner class ViewHolder(val binding: ItemPreviewStoryBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(story: Story){
            with(binding){
                storyLo.setOnClickListener {

                }
                itemMyPageStoryTitleTv.text = story.title
                itemMyPageStoryDetailTv.text = story.content
                itemMyPageStoryCommentTv.text = story.commentNumber.toString()
                itemMyPageStoryLikeTv.text = story.likeNumber.toString()
            }
//            Glide.with(context)
//                .load(myPageStory.user.profileImgUrl)
//                .into(binding.itemMyPageStoryProfileImgIv)
        }
    }

}