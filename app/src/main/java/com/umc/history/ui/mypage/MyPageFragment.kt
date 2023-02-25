package com.umc.history.ui.mypage

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.user.UserApiClient
import com.umc.history.HiStoryApplication
import com.umc.history.R
import com.umc.history.adapter.MyPageViewPagerAdapter
import com.umc.history.auth.AuthInterface
import com.umc.history.databinding.FragmentMypageBinding
import com.umc.history.ui.LoginActivity
import com.umc.history.ui.viewmodel.MyPageViewModel
import com.umc.history.ui.viewmodel.MyPageViewModelFactory


class MyPageFragment : Fragment(), AuthInterface {
    val information = arrayListOf("내 이야기","좋아하는 이야기")
    private var _binding: FragmentMypageBinding? = null
    private val binding get() = _binding!!
    private val myPageViewModel : MyPageViewModel by activityViewModels {
        MyPageViewModelFactory((requireContext().applicationContext as HiStoryApplication).storyRepository)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMypageBinding.inflate(inflater, container, false)

        checkLogin()

        binding.myPageLoginIb.setOnClickListener {
            login()
        }


        val builder = AlertDialog.Builder(requireContext())
        val dialogView = layoutInflater.inflate(R.layout.bottomdialog_mypage, null)
        builder.setView(dialogView)
        val alertDialog = builder.create()
        val window = alertDialog.window
        window?.setGravity(Gravity.BOTTOM)
        builder.setView(dialogView)

        fun showDialog() {
            alertDialog.show()
            alertDialog.findViewById<TextView>(R.id.bottomdialog_mypage_logout).setOnClickListener {
                logout()
                val intent = Intent(activity, LoginActivity::class.java)
                startActivity(intent)
            }
        }

        binding.myPageSettingIb.setOnClickListener {
            showDialog()
        }

        return binding.root
    }
    override fun login(){
        val intent = Intent(activity, LoginActivity::class.java)
        startActivity(intent)
    }

    override fun logout() {
        UserApiClient.instance.logout { error ->
            if(error != null){
                Log.d("logout", "로그아웃 실패", error)
            } else {
                Log.d("logout", "로그아웃 성공")
                binding.myPageNickNameTv.visibility = View.GONE
                binding.myPageMenuVp.visibility = View.INVISIBLE
                binding.myPageMenuTb.visibility = View.INVISIBLE
            }
        }
    }

    override fun checkLogin(){
        if(AuthApiClient.instance.hasToken()){
            UserApiClient.instance.me { user, error ->
                if(error != null){

                } else if (user != null) {
                    myPageViewModel.getStoryWriteByUser(user.id!!)

                    binding.myPageLoginTv.visibility = View.GONE
                    binding.myPageLoginIb.visibility = View.GONE
                    binding.myPageNickNameTv.visibility = View.VISIBLE
                    binding.myPageNickNameTv.text = user.kakaoAccount?.profile?.nickname.toString()

                    val myPageAdapter = MyPageViewPagerAdapter(this)
                    binding.myPageMenuVp.visibility = View.VISIBLE
                    binding.myPageMenuTb.visibility = View.VISIBLE
                    binding.myPageMenuVp.adapter = myPageAdapter
                    TabLayoutMediator(binding.myPageMenuTb, binding.myPageMenuVp) { tab, position ->
                        tab.text = information[position]
                    }.attach()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}