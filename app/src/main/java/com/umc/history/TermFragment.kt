package com.umc.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.umc.history.databinding.FragmentTermBinding

class TermFragment : Fragment() {
    lateinit var binding : FragmentTermBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTermBinding.inflate(inflater, container, false)
        binding.termWv.loadUrl("file:///android_asset/term.html")
        binding.termExitIb.setOnClickListener {
            (context as SignUpActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.signup_frm, PasswordFragment())
                .commit()
        }
        return binding.root
    }
}