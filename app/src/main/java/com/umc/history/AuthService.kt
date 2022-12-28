package com.umc.history

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AuthService {
    private lateinit var existView: ExistView
    private lateinit var authView: AuthView


    fun setExistView(existView: ExistView){
        this.existView = existView
    }
    fun setAuthView(authView: AuthView){
        this.authView = authView
    }
    fun nickNameExist(nickname: String?){
        val retrofit = Retrofit.Builder().baseUrl("http://history-balancer-5405023.ap-northeast-2.elb.amazonaws.com").addConverterFactory(GsonConverterFactory.create()).build()
        val authService = retrofit.create(AuthInterface::class.java)
        authService.nickNameExist(nickname).enqueue(object : Callback<ExistResponse>{
            override fun onResponse(call: Call<ExistResponse>, response: Response<ExistResponse>) {
                val resp = response.body()
                if(response.code() == 200 || response.code() == 202){
                    existView.onAuthSuccess(resp!!.body)
                } else {
                    existView.onAuthFailure()
                }

            }
            override fun onFailure(call: Call<ExistResponse>, t: Throwable) {
                existView.onAuthFailure()
            }


        })
    }

    fun userIdExist(userId : String?){
        val retrofit = Retrofit.Builder().baseUrl("http://history-balancer-5405023.ap-northeast-2.elb.amazonaws.com").addConverterFactory(GsonConverterFactory.create()).build()
        val authService = retrofit.create(AuthInterface::class.java)
        authService.userIdExist(userId).enqueue(object : Callback<ExistResponse>{
            override fun onResponse(call: Call<ExistResponse>, response: Response<ExistResponse>){
                val resp = response.body()
                if(response.code() == 200 || response.code() == 202){
                    existView.onAuthSuccess(resp!!.body)
                } else {
                    existView.onAuthFailure()
                }
            }
            override fun onFailure(call: Call<ExistResponse>, t: Throwable) {
                existView.onAuthFailure()
            }
        })
    }
    fun signUp(nickname:String?, id:String?, password:String){
        val retrofit = Retrofit.Builder().baseUrl("http://history-balancer-5405023.ap-northeast-2.elb.amazonaws.com").addConverterFactory(GsonConverterFactory.create()).build()
        val authService = retrofit.create(AuthInterface::class.java)
        authService.signUp(User(id, nickname, password)).enqueue(object : Callback<AuthResponse>{
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {

            }
        })
    }

    fun login(id:String, password:String){
        val retrofit = Retrofit.Builder().baseUrl("http://history-balancer-5405023.ap-northeast-2.elb.amazonaws.com")
            .addConverterFactory(GsonConverterFactory.create()).build()
        val authService = retrofit.create(AuthInterface::class.java)
        authService.login(Login(id, password)).enqueue(object : Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                val resp = response.body()
                if(response.code() == 200 || response.code() == 202){
                    authView.onAuthSuccess(resp!!.body)
                } else{
                    authView.onAuthFailure()

                }
            }
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                authView.onAuthFailure()
            }
        })
    }

    fun tokenReissue(accessToken : String, refreshToken : String){
        val retrofit = Retrofit.Builder().baseUrl("http://history-balancer-5405023.ap-northeast-2.elb.amazonaws.com")
            .addConverterFactory(GsonConverterFactory.create()).build()
        val authService = retrofit.create(AuthInterface::class.java)
        authService.tokenReissue(Token(accessToken, refreshToken)).enqueue(object : Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                val resp = response.body()
                if(response.code() == 200 || response.code() == 202){
                    authView.onAuthSuccess(resp!!.body)
                } else{
                    authView.onAuthFailure()
                }
            }
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                authView.onAuthFailure()
            }
        })
    }
}