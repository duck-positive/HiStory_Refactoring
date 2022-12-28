package com.umc.history

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LikeService {
    private lateinit var likeView : LikeView

    fun setLikeView(likeView : LikeView){
        this.likeView = likeView
    }
    fun postLike(token : String, postIdx : Int){
        val retrofit = Retrofit.Builder().baseUrl("http://history-balancer-5405023.ap-northeast-2.elb.amazonaws.com").addConverterFactory(
            GsonConverterFactory.create()).build()
        val likeService = retrofit.create(LikeInterface::class.java)
        likeService.postLike("Bearer $token",postIdx).enqueue(object : Callback<DeleteResponse> {
            override fun onResponse(call: Call<DeleteResponse>, response: Response<DeleteResponse>) {
                val resp = response.body()
            }
            override fun onFailure(call: Call<DeleteResponse>, t: Throwable) {

            }
        })
    }
    fun checkLike(token : String, postIdx : Int){
        val retrofit = Retrofit.Builder().baseUrl("http://history-balancer-5405023.ap-northeast-2.elb.amazonaws.com").addConverterFactory(
            GsonConverterFactory.create()).build()
        val likeService = retrofit.create(LikeInterface::class.java)
        likeService.postLike("Bearer $token",postIdx).enqueue(object : Callback<DeleteResponse> {
            override fun onResponse(call: Call<DeleteResponse>, response: Response<DeleteResponse>) {
                val resp = response.body()
                if(response.code() == 200 || response.code() == 202){
                    likeView.onLikeSuccess(resp!!.body)
                } else {
                    likeView.onLikeFailure()
                }

            }
            override fun onFailure(call: Call<DeleteResponse>, t: Throwable) {
                likeView.onLikeFailure()
            }
        })
    }
}