package com.umc.history

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class MyPageViewPagerAdapter(fragment: MyPageFragment): FragmentStateAdapter(fragment){

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0-> MyPageMyStoryFragment()
            else->MyPageLikeStoryFragment()
        }
    }
}