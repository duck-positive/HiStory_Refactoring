package com.umc.history.ui.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.umc.history.R
import com.umc.history.databinding.FragmentQuizAnswerBinding

class QuizAnswerFragment : Fragment() {
    private var _binding : FragmentQuizAnswerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuizAnswerBinding.inflate(inflater, container, false)

        binding.quetionAnswerNextBtn.setOnClickListener {
            parentFragmentManager.beginTransaction()
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