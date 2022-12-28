package com.umc.history


import okhttp3.MediaType.parse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import org.json.*

class StoryService {
    private lateinit var storyView : StoryView
    private lateinit var oneStoryView : OneStoryView
    private lateinit var deleteView : DeleteView

    fun setStoryView(storyView : StoryView){
        this.storyView = storyView
    }
    fun setOneStoryView(oneStoryView: OneStoryView){
        this.oneStoryView = oneStoryView
    }

    fun setDeleteView(deleteView : DeleteView){
        this.deleteView = deleteView
    }

    fun writeStory(token : String?, pathList : List<MultipartBody.Part?>, id : String, title: String,
                   category: String, contents: String, hashtagList : List<String>?){
        val retrofit = Retrofit.Builder().baseUrl("http://history-balancer-5405023.ap-northeast-2.elb.amazonaws.com").addConverterFactory(GsonConverterFactory.create()).build()
        val storyService = retrofit.create(StoryInterface::class.java)
        val jsonArray = arrayListOf<String>()
        if((hashtagList?.isNotEmpty())!!){
            for(hashtag in hashtagList){
                jsonArray.add("\"$hashtag\"")
            }
        }
        val body = RequestBody.create(MultipartBody.FORM,"")
        val emptyPart = MultipartBody.Part.createFormData("imageList","",body)
        val emptyList = arrayListOf<MultipartBody.Part>()
        emptyList.add(emptyPart)

        val jsonObject = JSONObject("{\"userId\":\"${id}\",\"category\":\"${category}\",\"title\":\"${title}\",\"contents\":\"${contents}\",\"hashTags\":${jsonArray}}").toString()
        val jsonBody = RequestBody.create(parse("application/json"),jsonObject)

        storyService.writeStory("Bearer $token", if(pathList.isEmpty()){
            emptyList
        } else{
              pathList
              }, jsonBody).enqueue(object : Callback<StoryResponse>{
            override fun onResponse(call: Call<StoryResponse>, response: Response<StoryResponse>) {

            }
            override fun onFailure(call: Call<StoryResponse>, t: Throwable) {

            }
            })
    }
    fun deleteStory(token: String, storyIdx : Int){
        val retrofit = Retrofit.Builder().baseUrl("http://history-balancer-5405023.ap-northeast-2.elb.amazonaws.com").addConverterFactory(GsonConverterFactory.create()).build()
        val storyService = retrofit.create(StoryInterface::class.java)

        storyService.deleteStory("Bearer $token", storyIdx).enqueue(object : Callback<DeleteResponse>{
            override fun onResponse(call: Call<DeleteResponse>, response: Response<DeleteResponse>) {
                val resp = response.body()
                if(resp!!.body){
                    deleteView.onDeleteSuccess(resp.body)
                } else {
                    deleteView.onDeleteFailure()
                }
            }
            override fun onFailure(call: Call<DeleteResponse>, t: Throwable) {

            }
        })
    }
    fun getStory(postIdx : Int){
        val retrofit = Retrofit.Builder().baseUrl("http://history-balancer-5405023.ap-northeast-2.elb.amazonaws.com").addConverterFactory(GsonConverterFactory.create()).build()
        val storyService = retrofit.create(StoryInterface::class.java)

        storyService.getStory(postIdx).enqueue(object : Callback<GetOneStoryResponse>{
            override fun onResponse(call: Call<GetOneStoryResponse>, response: Response<GetOneStoryResponse>) {
                val resp = response.body()
                if(response.code() == 200 || response.code() == 202){
                    oneStoryView.onStorySuccess(resp!!.status, resp.body)
                } else {
                    oneStoryView.onStoryFailure()
                }

            }
            override fun onFailure(call: Call<GetOneStoryResponse>, t: Throwable) {
                oneStoryView.onStoryFailure()
            }
        })
    }
    fun getStoriesAllOrderByRecent(){
        val retrofit = Retrofit.Builder().baseUrl("http://history-balancer-5405023.ap-northeast-2.elb.amazonaws.com").addConverterFactory(GsonConverterFactory.create()).build()
        val storyService = retrofit.create(StoryInterface::class.java)

        storyService.getStoriesAllOrderByRecent().enqueue(object : Callback<GetAllStoryResponse>{
            override fun onResponse(call: Call<GetAllStoryResponse>, response: Response<GetAllStoryResponse>) {
                val resp = response.body()
                if(response.code() == 200 || response.code() == 202) {
                    storyView.onStorySuccess(resp!!.status, resp.body)
                } else {
                    storyView.onStoryFailure()
                }
            }
            override fun onFailure(call: Call<GetAllStoryResponse>, t: Throwable) {
                storyView.onStoryFailure()
            }
        })
    }
    fun getStoriesAllOrderByLike(){
        val retrofit = Retrofit.Builder().baseUrl("http://history-balancer-5405023.ap-northeast-2.elb.amazonaws.com").addConverterFactory(GsonConverterFactory.create()).build()
        val storyService = retrofit.create(StoryInterface::class.java)

        storyService.getStoriesAllOrderByLike().enqueue(object : Callback<GetAllStoryResponse>{
            override fun onResponse(call: Call<GetAllStoryResponse>, response: Response<GetAllStoryResponse>) {
                val resp = response.body()
                if(response.code() == 200 || response.code() == 202){
                    storyView.onStorySuccess(resp!!.status, resp.body)
                } else {
                    storyView.onStoryFailure()
                }

            }
            override fun onFailure(call: Call<GetAllStoryResponse>, t: Throwable) {
                storyView.onStoryFailure()
            }
        })
    }
    fun getStoriesCategoryOrderByLike(category: String){
        val retrofit = Retrofit.Builder().baseUrl("http://history-balancer-5405023.ap-northeast-2.elb.amazonaws.com").addConverterFactory(GsonConverterFactory.create()).build()
        val storyService = retrofit.create(StoryInterface::class.java)

        storyService.getStoriesCategoryOrderByLike(category).enqueue(object : Callback<GetAllStoryResponse>{
            override fun onResponse(call: Call<GetAllStoryResponse>, response: Response<GetAllStoryResponse>) {
                val resp = response.body()
                if(response.code() == 200 || response.code() == 202){
                    storyView.onStorySuccess(resp!!.status, resp.body)
                } else {
                    storyView.onStoryFailure()
                }
            }
            override fun onFailure(call: Call<GetAllStoryResponse>, t: Throwable) {
                storyView.onStoryFailure()
            }
        })
    }
    fun getStoriesCategoryOrderByRecent(category: String){
        val retrofit = Retrofit.Builder().baseUrl("http://history-balancer-5405023.ap-northeast-2.elb.amazonaws.com").addConverterFactory(GsonConverterFactory.create()).build()
        val storyService = retrofit.create(StoryInterface::class.java)

        storyService.getStoriesCategoryOrderByRecent(category).enqueue(object : Callback<GetAllStoryResponse>{
            override fun onResponse(call: Call<GetAllStoryResponse>, response: Response<GetAllStoryResponse>) {
                val resp = response.body()
                if(response.code() == 200 || response.code() == 202){
                    storyView.onStorySuccess(resp!!.status, resp.body)
                } else {
                    storyView.onStoryFailure()
                }
            }
            override fun onFailure(call: Call<GetAllStoryResponse>, t: Throwable) {
                storyView.onStoryFailure()
            }
        })
    }


}