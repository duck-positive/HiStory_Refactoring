package com.umc.history.ui.home

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.umc.history.ui.home.all.AllFragment

class HomeViewPagerAdapter (fragment:Fragment) : FragmentStateAdapter(fragment){

    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0-> AllFragment(0)
            1-> AllFragment(1)
            2-> AllFragment(2)
            else-> AllFragment(3)
        }
    }
}