package com.umc.history.ui.home

import android.app.AlertDialog
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.history.HiStoryApplication
import com.umc.history.R
import com.umc.history.data.Story
import com.umc.history.databinding.FragmentStoryBinding
import com.umc.history.ui.detail.StoryDetailFragment
import com.umc.history.ui.viewmodel.StoryViewModel
import com.umc.history.ui.viewmodel.StoryViewModelFactory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class StoryFragment(private val type : Int): Fragment() {

    private var _binding : FragmentStoryBinding? = null
    private val binding get() = _binding!!
    private val storyViewModel : StoryViewModel by viewModels {
        StoryViewModelFactory((requireContext().applicationContext as HiStoryApplication).storyRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStoryBinding.inflate(inflater,container,false)
        //insertDummy()
        checkType(type)

        val builder = AlertDialog.Builder(activity)
        val dialogView = layoutInflater.inflate(R.layout.dialog_align, null)
        builder.setView(dialogView)
        val alertDialog = builder.create()
        val window = alertDialog.window
        window?.setGravity(Gravity.BOTTOM)
        builder.setView(dialogView)

        binding.homeStoryAlignIv.setOnClickListener {
            //storyViewModel.insertStory(Story(2, "dasdasd"))
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

    private fun checkType(type : Int){
        val storyRecyclerView = binding.homeStoryRecyclerView
        val adapter = StoryListAdapter()
        adapter.setItemClickListener(object : StoryListAdapter.ItemClickListener {
            override fun onItemClick(story: Story) {
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.fl_container, StoryDetailFragment(story)).commit()
            }
        })
        storyRecyclerView.adapter = adapter
        storyRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        when(type) {
            0 -> viewLifecycleOwner.lifecycleScope.launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    storyViewModel.allStory.collect { adapter.submitList(it) }
                }
            }
            1 -> viewLifecycleOwner.lifecycleScope.launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    storyViewModel.koreanStory.collect { adapter.submitList(it) }
                }
            }

            2-> {
                storyViewModel.orientalStory.observe(viewLifecycleOwner, Observer { story->
                    story.let { adapter.submitList(it) }
                })
            }

            else -> {
                storyViewModel.westernStory.observe(viewLifecycleOwner, Observer { story->
                    story.let { adapter.submitList(it) }
                })
            }
        }
    }
    fun insertDummy(){
        val vit = Bitmap.createBitmap(1,2, Bitmap.Config.ARGB_8888)
        storyViewModel.insertStory(Story(1,"KOREAN","da", "da", listOf(vit), listOf("da")))
        storyViewModel.insertStory(Story(3,"KOREAN","da", "da", listOf(vit), listOf("da")))
        storyViewModel.insertStory(Story(4,"KOREAN","da", "da", listOf(vit), listOf("da")))
        storyViewModel.insertStory(Story(5,"KOREAN","da", "da", listOf(vit), listOf("da")))
        storyViewModel.insertStory(Story(6,"KOREAN","da", "da", listOf(vit), listOf("da")))
        storyViewModel.insertStory(Story(7,"KOREAN","da", "da", listOf(vit), listOf("da")))
        storyViewModel.insertStory(Story(8,"KOREAN","da", "da", listOf(vit), listOf("da")))
        storyViewModel.insertStory(Story(9,"KOREAN","da", "da", listOf(vit), listOf("da")))
        storyViewModel.insertStory(Story(10,"KOREAN","da", "da", listOf(vit), listOf("da")))
        storyViewModel.insertStory(Story(11,"KOREAN","da", "da", listOf(vit), listOf("da")))
        storyViewModel.insertStory(Story(12,"KOREAN","da", "da", listOf(vit), listOf("da")))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}