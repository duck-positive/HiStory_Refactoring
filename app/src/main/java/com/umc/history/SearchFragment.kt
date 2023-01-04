package com.umc.history

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.history.databinding.FragmentSearchBinding
import com.umc.history.ui.MainActivity

class SearchFragment : Fragment(), SearchView, OneStoryView {
    lateinit var binding : FragmentSearchBinding
    private var storyDatas = ArrayList<Body>()
    var flag = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        val builder = AlertDialog.Builder(activity)
        val dialogView = layoutInflater.inflate(R.layout.dialog_search, null)
        builder.setView(dialogView)
        val alertDialog = builder.create()
        val window = alertDialog.window
        window?.setGravity(Gravity.BOTTOM)
        builder.setView(dialogView)
        binding.searchChangeIv.setOnClickListener {
            alertDialog.show()
            alertDialog.findViewById<TextView>(R.id.dialog_title_tv).setOnClickListener {
                flag = 1
                alertDialog.hide()
            }
            alertDialog.findViewById<TextView>(R.id.dialog_content_tv).setOnClickListener {
                flag = 0
                alertDialog.hide()
            }

        }

        binding.searchSearchEt.setOnEditorActionListener(object : TextView.OnEditorActionListener{
            override fun onEditorAction(p0: TextView?, p1: Int, p2: KeyEvent?): Boolean {
                if (p1 == EditorInfo.IME_ACTION_SEARCH){
                    searchTitle()
                    hideKeyboard(binding.searchSearchEt)
                    return true
                }
                return false
            }
        })

        val dividerItemDecoration = DividerItemDecoration(binding.searchStoryRv.context, LinearLayoutManager(activity).orientation)
        binding.searchStoryRv.addItemDecoration(dividerItemDecoration)
        return binding.root
    }

    private fun hideKeyboard(editText: EditText){
        (requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).apply {
            hideSoftInputFromWindow(editText.windowToken, 0)
        }
    }

    override fun onStoryFailure() {
        Toast.makeText(activity,"인터넷 연결을 확인해주세요",Toast.LENGTH_SHORT).show()
    }

    override fun onStoryLoading() {

    }

    override fun onStorySuccess(status: String, body: OneStory?) {
        (context as MainActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.fl_container, StoryDetailFragment(body!!)).commitAllowingStateLoss()
    }

    override fun onSearchFailure() {
        Toast.makeText(activity,"인터넷 연결을 확인해주세요",Toast.LENGTH_SHORT).show()
    }

    override fun onSearchLoading() {

    }

    override fun onSearchSuccess(searchBody : List<Body>?) {
        if(searchBody!!.isNotEmpty()){
            storyDatas.clear()
            for(story in searchBody){
                storyDatas.add(story)
            }
            binding.searchStoryRv.visibility = View.VISIBLE
            binding.searchNoneTv.visibility = View.GONE
            val searchStoryRVAdapter = SearchStoryRVAdapter(storyDatas)
            binding.searchStoryRv.adapter = searchStoryRVAdapter
            searchStoryRVAdapter.searchItemClickListener(object : SearchStoryRVAdapter.SearchItemClickListener{
                override fun onItemClick(story: Body) {
                    getOneStory(story)
                }
            })
        } else {
            binding.searchStoryRv.visibility = View.GONE
            binding.searchNoneTv.visibility = View.VISIBLE
        }
    }
    private fun getOneStory(story:Body){
        val storyService = StoryService()
        storyService.setOneStoryView(this)
        storyService.getStory(story.postIdx)
    }
    private fun searchTitle(){
        if(binding.searchSearchEt.text.isNotEmpty()){
            binding.searchSearchTv.text = "'${binding.searchSearchEt.text.trim()}'와 일치하는 이야기"
        }
        val searchService = SearchService()
        searchService.setSearchView(this)
        searchService.searchContents(binding.searchSearchEt.text.toString().trim())
        when (flag){
            0->searchService.searchContents(binding.searchSearchEt.text.toString().trim())
            else->searchService.searchTitle(binding.searchSearchEt.text.toString().trim())
        }
    }
}