package com.umc.history.ui.mypage

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.history.HiStoryApplication
import com.umc.history.OneStory
import com.umc.history.databinding.FragmentMypageMyStoryBinding
import com.umc.history.ui.viewmodel.MyPageViewModel
import com.umc.history.ui.viewmodel.MyPageViewModelFactory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body

class MyPageMyStoryFragment(val type : Int): Fragment() {
    private var _binding: FragmentMypageMyStoryBinding? = null
    private val binding get() = _binding!!
    private val myPageViewModel : MyPageViewModel by activityViewModels {
        MyPageViewModelFactory((requireContext().applicationContext as HiStoryApplication).storyRepository)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMypageMyStoryBinding.inflate(inflater,container,false)
        checkType(type)


        val dividerItemDecoration =
            DividerItemDecoration(binding.homeStoryRecyclerView.context, LinearLayoutManager(activity).orientation)

        binding.homeStoryRecyclerView.addItemDecoration(dividerItemDecoration)


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    fun checkType(type: Int) {
        val myPageStoryRVAdapter = MyPageStoryRVAdapter()

        binding.homeStoryRecyclerView.adapter = myPageStoryRVAdapter
        binding.homeStoryRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        if(type == 0){
            Log.d("my_page", "my")
            viewLifecycleOwner.lifecycleScope.launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    myPageViewModel.storyWriteByUser.collect {
                        myPageStoryRVAdapter.submitList(it)
                    }
                }
            }
        }
    }


}