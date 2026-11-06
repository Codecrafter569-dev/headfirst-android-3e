package com.hellkiya.vikashKuka.font_size_changer

import android.os.Bundle
import android.util.TypedValue
import android.widget.Button
import android.widget.TextView
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var sampleText: TextView
    private lateinit var sizeLabel: TextView
    private lateinit var btnIncrease: Button
    private lateinit var btnDecrease: Button
    private lateinit var setText: Button
    private lateinit var editText: EditText
    
    private var currentSize = DEFAULT_SIZE
    
    companion object {
      const val DEFAULT_SIZE = 20
      const val MIN_SIZE = 10
      const val MAX_SIZE = 90
      const val STEP = 2
      const val KEY_SIZE = "key_size"
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        sampleText = findViewById<TextView>(R.id.sampleText)
        sizeLabel = findViewById<TextView>(R.id.sizeLabel)
        btnIncrease = findViewById<Button>(R.id.btnIncrease)
        btnDecrease = findViewById<Button>(R.id.btnDecrease)
        setText   =  findViewById<Button>(R.id.setText)
        editText  =   findViewById<EditText>(R.id.editText)
        
        if(savedInstanceState != null){
          currentSize = savedInstanceState.getInt(KEY_SIZE,DEFAULT_SIZE)
        }
        btnIncrease.setOnClickListener{
          if(currentSize < MAX_SIZE){
            currentSize += STEP
            updateTextSize()
          }
          
        }
        
        btnDecrease.setOnClickListener{
          if(currentSize > MIN_SIZE){
            currentSize -= STEP
            updateTextSize()
          }
        }
        setText.setOnClickListener{
          sampleText.text  = editText.text.toString()
        }
        updateTextSize()
        
        
    }
    
    private fun updateTextSize(){
      sampleText.setTextSize(TypedValue.COMPLEX_UNIT_SP,currentSize.toFloat())
      sizeLabel.text = "Size : $currentSize sp"
      
      
      btnIncrease.isEnabled = currentSize < MAX_SIZE
      btnDecrease.isEnabled = currentSize > MIN_SIZE
    }
    
    override fun onSaveInstanceState(outState : Bundle){
      super.onSaveInstanceState(outState)
      outState.putInt(KEY_SIZE,DEFAULT_SIZE)
    }
}