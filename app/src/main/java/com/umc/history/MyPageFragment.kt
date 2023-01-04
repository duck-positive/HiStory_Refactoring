package com.umc.history

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.user.UserApiClient
import com.umc.history.databinding.FragmentMypageBinding
import com.umc.history.login.LoginActivity
import com.umc.history.login.LoginViewModel


class MyPageFragment : Fragment() {
    val information = arrayListOf("내 이야기","좋아하는 이야기")
    lateinit var binding: FragmentMypageBinding
    private val loginViewModel : LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMypageBinding.inflate(inflater, container, false)
        var token: String? = null

        Log.d("mypage_login","${loginViewModel.currentAccessToken.value}")
        checkLogin()

        val myPageAdapter = MyPageViewPagerAdapter(this)
        binding.myPageMenuVp.adapter = myPageAdapter
        TabLayoutMediator(binding.myPageMenuTb, binding.myPageMenuVp) { tab, position ->
            tab.text = information[position]
        }.attach()
//        val spf = activity?.getSharedPreferences("token",AppCompatActivity.MODE_PRIVATE)
//        val token = spf?.getString("accessToken", null)
//        if(token == null){
//            var myPageLogoutFragment = MyPageLogoutFragment()
//            binding.myPageSettingLy.visibility = View.GONE
//            childFragmentManager.beginTransaction().replace(R.id.myPage_profile_ly, myPageLogoutFragment)
//                .commit()
//        } else {
//            var myPageLoginFragment = MyPageLoginFragment()
//            binding.myPageSettingLy.visibility = View.VISIBLE
//            childFragmentManager.beginTransaction().replace(R.id.myPage_profile_ly, myPageLoginFragment)
//                .commit()
//
//        }

        fun toast(message: String) {
            Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
        }

//        binding.myPageSettingLy.setOnClickListener {
//            val items = arrayOf("프로필 편집","잠금 화면 설정","로그아웃")
//            val builder = AlertDialog.Builder(activity)
//            builder.setItems(items){
//                    dialog,which->toast("${items[which]}is selected")
//            }
//
//            val alertDialog = builder.create()
//            val window = alertDialog.window
//            window?.setGravity(Gravity.BOTTOM)
//
//            alertDialog.show()
//

//        }
        val builder = AlertDialog.Builder(activity)
        val dialogView = layoutInflater.inflate(R.layout.dialog01, null)
        builder.setView(dialogView)



        val alertDialog = builder.create()
        val window = alertDialog.window
        window?.setGravity(Gravity.BOTTOM)

        builder.setView(dialogView)

        fun showDialog(){
            alertDialog.show()
//            alertDialog.findViewById<TextView>(R.id.dialog01_profile).setOnClickListener{
//                val intent = Intent(activity,ProfileEditorActivity::class.java)
//                startActivity(intent)
//            }
//            alertDialog.findViewById<TextView>(R.id.dialog01_lock_setting).setOnClickListener{
//                val intent = Intent(activity,LockSettingActivity::class.java)
//                startActivity(intent)
//            }

            alertDialog.findViewById<TextView>(R.id.dialog01_logout).setOnClickListener{
                val spf = activity?.getSharedPreferences("token",AppCompatActivity.MODE_PRIVATE)
                val editor = spf!!.edit()
                editor.clear()
                editor.commit()
                val intent = Intent(activity, LoginActivity::class.java)
                startActivity(intent)
            }


        }

        binding.myPageSettingIb.setOnClickListener {
           showDialog()
        }

        return binding.root
    }

    private fun checkLogin(){
        if(AuthApiClient.instance.hasToken()){
            binding.myPageNickNameTv.visibility = View.VISIBLE
            binding.myPageLoginTv.visibility = View.INVISIBLE
            binding.myPageLoginIb.visibility = View.GONE
            UserApiClient.instance.me { user, error ->
                if(error != null){

                } else if (user != null) {
                    binding.myPageNickNameTv.text = user.kakaoAccount?.profile?.nickname
                }
            }
        } else {
            binding.myPageNickNameTv.visibility = View.GONE
            binding.myPageLoginTv.visibility = View.VISIBLE
            binding.myPageLoginIb.visibility = View.VISIBLE

        }


    }


}