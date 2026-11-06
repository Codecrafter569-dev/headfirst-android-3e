package com.codecrafter.vikash.texteditor_c


import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val editor = findViewById<EditText>(R.id.editor)
        val clearButton = findViewById<Button>(R.id.clearButton)
        
        
        clearButton.setOnClickListener{
          editor.text.clear()
          
        }
    }
}