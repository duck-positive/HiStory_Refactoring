package com.umc.history.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.kakao.sdk.auth.AuthApiClient
import com.umc.history.R
import com.umc.history.TestActivity
import com.umc.history.databinding.ActivityMainBinding
import com.umc.history.ui.home.HomeFragment
import com.umc.history.ui.mypage.MyPageFragment
import com.umc.history.ui.search.SearchFragment
import com.umc.history.ui.viewmodel.LoginViewModel
import com.umc.history.ui.write.WriteFragment

class MainActivity : AppCompatActivity() {
    private var mBinding : ActivityMainBinding? = null
    private val binding get() = mBinding!!
    private val loginViewModel : LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var token : String? = null
        val loginObserver = Observer<String> { loginToken ->
            token = loginToken
        }
        loginViewModel.currentAccessToken.observe( this, loginObserver)

        binding.bnvMain.selectedItemId = R.id.nav_home
        supportFragmentManager
            .beginTransaction()
            .add(binding.flContainer.id, HomeFragment())
            .commitAllowingStateLoss()
        binding.bnvMain.setOnItemSelectedListener {
            changeFragment(it.itemId)
            true
        }
    }

    private fun changeFragment(menuId : Int){
        val currentFragment = supportFragmentManager.fragments[0]
        if(currentFragment is WriteFragment){
            if(menuId != R.id.nav_writing){
                showAlert(menuId)
            }
        } else {
            when(menuId){
                R.id.nav_home -> replaceFragment(HomeFragment())
                R.id.nav_writing -> {
                    //TODO("1. token validation check 필요
                    //      2. navigation item select 상태 유지 필요")
                    if(AuthApiClient.instance.hasToken()){
                        replaceFragment(WriteFragment())
                    } else {
                        Toast.makeText(this, "로그인 후 작성할 수 있습니다", Toast.LENGTH_SHORT).show()
                    }
                }
                R.id.nav_my_page -> replaceFragment(MyPageFragment())
                R.id.nav_search -> replaceFragment(SearchFragment())
                R.id.nav_quiz -> {
                    val intent = Intent(applicationContext, TestActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }

    private fun replaceFragment(destination : Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(binding.flContainer.id, destination)
            .commitAllowingStateLoss()
    }

    private fun showAlert(item : Int){
        val builder = AlertDialog.Builder(this)
        with(builder){
            setTitle("작성중이던 내용이 사라져요")
            setMessage("다음에 이야기를 들려주실건가요?")
            setPositiveButton("떠나기"){ _, _ ->
                when(item){
                    R.id.nav_home -> replaceFragment(HomeFragment())
                    R.id.nav_writing -> replaceFragment(WriteFragment())
                    R.id.nav_my_page -> replaceFragment(MyPageFragment())
                    R.id.nav_search -> replaceFragment(SearchFragment())
                    R.id.nav_quiz -> {
                        val intent = Intent(applicationContext, TestActivity::class.java)
                        startActivity(intent)
                    }
                }
            }
            setNegativeButton("남아있기", null)
            show()
        }
    }
}


