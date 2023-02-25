package com.umc.history.adapter


import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.umc.history.databinding.ItemImgBinding


class ImageRVAdapter(private var imageList : ArrayList<Bitmap>) : RecyclerView.Adapter<ImageRVAdapter.ViewHolder>(){


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemImgBinding
            .inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder : ViewHolder, position : Int){
        holder.bind(imageList[position])
        holder.binding.writeImgDelIv.setOnClickListener {
            removeImg(position)
        }
    }

    override fun getItemCount(): Int = imageList.size
    inner class ViewHolder(val binding : ItemImgBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(image : Bitmap){
            binding.writeImgIv?.setImageBitmap(image)
        }
    }

    fun removeImg(position: Int){
        imageList.removeAt(position)

        notifyDataSetChanged()
    }

}