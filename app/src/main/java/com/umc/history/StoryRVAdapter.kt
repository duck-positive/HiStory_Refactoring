package com.umc.history

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.umc.history.databinding.ItemStoryBinding

class StoryRVAdapter(private val storyList:ArrayList<OneStory>) :RecyclerView.Adapter<StoryRVAdapter.ViewHolder>(){

    lateinit var context:Context
    interface MyItemClickListener{
        fun onItemClick(story : OneStory)
    }
    private lateinit var mItemClickListener : MyItemClickListener
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        context = recyclerView.context
    }
    fun myItemClickListener(itemClickListener: MyItemClickListener){
        mItemClickListener = itemClickListener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemStoryBinding = ItemStoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(storyList[position])
        holder.binding.itemStoryLo.setOnClickListener {
            mItemClickListener.onItemClick(storyList[position])
        }

    }

    override fun getItemCount(): Int{
        return storyList.size}


    //뷰홀더

    inner class ViewHolder(val binding:ItemStoryBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(story : OneStory){
            binding.itemStoryTitleTv.text=story.title
            binding.itemStoryLikeTv.text= story.totalLike.toString()
            binding.itemStoryCommentTv.text=story.totalComment.toString()
            Glide.with(context)
                .load(if(story.images.isNullOrEmpty()){
                    "https://history-app-story-image.s3.ap-northeast-2.amazonaws.com/static/35dd9731-2e90-41ba-a47b-79c36e9c3435history_logo.png"
                } else {
                    story.images[0].imageUrl
                })
                .into(binding.itemStoryCoverImgIv)

        }
    }

}