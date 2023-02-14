package com.umc.history.ui.detail

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.umc.history.Comment
import com.umc.history.LikeService
import com.umc.history.R
import com.umc.history.data.Story
import com.umc.history.databinding.FragmentStoryDetailBinding
import com.umc.history.ui.MainActivity
import com.umc.history.ui.home.HomeFragment


class StoryDetailFragment(story : Story) : Fragment() {
    lateinit var binding : FragmentStoryDetailBinding
    private var hashtagList = arrayListOf<String>()
    private var commentList = arrayListOf<Comment>()
    var story = story
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStoryDetailBinding.inflate(inflater, container, false)
        getHashTag()
        getComment()
        checkLike()

        binding.storyCommentEt.onFocusChangeListener = View.OnFocusChangeListener{ _, p1 ->
            if(p1){
            } else {
                hideKeyboard(binding.storyCommentEt)
            }
        }
        binding.storyCommentEt.setOnEditorActionListener(object : TextView.OnEditorActionListener{
            override fun onEditorAction(p0: TextView?, p1: Int, p2: KeyEvent?): Boolean {
                if (p1 == EditorInfo.IME_ACTION_DONE){
                    hideKeyboard(binding.storyCommentEt)
                    if(binding.storyCommentEt.text.isNotEmpty()){
                        postComment()
                    }
                    return true
                }
                return false
            }
        })

        val builder = AlertDialog.Builder(activity)
        val dialogView = layoutInflater.inflate(R.layout.dialog_report, null)
        builder.setView(dialogView)
        val alertDialog = builder.create()
        val window = alertDialog.window
        window?.setGravity(Gravity.BOTTOM)
        builder.setView(dialogView)


        binding.storyTitleTv.text = story.title

//        if(story.images.isNullOrEmpty()) {
//            binding.storyImageSv.visibility = View.GONE
//            binding.storyImageIv.visibility = View.GONE
//        } else {
//            getImage(story.images!!.size)
//        }

//        binding.storyContentTv.text = story.contents
//        binding.storyLikeTv.text = story.totalLike.toString()
//        binding.storyCommentTv.text = story.totalComment.toString()


        binding.storyLikeIv.setOnClickListener {
            postLike()
        }

        binding.storyLikeOnIv.setOnClickListener {
            postLike()
        }

        binding.storySettingLo.setOnClickListener {
            alertDialog.show()
            alertDialog.findViewById<TextView>(R.id.dialog_report_tv).setOnClickListener {
                report()
            }
            alertDialog.findViewById<TextView>(R.id.dialog_delete_tv).setOnClickListener {
                alertDialog.hide()
                delete()
            }
        }

        binding.storyExitIv.setOnClickListener {
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.fl_container, HomeFragment())
                .commitAllowingStateLoss()
        }

        return binding.root
    }
    private fun delete(){
        val spf = activity?.getSharedPreferences("token",AppCompatActivity.MODE_PRIVATE)
        val token = spf?.getString("accessToken",null)
//        if(token == null){
//            Toast.makeText(activity,"로그인이 되어있지 않습니다.",Toast.LENGTH_SHORT).show()
//        }else{
//            storyService.deleteStory(token,story.postIdx)
//        }

    }
    private fun report(){
        val addressList = "gyeondeo@gmail.com"
        val intent = Intent(Intent.ACTION_SEND, Uri.fromParts("mailto", "example@gasd.com", null)).apply {
            type = "message/rfc822"
            putExtra(Intent.EXTRA_EMAIL, addressList)
            putExtra(Intent.EXTRA_TEXT, "수신자: gyeondeo@gmail.com\n신고 게시글 제목:\n사유:")
        }
            //Uri.parse("mailto:")
        startActivity(Intent.createChooser(intent,"메일 전송하기"))
    }
    private fun getComment(){
//        commentService.getComments(story.postIdx)
    }
    private fun postComment(){
        val userSpf = activity?.getSharedPreferences("token",AppCompatActivity.MODE_PRIVATE)
        val token = userSpf?.getString("accessToken", null)
        if(token == null){
            Toast.makeText(activity,"로그인이 되어있지 않습니다.",Toast.LENGTH_SHORT).show()
        } else{
            //commentService.postComment(token,story.postIdx, binding.storyCommentEt.text.toString())
        }
    }
    private fun getHashTag(){
//        if(story.hashTags!!.isNotEmpty()){
//            val list = story.hashTags
//            for(hashTag in list!!){
//                hashtagList.add(hashTag.tag)
//            }
//            binding.storyHashtagRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//            binding.storyHashtagRv.adapter = StoryHashtagRVAdapter(hashtagList)
//        }
    }
    private fun postLike(){
        val userSpf = requireContext().getSharedPreferences("token",AppCompatActivity.MODE_PRIVATE)
        val token = userSpf.getString("accessToken", null)
        if(token == null){
            Toast.makeText(requireContext(),"로그인이 되어 있지 않습니다", Toast.LENGTH_SHORT).show()
        } else {
            val likeService = LikeService()
//            likeService.postLike(token, story.postIdx)
            if(binding.storyLikeOnIv.visibility == View.VISIBLE){
                binding.storyLikeOnIv.visibility = View.GONE
                binding.storyLikeIv.visibility = View.VISIBLE
            } else {
                binding.storyLikeOnIv.visibility = View.VISIBLE
                binding.storyLikeIv.visibility = View.GONE
            }
        }
    }
    private fun checkLike(){
        val userSpf = activity?.getSharedPreferences("token",AppCompatActivity.MODE_PRIVATE)
        val token = userSpf?.getString("accessToken", null)
        if(token != null){
//            likeService.checkLike(token, story.postIdx)
        }
    }


    private fun hideKeyboard(editText: EditText){
        (requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).apply {
            hideSoftInputFromWindow(editText.windowToken, 0)
        }
    }


}