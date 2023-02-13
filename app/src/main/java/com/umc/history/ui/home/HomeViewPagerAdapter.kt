package com.umc.history.ui.home

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class HomeViewPagerAdapter (fragment:Fragment) : FragmentStateAdapter(fragment){
    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0-> StoryFragment(0)
            1-> StoryFragment(1)
            2-> StoryFragment(2)
            else-> StoryFragment(3)
        }
    }
}