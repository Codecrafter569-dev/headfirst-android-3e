package com.codecrafter.vikash.texteditor_vk

import android.os.Bundle

import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
      
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val helloText = findViewById<TextView>(R.id.helloText)
        val changeText = findViewById<Button>(R.id.changeText)
        val resetText = findViewById<Button>(R.id.resetText)
        changeText.setOnClickListener{
          helloText.text = getString(R.string.changed_text)
        }
        resetText.setOnClickListener{
          helloText.text = getString(R.string.hello_text)
        }
        
    }
    
    
}