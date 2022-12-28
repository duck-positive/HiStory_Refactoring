package com.umc.history

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TestService {
    private lateinit var testView : TestView

    fun setTestView(testView: TestView){
        this.testView = testView
    }

    fun getTest(token : String, category : String){
        val retrofit = Retrofit.Builder().baseUrl("http://history-balancer-5405023.ap-northeast-2.elb.amazonaws.com").addConverterFactory(GsonConverterFactory.create()).build()
        val testService = retrofit.create(TestInterface::class.java)
        testService.getTest("Bearer $token", category).enqueue(object : Callback<GetTestResponse>{
            override fun onResponse(
                call: Call<GetTestResponse>,
                response: Response<GetTestResponse>
            ) {
                val resp = response.body()
                when(response.code()){
                    200 -> testView.onGetTestSuccess(resp!!.body)
                    else -> testView.onGetTestFailure()
                }
            }
            override fun onFailure(call: Call<GetTestResponse>, t: Throwable) {
                testView.onGetTestFailure()
            }
        })
    }
}