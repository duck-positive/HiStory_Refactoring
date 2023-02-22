package com.umc.history.ui.quiz

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.umc.history.HiStoryApplication
import com.umc.history.R
import com.umc.history.databinding.FragmentQuizAnswerBinding
import com.umc.history.ui.MainActivity
import com.umc.history.ui.viewmodel.QuizViewModel
import com.umc.history.ui.viewmodel.QuizViewModelFactory

class QuizAnswerFragment : Fragment() {
    private var _binding : FragmentQuizAnswerBinding? = null
    private val binding get() = _binding!!
    private val quizViewModel : QuizViewModel by activityViewModels {
        QuizViewModelFactory((requireContext().applicationContext as HiStoryApplication).quizRepository)
    }
    private var answer : Boolean? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuizAnswerBinding.inflate(inflater, container, false)

        parentFragmentManager.setFragmentResultListener("ANSWER", this) { _, bundle ->
            answer = bundle.getBoolean("bundleKey")
        }

        quizViewModel.quizList.observe(viewLifecycleOwner, Observer { quiz ->
            if(answer == quiz[0].answer) binding.questionAnswerTv.text = "정답입니다"
            else binding.questionAnswerTv.text = "오답입니다"
            quizViewModel.removeSolvedQuiz()
            Log.d("quiz_answer", "${quiz.toString()}")
        })

        binding.quetionAnswerNextBtn.setOnClickListener {
            if (quizViewModel.quizList.value!!.isEmpty()){
                startActivity(Intent(requireContext(), MainActivity::class.java))
            }
            else parentFragmentManager.beginTransaction()
                .replace(R.id.quiz_container, QuizFragment())
                .commitAllowingStateLoss()
        }


        return binding.root
    }


//    override fun onResume() {
//        super.onResume()
//        val intent = intent
//        val testList = intent.getParcelableArrayListExtra<Test>("test")
//        if(testList!!.isEmpty()) {
//            binding.quetionAnswerNextBtn.text = "완료"
//            binding.questionAnswerTv.text = "고생하셨습니다!\n다른 문제들도 풀어볼까요?"
//        }
//        val answer = intent.getBooleanExtra("answer", true)
//        when(answer){
//            false -> {
//                binding.questionAnswerTv.text = "낙방하였습니다..\n한번 더 읽어보고 올까요?"
//            }
//            else -> {
//
//            }
//        }
//        binding.questionSolutionTv.bringToFront()
//        binding.questionSolutionTv.text = intent.getStringExtra("solution")
//        binding.quetionAnswerNextBtn.setOnClickListener {
//            if(testList!!.isEmpty()){
//                val exitIntent = Intent(applicationContext, QuizActivity::class.java)
//                startActivity(exitIntent)
//            } else{
//                //val nextIntent = Intent(applicationContext, QuestionActivity::class.java)
//                //nextIntent.putExtra("Test",testList)
//                //startActivity(nextIntent)
//            }
//        }
//    }
}