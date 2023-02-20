package com.umc.history.ui.quiz

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.umc.history.HiStoryApplication
import com.umc.history.R
import com.umc.history.databinding.FragmentQuizCategoryBinding
import com.umc.history.ui.MainActivity
import com.umc.history.ui.home.HomeFragment
import com.umc.history.ui.viewmodel.QuizViewModel
import com.umc.history.ui.viewmodel.QuizViewModelFactory

class QuizCategoryFragment : Fragment() {

    private var _binding : FragmentQuizCategoryBinding? = null
    private val binding get() = _binding!!
    private val quizViewModel : QuizViewModel by viewModels {
        QuizViewModelFactory((requireContext().applicationContext as HiStoryApplication).quizRepository)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuizCategoryBinding.inflate(inflater, container,false)

        binding.quizCategoryAllIv.setOnClickListener {
            quizViewModel.getQuiz("ALL")
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.quiz_container, QuizFragment())
                .commitAllowingStateLoss()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}