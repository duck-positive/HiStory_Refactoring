package com.umc.history.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment

import com.umc.history.databinding.ActivityMainBinding
import com.umc.history.*
import com.umc.history.ui.home.HomeFragment
import okhttp3.internal.Internal.instance

class MainActivity : AppCompatActivity() {
    private var mBinding : ActivityMainBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager
            .beginTransaction()
            .replace(binding.flContainer.id, HomeFragment())
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
                R.id.nav_writing -> replaceFragment(WriteFragment())
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


