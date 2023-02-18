package com.umc.history.ui.mypage

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.umc.history.LikedResponse
import com.umc.history.databinding.ItemPreviewStoryBinding

class MyPageStoryLikeRVAdapter (private val myPageStoryList:ArrayList<LikedResponse>) : RecyclerView.Adapter<MyPageStoryLikeRVAdapter.ViewHolder>(){

    lateinit var context:Context
    private lateinit var lItemClickListener: LikeItemClickListener
    interface LikeItemClickListener{
        fun onItemClick(story : LikedResponse)
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
        holder.bind(myPageStoryList[position])
        holder.binding.storyLo.setOnClickListener {
            lItemClickListener.onItemClick(myPageStoryList[position])
        }
    }

    override fun getItemCount(): Int = myPageStoryList.size

//    @SuppressLint("NotifyDataSetChanged")
//    fun addStories()
    //뷰홀더

    inner class ViewHolder(val binding: ItemPreviewStoryBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(myPageStory: LikedResponse){
            binding.itemMyPageStoryTitleTv.text=myPageStory.post?.title
            binding.itemMyPageStoryDetailTv.text=myPageStory.post?.contents
            binding.itemMyPageStoryNickNameTv.text=myPageStory.post?.user?.nickName
            binding.itemMyPageStoryLikeTv.text= myPageStory.post?.totalLike.toString()
            binding.itemMyPageStoryCommentTv.text=myPageStory.post?.totalComment.toString()
//            Glide.with(context)
//                .load(myPageStory.user.profileImageUrl)
//                .into(binding.itemMyPageStoryProfileImgIv)
        }
    }

}