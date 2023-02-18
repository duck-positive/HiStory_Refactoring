package com.umc.history.ui.mypage

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.umc.history.databinding.ItemPreviewStoryBinding

import retrofit2.http.Body

class MyPageStoryRVAdapter (private val myPageStoryList:ArrayList<Body>) : RecyclerView.Adapter<MyPageStoryRVAdapter.ViewHolder>(){

    lateinit var context:Context
    private lateinit var mItemClickListener: StoryItemClickListener
    interface StoryItemClickListener{
        fun onItemClick(story : Body)
    }
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        context = recyclerView.context
    }
    fun storyItemClickListener(storyItemClickListener: StoryItemClickListener){
        mItemClickListener = storyItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemPreviewStoryBinding = ItemPreviewStoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(myPageStoryList[position])
        holder.binding.storyLo.setOnClickListener {
            mItemClickListener.onItemClick(myPageStoryList[position])
        }
    }

    override fun getItemCount(): Int = myPageStoryList.size

//    @SuppressLint("NotifyDataSetChanged")
//    fun addStories()
    //뷰홀더

    inner class ViewHolder(val binding: ItemPreviewStoryBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(myPageStory: Body){

//            Glide.with(context)
//                .load(myPageStory.user.profileImgUrl)
//                .into(binding.itemMyPageStoryProfileImgIv)
        }
    }

}