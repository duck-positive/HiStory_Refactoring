package com.umc.history

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class HomeViewPagerAdapter (fragment:Fragment):FragmentStateAdapter(fragment){

    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0-> AllFragment()
            1->KoreanHistoryFragment()
            2->OrientalHistoryFragment()
            else->WesternHistoryFragment()
        }
    }
}