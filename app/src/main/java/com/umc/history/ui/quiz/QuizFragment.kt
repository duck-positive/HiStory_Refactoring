package com.umc.history.ui.quiz

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.umc.history.HiStoryApplication
import com.umc.history.R

import com.umc.history.databinding.FragmentQuizBinding
import com.umc.history.ui.MainActivity
import com.umc.history.ui.viewmodel.QuizViewModel
import com.umc.history.ui.viewmodel.QuizViewModelFactory

class QuizFragment : Fragment(){
    private var _binding: FragmentQuizBinding? = null
    private val binding get() = _binding!!
    private val quizViewModel : QuizViewModel by activityViewModels {
        QuizViewModelFactory((requireContext().applicationContext as HiStoryApplication).quizRepository)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        quizViewModel.quizList.observe(viewLifecycleOwner, Observer {
            quiz -> binding.quizQuizTv.text = quiz[0].question
        })

        binding.questionNextIv.setOnClickListener {
            if(binding.quizAnswerTrueIv.isSelected || binding.quizAnswerFalseIv.isSelected){
                parentFragmentManager.setFragmentResult("ANSWER", bundleOf("bundleKey" to binding.quizAnswerTrueIv.isSelected))
                parentFragmentManager
                    .beginTransaction()
                    .replace(R.id.quiz_container, QuizAnswerFragment())
                    .commitAllowingStateLoss()
            } else {
                Toast.makeText(requireContext(), "답을 선택해주세요", Toast.LENGTH_SHORT).show()
            }
        }

        binding.quizAnswerTrueIv.setOnClickListener {
            when(binding.quizAnswerFalseIv.isSelected){
                true -> {
                    binding.quizAnswerFalseIv.isSelected = false
                    binding.quizAnswerTrueIv.isSelected = true
                }
                else -> {
                    binding.quizAnswerTrueIv.isSelected = true
                }
            }
        }

        binding.quizAnswerFalseIv.setOnClickListener {
            when(binding.quizAnswerTrueIv.isSelected) {
                true -> {
                    binding.quizAnswerTrueIv.isSelected = false
                    binding.quizAnswerFalseIv.isSelected = true
                }
                else -> {
                    binding.quizAnswerFalseIv.isSelected = true
                }
            }
        }

        return binding.root
    }
    override fun onResume() {
        super.onResume()

//        var answer = true
//        var select = true
//        //val testList = intent?.getParcelableArrayListExtra<Test>("Test")
//        //val answerIntent = Intent(this, QuestionAnswerActivity::class.java)
//        if(testList!!.isNotEmpty()){
//            binding.questionQuestionTv.text = "Q. ${testList[0].question}"
//            answer = testList[0].answer
//            answerIntent.putExtra("solution",testList[0].solution)
//            testList.removeAt(0)
//            answerIntent.putExtra("test",testList)
//        }
//        binding.questionAnswerNotselectedOIv.setOnClickListener {
//            select = true
//            selectAnswer(select)
//        }
//        binding.questionAnswerNotselectedXIv.setOnClickListener {
//            select = false
//            selectAnswer(select)
//        }
//
//        binding.questionNextIv.setOnClickListener {
//            if(answer != select)
//                answerIntent.putExtra("answer",false)
//            else
//                answerIntent.putExtra("answer", true)
//            startActivity(answerIntent)
//        }
//
//        binding.questionExitLy.setOnClickListener{
//            val exitIntent = Intent(applicationContext,QuizActivity::class.java)
//            startActivity(exitIntent)
//        }
    }
//    private fun selectAnswer(answer : Boolean){
//        when(answer){
//            true -> {
//                binding.questionAnswerSelectedOIv.visibility = View.VISIBLE
//                binding.questionAnswerNotselectedOIv.visibility = View.GONE
//                binding.questionAnswerSelectedXIv.visibility = View.GONE
//                binding.questionAnswerNotselectedXIv.visibility = View.VISIBLE
//            }
//            false -> {
//                binding.questionAnswerNotselectedXIv.visibility = View.GONE
//                binding.questionAnswerSelectedXIv.visibility = View.VISIBLE
//                binding.questionAnswerSelectedOIv.visibility = View.GONE
//                binding.questionAnswerNotselectedOIv.visibility = View.VISIBLE
//            }
//        }
//    }
}