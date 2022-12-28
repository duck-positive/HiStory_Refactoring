package com.umc.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.umc.history.databinding.FragmentMypageLoginBinding
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyPageLoginFragment: Fragment() {
    lateinit var binding: FragmentMypageLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMypageLoginBinding.inflate(inflater, container, false)
        val spf = activity?.getSharedPreferences("token", AppCompatActivity.MODE_PRIVATE)
        val token = spf?.getString("accessToken", null)
        val retrofit = Retrofit.Builder().baseUrl("http://history-balancer-5405023.ap-northeast-2.elb.amazonaws.com").addConverterFactory(
            GsonConverterFactory.create()).build()
        val likedStoryService = retrofit.create(LikedStoryInterface::class.java)

        likedStoryService.getLikedStory("Bearer $token").enqueue(object : Callback<GetLikedStoryResponse> {
            override fun onResponse(
                call: retrofit2.Call<GetLikedStoryResponse>,
                response: retrofit2.Response<GetLikedStoryResponse>
            ) {
                val resp = response.body()
//                if (resp?.body != null) {
//                    val user = resp.body
//                    binding.myPageLoginNickNameTv.text = user[0].user.nickName
//                }
            }

            override fun onFailure(call: retrofit2.Call<GetLikedStoryResponse>, t: Throwable) {

            }
        })



        return binding.root
    }
}