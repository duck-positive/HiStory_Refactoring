package com.umc.history

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.umc.history.databinding.FragmentIdBinding
import com.umc.history.databinding.FragmentPasswordBinding
import org.w3c.dom.Text

class PasswordFragment : Fragment() {
    lateinit var binding : FragmentPasswordBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPasswordBinding.inflate(inflater, container, false)
        val nickname = arguments?.getString("nickname")
        val id = arguments?.getString("id")


        binding.signupPwdConfirmBtn.setOnClickListener {
            val regex = Regex("[^A-Za-z0-9]")
            val result = regex.replace(binding.signupPwdEt.text.toString(), "")
            result.filter { !it.isWhitespace() }
            if(result != binding.signupPwdEt.text.toString()){
                showWarning("공백과 특수문자, 한글은 사용이 불가능합니다.", 0)
            }else if(binding.signupPwdEt.length() < 4 || binding.signupPwdEt.length() > 9){
                showWarning("비밀번호는 4글자 이상 10글자 미만이어야합니다.", 0)
            }else{
                when {
                    binding.signupPwdEt.text.toString().isEmpty() -> showWarning("비밀번호를 입력해주세요",0)
                    binding.signupPwdCheckEt.text.toString() != binding.signupPwdEt.text.toString() -> showWarning("비밀번호가 일치하지 않습니다.",1)
                    else ->{
                        val authService = AuthService()
                        authService.signUp(nickname, id, binding.signupPwdEt.text.toString())
                        val intent = Intent(activity, LoginActivity::class.java)
                        startActivity(intent)
                    }
                }
            }

        }
        binding.signupPwdCheckEt.onFocusChangeListener = View.OnFocusChangeListener{ p0, p1 ->
            if(p1){

            } else {
                hideKeyboard(binding.signupPwdCheckEt)
            }
        }
        binding.signupPwdTerm2Tv.setOnClickListener {
            (context as SignUpActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.signup_frm, TermFragment())
                .commit()
        }

        return binding.root
    }

    private fun hideKeyboard(editText: EditText){
        (requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).apply {
            hideSoftInputFromWindow(editText.windowToken, 0)
        }
    }

    private fun showWarning(message : String, flag : Int){
        when (flag){
            0 -> {
                binding.signupPwdWarningTv.visibility = View.VISIBLE
                binding.signupPwdWarningIv.visibility = View.VISIBLE
                binding.signupPwdWarningTv.text = message
            }
            1 -> {
                binding.signupPwdCheckWarningIv.visibility = View.VISIBLE
                binding.signupPwdCheckWarningTv.visibility = View.VISIBLE
                binding.signupPwdWarningTv.visibility = View.INVISIBLE
                binding.signupPwdWarningIv.visibility = View.INVISIBLE
                binding.signupPwdCheckWarningTv.text = message
            }
        }

    }
}