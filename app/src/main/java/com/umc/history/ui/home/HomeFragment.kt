package com.umc.history.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.user.UserApiClient
import com.umc.history.R
import com.umc.history.databinding.FragmentHomeBinding
import com.umc.history.login.LoginActivity
import com.umc.history.ui.viewmodel.HomeViewModel


class HomeFragment: Fragment(){
    lateinit var binding: FragmentHomeBinding
    private var token : String? = null
    private val homeViewModel : HomeViewModel by viewModels()
    val information = arrayListOf("전체","한국사","동양사","서양사")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        checkLogin()

        val homeAdapter = HomeViewPagerAdapter(this)
        binding.homeMenuVp.adapter = homeAdapter
        TabLayoutMediator(binding.homeMenuTb,binding.homeMenuVp){ tab,position->
            tab.text = information[position]
            tab.view.setOnClickListener {
                    binding.homeBannerIv.setImageResource(
                        when (position) {
                            0 -> R.drawable.home_banner_img
                            1 -> R.drawable.korean_banner
                            2 -> R.drawable.oriental_banner
                            else -> R.drawable.western_banner
                        }
                    )
            }
        }.attach()

        binding.homeLoginIv.setOnClickListener {
            login()
        }
        binding.homeLoginTv.setOnClickListener {
            logout()
        }
        return binding.root
    }

    private fun login(){
        val intent = Intent(activity, LoginActivity::class.java)
        startActivity(intent)
    }

    private fun logout() {
        UserApiClient.instance.logout { error ->
            if(error != null){
                Log.d("logout", "로그아웃 실패", error)
            } else {
                Log.d("logout", "로그아웃 성공")
                binding.homeLoginTv.visibility = View.GONE
                binding.homeLoginIv.visibility = View.VISIBLE
            }
        }
    }

    private fun checkLogin(){
        if(AuthApiClient.instance.hasToken()){
            UserApiClient.instance.me { user, error ->
                if(error != null){

                } else if (user != null) {
                    binding.homeLoginTv.visibility = View.VISIBLE
                    binding.homeLoginIv.visibility = View.GONE
                }
            }
        }
    }
}