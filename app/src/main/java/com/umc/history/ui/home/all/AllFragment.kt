package com.umc.history.ui.home.all

import android.app.AlertDialog
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.umc.history.OneStory
import com.umc.history.R
import com.umc.history.databinding.FragmentAllBinding

class AllFragment(private val type : Int): Fragment() {
    lateinit var binding: FragmentAllBinding
    private var storyDatas = ArrayList<OneStory>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAllBinding.inflate(inflater,container,false)
       // checkType(type)

        val builder = AlertDialog.Builder(activity)
        val dialogView = layoutInflater.inflate(R.layout.dialog_align, null)
        builder.setView(dialogView)
        val alertDialog = builder.create()
        val window = alertDialog.window
        window?.setGravity(Gravity.BOTTOM)
        builder.setView(dialogView)

        binding.homeStoryAlignIv.setOnClickListener {
            alertDialog.show()
            alertDialog.findViewById<TextView>(R.id.dialog_like_tv).setOnClickListener {
                alertDialog.hide()
            }
            alertDialog.findViewById<TextView>(R.id.dialog_recent_tv).setOnClickListener {
                alertDialog.hide()
            }
        }
       return binding.root
    }

//    private fun checkType(type : Int){
//        when(type) {
//            0 -> {
//                TODO("전체 불러오기")
//            }
//
//            1 -> {
//                TODO("한국사 불러오기")
//            }
//            2-> {
//                TODO("동양사 불러오기")
//            }
//            else -> {
//                TODO("서양사 불러오기")
//            }
//        }
//    }


}