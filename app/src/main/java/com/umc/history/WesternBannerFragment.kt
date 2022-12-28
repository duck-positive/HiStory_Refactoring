package com.umc.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.umc.history.databinding.FragmentWesternBannerBinding

class WesternBannerFragment : Fragment() {
    lateinit var binding : FragmentWesternBannerBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWesternBannerBinding.inflate(inflater,container,false)

        return binding.root
    }
}