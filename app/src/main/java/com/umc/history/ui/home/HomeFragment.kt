package com.umc.history.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.user.UserApiClient
import com.umc.history.BannerViewPagerAdapter
import com.umc.history.databinding.FragmentHomeBinding
import com.umc.history.login.LoginActivity
import com.umc.history.login.LoginViewModel


class HomeFragment: Fragment(){
    lateinit var binding: FragmentHomeBinding
    private var token : String? = null
    private val loginViewModel : LoginViewModel by viewModels()
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
        TabLayoutMediator(binding.homeMenuTb,binding.homeMenuVp){
            tab,position->
            tab.text = information[position]
        }.attach()


        val bannerAdapter = BannerViewPagerAdapter(this)
        binding.homeBannerVp.adapter = bannerAdapter
        binding.homeBannerVp.orientation = ViewPager2.ORIENTATION_HORIZONTAL

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
                loginViewModel.currentAccessToken.value = null
            }

        }
    }

    private fun checkLogin(){
        if(AuthApiClient.instance.hasToken()){
            UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
                Log.d("home_login", tokenInfo.toString())
            }

            binding.homeLoginTv.visibility = View.VISIBLE
            binding.homeLoginIv.visibility = View.GONE
        }
    }
}