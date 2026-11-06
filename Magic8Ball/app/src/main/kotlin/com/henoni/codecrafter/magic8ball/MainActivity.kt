package com.henoni.codecrafter.magic8ball
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {


    private lateinit var questionInput: EditText
    private lateinit var btnAsk: Button
    private lateinit var answerText: TextView
    
    private val answers = listOf(
           "Yes",
        "No",
        "Definitely",
        "Absolutely not",
        "Ask later",
        "Maybe",
        "Very likely",
        "Don't count on it",
        "Without a doubt",
        "Cannot predict now",
        "Signs point to yes",
        "My sources say no",
        "Concentrate and ask again",
        "It is certain",
        "Very doubtful"
    
    
    )
    private var lastIndex = -1
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        questionInput = findViewById(R.id.questionInput)
        btnAsk = findViewById(R.id.btnAsk)
        answerText = findViewById(R.id.answerText)
        
        
        btnAsk.setOnClickListener{
          val question = questionInput.text.toString().trim()
          if(question.isEmpty()){
            questionInput.error = "Ask a question first"
            return@setOnClickListener
          }
          hideKeyboard()
          showAnswer(pickAnswer())
        }
    }
    
    private fun pickAnswer(): String{
      var index: Int
      do{
        index = Random.nextInt(answers.size)
      }while(index == lastIndex)
      
      lastIndex = index
      return answers[index]
    }
    
    private fun showAnswer(answer: String){
      answerText.text = answer
      answerText.alpha = 0f
      answerText.animate()
      .alpha(1f)
      .setDuration(600)
      .start()
    }
    
    private fun hideKeyboard(){
      val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
      imm.hideSoftInputFromWindow(questionInput.windowToken,0)
    }
}