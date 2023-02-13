package com.umc.history.ui.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.history.*
import com.umc.history.databinding.FragmentMypageLikestoryBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyPageLikeStoryFragment: Fragment(), OneStoryView {
    lateinit var binding: FragmentMypageLikestoryBinding
    private var myPageStoryDatas = ArrayList<LikedResponse>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMypageLikestoryBinding.inflate(inflater,container,false)
        val spf = activity?.getSharedPreferences("token", AppCompatActivity.MODE_PRIVATE)
        val token = spf?.getString("accessToken", null)
        if(token == null){
            binding.myPageStoryRecyclerView.visibility = View.GONE
        } else {

            val retrofit = Retrofit.Builder().baseUrl("http://history-balancer-5405023.ap-northeast-2.elb.amazonaws.com").addConverterFactory(GsonConverterFactory.create()).build()
            val likedStoryService = retrofit.create(LikedStoryInterface::class.java)

            likedStoryService.getLikedStory("Bearer $token").enqueue(object : Callback<GetLikedStoryResponse> {
                override fun onResponse(
                    call: Call<GetLikedStoryResponse>,
                    response: Response<GetLikedStoryResponse>
                ) {
                    val resp = response.body()
                    if (resp?.body != null) {
                        myPageStoryDatas.clear()
                        for (body in resp.body) {
                            myPageStoryDatas.add(body)
                        }

                    val myPageStoryLikeRVAdapter = MyPageStoryLikeRVAdapter(myPageStoryDatas)
                    //리사이클러뷰에 어댑터를 연결
                    binding.myPageStoryRecyclerView.adapter = myPageStoryLikeRVAdapter
                    myPageStoryLikeRVAdapter.likeItemClickListener(object :
                        MyPageStoryLikeRVAdapter.LikeItemClickListener {
                        override fun onItemClick(story: LikedResponse) {

                        }
                    })
                    binding.myPageStoryRecyclerView.visibility = View.VISIBLE
                    }
                }

                override fun onFailure(call: Call<GetLikedStoryResponse>, t: Throwable) {

                }
            })
        }






//        //데이터 리스트 생성 더미데이터
//        myPageStoryDatas.apply {
//            add(MyPageStory("제에에에목",R.drawable.mypage_profile_img_ex1,12,12,"이런 식으로 내용이 보여지게 됩니다 어떻게 해야하지 무슨 내용을 적지 으아아아아아아아아","닉네임"))
//            add(MyPageStory("제에에에목",R.drawable.mypage_profile_img_ex1,12,12,"이런 식으로 내용이 보여지게 됩니다 어떻게 해야하지 무슨 내용을 적지 으아아아아아아아아","닉네임"))
//            add(MyPageStory("제에에에목",R.drawable.mypage_profile_img_ex1,12,12,"이런 식으로 내용이 보여지게 됩니다 어떻게 해야하지 무슨 내용을 적지 으아아아아아아아아","닉네임"))
//        }






        val dividerItemDecoration =
            DividerItemDecoration(binding.myPageStoryRecyclerView.context, LinearLayoutManager(activity).orientation)

        binding.myPageStoryRecyclerView.addItemDecoration(dividerItemDecoration)


        return binding.root
    }


    override fun onStoryFailure() {

    }

    override fun onStoryLoading() {
    }

    override fun onStorySuccess(status: String, body: OneStory?) {
//        (context as MainActivity).supportFragmentManager.beginTransaction()
//            .replace(R.id.fl_container, StoryDetailFragment(body!!)).commitAllowingStateLoss()
    }
}