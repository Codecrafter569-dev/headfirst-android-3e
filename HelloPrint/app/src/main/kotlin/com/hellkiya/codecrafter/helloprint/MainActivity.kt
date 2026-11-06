package com.hellkiya.codecrafter.helloprint

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import android.widget.EditText
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        val button = findViewById<Button>(R.id.button)
        val view = findViewById<TextView>(R.id.view)
        val editor = findViewById<EditText>(R.id.editor)
        
        button.setOnClickListener {
    val name = editor.text.toString().trim()
    if (name.isNotEmpty()) {
        view.text = "$name, World"
    }
}
        
    }
}