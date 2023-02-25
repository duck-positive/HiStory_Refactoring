package com.umc.history.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.umc.history.ui.mypage.MyPageFragment
import com.umc.history.ui.mypage.MyPageMyStoryFragment

class MyPageViewPagerAdapter(fragment: MyPageFragment): FragmentStateAdapter(fragment){
    override fun getItemCount(): Int = 2
    override fun createFragment(position: Int): Fragment {
        return when(position){
            0-> MyPageMyStoryFragment(0)
            else-> MyPageMyStoryFragment(1)
        }
    }
}