package com.umc.history

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.umc.history.databinding.ActivityTestBinding
import com.umc.history.ui.MainActivity


class TestActivity : AppCompatActivity(), TestView {
    private var mBinding: ActivityTestBinding?=null
    private val binding get() = mBinding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.testExitLy.setOnClickListener{
            val intent = Intent(applicationContext,MainActivity::class.java)
            startActivity(intent)
        }
        binding.testAllIv.setOnClickListener {
            getTest("all")
        }
        binding.testKoreanHistoryIv.setOnClickListener {
            getTest("KOREAN")
        }
        binding.testOrientalIv.setOnClickListener {
            getTest("ASIAN")
        }
        binding.testWesternIv.setOnClickListener {
            getTest("WESTERN")
        }


    }

    override fun onGetTestSuccess(body: List<Test>?) {
        val testList = arrayListOf<Test>()
        testList.clear()
        for(i in body!!)
            testList.add(i)
        val intent = Intent(this, QuestionActivity::class.java)
        intent.putExtra("Test", testList)
        startActivity(intent)
    }
    override fun onGetTestLoading() {

    }
    override fun onGetTestFailure() {
        Toast.makeText(this,"퀴즈를 불러오는데 실패했습니다.",Toast.LENGTH_SHORT).show()
    }

    private fun getTest(category: String){
        val spf = this.getSharedPreferences("token", MODE_PRIVATE)
        val token = spf?.getString("accessToken", null)
        when(token){
            null -> Toast.makeText(this, "로그인 후에 사용할 수 있는 기능입니다.", Toast.LENGTH_SHORT).show()
            else -> {
                val testService = TestService()
                testService.setTestView(this)
                testService.getTest(token, category)
            }
        }
    }



}