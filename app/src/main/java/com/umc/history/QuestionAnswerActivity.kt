package com.umc.history

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.umc.history.databinding.ActivityQuestionAnswerBinding

class QuestionAnswerActivity : AppCompatActivity() {
    lateinit var binding : ActivityQuestionAnswerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionAnswerBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        val intent = intent
        val testList = intent.getParcelableArrayListExtra<Test>("test")
        if(testList!!.isEmpty()) {
            binding.quetionAnswerNextBtn.text = "완료"
            binding.questionAnswerTv.text = "고생하셨습니다!\n다른 문제들도 풀어볼까요?"
        }
        val answer = intent.getBooleanExtra("answer", true)
        when(answer){
            false -> {
                binding.questionAnswerTv.text = "낙방하였습니다..\n한번 더 읽어보고 올까요?"
            }
            else -> {

            }
        }
        binding.questionSolutionTv.bringToFront()
        binding.questionSolutionTv.text = intent.getStringExtra("solution")
        binding.quetionAnswerNextBtn.setOnClickListener {
            if(testList!!.isEmpty()){
                val exitIntent = Intent(applicationContext, TestActivity::class.java)
                startActivity(exitIntent)
            } else{
                val nextIntent = Intent(applicationContext, QuestionActivity::class.java)
                nextIntent.putExtra("Test",testList)
                startActivity(nextIntent)
            }
        }
    }
}