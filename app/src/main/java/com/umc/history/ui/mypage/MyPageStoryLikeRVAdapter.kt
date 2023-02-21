package com.umc.history.ui.mypage

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.umc.history.databinding.ItemPreviewStoryBinding

class MyPageStoryLikeRVAdapter () : RecyclerView.Adapter<MyPageStoryLikeRVAdapter.ViewHolder>(){

    lateinit var context:Context
    private lateinit var lItemClickListener: LikeItemClickListener
    interface LikeItemClickListener{
    }
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        context = recyclerView.context
    }
    fun likeItemClickListener(likeItemClickListener: LikeItemClickListener){
        lItemClickListener = likeItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemPreviewStoryBinding = ItemPreviewStoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int = 3

//    @SuppressLint("NotifyDataSetChanged")
//    fun addStories()
    //뷰홀더

    inner class ViewHolder(val binding: ItemPreviewStoryBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(){

        }
    }

}