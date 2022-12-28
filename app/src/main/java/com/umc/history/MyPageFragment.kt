package com.umc.history

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.umc.history.databinding.FragmentMypageBinding
import com.google.android.material.tabs.TabLayoutMediator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MyPageFragment : Fragment() {

    val information = arrayListOf("내 이야기","좋아하는 이야기")
    lateinit var binding: FragmentMypageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMypageBinding.inflate(inflater, container, false)

        val myPageAdapter = MyPageViewPagerAdapter(this)
        binding.myPageMenuVp.adapter = myPageAdapter
        TabLayoutMediator(binding.myPageMenuTb, binding.myPageMenuVp) { tab, position ->
            tab.text = information[position]
        }.attach()
        val spf = activity?.getSharedPreferences("token",AppCompatActivity.MODE_PRIVATE)
        val token = spf?.getString("accessToken", null)
        if(token == null){
            var myPageLogoutFragment = MyPageLogoutFragment()
            binding.myPageSettingLy.visibility = View.GONE
            childFragmentManager.beginTransaction().replace(R.id.myPage_profile_ly, myPageLogoutFragment)
                .commit()
        } else {
            var myPageLoginFragment = MyPageLoginFragment()
            binding.myPageSettingLy.visibility = View.VISIBLE
            childFragmentManager.beginTransaction().replace(R.id.myPage_profile_ly, myPageLoginFragment)
                .commit()

        }




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
                val intent = Intent(activity,LoginActivity::class.java)
                startActivity(intent)
            }


        }


        binding.myPageSettingLy.setOnClickListener {
           showDialog()
        }










        return binding.root
    }


}