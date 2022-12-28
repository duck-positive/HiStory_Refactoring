package com.umc.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.umc.history.databinding.FragmentKoreanBannerBinding

class KoreanBannerFragment : Fragment() {
    lateinit var binding : FragmentKoreanBannerBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentKoreanBannerBinding.inflate(inflater,container,false)

        return binding.root
    }
}