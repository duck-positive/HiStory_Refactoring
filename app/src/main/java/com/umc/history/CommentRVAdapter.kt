package com.umc.history


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.umc.history.databinding.ItemCommentBinding


class CommentRVAdapter(private var commentList : ArrayList<Comment>) : RecyclerView.Adapter<CommentRVAdapter.ViewHolder>(){
    lateinit var context : Context

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCommentBinding
            .inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder : ViewHolder, position : Int){
        holder.bind(commentList[position])
    }
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        context = recyclerView.context
    }

    override fun getItemCount(): Int = commentList.size
    inner class ViewHolder(val binding : ItemCommentBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(comment : Comment){
            binding.itemCommentCommentTv.text = comment.contents
            binding.itemCommentNicknameTv.text = comment.commentUser.nickName
//            Glide.with(context)
//                .load(if(comment.commentUser.profileImageUrl.isNullOrEmpty()){
//                    "https://history-app-story-image.s3.ap-northeast-2.amazonaws.com/static/35dd9731-2e90-41ba-a47b-79c36e9c3435history_logo.png"
//                } else {
//                    comment.commentUser.profileImageUrl
//                })
//                .into(binding.itemCommentProfileIv)
        }
    }

}