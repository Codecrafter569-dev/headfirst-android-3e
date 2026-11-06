package com.code.vikash.newlayout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.widget.Button
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        val e1 = findViewById<EditText>(R.id.editTextTextMultiLine)
        val e2 = findViewById<EditText>(R.id.editTextTextMultiLine2)
        val b1 = findViewById<Button>(R.id.button3)
        
        b1.setOnClickListener{
          e1.text.clear()
          e2.text.clear()
        }
    }
}