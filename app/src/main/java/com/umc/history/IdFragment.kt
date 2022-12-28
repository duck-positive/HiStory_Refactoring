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
import com.umc.history.databinding.FragmentIdBinding


class IdFragment : Fragment(), ExistView {
    lateinit var binding : FragmentIdBinding
    var existFlag = true
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIdBinding.inflate(inflater, container, false)
        val nickname = arguments?.getString("nickname")

        binding.signupIdCheckTv.setOnClickListener {
            val regex = Regex("[^A-Za-z0-9]")
            val result = regex.replace(binding.signupIdEt.text.toString(), "")
            result.filter { !it.isWhitespace() }
            if(result != binding.signupIdEt.text.toString()){
                showWarning("공백과 특수문자, 한글은 사용이 불가능합니다.")
            }else if(binding.signupIdEt.length() < 4 || binding.signupIdEt.length() > 14){
                showWarning("아이디는 4글자 이상 15글자 미만이어야합니다.")
            } else{
                checkExist()
            }
        }
        binding.signupIdEt.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                existFlag = true
                binding.signupIdCheckExistTv.visibility = View.INVISIBLE
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
        })
        binding.signupIdNextBtn.setOnClickListener {
            binding.signupIdWarningTv.visibility = View.GONE
            if(existFlag){
                showWarning("중복체크 버튼을 눌러주세요")
            } else if (binding.signupIdEt.text.toString().isEmpty()) {
                showWarning("아이디를 입력해주세요")
            } else {
                val passwordFragment = PasswordFragment()
                val bundle = Bundle()
                bundle.putString("nickname", nickname)
                bundle.putString("id", binding.signupIdEt.text.toString())
                passwordFragment.arguments = bundle
                (context as SignUpActivity).supportFragmentManager.beginTransaction()
                    .replace(R.id.signup_frm, passwordFragment)
                    .commitAllowingStateLoss()
            }
        }

        binding.signupIdEt.onFocusChangeListener = View.OnFocusChangeListener{ _, p1 ->
            if(p1){

            } else {
                hideKeyboard(binding.signupIdEt)
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
        binding.signupIdWarningTv.visibility = View.VISIBLE
        binding.signupIdWarningIv.visibility = View.VISIBLE
        binding.signupIdWarningTv.text = message
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
                binding.signupIdWarningTv.visibility = View.INVISIBLE
                binding.signupIdWarningIv.visibility = View.INVISIBLE
                binding.signupIdCheckExistTv.visibility = View.VISIBLE
            }
            true ->{
                showWarning("중복입니다")
            }
        }
    }
    private fun checkExist(){
        val authService = AuthService()
        authService.setExistView(this)
        authService.userIdExist(binding.signupIdEt.text.toString())
    }

}