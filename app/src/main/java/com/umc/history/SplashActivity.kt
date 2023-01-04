package com.umc.history


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kakao.sdk.common.KakaoSdk
import com.umc.history.ui.MainActivity

class SplashActivity:AppCompatActivity(), AuthView {

    val SPLASH_VIEW_TIME: Long = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        KakaoSdk.init(this, BuildConfig.app_key)

        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
        }, SPLASH_VIEW_TIME)
        val spf = getSharedPreferences("token", Activity.MODE_PRIVATE)
        val accessToken = spf.getString("accessToken", null)
        val refreshToken = spf.getString("refreshToken", null)
        if(accessToken != null && refreshToken != null){
            val authService = AuthService()
            authService.setAuthView(this)
            authService.tokenReissue(accessToken, refreshToken)
        }


    }

    override fun onAuthFailure() {
        Toast.makeText(this,"인터넷 연결을 확인해주세요",Toast.LENGTH_SHORT).show()
    }

    override fun onAuthLoading() {
    }

    override fun onAuthSuccess(tokenBody: TokenBody) {
        val spf = getSharedPreferences("token", Activity.MODE_PRIVATE)
        val editor = spf.edit()
        editor.putString("accessToken", tokenBody.accessToken)
        editor.putString("refreshToken", tokenBody.refreshToken)
        editor.commit()
    }



}