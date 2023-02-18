package com.umc.history.ui.search

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.umc.history.databinding.ItemPreviewStoryBinding
import retrofit2.http.Body

class SearchStoryRVAdapter (private val myPageStoryList:ArrayList<Body>) : RecyclerView.Adapter<SearchStoryRVAdapter.ViewHolder>(){

    lateinit var context:Context
    private lateinit var sItemClickListener: SearchItemClickListener
    interface SearchItemClickListener{
        fun onItemClick(story : Body)
    }
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        context = recyclerView.context
    }
    fun searchItemClickListener(searchItemClickListener: SearchItemClickListener){
        sItemClickListener = searchItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemPreviewStoryBinding = ItemPreviewStoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(myPageStoryList[position])
        holder.binding.storyLo.setOnClickListener {
            sItemClickListener.onItemClick(myPageStoryList[position])
        }
    }

    override fun getItemCount(): Int = myPageStoryList.size


    inner class ViewHolder(val binding: ItemPreviewStoryBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(searchStory: Body){

//            Glide.with(context)
//                .load(if(searchStory.user!!.profileImgUrl == null){
//                    "https://history-app-story-image.s3.ap-northeast-2.amazonaws.com/static/35dd9731-2e90-41ba-a47b-79c36e9c3435history_logo.png"
//                }else{
//                    searchStory.user.profileImgUrl
//                })
//                .into(binding.itemMyPageStoryProfileImgIv)
        }
    }

}