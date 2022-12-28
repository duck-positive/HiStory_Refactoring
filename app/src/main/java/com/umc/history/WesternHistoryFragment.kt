package com.umc.history

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.history.databinding.FragmentWesternhistoryBinding

class WesternHistoryFragment: Fragment(), StoryView, OneStoryView {
    lateinit var binding: FragmentWesternhistoryBinding
    private var storyDatas = ArrayList<OneStory>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWesternhistoryBinding.inflate(inflater,container,false)
        initView(0)

        val builder = android.app.AlertDialog.Builder(activity)
        val dialogView = layoutInflater.inflate(R.layout.dialog_align, null)
        builder.setView(dialogView)
        val alertDialog = builder.create()
        val window = alertDialog.window
        window?.setGravity(android.view.Gravity.BOTTOM)
        builder.setView(dialogView)

        binding.westernStoryAlignIv.setOnClickListener {
            alertDialog.show()
            alertDialog.findViewById<TextView>(R.id.dialog_like_tv).setOnClickListener {
                alertDialog.hide()
                initView(1)
            }
            alertDialog.findViewById<TextView>(R.id.dialog_recent_tv).setOnClickListener {
                alertDialog.hide()
                initView(0)
            }
        }

        return binding.root
    }

    override fun onStoryFailure() {
        Toast.makeText(activity,"인터넷 연결을 확인해주세요",Toast.LENGTH_SHORT).show()
    }

    override fun onStoryLoading() {

    }

    override fun onStorySuccess(status: String, body: List<OneStory>) {
        storyDatas.clear()
        for (story in body){
            storyDatas.add(
                story
            )

        }
        binding.westernStoryRecyclerView.layoutManager =
            LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
        val storyRVAdapter = StoryRVAdapter(storyDatas)
        storyRVAdapter.myItemClickListener(object : StoryRVAdapter.MyItemClickListener{
            override fun onItemClick(story: OneStory) {
                getOneStory(story.postIdx)
            }
        })
        binding.westernStoryRecyclerView.adapter = storyRVAdapter

    }
    private fun getOneStory(story:Int){
        val storyService = StoryService()
        storyService.setOneStoryView(this)
        storyService.getStory(story)
    }

    override fun onStorySuccess(status: String, body: OneStory?) {
        (context as MainActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.fl_container, StoryDetailFragment(body!!)).commitAllowingStateLoss()
    }

    private fun initView(flag : Int){
        val storyService = StoryService()
        storyService.setStoryView(this)
        when(flag){
            0 -> storyService.getStoriesCategoryOrderByRecent("WESTERN")
            else -> storyService.getStoriesCategoryOrderByLike("WESTERN")
        }
    }
}