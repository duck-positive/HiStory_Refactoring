package com.umc.history.ui.home.all

import android.app.AlertDialog
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.history.HiStoryApplication
import com.umc.history.OneStory
import com.umc.history.R
import com.umc.history.databinding.FragmentAllBinding
import com.umc.history.ui.home.StoryListAdapter
import com.umc.history.ui.viewmodel.StoryViewModel
import com.umc.history.ui.viewmodel.StoryViewModelFactory

class AllFragment(private val type : Int): Fragment() {
    lateinit var binding: FragmentAllBinding
    private var storyDatas = ArrayList<OneStory>()
    private val storyViewModel : StoryViewModel by viewModels {
        StoryViewModelFactory((requireContext().applicationContext as HiStoryApplication).repository)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAllBinding.inflate(inflater,container,false)
       // checkType(type)
        val storyRecyclerView = binding.homeStoryRecyclerView
        val adapter = StoryListAdapter()
        storyRecyclerView.adapter = adapter
        storyRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        storyViewModel.allStory.observe(viewLifecycleOwner, Observer{ story ->
            story.let {
                 adapter.notifyDataSetChanged()
            }
        })
        val builder = AlertDialog.Builder(activity)
        val dialogView = layoutInflater.inflate(R.layout.dialog_align, null)
        builder.setView(dialogView)
        val alertDialog = builder.create()
        val window = alertDialog.window
        window?.setGravity(Gravity.BOTTOM)
        builder.setView(dialogView)

        //binding.homeStoryRecyclerView.
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