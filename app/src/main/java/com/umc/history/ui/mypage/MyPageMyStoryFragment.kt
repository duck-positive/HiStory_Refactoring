package com.umc.history.ui.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.history.OneStory
import com.umc.history.databinding.FragmentMypageMyStoryBinding

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body

class MyPageMyStoryFragment: Fragment() {
    private var _binding: FragmentMypageMyStoryBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMypageMyStoryBinding.inflate(inflater,container,false)



        val dividerItemDecoration =
            DividerItemDecoration(binding.homeStoryRecyclerView.context, LinearLayoutManager(activity).orientation)

        binding.homeStoryRecyclerView.addItemDecoration(dividerItemDecoration)


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}