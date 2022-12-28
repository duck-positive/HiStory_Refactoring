package com.umc.history

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.umc.history.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity(), AuthView {
    lateinit var mainActivity: MainActivity
    lateinit var binding: ActivityLoginBinding
    var token : String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.loginPwEt.onFocusChangeListener = View.OnFocusChangeListener { _, p1 ->
            if(p1){

            } else{
                hideKeyboard(binding.loginPwEt)
            }
        }

        binding.loginEnterBtn.setOnClickListener{
            login()
        }
        binding.loginSignupTv.setOnClickListener {
            signUp()
        }
        binding.loginExitIv.setOnClickListener {
            exitLogin()
        }

    }

    override fun onAuthFailure() {
        showToast()
    }

    override fun onAuthLoading() {
    }

    override fun onAuthSuccess(tokenBody: TokenBody) {
        val token = tokenBody
        val accessToken = token.accessToken
        val refreshToken = token.refreshToken
        val spf = getSharedPreferences("token", MODE_PRIVATE)
        val editor = spf.edit()
        editor.putString("accessToken",accessToken)
        editor.putString("refreshToken",refreshToken)
        editor.putString("id",binding.loginIdEt.text.toString())
        editor.commit()
        exitLogin()
    }

    private fun login(){
        if(binding.loginIdEt.text.toString().isEmpty() || binding.loginPwEt.text.toString().isEmpty()) {
            Toast.makeText(this,"아이디나 비밀번호 입력", Toast.LENGTH_SHORT).show()
        }
        else {
            val authService = AuthService()
            authService.setAuthView(this)
            authService.login(binding.loginIdEt.text.toString(), binding.loginPwEt.text.toString())
            val currentUser = getSharedPreferences("token", MODE_PRIVATE)
            token = currentUser.getString("accessToken",null)

        }
    }
    private fun signUp(){
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }

    private fun exitLogin(){
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("token", token)
        startActivity(intent)
    }

    private fun showToast(){
        Toast.makeText(this,"아이디나 비밀번호를 확인해주세요",Toast.LENGTH_SHORT).show()
    }
    private fun hideKeyboard(editText: EditText){
        (getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager).apply {
            hideSoftInputFromWindow(editText.windowToken, 0)
        }
    }

}