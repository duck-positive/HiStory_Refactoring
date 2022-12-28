package com.umc.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.umc.history.databinding.FragmentOrientalBannerBinding

class OrientalBannerFragment() : Fragment() {
    lateinit var binding : FragmentOrientalBannerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrientalBannerBinding.inflate(inflater,container,false)

        return binding.root
    }
}