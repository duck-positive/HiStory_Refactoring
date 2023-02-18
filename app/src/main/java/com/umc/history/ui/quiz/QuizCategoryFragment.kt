package com.umc.history.ui.quiz

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.umc.history.R
import com.umc.history.databinding.FragmentQuizCategoryBinding
import com.umc.history.ui.MainActivity
import com.umc.history.ui.home.HomeFragment

class QuizCategoryFragment : Fragment() {

    private var _binding : FragmentQuizCategoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuizCategoryBinding.inflate(inflater, container,false)
        binding.quizExitLy.setOnClickListener {
            startActivity(Intent(requireContext(), MainActivity::class.java))
        }
        binding.quizCategoryAllIv.setOnClickListener {
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.quiz_container, QuizFragment())
                .commitAllowingStateLoss()
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}