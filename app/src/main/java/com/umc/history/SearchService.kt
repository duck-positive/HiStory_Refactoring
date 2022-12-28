package com.umc.history

import android.media.session.MediaSession
import android.util.Log
import okhttp3.internal.http.HttpHeaders
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URLEncoder

class SearchService {
    private lateinit var searchView : SearchView
    fun setSearchView(searchView: SearchView){
        this.searchView = searchView
    }
    fun searchContents(keyWord : String){
        val retrofit = Retrofit.Builder().baseUrl("http://history-balancer-5405023.ap-northeast-2.elb.amazonaws.com").addConverterFactory(GsonConverterFactory.create()).build()
        val searchService = retrofit.create(SearchInterface::class.java)
        searchService.searchContents(keyWord).enqueue(object : Callback<SearchResponse>{
            override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
                val resp = response.body()?.body
                if(response.code() == 200 || response.code() == 202){
                    searchView.onSearchSuccess(resp)
                } else {
                    searchView.onSearchFailure()
                }
            }
            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                searchView.onSearchFailure()
            }


        })
    }
    fun searchTitle(keyWord : String){
        val retrofit = Retrofit.Builder().baseUrl("http://history-balancer-5405023.ap-northeast-2.elb.amazonaws.com").addConverterFactory(GsonConverterFactory.create()).build()
        val searchService = retrofit.create(SearchInterface::class.java)
        searchService.searchTitle(keyWord).enqueue(object : Callback<SearchResponse>{
            override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
                val resp = response.body()?.body
                if(response.code() == 200 || response.code() == 202){
                    searchView.onSearchSuccess(resp)
                } else {
                    searchView.onSearchFailure()
                }
            }
            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                searchView.onSearchFailure()
            }


        })
    }
}