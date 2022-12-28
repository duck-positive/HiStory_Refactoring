package com.umc.history

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.umc.history.databinding.FragmentNicknameBinding

class NicknameFragment : Fragment(), ExistView {
    lateinit var binding: FragmentNicknameBinding
    private var existFlag = true
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNicknameBinding.inflate(inflater, container, false)

        binding.signupNicknameEt.onFocusChangeListener = View.OnFocusChangeListener{ p0, p1 ->
            if(p1){

            } else {
                hideKeyboard(binding.signupNicknameEt)
            }

        }

        binding.signupNicknameCheckTv.setOnClickListener {
            val regex = Regex("[^A-Za-z0-9가-힣]")
            val result = regex.replace(binding.signupNicknameEt.text.toString(), "")
            result.filter { !it.isWhitespace() }
            if(result != binding.signupNicknameEt.text.toString()){
                showWarning("공백과 특수문자는 사용이 불가능합니다.")
            }else if(binding.signupNicknameEt.length() < 2 || binding.signupNicknameEt.length() > 9){
                showWarning("닉네임은 2글자 이상 10글자 미만이어야합니다.")
            } else{
                checkExist()
            }
        }
        binding.signupNicknameEt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                existFlag = true
                binding.signupNicknameCheckExistTv.visibility = View.INVISIBLE
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
        })

        binding.signupNicknameNextBtn.setOnClickListener {
            binding.signupNicknameWarningTv.visibility = View.GONE
            if(existFlag){
                showWarning("중복체크 버튼을 눌러주세요")
            } else
            if (binding.signupNicknameEt.text.toString().isEmpty()) {
                showWarning("닉네임을 입력해주세요")
            } else {
                val idFragment = IdFragment()
                val bundle = Bundle()
                bundle.putString("nickname",binding.signupNicknameEt.text.toString())
                idFragment.arguments = bundle

                (context as SignUpActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.signup_frm, idFragment)
                .commit()
            }
        }
        return binding.root
    }
    private fun hideKeyboard(editText: EditText){
        (requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).apply {
            hideSoftInputFromWindow(editText.windowToken, 0)
        }
    }

    private fun showWarning(message : String){
        binding.signupNicknameWarningIv.visibility = View.VISIBLE
        binding.signupNicknameWarningTv.visibility = View.VISIBLE
        binding.signupNicknameWarningTv.text = message
    }

    override fun onAuthFailure() {
        Toast.makeText(activity,"인터넷 연결을 확인해주세요",Toast.LENGTH_SHORT).show()
    }

    override fun onAuthLoading() {

    }

    override fun onAuthSuccess(body: Boolean) {
        existFlag = body
        when(existFlag){
            false ->{
                binding.signupNicknameWarningTv.visibility = View.INVISIBLE
                binding.signupNicknameWarningIv.visibility = View.INVISIBLE
                binding.signupNicknameCheckExistTv.visibility = View.VISIBLE
            }
            true ->{
                binding.signupNicknameWarningTv.visibility = View.VISIBLE
                binding.signupNicknameWarningIv.visibility = View.VISIBLE
                binding.signupNicknameCheckExistTv.visibility = View.INVISIBLE
                showWarning("중복입니다")
            }
        }
    }

    private fun checkExist(){
        val authService = AuthService()
        authService.setExistView(this)
        authService.nickNameExist(binding.signupNicknameEt.text.toString())
    }
}