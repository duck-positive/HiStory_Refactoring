package com.umc.history.ui.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.history.OneStory
import com.umc.history.OneStoryView
import com.umc.history.databinding.FragmentMypageMyStoryBinding

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body

class MyPageMyStoryFragment: Fragment(), OneStoryView {
    lateinit var binding: FragmentMypageMyStoryBinding
    private var myPageStoryDatas = ArrayList<Body>()
    private var token : String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMypageMyStoryBinding.inflate(inflater,container,false)
        //더미데이터랑 어댑터 연결
        val spf = activity?.getSharedPreferences("token", AppCompatActivity.MODE_PRIVATE)
        val token = spf?.getString("accessToken", null)
        if(token == null){
            binding.homeStoryRecyclerView.visibility = View.GONE
        } else {



            val retrofit = Retrofit.Builder().baseUrl("http://history-balancer-5405023.ap-northeast-2.elb.amazonaws.com").addConverterFactory(
                GsonConverterFactory.create()).build()



        }



//        //데이터 리스트 생성 더미데이터
//        myPageStoryDatas.apply {
//            add(MyPageStory("제에에에목",R.drawable.mypage_profile_img_ex1,12,12,"이런 식으로 내용이 보여지게 됩니다 어떻게 해야하지 무슨 내용을 적지 으아아아아아아아아","닉네임"))
//            add(MyPageStory("제에에에목",R.drawable.mypage_profile_img_ex1,12,12,"이런 식으로 내용이 보여지게 됩니다 어떻게 해야하지 무슨 내용을 적지 으아아아아아아아아","닉네임"))
//            add(MyPageStory("제에에에목",R.drawable.mypage_profile_img_ex1,12,12,"이런 식으로 내용이 보여지게 됩니다 어떻게 해야하지 무슨 내용을 적지 으아아아아아아아아","닉네임"))
//        }




        val dividerItemDecoration =
            DividerItemDecoration(binding.homeStoryRecyclerView.context, LinearLayoutManager(activity).orientation)

        binding.homeStoryRecyclerView.addItemDecoration(dividerItemDecoration)


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