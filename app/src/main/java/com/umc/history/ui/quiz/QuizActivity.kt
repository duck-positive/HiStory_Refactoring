package com.umc.history.ui.quiz

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.umc.history.databinding.ActivityQuizBinding
import com.umc.history.databinding.FragmentQuizCategoryBinding

import com.umc.history.ui.MainActivity
import com.umc.history.ui.quiz.QuizCategoryFragment


class QuizActivity : AppCompatActivity() {
    private var _binding: ActivityQuizBinding?=null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager
            .beginTransaction()
            .add(binding.quizContainer.id, QuizCategoryFragment())
            .commitAllowingStateLoss()

        binding.questionExitBtn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}