package com.umc.history

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.umc.history.databinding.ActivityQuestionBinding

class QuestionActivity: AppCompatActivity(){
    lateinit var binding: ActivityQuestionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    override fun onResume() {
        super.onResume()
        val intent  = intent
        var answer = true
        var select = true
        val testList = intent?.getParcelableArrayListExtra<Test>("Test")
        val answerIntent = Intent(this, QuestionAnswerActivity::class.java)
        if(testList!!.isNotEmpty()){
            binding.questionQuestionTv.text = "Q. ${testList[0].question}"
            answer = testList[0].answer
            answerIntent.putExtra("solution",testList[0].solution)
            testList.removeAt(0)
            answerIntent.putExtra("test",testList)
        }
        binding.questionAnswerNotselectedOIv.setOnClickListener {
            select = true
            selectAnswer(select)
        }
        binding.questionAnswerNotselectedXIv.setOnClickListener {
            select = false
            selectAnswer(select)
        }

        binding.questionNextIv.setOnClickListener {
            if(answer != select)
                answerIntent.putExtra("answer",false)
            else
                answerIntent.putExtra("answer", true)
            startActivity(answerIntent)
        }

        binding.questionExitLy.setOnClickListener{
            val exitIntent = Intent(applicationContext,TestActivity::class.java)
            startActivity(exitIntent)
        }
    }
    private fun selectAnswer(answer : Boolean){
        when(answer){
            true -> {
                binding.questionAnswerSelectedOIv.visibility = View.VISIBLE
                binding.questionAnswerNotselectedOIv.visibility = View.GONE
                binding.questionAnswerSelectedXIv.visibility = View.GONE
                binding.questionAnswerNotselectedXIv.visibility = View.VISIBLE
            }
            false -> {
                binding.questionAnswerNotselectedXIv.visibility = View.GONE
                binding.questionAnswerSelectedXIv.visibility = View.VISIBLE
                binding.questionAnswerSelectedOIv.visibility = View.GONE
                binding.questionAnswerNotselectedOIv.visibility = View.VISIBLE
            }
        }
    }
}