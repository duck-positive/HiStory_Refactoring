package com.umc.history

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.umc.history.databinding.FragmentMypageLogoutBinding

class MyPageLogoutFragment : Fragment() {
    lateinit var binding: FragmentMypageLogoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMypageLogoutBinding.inflate(inflater, container, false)

        binding.myPageLogoutLoginBtnIv.setOnClickListener {
            val spf = activity?.getSharedPreferences("token", AppCompatActivity.MODE_PRIVATE)
            val editor = spf!!.edit()
            editor.clear()
            editor.commit()
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
        }



        return binding.root
    }
}